/**  
 * @Title: CashierSecurityUtils.java
 * @Package com.mls.pay.utils
 * @Description: TODO
 * @author liangliangmeng  
 * @date 2014年12月19日 下午4:33:39
 * @version V1.0  
 */
package com.mls.pay.access.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.mls.pay.utils.encrypt.DESede;
import com.mls.pay.utils.encrypt.Digest;
import com.mls.pay.utils.encrypt.pki.RSA;
import com.mls.pay.utils.security.rsa.Base64;

/**
 * @ClassName: CashierSecurityUtils
 * @Description: TODO
 * @author liangliangmeng
 * @date 2014年12月19日 下午4:33:39
 *
 */
public class CashierSecurityUtils {

	private final static Logger logger = LoggerFactory
			.getLogger(CashierSecurityUtils.class);

	public static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChDzcjw/rWgFwnxunbKp7/4e8w/UmXx2jk6qEEn69t6N2R1i/LmcyDT1xr/T2AHGOiXNQ5V8W4iCaaeNawi7aJaRhtVx1uOH/2U378fscEESEG8XDqll0GCfB1/TjKI2aitVSzXOtRs8kYgGU78f7VmDNgXIlk3gdhnzh+uoEQywIDAQAB";

	public static final String DEFAULT_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKEPNyPD+taAXCfG"
			+ "\r"
			+ "6dsqnv/h7zD9SZfHaOTqoQSfr23o3ZHWL8uZzINPXGv9PYAcY6Jc1DlXxbiIJpp4"
			+ "\r"
			+ "1rCLtolpGG1XHW44f/ZTfvx+xwQRIQbxcOqWXQYJ8HX9OMojZqK1VLNc61GzyRiA"
			+ "\r"
			+ "ZTvx/tWYM2BciWTeB2GfOH66gRDLAgMBAAECgYBp4qTvoJKynuT3SbDJY/XwaEtm"
			+ "\r"
			+ "u768SF9P0GlXrtwYuDWjAVue0VhBI9WxMWZTaVafkcP8hxX4QZqPh84td0zjcq3j"
			+ "\r"
			+ "DLOegAFJkIorGzq5FyK7ydBoU1TLjFV459c8dTZMTu+LgsOTD11/V/Jr4NJxIudo"
			+ "\r"
			+ "MBQ3c4cHmOoYv4uzkQJBANR+7Fc3e6oZgqTOesqPSPqljbsdF9E4x4eDFuOecCkJ"
			+ "\r"
			+ "DvVLOOoAzvtHfAiUp+H3fk4hXRpALiNBEHiIdhIuX2UCQQDCCHiPHFd4gC58yyCM"
			+ "\r"
			+ "6Leqkmoa+6YpfRb3oxykLBXcWx7DtbX+ayKy5OQmnkEG+MW8XB8wAdiUl0/tb6cQ"
			+ "\r"
			+ "FaRvAkBhvP94Hk0DMDinFVHlWYJ3xy4pongSA8vCyMj+aSGtvjzjFnZXK4gIjBjA"
			+ "\r"
			+ "2Z9ekDfIOBBawqp2DLdGuX2VXz8BAkByMuIh+KBSv76cnEDwLhfLQJlKgEnvqTvX"
			+ "\r"
			+ "TB0TUw8avlaBAXW34/5sI+NUB1hmbgyTK/T/IFcEPXpBWLGO+e3pAkAGWLpnH0Zh"
			+ "\r"
			+ "Fae7oAqkMAd3xCNY6ec180tAe57hZ6kS+SYLKwb4gGzYaCxc22vMtYksXHtUeamo"
			+ "\r" + "1NMLzI2ZfUoX" + "\r";

//	public static String sha1(String content) {
//		String resultString = new String(Base64.encodeBase64(Digest
//				.shaDigest(content)));
//		return resultString;
//	}

	public static boolean verifyCashierSignature(String origMsg,
			String keyCipher, String signature) {
		logger.info("收银台验证签名");

		String localSignature = signatureCashierData(origMsg, keyCipher)
				.substring(0, 16);

		logger.info("收银台 resign = " + localSignature);

		if (signature.equalsIgnoreCase(localSignature)) {
			return true;
		}

		return false;

	}

	public static boolean verifyCashierSignatureByOrigkey(String origMsg,
			String origkey, String signature) {
		logger.info("收银台验证签名请求参数：origMsg=" + origMsg + "|origKey=" + origkey
				+ "|sinature=" + signature);

		String localSignature = signatureCashierDataByOrigkey(origMsg, origkey);
		logger.info("收银台验证签名生成localSignature＝" + localSignature);
		if (signature.equalsIgnoreCase(localSignature)) {
			return true;
		}
		return false;
	}

