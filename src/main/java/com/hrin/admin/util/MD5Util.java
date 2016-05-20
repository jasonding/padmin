/**
 * 
 */
package com.hrin.admin.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dingjs
 */
public class MD5Util {
	
	public static String encode(String str) {
		return encode(str, 1);
	}
	
	public static String encode(String str,Integer encodeCount) {
		try {
			if(encodeCount >= 1) {
				MessageDigest md =MessageDigest.getInstance("MD5");
				byte[] digestByte = md.digest(str.getBytes());
				BASE64Encoder base64 = new BASE64Encoder();
				encodeCount = encodeCount - 1;
				str = encode(base64.encode(digestByte), encodeCount);
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return str;
	}

}
