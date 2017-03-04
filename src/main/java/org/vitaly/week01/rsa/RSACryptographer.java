package org.vitaly.week01.rsa;

import java.math.BigInteger;
import java.util.Random;

/**
 * RSACryptographer class implements RSA cryptosystem. RSA is public-key cryptosystems.
 * In such a cryptosystem, the encryption key is public and differs from the decryption
 * key which is kept secret. In RSA, this asymmetry is based on the practical difficulty
 * of factoring the product of two large prime numbers, the factoring problem.
 *
 * @author vitaly
 */
public class RSACryptographer {

    /**
     * Size for generation of keys using RSA algorithms
     */
    public static final int BIT_LENGTH = 128;

    /**
     * A measure of the uncertainty that ensures that random numbers for key generation will be
     * primes with probability (1 - 0.5^CERTAINTY).
     */
    public static final int CERTAINTY = 50;

    private RSACryptographer() {
    }

    /**
     * Generate pair of keys using two given primes
     *
     * @param primes pair of primes for generating keys
     * @return KeyPair object containing generated public and private keys
     * @throws IllegalArgumentException if supplied pair of primes contains not a prime or negative primes
     */
    public static KeyPair generateKeyPair(Pair primes) {
        if (!primes.getFirst().isProbablePrime(CERTAINTY) || !primes.getSecond().isProbablePrime(CERTAINTY)) {
            throw new IllegalArgumentException("At least one of supplied numbers is not a prime number!");
        }
        if (primes.getFirst().signum() < 0 || primes.getSecond().signum() < 0) {
            throw new IllegalArgumentException("At least one of supplied numbers is negative number!");
        }

        BigInteger modulus = primes.getFirst().multiply(primes.getSecond());

        BigInteger eulerFunction = getEulerFunction(primes);

        BigInteger publicExponent = getPublicExponent(eulerFunction);

        BigInteger privateExponent = publicExponent.modInverse(eulerFunction);

        Key publicKey = new Key(publicExponent, modulus);
        Key privateKey = new Key(privateExponent, modulus);

        return new KeyPair(publicKey, privateKey);

    }

    /**
     * Generate random pair of keys
     *
     * @return KeyPair object containing generated public and private keys
     */
    public static KeyPair generateKeyPair() {
        Pair primes = getPrimesPair();

        return generateKeyPair(primes);
    }

    /**
     * Generate two primes
     *
     * @return Pair object that contains two primes with length of BIT_LENGTH
     */
    public static Pair getPrimesPair() {
        Random random = new Random();

        BigInteger firstPrime;
        BigInteger secondPrime;

        do {
            firstPrime = new BigInteger(BIT_LENGTH, CERTAINTY, random);
            secondPrime = new BigInteger(BIT_LENGTH, CERTAINTY, random);
        } while (firstPrime.equals(secondPrime));

        return new Pair(firstPrime, secondPrime);
    }

    /**
     * Calculates Euler function from given pair of primes
     *
     * @param primes Pair of primes numbers
     * @return Euler function
     */
    public static BigInteger getEulerFunction(Pair primes) {
        BigInteger firstMultiplicand = primes.getFirst().subtract(BigInteger.ONE);
        BigInteger secondMultiplicand = primes.getSecond().subtract(BigInteger.ONE);

        return firstMultiplicand.multiply(secondMultiplicand);
    }

    /**
     * Generate public exponent with given Euler function
     *
     * @param eulerFunction Euler function
     * @return public exponent for public key
     */
    public static BigInteger getPublicExponent(BigInteger eulerFunction) {
        Random random = new Random();
        BigInteger publicExponent = new BigInteger(BIT_LENGTH / 2, CERTAINTY, random);

        while (!publicExponent.isProbablePrime(CERTAINTY)
                && !eulerFunction.gcd(publicExponent).equals(BigInteger.ONE)) {
            publicExponent = publicExponent.nextProbablePrime();
        }

        return publicExponent;
    }

