package com.mls.pay.access.testcase;

import com.mls.pay.access.common.*;
//import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
//import org.testng.Assert;
//
//import java.util.Map;

/**
 * Created by whuyi123 on 15-4-29.
 */
public class TestOnly {
    public static void main(String[] args) throws Exception {
        //生成orderinfo
//        String orderNo="15"+ CommonMethod.get_random_string(12);//生成14位数的订单
//        String busiTypeId = "DOOTA";
//        String merchantId = "MLS_I_00000001";
//        String merchantcode = "MLS_I_00000001";
//        String orderDate = CommonMethod.getOrderTime();
//        String validityDate=CommonMethod.getTomorrow();
//        String orderAmount = "0.01";
//        String userAccount = "58264839";
//        String curId="CNY";
//        String shareData = "[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"\"}]";
//        String version = "20131111";
//        //String source="3-1.0";
//        String source="1-5.2";
//        String transTypeId="DANBAO";
//        //String pageRetUrl="http://www.lab9.qalab.meilishuo.com/2.0/pay/callback?out_trade_no="+orderNo;
//        //String bgRetUrl="http://dootapc.lab9.qalab.meilishuo.com/mpay/mpay_notify";
//        String pageRetUrl = "http://doota.meilishuo.com/2.0/pay/callback?out_trade_no=15054439302021";
//        String bgRetUrl = "http://qa.mpay.meilishuo.com/AliWapPayBgRetUrl.do";
//        String productName="meilishuoorder";
//        String browserType="NIE";
//        shareData=shareData.replace("\"orderId\":\"\"","\"orderId\":\""+orderNo+"\"");
//        System.out.println("===================orderNO: " + orderNo);
//
//        String para = "bgRetUrl="+bgRetUrl+"&browserType="+browserType+"&busiTypeId=" + busiTypeId +"&curId="+curId  +
//                "&merchantId=" + merchantId + "&orderAmount=" + orderAmount +"&orderDate="+orderDate+ "&orderNo=" + orderNo + "&pageRetUrl="+pageRetUrl+"&productName="+productName+"&shareData=" + shareData
//                + "&source="+source+"&transTypeId="+transTypeId+"&userAccount="+userAccount+"&validityDate="+validityDate+ "&version=" + version;
//        System.out.println("===================para \n : " + para);
//        String result= DefaultHttpRequest.generate_orderinfo(para);
//        System.out.println("######################################  generate orderinfo result: \n " + result);

//               获取渠道列表
        String orderNo="15275552587564";
        String access_token =CommonMethod.get_access_token();
        String link = "http://mpay.yicheng.qalab.meilishuo.com/payaccess/getchannellist";
//        String link = CommonMethod.getchannellist_link;
        String g_para="access_token="+access_token+"&r=getchannellist&orderno="+orderNo+"&merchantcode=MLS_I_00000001&biztype=1&transtype=2&source=1-5.2&version=20131111";
        String linkpara ="biztype&merchantcode&orderno&source&transtype&version";
        String g_result = DefaultHttpRequest.access_post_method(link, g_para, linkpara);
        System.out.println("getchannellist  result====================" + g_result);
////
////
////
////
////
//        //生成payinfo
//        System.out.println("access_token================"+access_token);
//        String g_link="http://mpay.yicheng.qalab.meilishuo.com/payaccess/generatepayinfo";
//        String g_para="access_token="+access_token+"&r=generatepayinfo&merchantcode="+merchantcode+"&orderno="+orderNo+"&pmcode=WAPGWCARD&bankcode=ALIPAY&paytype=2&source="+source+"&version=20131111&usedamount=0&payamount=0.01";
//        String g_linkpara="orderno&merchantcode&paytype&usedamount&payamount&pmcode&bankcode&source&version";
//        String g_result= DefaultHttpRequest.access_post_method(g_link, g_para, g_linkpara);
//        System.out.println("generate payinfo result===================="+g_result);
//        Assert.assertTrue(g_result.contains("\"code\":\"000000\""));
////
//
//
//
//
//
//        String orderNo="15501656330550";
//        String shareData="[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"15501656330550\"}]";
//        String cancelRetUrl="http://dootalabpc.yicheng.qalab.meilishuo.com/mpay/mpay_danbao_cancel_notify";
//        String orderDate="20150624175539";
//        System.out.println(orderDate);
//        //担保撤销
//        String orderCancelTime=orderDate;
//        String danbaocancel_request="http://mpay.yicheng.qalab.meilishuo.com/DanbaoCancel.do?";
//        String danbaocancel_para="version=20131111&busiTypeId=DOOTA&merchantId=MLS_I_00000002&totalNo="+orderNo+"&orderNo="+orderNo+"&orderDate="+orderDate+"&cancelNo="+orderDate+"DANBAOCANCEL&cancelAmount=0.01&shareData="+shareData+"&cancelReqTime="+orderCancelTime+"&refundMode=BANK&cancelRetUrl="+cancelRetUrl+"";
//        String danbaocancel_result=DefaultHttpRequest.server_post_method(danbaocancel_request,danbaocancel_para);
//        System.out.println("daobancancel result===================="+danbaocancel_result);




//        //退款
//        String orderNo="15879164113383";
//        String orderDate="20150624192850";
//        String refReqTime=CommonMethod.getOrderTime();
//        System.out.println(refReqTime);
//        String refRetUrl="http://dootalabpc.yicheng.qalab.meilishuo.com/mpay/mpay_refund_notify";
//        String sharaData="[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"15879164113383\"}]";
//        String refund_link=CommonMethod.refund_link;
//        String refund_para="version=20131111&busiTypeId=DOOTA&merchantId=MLS_I_00000002&orderNo="+orderNo+"&orderDate="+orderDate+"&refOrderNo="+orderNo+"REFUND&refAmount=0.01&refundMode=bank&shareData="+sharaData+"&refReqTime="+refReqTime+"&refRetUrl="+refRetUrl+"";
//        String danbaocancel_result=DefaultHttpRequest.server_post_method(refund_link,refund_para);
//        System.out.println("refund result===================="+danbaocancel_result);


        //退款
//        String orderNo="15062549918493";
//        String orderDate="20150625105446";
//        String refReqTime=CommonMethod.getOrderTime();
//        System.out.println(refReqTime);
//        String refRetUrl="http://dootalabpc.yicheng.qalab.meilishuo.com/mpay/mpay_refund_notify";
//        String sharaData="[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"\"}]";
//        String refund_link=CommonMethod.refund_link;
//        String refund_para="version=20131111&busiTypeId=DOOTA&merchantId=MLS_I_00000002&orderNo="+orderNo+"&orderDate="+orderDate+"&refOrderNo="+orderNo+"REFUND&refAmount=0.01&refundMode=bank&shareData="+sharaData+"&refReqTime="+refReqTime+"&refRetUrl="+refRetUrl+"";
//        String danbaocancel_result=DefaultHttpRequest.server_post_method(refund_link,refund_para);
//        System.out.println("refund result===================="+danbaocancel_result);


//        String amount="0.01";
////        Double a=Double.parseDouble(amount)*100;
//        System.out.println(amount);



    }
}
