package com.robust.robustutilslib.util;

import javax.crypto.SecretKey;

/**
 * Created by chenhewen on 2017/10/15.
 */

public class Cryptor {

    SecretKey key;

    public SecretKey deriveKey(String password, byte[] salt) {
        return Crypto.deriveKeyPad(password);
    }

    public String encrypt(String plaintext, String password) {
        key = deriveKey(password, null);
        return Crypto.encrypt(plaintext, key, null);
    }

    public String decrypt(String ciphertext, String password) {
        SecretKey key = deriveKey(password, null);

        return Crypto.decryptNoSalt(ciphertext, key);
    }

    public String getRawKey() {
        if (key == null) {
            return null;
        }

        return Crypto.toHex(key.getEncoded());
    }
}
