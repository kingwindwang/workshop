package com.time.workshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 * 
 * @author hjt
 * 
 */
public class MD5Util {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String convert32To16(String s) {
		return s.substring(8, 24);
	}

	/**
	 * MD5 加密 (16位)
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return 加密好的字符串
	 */
	public static String encode16(String s) {
		return convert32To16(encode(s));
	}

	/**
	 * MD5 加密
	 * 
	 * @param s
	 *            待加密的字符串
	 * @return 加密好的字符串
	 */
	public static String encode(String s) {
		try {
			return getMD5String(s.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "";
	}

	/**
	 * MD5 加密
	 * 
	 * @param bytes
	 *            待加密的字符数组
	 * @return 加密好的字符串
	 */
	private static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
}
