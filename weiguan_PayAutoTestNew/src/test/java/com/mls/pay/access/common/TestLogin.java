package com.mls.pay.access.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by danni on 15-5-11.
 */
public class TestLogin {

    public static void main(String[] args) throws IOException {


        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setMaxRedirects(3)
                .build();

        DefaultHttpClient httpclient = new DefaultHttpClient();

        String oauthauthorize = "http://mobapi.lab4.meilishuo.com/2.0/oauth/authorize?response_type=code&client_id=30900&redirect_uri=";
        HttpGet httpgetcode = new HttpGet(oauthauthorize.trim());
        httpgetcode.setConfig(requestConfig);
        httpgetcode.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        HttpResponse response = null;
        response = httpclient.execute(httpgetcode);
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = EntityUtils.toString(entity);
        System.out.println(resultString);
        String code = resultString.split("\"")[3];
        System.out.println("====code==="+code);

        String oauthaccess_token = "http://mobapi.lab4.meilishuo.com/2.0/oauth/access_token?grant_type=authorization_code&client_id=30900&code=" + code +"&redirect_uri=";
        HttpGet httpgettoken = new HttpGet(oauthaccess_token.trim());
        httpgetcode.setConfig(requestConfig);
        httpgetcode.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        response = httpclient.execute(httpgettoken);
        entity = response.getEntity();
        // 把内容转成字符串
        resultString = EntityUtils.toString(entity);
        System.out.println(resultString);
        String token = resultString.split("\"")[3];
        System.out.println("====token==="+token);

        //加密开始
        System.out.println("加密开始");
        String st = System.currentTimeMillis() + "";
        String req = "access_token=" + token + "&imei=862845027122451&mac=38:bc:1a:0e:49:7b&password=hello1234&qudaoid=30900&st=" + st + "&username=whuyi";

        String private_key = "#SHJ5o2&4";
        String md5value = EncoderHandler.encode("MD5", req);
        String sha1value = EncoderHandler.encode("SHA1", md5value+private_key);

        System.out.println("st = "+st);
        System.out.println("md5 = "+md5value);
        System.out.println("sha1value = "+sha1value);

        req = req + "&st=" +st+"&_sign="+sha1value;
        String login="https://account.lab4.meilishuo.com/2.0/account/login";
        System.out.println("加密结束，result = "+req);


        HttpPost httpost = new HttpPost(login.trim());
        CommonMethod.enableSSL(httpclient);
        httpost.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        httpost.setHeader("User-Agent","meilishuo android 30900 6.2.0");

        List<BasicNameValuePair> param = CommonMethod.
                getBasicNameValuePair(req);
        httpost.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));// 处理中文参数
        response = httpclient.execute(httpost);
        entity = response.getEntity();
        // 把内容转成字符串
        resultString = EntityUtils.toString(entity);
        System.out.println(resultString);
    }
}
