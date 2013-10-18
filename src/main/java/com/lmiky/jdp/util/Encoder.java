package com.lmiky.jdp.util;

import java.security.MessageDigest;

/**
 * 加密/编码
 * @author lmiky
 * @date 2013-4-24
 */
public class Encoder {
	
	/**
	 * MD5编码
	 * @author lmiky
	 * @date 2013-4-24
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String md5(String source) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.reset();
		md5.update(source.getBytes());
		byte[] hashBytes = md5.digest();
		String ret = "";
		for(int i=0; i<hashBytes.length; i++) {
			ret += toHexString(hashBytes[i]);
		}
		return ret;
	}
	
	/**
	 * 将字节转换为16进制字符
	 * @author lmiky
	 * @date 2013-4-24
	 * @param b
	 * @return
	 */
	private static String toHexString(byte b) {
        int intValue = (b>=0 ? b : 256+b);
        String hex = Integer.toHexString(intValue).toUpperCase();
        return hex.length()<2 ? ("0" + hex) : hex;
    }
	
	public static void main(String[] args) throws Exception {
		System.out.println(Encoder.md5("MHm4HWaJvSgaavHJ.1369274408.geturl.qQrytHbP"));
	}

}
