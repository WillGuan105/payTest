package com.mls.pay.access.testcase;

import com.mls.pay.access.common.*;
import com.mls.pay.account.BaseTest;
import org.testng.annotations.Test;


/**
 * Created by whuyi123 on 15-5-5.
 */
public class WalletPay extends BaseTest{
    /**
     * 组合支付，退款
     * @param sharaData
     * @param busiTypeId
     * @param tppCode
     * @param bankCode
     * @param pmCode
     * @param paytype
     * @param payamount
     * @param pageRetUrl
     * @param bgRetUrl
     * @param cancelRetUrl
     * @param refRetUrl
     * @throws Exception
     */
    @Test(dataProvider = "dp", dataProviderClass = com.mls.pay.access.testdata.Dataprovider.class)
    public void walletpay(String id,String sharaData, String busiTypeId,
                       String tppCode, String bankCode,String pmCode,String merchantcode,String source,String paytype,String orderamount,String usedamount,String payamount,String pageRetUrl,String bgRetUrl,String cancelRetUrl,String refRetUrl) throws Exception {

        final String testName = "walletpay";

        CommonTest.outputHeader(id, testName);

        /*
        ********参数准备
         */
        String orderDate=CommonTest.orderDate;

        /*
            orderinfo 所需参数
         */
        String orderNo="15"+ CommonMethod.get_random_string(12);//生成14位数的orderNo
        //修改sharedata
        sharaData=sharaData.replace("\"orderId\":\"\"","\"orderId\":\""+orderNo+"\"");

        String order_para = "bgRetUrl="+bgRetUrl+"&browserType=NIE&busiTypeId=" + busiTypeId +"&curId=CNY"+
                "&merchantId=" + merchantcode + "&orderAmount=" + orderamount +"&orderDate="+orderDate+ "&orderNo=" + orderNo + "&pageRetUrl="+pageRetUrl+"&productName=美丽说订单&shareData=" + sharaData+
                "&source="+source+"&transTypeId=DANBAO&userAccount="+CommonTest.userAccount+"&validityDate="+CommonTest.validityDate+ "&version=20131111";

        /*
            payinfo 所需参数
         */
        String pay_para="access_token="+CommonTest.access_token+"&r=generatepayinfo&merchantcode="+merchantcode+"&orderno="+orderNo+"&pmcode="+pmCode+"&bankcode="+bankCode+"&paytype="+paytype+"&source="+source+"&version=20131111&usedamount="+usedamount+"&payamount="+payamount;//参数
        String pay_linkpara = CommonMethod.generatepayinfo_linkpara; //参与加密的参数
        //pc余额支付需要密码，mob不需要
        if(!pmCode.contains("WAP")) {
            String pwd = DESede.encryptToBase64("111111", "12345678qwertyui12345678");
            pay_para = pay_para + "&password="+pwd;
            pay_linkpara = pay_linkpara+"&password";
        }


        /*
        ********开始测试
         */
        CommonTest.runAllTest(testName, id, orderNo, order_para, merchantcode, source, pay_para, pay_linkpara,orderamount);

        try {
            String confirm_sharedata=sharaData;
            //支付记账，记账金额
            CommonTest.paykeepaccount(syncKeepAccountBusinessFacade,confirm_sharedata,pmCode,tppCode,bankCode,orderNo,orderamount);

            //担保确认，担保确认金额(usedamount)
            String danbaoconfirm_para = "version=20131111&busiTypeId=" + busiTypeId + "&merchantId=" + merchantcode + "&totalNo=" + orderNo + "&orderNo=" + orderNo + "&orderDate=" + orderDate + "&confirmNo=" + orderDate + "DANBAOCONFIRM&confirmAmount=" + orderamount + "&shareData=" + confirm_sharedata + "";
            CommonTest.danbaoconfirmTest(danbaoconfirm_para, orderNo);

            //担保确认记账,金额
            CommonTest.danbaoconfirmkeepaccount(syncKeepAccountBusinessFacade, confirm_sharedata, pmCode, tppCode, bankCode, orderNo,orderamount);

            //退款,退款金额
            String refund_para="version=20131111&busiTypeId="+busiTypeId+"&merchantId="+merchantcode+"&orderNo="+orderNo+"&orderDate="+orderDate+"&refOrderNo="+orderNo+"REFUND&refAmount="+orderamount+"&refundMode=BANK&shareData="+confirm_sharedata+"&refReqTime="+CommonMethod.getOrderTime()+"&refRetUrl="+refRetUrl+"";
            CommonTest.refundTest(refund_para,orderNo);
            CommonTest.outputTestSuccess(id, testName);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            //清理数据
            CommonTest.cleanTestData(orderNo);
        }
    }

}
