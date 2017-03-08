package org.vitaly.homework01.rsa;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;
import static org.vitaly.homework01.rsa.RSACryptographer.*;

public class RSACryptographerTest {
    private final BigInteger FIRST_PRIME = new BigInteger("13665292845261266843");
    private final BigInteger SECOND_PRIME = new BigInteger("12454972287342439433");
    private final BigInteger EXPECTED_EULER_FUNCTION = new BigInteger("170200843686147992911666939762974913744");
    private final Pair PRIMES = new Pair(FIRST_PRIME, SECOND_PRIME);
    private KeyPair keyPair = generateKeyPair(PRIMES);

    @Test
    public void testGetPrimesPair() throws Exception {
        Pair primes = getPrimesPair();

        assertTrue(primes.getFirst().isProbablePrime(CERTAINTY));
        assertTrue(primes.getSecond().isProbablePrime(CERTAINTY));
    }

    @Test
    public void testEulerFunction() throws Exception {
        assertEquals(EXPECTED_EULER_FUNCTION, getEulerFunction(PRIMES));

        BigInteger modulus = FIRST_PRIME.multiply(SECOND_PRIME);
        assertTrue(EXPECTED_EULER_FUNCTION.compareTo(modulus) < 0);
    }

    @Test
    public void testPublicExponent() throws Exception {
        BigInteger publicExponent = getPublicExponent(EXPECTED_EULER_FUNCTION);

        assertTrue(publicExponent.compareTo(BigInteger.ONE) > 0);
        assertTrue(publicExponent.compareTo(EXPECTED_EULER_FUNCTION) < 0);
        assertTrue(publicExponent.gcd(EXPECTED_EULER_FUNCTION).equals(BigInteger.ONE));
    }

    @Test
    public void testKeyPairGenerationWithGivenPrimes() throws Exception {
        BigInteger eulerFunction = getEulerFunction(PRIMES);

        BigInteger publicExponent = keyPair.getPublicKey().getExponent();
        BigInteger privateExponent = keyPair.getPrivateKey().getExponent();

        assertTrue(publicExponent.multiply(privateExponent).mod(eulerFunction).equals(BigInteger.ONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonPrimesForKeyGeneration() throws Exception {
        generateKeyPair(new Pair(BigInteger.valueOf(14), BigInteger.valueOf(41)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroForKeyGeneration() throws Exception {
        generateKeyPair(new Pair(BigInteger.ZERO, BigInteger.valueOf(41)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneForKeyGeneration() throws Exception {
        generateKeyPair(new Pair(BigInteger.ONE, BigInteger.valueOf(41)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneNegativeForKeyGeneration() throws Exception {
        generateKeyPair(new Pair(BigInteger.valueOf(-11), BigInteger.valueOf(13)));
    }

    @Test
    public void testCipherDecipherNumber() throws Exception {
        BigInteger information = new BigInteger("12345678987654321");

        assertEquals(doDecipherAsBigInteger(
                doCipher(information, keyPair.getPublicKey()), keyPair.getPrivateKey()), information);
    }

    @Test
    public void testCipherDecipherByteArray() throws Exception {
        byte[] information = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertArrayEquals(doDecipherAsByteArray(
                doCipher(information, keyPair.getPublicKey()), keyPair.getPrivateKey()), information);
    }

    @Test
    public void testCipherDecipherByteArrays() throws Exception {
        byte[][] initialInformation = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        BigInteger[] cipheredInformation = doCipher(initialInformation, keyPair.getPublicKey());
        byte[][] decipheredInformation = doDecipherAsByteArrays(cipheredInformation, keyPair.getPrivateKey());

        for (int i = 0; i < initialInformation.length; i++) {
            assertArrayEquals(initialInformation[i], decipheredInformation[i]);
        }
    }

    @Test
    public void testCipherDecipherString() throws Exception {
        String information = "Well beyond your window " +
                "There is so much more ";
        BigInteger[] cipheredInformation = doCipher(information, keyPair.getPublicKey(), 4);

        assertEquals(information, doDecipherAsString(cipheredInformation, keyPair.getPrivateKey()));
    }

    @Test
    public void testCipherDecipherStringWithBigChunkSize() throws Exception {
        String information = "Even every prison " +
                "Has an open door";
        BigInteger[] cipheredInformation = doCipher(information, keyPair.getPublicKey(), information.length() / 2 - 1);

        assertEquals(information, doDecipherAsString(cipheredInformation, keyPair.getPrivateKey()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCipherDecipherStringWithZeroChunkSize() throws Exception {
        String information = "Veronika, Saint Veronika";
        doCipher(information, keyPair.getPublicKey(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCipherDecipherStringWithGiantChunkSize() throws Exception {
        String information = "Veronika, Saint Veronika";
        doCipher(information, keyPair.getPublicKey(), RSACryptographer.BIT_LENGTH + 1);
    }
}