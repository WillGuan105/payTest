package com.mls.pay.access.testcase;

import com.mls.pay.access.common.*;
import com.mls.pay.account.BaseTest;
import org.testng.annotations.Test;


/**
 * Created by whuyi123 on 15-5-12.
 */
public class FreightCoupon extends BaseTest {
    /**
     * 有运费和优惠劵，非余额支付。担保撤销
     * @param sharaData
     * @param busiTypeId
     * @param tppCode
     * @param bankCode
     * @param pmCode
     * @param source
     * @param paytype
     * @param freight
     * @param coupon
     * @param payamount
     * @param pageRetUrl
     * @param bgRetUrl
     * @param cancelRetUrl
     * @param refRetUrl
     * @throws Exception
     */
    @Test(dataProvider = "dp", dataProviderClass = com.mls.pay.access.testdata.Dataprovider.class)
    public void freightcoupon(String id,String sharaData, String busiTypeId,
                       String tppCode, String bankCode,String pmCode,String merchantcode,String source,String paytype,String freight,String coupon,String payamount,String pageRetUrl,String bgRetUrl,String cancelRetUrl,String refRetUrl) throws Exception {

        final String testName = "FreightCoupon";

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
                "&merchantId=" + merchantcode + "&orderAmount=" + payamount +"&orderDate="+orderDate+ "&orderNo=" + orderNo + "&pageRetUrl="+pageRetUrl+"&productName=美丽说订单&shareData=" + sharaData+
                 "&source="+source+"&transTypeId=DANBAO&userAccount="+CommonTest.userAccount+"&validityDate="+CommonTest.validityDate+ "&version=20131111";

        /*
            payinfo 所需参数
         */
        String pay_para="access_token="+CommonTest.access_token+"&r=generatepayinfo&merchantcode="+merchantcode+"&orderno="+orderNo+"&pmcode="+pmCode+"&bankcode="+bankCode+"&paytype="+paytype+"&source="+source+"&version=20131111&usedamount=0&payamount="+payamount;
        String pay_linkpara=CommonMethod.generatepayinfo_linkpara;


         /*
            danbaocancel 所需参数
         */
        String danbaocancel_para="version=20131111&busiTypeId="+busiTypeId+"&merchantId="+merchantcode+"&totalNo="+orderNo+"&orderNo="+orderNo+"&orderDate="+orderDate+"&cancelNo="+orderDate+"DANBAOCANCEL&cancelAmount="+payamount+"&shareData="+sharaData+"&cancelReqTime="+orderDate+"&refundMode=BANK&cancelRetUrl="+cancelRetUrl+"";



        /*
        ********开始测试
         */
        CommonTest.runAllTest(testName, id, orderNo, order_para, merchantcode, source, pay_para, pay_linkpara,payamount);

        try {
            //支付记账
            CommonTest.paykeepaccount(syncKeepAccountBusinessFacade, sharaData, pmCode, tppCode, bankCode, orderNo,payamount);

            //担保撤销
            CommonTest.danbaocancelTest(danbaocancel_para, orderNo);

            //担保撤销记账
            CommonTest.danbaocancelkeepaccount(syncKeepAccountBusinessFacade, sharaData, pmCode, tppCode, bankCode, orderNo,payamount);
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
