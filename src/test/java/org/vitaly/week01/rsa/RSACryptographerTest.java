package org.vitaly.week01.rsa;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;
import static org.vitaly.week01.rsa.RSACryptographer.*;

public class RSACryptographerTest {
    private final BigInteger FIRST_PRIME = new BigInteger(
            "139353002759250767128760847340591964619700789910313139616204653696735957730454772417668221835180864698" +
                    "7991123365587572755938042844749530833833559020428338857936899016690436658749986686619378660286" +
                    "1156170472358283398091512089189142241721979703885705393405393281597923819511951113853955634732" +
                    "1964614536619384389");
    private final BigInteger SECOND_PRIME = new BigInteger(
            "1348933785989343165539482220978725415583672654497" +
                    "8589709806089037182055218317075899302835186773658172875758036114315610119468456187940043549091" +
                    "4228326976483801900163093407525531661797611842682818325058305797160401145080806519362953322187" +
                    "382144173259793109672184705801623015870343419192307869515678544124558603");
    private final BigInteger EXPECTED_EULER_FUNCTION = new BigInteger(
            "187977973601019521944422306571413177136769476303919143595318156987982231894654077959006184540904969167" +
                    "5910888007590002073005997503205238774841377006819448536187010228161122668516002359675839953044" +
                    "1862918550008901823833044357270992952023150995871079746852711007625039228293176043708324271312" +
                    "2902338389851599022540033247803071090443381918158805034612760976111462458673606148975239220275" +
                    "5358842581994784358147366592939610401911691501270126533008100017341186152071272446003409515291" +
                    "3876771076451409472081838382018585654191838527822401464839787636366849787043472321206657654655" +
                    "232011377735310814163624275530359397469905576");
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
        BigInteger[] cipheredInformation = doCipher(information, keyPair.getPublicKey(), 1024);

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
        doCipher(information, keyPair.getPublicKey(), 1025);
    }
}