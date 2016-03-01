package com.mls.pay.access.common;


import com.google.common.collect.Maps;
import com.mls.pay.access.databean.*;
import com.mls.pay.account.common.business.KeepAccountBusinessFacade;
import com.mls.pay.account.common.vo.ResultInfo;

import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MLS on 15/6/16.
 */
public class CommonTest {
    //所有case可以共用的参数放在这里， 需要单独配置的参数放在具体的case中
    public static String orderDate = CommonMethod.getOrderTime();
    public static String validityDate=CommonMethod.getTomorrow();
    public static String userAccount = CommonMethod.userid;
    public static String access_token = CommonMethod.get_access_token();

    public static String orderinfoTest(String orderNo, String order_para) throws SQLException {
        System.out.println("\n ========================== Begin orderinfoTest=================================");
        String orderinfo_result=DefaultHttpRequest.generate_orderinfo(order_para);
        System.out.println("生成orderinfo===========" + orderinfo_result);
        Assert.assertTrue(orderinfo_result.contains("\"returnCode\":\"000000\""));
        //检查orderinfo
        ArrayList<OrderInfo> orderinfo_list= CheckData.get_orderinfo(orderNo);
        Assert.assertTrue(orderinfo_list.size() == 1);
        OrderInfo orderInfo=orderinfo_list.get(0);
        Assert.assertTrue(orderInfo.getStatus().equals("0"));
        System.out.println("\n ========================== End orderinfoTest=================================");
        return orderinfo_result;
    }


    public static void getChannelListTest(String orderNo, String merchantcode, String source) throws Exception {
        System.out.println("\n ========================== Begin getChannelListTest=================================");
        String g_link = CommonMethod.getchannellist_link;
        String g_para="access_token="+access_token+"&r=getchannellist&orderno="+orderNo+"&merchantcode="+merchantcode+"&biztype=1&transtype=2&source="+source+"&version=20131111";
        String g_linkpara =CommonMethod.getchannellist_linkpara;
        String g_result = DefaultHttpRequest.access_post_method(g_link, g_para, g_linkpara);
        System.out.println("getchannellist result=====================" + g_result);
        Assert.assertTrue(g_result.contains("\"code\":\"000000\""));
        System.out.println("\n ========================== End   getChannelListTest=================================");
    }
    
    /*
     * 白条相关
     */
    public static void getioucreditline() throws Exception {
    	System.out.println("\n ========================== Begin getioucreditline=================================");
    	String g_link = "http://mpay.weiguan.qalab.meilishuo.com/payaccess/getioucreditline";
        String g_para = "access_token="+access_token+"&r=getioucreditline&source=1-5.2&version=20131111";
        String g_linkpara="source&version";
        String result=DefaultHttpRequest.access_post_method(g_link, g_para,g_linkpara);
    	System.out.println("\n ========================== End getioucreditline=================================");
        System.out.println("result======="+result);
    }
    
    

    /*
    parameters:
    caseName: 因WalletPay 与其它Case 判断Payinfo的方式不同，所以传入此参数加以区分
     */
    public static void payinfoTest(String testName, String para, String linkpara, String orderNo) throws Exception {
        System.out.println("\n ========================== Begin payinfoTest=================================");
        System.out.println("access_token================"+access_token);
        String link=CommonMethod.generatepayinfo_link;
        String result= DefaultHttpRequest.access_post_method(link, para, linkpara);
        System.out.println("generate payinfo result===================="+result);
        //纯余额支付，实际生成了payinfo但是回调会失败
        if(!testName.contains("walletpay")) {
            Assert.assertTrue(result.contains("\"code\":\"000000\""));
        }
        //检查是否生成payinfo
        ArrayList<PayInfo> payinfo_list= CheckData.get_payinfo(orderNo);
        Assert.assertTrue(payinfo_list.size()>=1);
        PayInfo payInfo=payinfo_list.get(0);
        if(!testName.equals("walletpay"))
            Assert.assertTrue(payInfo.getStatus().equals("0"));

        System.out.println("\n ========================== End   payinfoTest=================================");
    }

