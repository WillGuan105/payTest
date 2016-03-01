package com.mls.pay.access.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CommonMethod {


    public static String meilishuo_mm = null;
    public static String access_token = null;

    public static String labn;
    public static String generatepayinfo_link;
    public static String generatepayinfo_linkpara;
    public static String generateorderinfo_link;
    public static String getchannellist_link;
    public static String getchannellist_linkpara;
    public static String getCookieLink;
    public static String danbaocancel_link;
    public static String danbaoconfirm_link;
    public static String refund_link;
    public static String mob_key;
    public static String pc_key;

    //登录获取accesstoken的账号信息
    public static String username;
    public static String password;
    public static String userid;

    static {
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/interface.properties");
        try {
            prop.load(in);
            labn = prop.getProperty("labn").trim();
            username = prop.getProperty("username").trim();
            password = prop.getProperty("password").trim();
            userid=prop.getProperty("userid").trim();
            List<String> attrList = new ArrayList<String>();
            attrList.add(labn);
            System.out.println(attrList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties prop2 = new Properties();
        InputStream in2 = Object.class.getResourceAsStream("/case.properties");
        try {
            prop2.load(in2);
            generatepayinfo_link = prop2.getProperty("generatepayinfo_link").trim().replace("labn", labn);
            generatepayinfo_linkpara = prop2.getProperty("generatepayinfo_linkpara").trim();
            generateorderinfo_link = prop2.getProperty("generateorderinfo_link").trim().replace("labn", labn);
            getchannellist_link = prop2.getProperty("getchannellist_link").trim().replace("labn", labn);
            getchannellist_linkpara = prop2.getProperty("getchannellist_linkpara").trim();
            getCookieLink = prop2.getProperty("getCookieLink").trim().replace("labn", labn);
            danbaocancel_link = prop2.getProperty("danbaocancel_link").trim().replace("labn", labn);
            danbaoconfirm_link = prop2.getProperty("danbaoconfirm_link").trim().replace("labn", labn);
            refund_link = prop2.getProperty("refund_link").trim().replace("labn", labn);
            pc_key = prop2.getProperty("pc_key").trim();
            mob_key = prop2.getProperty("mob_key").trim();

            List<String> attrList = new ArrayList<String>();
            attrList.add(generatepayinfo_link);
            attrList.add(generatepayinfo_linkpara);
            attrList.add(generateorderinfo_link);
            attrList.add(getchannellist_link);
            attrList.add(getchannellist_linkpara);
            attrList.add(danbaocancel_link);
            attrList.add(danbaoconfirm_link);
            attrList.add(refund_link);
            System.out.println(attrList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改hessian的配置
        try {
            EditHessian.modiryHessian(labn);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断userid是否在分流器，保证该账号走新支付,钱包余额足够
        try {
            CheckData.check_userAccount(userid);
            CheckData.check_accountinfo(userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 重写验证方法，取消检测ssl
     */
    private static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }

    };

    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static final boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

//	public static NameValuePair[] getNameValuePair(String para) {
//		String[] str = null;
//		if (para.contains("&")) {
//			str = para.split("&");
//		} else {
//			str = para.split(",");
//		}
//		NameValuePair[] lists = new NameValuePair[str.length];
//		for (int i = 0; i < str.length; i++) {
//			if (str[i].split("=").length > 1) {
//				if (str[i].split("=").length == 3) {
//					NameValuePair nv = new NameValuePair(str[i].split("=")[0],
//							str[i].split("=")[1] + "=" + str[i].split("=")[2]);
//					lists[i] = nv;
//				} else {
//					NameValuePair nv = new NameValuePair(str[i].split("=")[0],
//							str[i].split("=")[1]);
//					lists[i] = nv;
//				}
//
//			} else {
//				NameValuePair nv = new NameValuePair(str[i].split("=")[0], "");
//				lists[i] = nv;
//			}
//		}
//		return lists;
//	}


//	public static List<NameValuePair> getNVPairList(String para){
//		String[] str = null;
//		if (para.contains("&")) {
//			str = para.split("&");
//		} else {
//			str = para.split(",");
//		}
//		List<NameValuePair> lists = new ArrayList<NameValuePair>();
//		for (int i = 0; i < str.length; i++) {
//			if (str[i].split("=").length > 1) {
//				if (str[i].split("=").length == 3) {
//					NameValuePair nv = new NameValuePair(str[i].split("=")[0],
//							str[i].split("=")[1] + "=" + str[i].split("=")[2]);
//					lists.add(nv);
//				} else {
//					NameValuePair nv = new NameValuePair(str[i].split("=")[0],
//							str[i].split("=")[1]);
//					lists.add(nv);
//				}
//
//			} else {
//				NameValuePair nv = new NameValuePair(str[i].split("=")[0], "");
//				lists.add(nv);
//			}
//		}
//		return lists;
//	}


    public static List<BasicNameValuePair> getBasicNameValuePair(String para) {
        String[] str = null;
        if (para.contains("&")) {
            str = para.split("&");
        } else {
            str = para.split(",");
        }
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        for (int i = 0; i < str.length; i++) {
            if (str[i].split("=").length > 1) {
                if (str[i].split("=").length == 3) {
                    BasicNameValuePair nv = new BasicNameValuePair(
                            str[i].split("=")[0], str[i].split("=")[1] + "="
                            + str[i].split("=")[2]);
                    nvps.add(nv);
                } else {
                    BasicNameValuePair nv = new BasicNameValuePair(
                            str[i].split("=")[0], str[i].split("=")[1]);
                    nvps.add(nv);
                }

            } else {
                BasicNameValuePair nv = new BasicNameValuePair(
                        str[i].split("=")[0], "");
                nvps.add(nv);
            }
        }
        return nvps;
    }


    public static String[] get_except(String result) {
        return result.split(",");
    }

    public static String[] get_pay_except(String result) {
        return result.split("\\|");
    }

    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    public static String sign(String text, String key, String input_charset) {
        // logger.debug("MonitorProject=mlsPayCore | MonitorClass=CheckHmacServiceImpl.sign |  text="+text+" , key="+key);
        text = key + text;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5:"
                    + charset);
        }
    }

    public static Map<String, String> getMap(String para) {
        Map<String, String> map = new HashMap<String, String>();
        String[] kv = para.split("&");
        for (int i = 0; i < kv.length; i++) {
            String[] str = kv[i].split("=");
            if (str.length > 1) {
                map.put(kv[i].split("=")[0], kv[i].split("=")[1]);
            } else {
                map.put(kv[i].split("=")[0], "");
            }
        }
        return map;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("HMAC")) {
                continue;
            }
            result.put(key, value.trim());
        }
        return result;
    }


    public static String getHMAC(String para) {
        String linkString = createLinkString(paraFilter(getMap(para)));
        String key = "";
        if (para.contains("MLS_I_00000001"))
            key = CommonMethod.pc_key;
        else
            key = CommonMethod.mob_key;
        String hack = sign(linkString, key, "UTF-8");
        return hack;
    }


    public static String get_sorted_string(String para) {
        String linkString = createLinkString(paraFilter(getMap(para)));
        return linkString;
    }


    public static String getNow() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    //转换日期格式为时间戳格式
    public static String get_ordertime(String ordertime) {
        String time = ordertime.replace("-", "").replace(":", "").replace(" ", "");
        return time;
    }

    //获取时间戳格式的当前时间
    public static String getOrderTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }


    public static String getTomorrow() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);    //得到后一天
        Date date = calendar.getTime();
        return df.format(date);
    }


    // 获取随机数字字符串
    public static String get_random_string(int length) {
        String base = "0123456789"; //
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    // 获取美丽说mm
    public static String get_meilishuocookie() {
        if (meilishuo_mm == null) {
            String meilishuo_cookies = "";
            DefaultHttpClient client = new DefaultHttpClient();
            enableSSL(client);
            HttpPost method = new HttpPost(
                    CommonMethod.getCookieLink);
            method.setHeader("Content-Type",
                    "application/x-www-form-urlencoded;charset=utf-8");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("login_name", username));
            nvps.add(new BasicNameValuePair("password", password));
            nvps.add(new BasicNameValuePair("save_state", "1"));
            try {
                method.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));// 处理中文参数
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            method.setHeader("User-Agent", "Zabbix-MLS (Meilishuo monitor system)");
            HttpResponse response = null;
            try {
                response = client.execute(method);
            } catch (IOException e) {
                e.printStackTrace();
            }
            org.apache.http.Header[] hs = response.getHeaders("Set-Cookie");
            for (int i = 0; i < hs.length; i++) {
                if (hs[i].toString().contains("santorini_mm")) {
                    meilishuo_cookies = hs[i].toString();
                }
            }
            int code = response.getStatusLine().getStatusCode();// 返回状态码
            System.out.println("登录后返回的状态码：" + code);
            System.out.println("登录获取的cookie=========" + meilishuo_cookies);
            // 获取美丽说MMcookie
            String str = meilishuo_cookies.split("santorini_mm=")[1].split(";")[0];
            method.releaseConnection();
            meilishuo_mm = "santorini_mm=" + str;
        }
        return meilishuo_mm;
    }

    @SuppressWarnings("deprecation")
    public static void enableSSL(DefaultHttpClient httpclient) {
        // 调用ssl
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{truseAllManager}, null);
            @SuppressWarnings("deprecation")
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme https = new Scheme("https", sf, 443);
            httpclient.getConnectionManager().getSchemeRegistry()
                    .register(https);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取access_token
    public static String get_access_token() {
        if (access_token == null) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(10000)
                    .setConnectTimeout(10000)
                    .setConnectionRequestTimeout(10000)
                    .setMaxRedirects(3)
                    .build();

            DefaultHttpClient httpclient = new DefaultHttpClient();

            String oauthauthorize = "http://mobapi." + labn + ".meilishuo.com/2.0/oauth/authorize?response_type=code&client_id=30900&redirect_uri=";
            HttpGet httpgetcode = new HttpGet(oauthauthorize.trim());
            httpgetcode.setConfig(requestConfig);
            httpgetcode.setHeader("Content-Type",
                    "application/x-www-form-urlencoded;charset=utf-8");
            HttpResponse response = null;
            try {
                response = httpclient.execute(httpgetcode);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity entity = response.getEntity();
            // 把内容转成字符串
            String resultString = null;
            try {
                resultString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(resultString);
            String code = resultString.split("\"")[3];
            System.out.println("====code===" + code);

            String oauthaccess_token = "http://mobapi." + labn + ".meilishuo.com/2.0/oauth/access_token?grant_type=authorization_code&client_id=30900&code=" + code + "&redirect_uri=";
            HttpGet httpgettoken = new HttpGet(oauthaccess_token.trim());
            httpgetcode.setConfig(requestConfig);
            httpgetcode.setHeader("Content-Type",
                    "application/x-www-form-urlencoded;charset=utf-8");
            try {
                response = httpclient.execute(httpgettoken);
            } catch (IOException e) {
                e.printStackTrace();
            }
            entity = response.getEntity();
            // 把内容转成字符串
            try {
                resultString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(resultString);
            access_token = resultString.split("\"")[3];
            System.out.println("access_token===" + access_token);

            //加密开始
            System.out.println("加密开始");
            String st = System.currentTimeMillis() + "";
            String req = "access_token=" + access_token + "&imei=862845027122451&mac=38:bc:1a:0e:49:7b&password=" + password + "&qudaoid=30900&st=" + st + "&username=" + username + "";

            String private_key = "#SHJ5o2&4";
            String md5value = EncoderHandler.encode("MD5", req);
            String sha1value = EncoderHandler.encode("SHA1", md5value + private_key);

            System.out.println("st = " + st);
            System.out.println("md5 = " + md5value);
            System.out.println("sha1value = " + sha1value);

            req = req + "&st=" + st + "&_sign=" + sha1value;
            String login = "https://account." + labn + ".meilishuo.com/2.0/account/login";
            System.out.println("加密结束，result = " + req);


            HttpPost httpost = new HttpPost(login.trim());
            CommonMethod.enableSSL(httpclient);
            httpost.setHeader("Content-Type",
                    "application/x-www-form-urlencoded;charset=utf-8");
            httpost.setHeader("User-Agent", "meilishuo android 30900 6.2.0");

            List<BasicNameValuePair> param = CommonMethod.
                    getBasicNameValuePair(req);
            try {
                httpost.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));// 处理中文参数
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                response = httpclient.execute(httpost);
            } catch (IOException e) {
                e.printStackTrace();
            }
            entity = response.getEntity();
            // 把内容转成字符串
            try {
                resultString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(resultString);
        }
        return access_token;
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
//		String s = "version=20131111&browserType=NIE&busiTypeId=DOOTA&merchantId=MLS_TEST_01";
//		String str = getHMAC(s);
//		System.out.println(str);
//        get_access_token();
//        System.out.println(access_token);
        get_meilishuocookie();
        System.out.println(meilishuo_mm);
//        try {
//            CommonTest.getioucreditline();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}
