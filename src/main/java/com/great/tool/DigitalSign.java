package com.great.tool;

import java.security.MessageDigest;

/**
 * ����ǩ��
 * 
 * @author maste
 * @Date 2018-2-27
 */
public class DigitalSign {

	private DigitalSign() {
		super();
	}

	/**
	 * ���MD5
	 * 
	 * @param message
	 * @return
	 */
	public static String getMD5(String message) {
		return MD(message, "MD5");
	}

	/**
	 * ���SHA-1
	 * 
	 * @param message
	 * @return
	 */
	public static String getSHA_1(String message) {
		return MD(message, "SHA-1");
	}

	/**
	 * ���ָ�����㷨����У����
	 * @param message
	 * @param algorithm
	 * @return
	 */
	private static String MD(String message, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] bytes = md.digest(message.getBytes("utf-8"));
			return byte2Hex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ������תʮ�����
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2Hex(byte[] bytes) {
		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return sb.toString();
	}
}