    public static void danbaocancelTest(String para,String orderNo) throws Exception {
        System.out.println("\n ========================== Begin danbaocancelTest=================================");
        String link=CommonMethod.danbaocancel_link;
        String result=DefaultHttpRequest.server_post_method(link, para);
        System.out.println("danbaocancel result===================="+result);
        Assert.assertTrue(result.contains("\"ret\":true"));
        //检查是否生成danbaocancel
        ArrayList<DanbaoCancel> danbaocancel_list= CheckData.get_danbaocancel(orderNo);
        Assert.assertTrue(danbaocancel_list.size()>=1);
    }

    public static void danbaoconfirmTest(String para,String orderNo) throws Exception {
        System.out.println("\n ========================== Begin danbaoconfirmTest=================================");
        String link=CommonMethod.danbaoconfirm_link;
        String result=DefaultHttpRequest.server_post_method(link, para);
        System.out.println("danbaoconfirm result===================="+result);
        Assert.assertTrue(result.contains("\"ret\":true"));
        //检查是否生成danbaoconfirm
        ArrayList<DanbaoConfirm> danbaoconfirm_list= CheckData.get_danbaoconfirm(orderNo);
        Assert.assertTrue(danbaoconfirm_list.size()>=1);
    }

    public static void refundTest(String para,String orderNo) throws Exception {
        System.out.println("\n ========================== Begin refundTest=================================");
        String link=CommonMethod.refund_link;
        String result=DefaultHttpRequest.server_post_method(link, para);
        System.out.println("refund result===================="+result);
        Assert.assertTrue(result.contains("\"ret\":true"));
        //检查是否生成refundorder
        ArrayList<RefundOrder> refundorder_list= CheckData.get_refundorder(orderNo);
        Assert.assertTrue(refundorder_list.size()>=1);
    }

