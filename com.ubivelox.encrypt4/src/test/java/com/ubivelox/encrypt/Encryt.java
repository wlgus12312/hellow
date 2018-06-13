package com.ubivelox.encrypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import com.ubivelox.gaia.util.GaiaUtils;

import test.com.ubivelox.gaia.GaiaException;

public class Encryt {
	
	private static Cipher CIPHER;
	private static byte[] KSP = {
									0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47
									, 0x48, 0x49, 0x4A, 0x4B, 0x4C, 0x4D, 0x4E, 0x4F
									, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47};
	private static DESedeKeySpec DESKEYSPEC;
	private static SecretKeyFactory KEYFACTORY;
	private static Key KEY;
	

	public static String encryt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, GaiaException, IllegalBlockSizeException, BadPaddingException {
		SetEnc();
		
		byte[] encrytByteArray = null;
        byte[] dencrytByteArray = null;
        String result = "";
        String hexaEnc = "";

        byte[] byteText = GaiaUtils.convertHexaStringToByteArray(text);

        encrytByteArray = encryptsByteOne(byteText);
        
        hexaEnc = GaiaUtils.convertByteArrayToHexaString(encrytByteArray);
        
        dencrytByteArray = descryptionOne(encrytByteArray);
        
        result = GaiaUtils.convertByteArrayToHexaString(dencrytByteArray);
        
        return result;
	}
	
	private static byte[] descryptionOne(final byte[] encrytByteArray) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {

        CIPHER.init(Cipher.DECRYPT_MODE, KEY);

        return CIPHER.doFinal(encrytByteArray);
    }
	
	private static byte[] encryptsByteOne(final byte[] encrytByteArray) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        CIPHER.init(Cipher.ENCRYPT_MODE, KEY);

        return CIPHER.doFinal(encrytByteArray);
    }
	


	private static void SetEnc() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
		
		CIPHER = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		DESKEYSPEC = new DESedeKeySpec(KSP);
		KEYFACTORY = SecretKeyFactory.getInstance("DESede");
		KEY = KEYFACTORY.generateSecret(DESKEYSPEC);
		
	}

}
