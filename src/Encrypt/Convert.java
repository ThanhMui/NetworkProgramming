/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encrypt;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author ASUS
 */
public class Convert {
    
    public static SecretKey convertStringToSecretKeyto(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }

    public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
        byte[] rawData = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(rawData);
        return encodedKey;
    }

    public static String convertPublicKeyToString(PublicKey publicKey) {
        String encodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return encodedKey;
    }

    public static PublicKey convertStringToPublicKey(String encodedKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] publicBytes = Base64.getDecoder().decode(encodedKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //254514307525033576001193285232372949893071337925033456554037720857868219098745415141650072872931516145981910781716044057959270276351154879532536
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        return pubKey;
    }

        public static String convertPrivateKeyToString(PrivateKey privateKey) {
            String encodedKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            return encodedKey;
        }

    public static PrivateKey convertStringToPrivateKey(String encodedKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        byte[] encodedPv = Base64.getDecoder().decode(encodedKey);
        PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
        PrivateKey privateKey = kf.generatePrivate(keySpecPv);
        return privateKey;
        
        
    }
    
}