    //支付记账
    public static void paykeepaccount(KeepAccountBusinessFacade asyncKeepAccountBusinessFacade, String sharaData,String pmCode,String tppCode,String bankCode,String orderNo,String payAmount){
        int amount=Integer.valueOf((int) (Double.valueOf(payAmount)*100));
        Map<String,Object> payinfoMap = Maps.newHashMap();
        payinfoMap.put("id",Long.parseLong(orderNo)); //每次修改
        //merchantCode 必须真实存在
        payinfoMap.put("shareData",sharaData);
        payinfoMap.put("pmCode",pmCode);
        payinfoMap.put("tppCode",tppCode);
        payinfoMap.put("bankCode",bankCode);
        payinfoMap.put("orderNo",orderNo);
        payinfoMap.put("orderTime",new Date());
        payinfoMap.put("payAmount",Long.valueOf(amount));
        payinfoMap.put("payTime",new Date());
        payinfoMap.put("transTypeId","DANBAO");
        ResultInfo resultInfo= asyncKeepAccountBusinessFacade.payinfoAccount(payinfoMap);
        //验证记账记录
        try {
            ArrayList<MerchantAccountDetail> merchantAccountDetails=CheckData.get_merchantaccountdetail(orderNo);
            //如果有优惠券，也会记账
            if(sharaData.contains("\"coupon\":\"1.00\"")) {
                Assert.assertTrue(merchantAccountDetails.size()==2);
            }else{
                Assert.assertTrue(merchantAccountDetails.size()>=1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //担保确认记账
    public static void danbaoconfirmkeepaccount(KeepAccountBusinessFacade asyncKeepAccountBusinessFacade, String sharaData,String pmCode,String tppCode,String bankCode,String orderNo,String payAmount){
        int amount=Integer.valueOf((int) (Double.valueOf(payAmount)*100));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",Long.parseLong(orderNo));
        map.put("shareData",sharaData);
        map.put("pmCode",pmCode );
        map.put("tppCode",tppCode);
        map.put("bankCode",bankCode);
        map.put("orderNo",orderNo);
        map.put("orderTime",new Date());
        map.put("payTime",new Date());
        map.put("payAmount",Long.valueOf(amount));
        ResultInfo danbaoconfirm_resultInfo = asyncKeepAccountBusinessFacade.danbaoconfirmAccount(map);
        System.out.println(danbaoconfirm_resultInfo.toString());
        //验证记账记录
        try {
            ArrayList<MerchantAccountDetail> merchantAccountDetails=CheckData.get_merchantaccountdetail(orderNo);
            Assert.assertTrue(merchantAccountDetails.size()>=3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //担保撤销记账
    public static void danbaocancelkeepaccount(KeepAccountBusinessFacade asyncKeepAccountBusinessFacade, String sharaData,String pmCode,String tppCode,String bankCode,String orderNo,String payAmount){
        int amount=Integer.valueOf((int) (Double.valueOf(payAmount)*100));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",Long.parseLong(orderNo));
        map.put("shareData",sharaData);
        map.put("pmCode",pmCode);
        map.put("tppCode",tppCode);
        map.put("bankCode",bankCode);
        map.put("orderNo",orderNo);
        map.put("orderTime",new Date());
        map.put("payAmount",Long.valueOf(amount));
        map.put("payTime",new Date());
        map.put("associateId", Long.parseLong(orderNo));
        ResultInfo resultInfo = asyncKeepAccountBusinessFacade.danbaocancelAccount(map);
        System.out.println(resultInfo.toString());
        //验证记账记录
        try {
            ArrayList<MerchantAccountDetail> merchantAccountDetails=CheckData.get_merchantaccountdetail(orderNo);
            //如果有优惠券，也会记账
            if(sharaData.contains("\"coupon\":\"1.00\"")) {
                Assert.assertTrue(merchantAccountDetails.size()==4);
            }else{
                Assert.assertTrue(merchantAccountDetails.size()==2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public static void outputHeader(String id, String testName) {
        System.out.println("\n ###################################### " + id +" ###################################################");
        System.out.println("=========================================================================================");
        System.out.println("This is " + testName + " test");
        System.out.println("=========================================================================================");
    }

    public static void cleanTestData(String orderNo) {
        String dl_orderinfo="DELETE FROM trade_orderinfo where orderNo='"+orderNo+"'";
        CheckData.clear_data(dl_orderinfo,"pay_trade");
        String dl_payinfo="DELETE FROM pay_payinfo where orderNo='"+orderNo+"'";
        CheckData.clear_data(dl_payinfo,"pay_pay");
        String dl_danbaoconfirm="DELETE FROM trade_danbaoconfirm where orderNo='"+orderNo+"'";
        CheckData.clear_data(dl_danbaoconfirm,"pay_trade");
        String dl_danbaocancel="DELETE FROM trade_danbaocancel where orderNo='"+orderNo+"'";
        CheckData.clear_data(dl_danbaocancel,"pay_trade");
        String dl_merchantaccountdetail="DELETE FROM merchantaccountdetail where orderNo='"+orderNo+"'";
        CheckData.clear_data(dl_merchantaccountdetail,"mlspay_server");
        //获取orderId
        String orderId= null;
        try {
            orderId = CheckData.get_orderId(orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String dl_refundorder="DELETE FROM trade_refundorder where orderId='"+orderId+"'";
        CheckData.clear_data(dl_refundorder,"pay_trade");
        String dl_refundpay="DELETE FROM refund_refundpay where orderno='"+orderNo+"'";
        CheckData.clear_data(dl_refundpay,"pay_refund");

        System.out.println("=============================================================================================================");
        System.out.println("================================= Complete clean data========================================================");
        System.out.println("=============================================================================================================");
    }

    public static void outputTestSuccess(String id, String testName) {
        System.out.println("\n=========================================================================================");
        System.out.println(testName + " case: " + id + " Pass !!!");
        System.out.println("=========================================================================================\n");
    }

    public static void runAllTest(String testName, String id, String orderNo, String order_para,String merchantcode, String source, String pay_para, String pay_linkpara,String payamount) {
        try {
            //orderinfo接口测试
            CommonTest.orderinfoTest(orderNo, order_para);

            //getchannellist 接口测试
            //CommonTest.getChannelListTest(orderNo, merchantcode, source);

            //payinfo 接口测试
            CommonTest.payinfoTest(testName, pay_para, pay_linkpara, orderNo);

            //修改数据库状态，将orderinfo和payinfo未支付状态修改为支付完成
            CheckData.update_orderinfo(orderNo,payamount);
            CheckData.update_payinfo(orderNo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
