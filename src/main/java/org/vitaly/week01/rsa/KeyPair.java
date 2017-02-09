package org.vitaly.week01.rsa;

/**
 * KeyPair class object contains public and private key for cryptosystem
 *
 * @author vitaly
 */
public class KeyPair {
    private final Key publicKey;
    private final Key privateKey;

    /**
     * Create object of KeyPair class using supplied public and private keys stored in Key objects
     *
     * @param publicKey  public key
     * @param privateKey private key
     */
    public KeyPair(Key publicKey, Key privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Returns public key
     *
     * @return public key
     */
    public Key getPublicKey() {
        return publicKey;
    }

    /**
     * Returns private key
     *
     * @return private key
     */
    public Key getPrivateKey() {
        return privateKey;
    }
}
