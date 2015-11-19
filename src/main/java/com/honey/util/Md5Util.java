package com.honey.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	
	public static String md5(String value){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.reset();
		try {
			md.update(value.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		byte[] digest = md.digest();

		StringBuffer hexValue = new StringBuffer();
		
		for (int i = 0; i < digest.length; i++) {
			int val = ((int) digest[i]) & 0xff;
			if (val < 16) hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}
	
}
