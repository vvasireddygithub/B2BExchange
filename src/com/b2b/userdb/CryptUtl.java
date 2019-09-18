package com.b2b.userdb;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptUtl {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	protected static final Logger log = LoggerFactory.getLogger(CryptUtl.class.getName());

	public static void setKey(String myKey) {
		// MessageDigest sha256=null;
		try {
			key = myKey.getBytes("UTF-8");
			key = Arrays.copyOf(key, 32);
			secretKey = new SecretKeySpec(key, "AES");
			//System.out.println("Secret key " + secretKey);
		} catch (UnsupportedEncodingException ex1) {
			log.error(" *** setKey *** ", ex1);
		}

	}

	public static String encrypt(String strtoEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strtoEncrypt.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | IllegalBlockSizeException
				| BadPaddingException | NoSuchPaddingException | InvalidKeyException ex1) {
			log.error(" *** encrypt *** ", ex1);

		}
		return null;
	}

	public static String decrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | KeyException | IllegalBlockSizeException
				| BadPaddingException ex) {
			log.error(" *** decrypt *** ", ex);
		}
		return null;

	}

	public static void main(String[] args) {
		final String secretKey = "helo";
	     
	    String originalString = "venu";
	    String encryptedString = encrypt(originalString, secretKey) ;
	    String decryptedString = decrypt(encryptedString, secretKey) ;
	     
	    System.out.println(originalString);
	    System.out.println(encryptedString);
	    System.out.println(decryptedString);

	}

}
