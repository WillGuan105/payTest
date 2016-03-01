package com.mls.pay.access.interfacetest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2013048 on 15-6-3.
 */
public class RecMess {
    @Test
    public  void recmess() throws Exception {

        //String access_token= CommonMethod.get_access_token();
        //System.out.println("access_token================"+access_token);
        String link = "http://qa.mpay.meilishuo.com/paytrade/RecvNewBg.do";


        String amount="1";
        String bizOrderNo="PAY00224201506041606380000074";
        String bankOrderNo="1";
        String trxId="1";
        String status="SUCCESS";
        String chReqTime="20150604161019";
        String finishTime="20150604161019";
        String shoudanOrg="TENPAY";
        String checkoutAccNo="111";


        HttpPost method=new HttpPost(link);
        //PostMethod method = new PostMethod(link.toString());
        //method.setRequestHeader("Content-Type",
        //   "application/x-www-form-urlencoded;charset=utf-8");
        List<NameValuePair> sang = new ArrayList<NameValuePair>();
        // sang.add(new BasicNameValuePair("access_token",access_token));
        sang.add(new BasicNameValuePair("amount",amount));
        sang.add(new BasicNameValuePair("bizOrderNo",bizOrderNo));
        sang.add(new BasicNameValuePair("bankOrderNo",bankOrderNo));
        sang.add(new BasicNameValuePair("trxId",trxId));
        sang.add(new BasicNameValuePair("status",status));
        sang.add(new BasicNameValuePair("chReqTime",chReqTime));
        sang.add(new BasicNameValuePair("finishTime",finishTime));
        sang.add(new BasicNameValuePair("shoudanOrg",shoudanOrg));
        sang.add(new BasicNameValuePair("checkoutAccNo",checkoutAccNo));


        method.setEntity(new UrlEncodedFormEntity(sang, HTTP.UTF_8));
        HttpClient httpClient=new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,10000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,10000);
        HttpResponse response=httpClient.execute(method);

        if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            //得到返回的字符串
            System.out.println(result);
            //打印输出
        }
    }


}
