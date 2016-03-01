package com.mls.pay.access.common;

import java.security.MessageDigest;

/**
 * blog www.micmiu.com
 *
 * @author Michael
 *
 */
public class EncoderHandler {

    private static final String ALGORITHM = "MD5";

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * encode string
     *
     * @param algorithm
     * @param str
     * @return String
     */
    public static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    public static String encodeByMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes
     *            the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }



    public static String testlinkEncoded(String link,String parameters,String httptype){
        //加密开始
        System.out.println("加密开始");
        String st = System.currentTimeMillis() + "";

        String req=parameters + "&st="+st;
        String private_key = "#SHJ5o2&4";
        String md5value = EncoderHandler.encode("MD5", req);
        String sha1value = EncoderHandler.encode("SHA1", md5value+private_key);

        System.out.println("st = "+System.currentTimeMillis());
        System.out.println("md5 = "+md5value);
        System.out.println("sha1value = "+sha1value);

        String testlink=null;
        if("post".equals(httptype)){
            testlink = parameters+"&_sign="+sha1value + "&st="+st;
        }else{
            testlink = link +  "?" + parameters+"&_sign="+sha1value + "&st="+st;
        }
        System.out.println("加密结束，result = "+testlink);
        return testlink;
    }



    public static void main(String[] args) {
        System.out.println("111111 MD5  :"
                + EncoderHandler.encodeByMD5("111111"));
        System.out.println("111111 MD5  :"
                + EncoderHandler.encode("MD5", "111111"));
        System.out.println("111111 SHA1 :"
                + EncoderHandler.encode("SHA1", "111111"));
    }

}