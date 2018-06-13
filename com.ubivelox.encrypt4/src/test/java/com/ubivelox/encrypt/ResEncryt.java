package com.ubivelox.encrypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ubivelox.gaia.util.GaiaUtils;

import test.com.ubivelox.gaia.GaiaException;

public class ResEncryt {
	
	private static Cipher CIPHER;
	private static KeyPair KEYPAIR;
	private static Key PUBLICKEY;
	private static Key PRIKEY;
	private static byte[] KSP = {
			0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47
			, 0x48, 0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F
			, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47};
	
	public static String resEncryt(final String text) throws GaiaException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		String result = "";	
		
		byte[] encrytByteArray = GaiaUtils.convertHexaStringToByteArray(text);
		byte[] dencrytByteArray = null;
		
		encrytByteArray = encrytRes(encrytByteArray);
		result = GaiaUtils.convertByteArrayToHexaString(encrytByteArray);
		
		System.out.println("암호화 = " + result);
		
		dencrytByteArray = decrytRes(encrytByteArray);
		result = GaiaUtils.convertByteArrayToHexaString(dencrytByteArray);
		
		System.out.println("복호화 = "+result);
		
		return result;
	}

	private static byte[] decrytRes(byte[] encrytByteArray) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		CIPHER.init(Cipher.DECRYPT_MODE, PRIKEY);
		
		return CIPHER.doFinal(encrytByteArray);
	}

	private static byte[] encrytRes(byte[] encrytByteArray) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		//CIPHER.getInstance("RSA/None/NoPadding");
		CIPHER = Cipher.getInstance("RSA/ECB/NoPadding");
		//SecureRandom random = new SecureRandom();
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(512);
		KEYPAIR = generator.generateKeyPair();
		PUBLICKEY = KEYPAIR.getPublic();
		PRIKEY = KEYPAIR.getPrivate();
		
		CIPHER.init(Cipher.ENCRYPT_MODE, PUBLICKEY);
		
		return CIPHER.doFinal(encrytByteArray);
	}
	
	
	
	
}
