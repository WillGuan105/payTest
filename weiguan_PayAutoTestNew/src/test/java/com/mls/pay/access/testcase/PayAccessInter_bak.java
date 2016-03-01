package com.mls.pay.access.testcase;

import com.mls.pay.access.common.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created by whuyi123 on 15-7-7.
 */
public class PayAccessInter_bak {

    @BeforeClass
    public void create_tbody(){
        try {
            CreateResult.create_tbody("payaccess");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void end_tbody(){
        try {
            CreateResult.end_tbody();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * payaccess接口
     */
    @Test(dataProvider = "dp", dataProviderClass = com.mls.pay.access.testdata.Dataprovider.class)
    public void payaccess(String name,String link,String expect,String para,String linkpara) throws SQLException {
        link="http://mpay."+ CommonMethod.labn+".meilishuo.com"+link;
        if(para.contains("get_access_token")){
            String access_token= CommonMethod.get_access_token();
            para=para.replace("get_access_token",access_token);
        }
        if(para.contains("get_orderno")){
            //生成orderinfo
            String orderNo="15"+ CommonMethod.get_random_string(12);
            create_order(orderNo);
            para=para.replace("get_orderno",orderNo);
        }
        //光大银行卡
        if(para.contains("get_cardno")){
            String cardno= DESede.encryptToBase64("6214920201169891", "12345678qwertyui12345678");
            para=para.replace("get_cardno",cardno);
        }
        //招行卡
        if(para.contains("iou_cardno")){
            String cardno= DESede.encryptToBase64("6214850105372426", "12345678qwertyui12345678");
            para=para.replace("iou_cardno",cardno);
        }

        if(para.contains("get_password")){
            String pwd = DESede.encryptToBase64("111111", "12345678qwertyui12345678");
            para=para.replace("get_password",pwd);
        }
        if(para.contains("get_payid")){
            //生成orderinfo
            String orderNo="15"+ CommonMethod.get_random_string(12);
            create_order(orderNo);
            //生成payinfo
            String payid=create_payinfo(orderNo);
            para=para.replace("get_payid",payid);
        }

        //光大银行绑卡id
        if(para.contains("get_boundcardid")){
            para=para.replace("get_boundcardid","1661605");
        }

        String caseresult = "true";
        try {
            String result="";
            if(para.contains("0yuangou")){
                String orderNo="15"+ CommonMethod.get_random_string(12);
                HashMap<String,String> results=create_0yuangou(orderNo);
                if(results.containsKey("true")){
                    result="\"returnCode\":\"000000\"";
                }
            }else {
               result= DefaultHttpRequest.access_post_method(link, para, linkpara);
            }
            System.out.println("result====================="+result);
            //预期值可以以"||"分割，包含其中一项就ok
            boolean flag=false;
            if(expect.contains("||")){
                String[] results=expect.split("||");
                int num=expect.split("||").length;
                for(int i=0;i<num;i++){
                    if(result.contains(results[i])){
                        flag=true;
                        break;
                    }
                }
            }else{
                if(result.contains(expect)){
                    flag=true;
                }
            }

            if (flag) {
                caseresult = "true";
            } else {
                caseresult = "false";
            }

            Assert.assertTrue(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                CreateResult.appendResult("payaccess", name,"mob","mob","", caseresult, "");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    //创建0元购订单
    public HashMap<String,String> create_0yuangou(String orderno){
        HashMap<String,String> result=new HashMap<String, String>();
        String sharaData="[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.00\",\"freight\":\"0.00\",\"coupon\":\"1.00\",\"orderId\":\"\"}]";
        String bgRetUrl="http://mpay."+ CommonMethod.labn+".meilishuo.com/UpmpPayWapBgRetUrl.do";
        String orderDate= CommonTest_bak.orderDate;
        String order_para = "bgRetUrl="+bgRetUrl+"&browserType=NIE&busiTypeId=DOOTA&curId=CNY"+
                "&merchantId=MLS_I_00000002&orderAmount=0.0&orderDate="+orderDate+ "&orderNo=" + orderno + "&pageRetUrl=http://doota.meilishuo.com/2.0/pay/callback?out_trade_no=15054439302021&productName=美丽说订单&shareData=" + sharaData+
                "&source=1-5.2&transTypeId=DANBAO&userAccount="+ CommonTest_bak.userAccount+"&validityDate="+ CommonTest_bak.validityDate+ "&version=20131111";
        try {
            result= CommonTest_bak.orderinfoTest(orderno, order_para);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //创建mob普通订单
    public  void create_order(String orderno){
        String sharaData="[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"\"}]";
        String bgRetUrl="http://mpay."+ CommonMethod.labn+".meilishuo.com/UpmpPayWapBgRetUrl.do";
        String orderDate= CommonTest_bak.orderDate;
        String order_para = "bgRetUrl="+bgRetUrl+"&browserType=NIE&busiTypeId=DOOTA&curId=CNY"+
                "&merchantId=MLS_I_00000002&orderAmount=0.01&orderDate="+orderDate+ "&orderNo=" + orderno + "&pageRetUrl=http://doota.meilishuo.com/2.0/pay/callback?out_trade_no=15054439302021&productName=美丽说订单&shareData=" + sharaData+
                "&source=1-5.2&transTypeId=DANBAO&userAccount="+ CommonTest_bak.userAccount+"&validityDate="+ CommonTest_bak.validityDate+ "&version=20131111";
        try {
            CommonTest_bak.orderinfoTest(orderno, order_para);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //创建payinfo(快捷支付)，获取payid
    public String create_payinfo(String orderno) {
        String payid="";
        String pay_para = "access_token=" + CommonTest_bak.access_token + "&r=generatepayinfo&merchantcode=MLS_I_00000002&orderno=" + orderno + "&pmcode=WAPQCCARD&bankcode=CEB&paytype=4&source=1-5.2&version=20131111&usedamount=0&payamount=0.01";
        String pay_linkpara = CommonMethod.generatepayinfo_linkpara;
        try {
            String link = CommonMethod.generatepayinfo_link;
            String result = DefaultHttpRequest.access_post_method(link, pay_para, pay_linkpara);
            System.out.println("generate payinfo result====================" + result);
            Assert.assertTrue(result.contains("\"code\":\"000000\""));
            if(result.contains("payid")){
                payid=result.split("\"payid\":\"")[1].split("\",")[0];
            }
            System.out.println(payid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payid;
    }
}
