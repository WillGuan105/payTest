package com.mls.pay.access.common;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by whuyi123 on 15-5-5.
 */
public class DefaultHttpRequest {
    public static String access_post_method(String link, String para, String linkpara) throws Exception {

        System.out.println("get cookie start========");
        String meilishuo_mm = CommonMethod.get_meilishuocookie();

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(link.toString());
        httpost.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");

        httpost.setHeader("Cookie", meilishuo_mm);

        StringBuilder strb = new StringBuilder();
        InputStream ins = null;
        //将para放入map，排序指定的参数，生成sign
        Map<String, String> sParaTemp = CommonMethod.getMap(para);
        String[] linkedpara = linkpara.split("&");
        String orginmsg = CashierSecurityUtils.linkParam(sParaTemp,
                linkedpara);
        System.out.println("orginmsg===============" + orginmsg);
        String key = CashierSecurityUtils.getKeyCipher();
        String sign = CashierSecurityUtils.signatureCashierData(orginmsg,
                key);
        List<BasicNameValuePair> lists = (List<BasicNameValuePair>) CommonMethod.getBasicNameValuePair(para);
        BasicNameValuePair sign_nv = new BasicNameValuePair("sign", sign);
        BasicNameValuePair key_nv = new BasicNameValuePair("key", key);
        lists.add(sign_nv);
        lists.add(key_nv);
        BasicNameValuePair[] param2 = new BasicNameValuePair[lists.size()];
        for (int j = 0; j < lists.size(); j++) {
            param2[j] = lists.get(j);
        }
        //发送post请求
        httpost.setEntity(new UrlEncodedFormEntity(lists, HTTP.UTF_8));// 处理中文参数
        HttpResponse response = httpclient.execute(httpost);
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        //String resultString =EntityUtils.toString(entity);
        String resultString = new String(EntityUtils.toString(entity, "UTF-8"));
        return resultString;
    }


    public static String server_post_method(String link ,String para) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        String hmac = CommonMethod.getHMAC(para);
        System.out.println("hmac========"+hmac);
        HttpPost httpost = new HttpPost(link.toString());

        httpost.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");

        List<BasicNameValuePair> lists = (List<BasicNameValuePair>) CommonMethod.getBasicNameValuePair(para);
        BasicNameValuePair nv = new BasicNameValuePair("HMAC", hmac);
        lists.add(nv);
        BasicNameValuePair[] param = new BasicNameValuePair[lists.size()];
        for (int j = 0; j < lists.size(); j++) {
            param[j] = lists.get(j);
        }
        //发送post请求
        httpost.setEntity(new UrlEncodedFormEntity(lists, HTTP.UTF_8));// 处理中文参数
        HttpResponse response = httpclient.execute(httpost);
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString =new String(EntityUtils.toString(entity, "UTF-8"));
        return resultString;
    }

    /**
     * 创建orderinfo
     */
    public static void create_orderinfo(String merchantcode,String orderNo,String orderTime,int orderAmount,String sharaData,String busiTypeId,String tppCode,String bankCode,String pmCode,String pageRetUrl,String bgRetUrl){
        String sql="INSERT INTO orderinfo (version, busiTypeId, transTypeId, merchantCode, orderNo, orderTime,orderAmount,productName,sharaData,tppCode,bankCode,pmCode,curId,pageRetUrl,bgRetUrl,userIp,createTime,updateTime)\n" +
                "VALUES\n" +
                "('20131111', '"+busiTypeId+"','DANBAO', '"+merchantcode+"','"+orderNo+"','"+orderTime+"',"+orderAmount+",'美丽说订单','"+sharaData+"','"+tppCode+"','"+bankCode+"','"+pmCode+"','CNY','"+pageRetUrl+"','"+bgRetUrl+"','172.16.13.205','"+orderTime+"','"+orderTime+"');";
        try {
            Connection conn = ConnectDB.getConn();
            Statement stmt = conn.createStatement();
            int rows=stmt.executeUpdate(sql);
            if(stmt != null){   // 关闭声明
                try{
                    stmt.close() ;
                }catch(SQLException e){
                    e.printStackTrace() ;
                }
            }
            if(conn != null) {  // 关闭连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("insert success============"+rows);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //生成orderinfo
    public static String generate_orderinfo(String para){
        String link=CommonMethod.generateorderinfo_link;
        String key = "";
        if(para.contains("MLS_I_00000001"))
            key = CommonMethod.pc_key;
        else
            key = CommonMethod.mob_key;
        String linkString = para;
        String hmac="";
        try {
            hmac = DigestUtils.md5Hex((key + linkString).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(link.toString());
        httpost.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        List<BasicNameValuePair> lists = (List<BasicNameValuePair>) CommonMethod.getBasicNameValuePair(para);
        BasicNameValuePair nv = new BasicNameValuePair("HMAC", hmac);
        lists.add(nv);
        BasicNameValuePair[] param = new BasicNameValuePair[lists.size()];
        for (int j = 0; j < lists.size(); j++) {
            param[j] = lists.get(j);
        }
        //发送post请求
        try {
            httpost.setEntity(new UrlEncodedFormEntity(lists, HTTP.UTF_8));// 处理中文参数
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = null;
        try {
            resultString = new String(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
