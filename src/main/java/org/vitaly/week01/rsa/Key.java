package org.vitaly.week01.rsa;

import java.math.BigInteger;

/**
 * Key class object stores exponent and modulus of private or public key
 *
 * @author vitaly
 */
public class Key {
    private final BigInteger exponent;
    private final BigInteger modulus;

    /**
     * Create object of Key class using supplied exponent and modulus
     *
     * @param exponent exponent for key
     * @param modulus  modulus for key
     */
    public Key(BigInteger exponent, BigInteger modulus) {
        this.exponent = exponent;
        this.modulus = modulus;
    }

    /**
     * Returns exponent of key stored in object
     *
     * @return exponent of key
     */
    public BigInteger getExponent() {
        return exponent;
    }

    /**
     * Returns modulus of key stored in object
     *
     * @return modulus of key
     */
    public BigInteger getModulus() {
        return modulus;
    }
}
