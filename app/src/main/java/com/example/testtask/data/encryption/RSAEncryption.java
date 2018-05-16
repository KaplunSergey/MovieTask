package com.example.testtask.data.encryption;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface RSAEncryption {

    KeyPair generateKeys() throws NoSuchAlgorithmException;

    String encrypt(String encrypt, String publicKey) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException;

    String decrypt(String decrypt, String privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    String getPrivateKey(PrivateKey privateKey);

    String getPublicKey(PublicKey publicKey);
}
