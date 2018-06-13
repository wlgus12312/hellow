package com.ubivelox.encrypt;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import test.com.ubivelox.gaia.GaiaException;

public class EncrytTest {

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, GaiaException, NoSuchProviderException {
	
		assertEquals("41424344", Encryt.encryt("41424344"));
		
		
		assertEquals("41424344", ResEncryt.resEncryt("41424344"));
		
	
	}

}