	public static String signatureCashierData(String origMsg, String keyCipher) {
		logger.info("收银台签名");

		try {
			String origkey = getKeyCipher(keyCipher);

			String content = origMsg + origkey;

			logger.info("content = " + content);

			return Digest.shaDigestToBase64(content).substring(0, 16);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			logger.error("解密失败＝[{}]", e);

			return "";
		}

	}

	public static String signatureCashierDataByOrigkey(String origMsg,
			String origkey) {
		logger.info("收银台签名");

		try {
			String content = origMsg + origkey;

			logger.info("content = " + content);

			return Digest.shaDigestToBase64(content);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			logger.error("解密失败＝[{}]", e);

			return "";
		}

	}

	public static String decryptCashierData(String origkey, String cipherText) {

		logger.info("解密敏感信息得到原文");

		try {

			return DESede.decryptFromBase64(cipherText, origkey);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			logger.error("解密失败＝[{}]", e);

			return null;
		}

	}

//	public static String decryptPayPwd(String origkey, String password) {
//		logger.info("敏感信息sha1并base64编码");
//
//		String plaintext = decryptCashierData(origkey, password);
//
//		logger.info("plaintext:" + plaintext);
//
//		return new String(Base64.encodeBase64(Digest.shaDigest(plaintext)));
//
//	}

	/**
	 * 获取key明文
	 * 
	 * @param keyCipher
	 *            key密文
	 * @return
	 * @throws Exception
	 */
	public static String getKeyCipher(String keyCipher) {
		RSA rsa = new RSA();
		String key = "";

		try {
			rsa.loadPrivateKey(loadPrivateKeyString());

			key = new String(rsa.decrypt(rsa.getPrivateKey(),
					Base64.decodeBase64(keyCipher)));

			logger.info("key明文 : " + key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return key;
	}

	private static String loadPrivateKeyString() {
		return DEFAULT_PRIVATE_KEY;
	}

	public static String linkParam(Map<String, String> mapParam,
			String... paramNames) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<String> keys = Arrays.asList(paramNames);
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = mapParam.get(key);
			if (null != value && !"".equals(value)) {
				if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
					sb.append(key + "=" + value);
				} else {
					sb.append(key + "=" + value + "&");
				}
			}
		}
		return sb.toString();
	}

	public static String getKeyCipher() throws Exception {

		RSA rsa = new RSA();

		rsa.loadPublicKey(DEFAULT_PUBLIC_KEY);

		String des3Key = "12345678qwertyui12345678";

		String keyCipher = rsa.encryptBase64(rsa.getPublicKey(),
				org.apache.commons.codec.binary.Base64
						.encodeBase64String(des3Key.getBytes()));

		return keyCipher;

	}

	public static void main(String[] args) {
		String key = null;
		try {
			key = getKeyCipher();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String workKey = "12345678qwertyui12345678";
		System.out.println("key == " + key + "||workKey = " + workKey);
		String identityno = "123456789987654321";
		String cardno = "123456789123456789";
		String mobile = "1888888888";
		String enCardNo = "";
		String enMobile = "";
		String enIdentityCode = "";
		try {
			enIdentityCode = DESede.encryptToBase64(identityno, workKey);
			enCardNo = DESede.encryptToBase64(cardno, workKey);
			enMobile = DESede.encryptToBase64(mobile, workKey);
			System.out.println("identityno = " + identityno + "||cardno = "
					+ cardno + "||mobile=" + mobile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("解密paramMap中的敏感数据begin [workKey = " + workKey + "]");
		String deCardNo = DESede.decryptFromBase64(enCardNo, workKey);
		String deMobile = DESede.decryptFromBase64(enMobile, workKey);
		String deIdentityCode = DESede.decryptFromBase64(enIdentityCode,
				workKey);
		System.out.println("解密paramMap中的敏感数据----原始数据 [enCardNo = " + enCardNo
				+ "]" + " [enMobile = " + enMobile + "]"
				+ " [enIdentityCode = " + enIdentityCode + "]");
		System.out.println("解密paramMap中的敏感数据----解密数据 [deCardNo = "
				+ deCardNo.substring(deCardNo.length() - 4) + "]"
				+ " [deMobile = " + deMobile.substring(deMobile.length() - 4)
				+ "]" + " [deIdentityCode = "
				+ deIdentityCode.substring(deIdentityCode.length() - 4) + "]");

	}

}
