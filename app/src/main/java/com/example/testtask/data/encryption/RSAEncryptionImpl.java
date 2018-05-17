package com.example.testtask.data.encryption;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryptionImpl implements RSAEncryption {

    private static final String CRYPTO_METHOD = "RSA";
    private static final int CRYPTO_BITS = 2048;
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";

    @Override
    public KeyPair generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(CRYPTO_METHOD);
        keyPairGenerator.initialize(CRYPTO_BITS);
        return keyPairGenerator.genKeyPair();
    }

    @Override
    public String encrypt(String encrypt, String publicKey) throws BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException {

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKeyByString(publicKey));
        byte[] encryptedBytes = cipher.doFinal(encrypt.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    @Override
    public String decrypt(String decrypt, String privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKeyByString(privateKey));
        byte[] decryptedBytes = cipher.doFinal(Base64.decode(decrypt, Base64.DEFAULT));

        return new String(decryptedBytes);
    }

    @Override
    public String getPrivateKey(PrivateKey privateKey) {
        return Base64.encodeToString(privateKey.getEncoded(), Base64.DEFAULT);
    }

    @Override
    public String getPublicKey(PublicKey publicKey) {
        return Base64.encodeToString(publicKey.getEncoded(), Base64.DEFAULT);
    }

    private PrivateKey getPrivateKeyByString(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(CRYPTO_METHOD);
        byte[] decodeBytes = Base64.decode(privateKey, Base64.DEFAULT);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodeBytes);

        return keyFactory.generatePrivate(privateKeySpec);
    }

    private PublicKey getPublicKeyByString(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(CRYPTO_METHOD);
        byte[] decodeBytes = Base64.decode(publicKey, Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodeBytes);

        return keyFactory.generatePublic(keySpec);
    }
}