    /**
     * Ciphers String object using supplied public key. String is divided in chunks of given size.
     *
     * @param information String to be ciphered
     * @param publicKey   public key to be used for ciphering
     * @param chunkSize   size of chunks in which information will be divided
     * @return BigInteger object containing ciphered value
     * @throws IllegalArgumentException chunk size for string division is less than 0 or greater than bit length of keys
     */
    public static BigInteger[] doCipher(String information, Key publicKey, int chunkSize) {
        if (chunkSize < 1 || chunkSize > BIT_LENGTH || chunkSize >= information.length() / 2) {
            throw new IllegalArgumentException("Chunk size is less than one!");
        }

        String[] chunks = information.split("(?<=\\G.{" + chunkSize + "})");

        byte[][] byteChunks = new byte[chunks.length][];

        for (int i = 0; i < chunks.length; i++) {
            byteChunks[i] = chunks[i].getBytes();
        }

        return doCipher(byteChunks, publicKey);
    }

    /**
     * Ciphers array of byte arrays object using supplied public key.
     *
     * @param information array of byte arrays to be ciphered
     * @param publicKey   public key to be used for ciphering
     * @return BigInteger object containing ciphered value
     */
    public static BigInteger[] doCipher(byte[][] information, Key publicKey) {
        BigInteger[] result = new BigInteger[information.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = doCipher(information[i], publicKey);
        }

        return result;
    }

    /**
     * Ciphers byte array object using supplied public key.
     *
     * @param information byte array to be ciphered
     * @param publicKey   public key to be used for ciphering
     * @return BigInteger object containing ciphered value
     */
    public static BigInteger doCipher(byte[] information, Key publicKey) {
        BigInteger info = new BigInteger(information);

        return doCipher(info, publicKey);
    }

    /**
     * Ciphers number object using supplied public key.
     *
     * @param information number to be ciphered
     * @param publicKey   public key to be used for ciphering
     * @return BigInteger object containing ciphered value
     */
    public static BigInteger doCipher(BigInteger information, Key publicKey) {
        return information.modPow(publicKey.getExponent(), publicKey.getModulus());
    }

    /**
     * Decipher ciphered information as number using private key.
     *
     * @param cipheredInformation number containing ciphered information
     * @param privateKey          private key for deciphering information
     * @return deciphered String object
     */
    public static String doDecipherAsString(BigInteger[] cipheredInformation, Key privateKey) {
        byte[][] decipheredBytes = doDecipherAsByteArrays(cipheredInformation, privateKey);

        StringBuilder result = new StringBuilder();
        for (byte[] decipheredChunk : decipheredBytes) {
            result.append(new String(decipheredChunk));
        }

        return result.toString();
    }

    /**
     * Decipher ciphered information as array of byte arrays using private key.
     *
     * @param cipheredInformation array of byte arrays containing ciphered information
     * @param privateKey          private key for deciphering information
     * @return deciphered array of byte arrays
     */
    public static byte[][] doDecipherAsByteArrays(BigInteger[] cipheredInformation, Key privateKey) {
        byte[][] result = new byte[cipheredInformation.length][];

        for (int i = 0; i < result.length; i++) {
            result[i] = doDecipherAsByteArray(cipheredInformation[i], privateKey);
        }

        return result;
    }

    /**
     * Decipher ciphered information as byte array using private key.
     *
     * @param cipheredInformation byte array containing ciphered information
     * @param privateKey          private key for deciphering information
     * @return deciphered byte array
     */
    public static byte[] doDecipherAsByteArray(BigInteger cipheredInformation, Key privateKey) {
        return doDecipherAsBigInteger(cipheredInformation, privateKey).toByteArray();
    }

    /**
     * Decipher ciphered information as number using private key.
     *
     * @param cipheredInformation byte array containing ciphered information
     * @param privateKey          private key for deciphering information
     * @return deciphered number
     */
    public static BigInteger doDecipherAsBigInteger(BigInteger cipheredInformation, Key privateKey) {
        return cipheredInformation.modPow(privateKey.getExponent(), privateKey.getModulus());
    }
}
