package com.mls.pay.access.testcase;

import com.mls.pay.access.common.*;
import com.mls.pay.account.BaseTest;
import org.testng.annotations.*;



public class NormalOrder extends BaseTest{

    /**
     * 普通订单，非余额支付，担保撤销
     * @param sharaData
     * @param busiTypeId
     * @param tppCode
     * @param bankCode
     * @param pmCode
     * @param payamount 订单金额
     * @param pageRetUrl
     * @param bgRetUrl
     * @param cancelRetUrl
     * @param refRetUrl
     * @throws Exception
     */

    @Test(dataProvider = "dp", dataProviderClass = com.mls.pay.access.testdata.Dataprovider.class)
    public void normalorder(String id,String sharaData, String busiTypeId,
                          String tppCode, String bankCode,String pmCode,String merchantcode,String source,String paytype,String payamount,String pageRetUrl,String bgRetUrl,String cancelRetUrl,String refRetUrl) throws Exception {

        final String testName = "NormalOrder";

        CommonTest.outputHeader(id, testName);

         /*
        ********参数准备
         */
        String orderDate=CommonTest.orderDate;

        /*
            orderinfo 所需参数
         */
        String orderNo="15"+CommonMethod.get_random_string(12);//生成14位数的orderNo

        String order_para = "bgRetUrl="+bgRetUrl+"&browserType=NIE&busiTypeId=" + busiTypeId +"&curId=CNY"+
                "&merchantId=" + merchantcode + "&orderAmount=" + payamount +"&orderDate="+orderDate+ "&orderNo=" + orderNo + "&pageRetUrl="+pageRetUrl+"&productName=美丽说订单&shareData=" + sharaData+
                "&source="+source+"&transTypeId=DANBAO&userAccount="+CommonTest.userAccount+"&validityDate="+CommonTest.validityDate+ "&version=20131111";

        /*
            payinfo 所需参数
         */
        String pay_para="access_token="+CommonTest.access_token+"&r=generatepayinfo&merchantcode="+merchantcode+"&orderno="+orderNo+"&pmcode="+pmCode+"&bankcode="+bankCode+"&paytype="+paytype+"&source="+source+"&version=20131111&usedamount=0&payamount="+payamount;
        String pay_linkpara=CommonMethod.generatepayinfo_linkpara;

        /*
        ********开始测试
         */
        CommonTest.runAllTest(testName, id, orderNo, order_para, merchantcode, source, pay_para, pay_linkpara,payamount);

        try {
            //支付记账
            CommonTest.paykeepaccount(syncKeepAccountBusinessFacade,sharaData,pmCode,tppCode,bankCode,orderNo,payamount);

            //担保确认
            String danbaoconfirm_para = "version=20131111&busiTypeId=" + busiTypeId + "&merchantId=" + merchantcode + "&totalNo=" + orderNo + "&orderNo=" + orderNo + "&orderDate=" + orderDate + "&confirmNo=" + orderDate + "DANBAOCONFIRM&confirmAmount=" + payamount + "&shareData=" + sharaData + "";
            CommonTest.danbaoconfirmTest(danbaoconfirm_para, orderNo);
             //担保确认记账
            CommonTest.danbaoconfirmkeepaccount(syncKeepAccountBusinessFacade, sharaData, pmCode, tppCode, bankCode, orderNo,payamount);

            //退款
            String refund_para="version=20131111&busiTypeId="+busiTypeId+"&merchantId="+merchantcode+"&orderNo="+orderNo+"&orderDate="+orderDate+"&refOrderNo="+orderNo+"REFUND&refAmount="+payamount+"&refundMode=BANK&shareData="+sharaData+"&refReqTime="+CommonMethod.getOrderTime()+"&refRetUrl="+refRetUrl+"";
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
