package com.mls.pay.access.testcase;

import com.mls.pay.access.common.CheckData;
import com.mls.pay.access.common.CommonMethod;
import com.mls.pay.access.common.CommonTest_bak;
import com.mls.pay.access.common.CreateResult;
import com.mls.pay.account.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * Created by whuyi123 on 15-5-12.
 */
public class FreightCoupon_bak extends BaseTest {
    @BeforeClass
    public void create_tbody() {
        try {
            CreateResult.create_tbody("freightcoupon");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void end_tbody() {
        try {
            CreateResult.end_tbody();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有运费和优惠劵，非余额支付。担保撤销
     *
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
    public void freightcoupon(String id, String sharaData, String busiTypeId,
                              String tppCode, String bankCode, String pmCode, String merchantcode, String source, String paytype, String freight, String coupon, String payamount, String pageRetUrl, String bgRetUrl, String cancelRetUrl, String refRetUrl) throws Exception {

        final String testName = "FreightCoupon";

        CommonTest_bak.outputHeader(id, testName);

        /*
        ********参数准备
         */
        String orderDate = CommonTest_bak.orderDate;

        /*
            orderinfo 所需参数
         */
        String orderNo = "15" + CommonMethod.get_random_string(12);//生成14位数的orderNo

        //修改sharedata
        sharaData = sharaData.replace("\"orderId\":\"\"", "\"orderId\":\"" + orderNo + "\"");

        String order_para = "bgRetUrl=" + bgRetUrl + "&browserType=NIE&busiTypeId=" + busiTypeId + "&curId=CNY" +
                "&merchantId=" + merchantcode + "&orderAmount=" + payamount + "&orderDate=" + orderDate + "&orderNo=" + orderNo + "&pageRetUrl=" + pageRetUrl + "&productName=美丽说订单&shareData=" + sharaData +
                "&source=" + source + "&transTypeId=DANBAO&userAccount=" + CommonTest_bak.userAccount + "&validityDate=" + CommonTest_bak.validityDate + "&version=20131111";

        /*
            payinfo 所需参数
         */
        String pay_para = "access_token=" + CommonTest_bak.access_token + "&r=generatepayinfo&merchantcode=" + merchantcode + "&orderno=" + orderNo + "&pmcode=" + pmCode + "&bankcode=" + bankCode + "&paytype=" + paytype + "&source=" + source + "&version=20131111&usedamount=0&payamount=" + payamount;
        String pay_linkpara = CommonMethod.generatepayinfo_linkpara;


         /*
            danbaocancel 所需参数
         */
        String danbaocancel_para = "version=20131111&busiTypeId=" + busiTypeId + "&merchantId=" + merchantcode + "&totalNo=" + orderNo + "&orderNo=" + orderNo + "&orderDate=" + orderDate + "&cancelNo=" + orderDate + "DANBAOCANCEL&cancelAmount=" + payamount + "&shareData=" + sharaData + "&cancelReqTime=" + orderDate + "&refundMode=BANK&cancelRetUrl=" + cancelRetUrl + "";

        String errmsg = "";
        String flag = "true";

        /*
        ********开始测试
         */
        //CommonTest_bak.runAllTest(testName, id, orderNo, order_para, merchantcode, source, pay_para, pay_linkpara, payamount);
        try {
            boolean result = true;
            while (result) {
                //orderinfo接口测试
                HashMap<String, String> order_result = CommonTest_bak.orderinfoTest(orderNo, order_para);
                if (order_result.containsKey("false")) {
                    errmsg = order_result.get("false");
                    flag = "false";
                    break;
                }

                //getchannellist 接口测试
                HashMap<String, String> channellist_result = CommonTest_bak.getChannelListTest(orderNo, merchantcode, source);
                if (channellist_result.containsKey("false")) {
                    errmsg = channellist_result.get("false");
                    flag = "false";
                    break;
                }
                //payinfo 接口测试
                HashMap<String, String> payinfo_result = CommonTest_bak.payinfoTest(testName, pay_para, pay_linkpara, orderNo);
                if (payinfo_result.containsKey("false")) {
                    errmsg = payinfo_result.get("false");
                    flag = "false";
                    break;
                }
                //修改数据库状态，将orderinfo和payinfo未支付状态修改为支付完成
                CheckData.update_orderinfo(orderNo, payamount);
                CheckData.update_payinfo(orderNo);

                //支付记账
                HashMap<String, String> payka_result = CommonTest_bak.paykeepaccount(syncKeepAccountBusinessFacade, sharaData, pmCode, tppCode, bankCode, orderNo, payamount);

                if (payka_result.containsKey("false")) {
                    errmsg = payka_result.get("false");
                    flag = "false";
                    break;
                }

                //担保撤销
                HashMap<String, String> danbaocancel_result = CommonTest_bak.danbaocancelTest(danbaocancel_para, orderNo);
                if (danbaocancel_result.containsKey("false")) {
                    errmsg = danbaocancel_result.get("false");
                    flag = "false";
                    break;
                }

                //担保撤销记账
                HashMap<String, String> danbaocancelka_result = CommonTest_bak.danbaocancelkeepaccount(syncKeepAccountBusinessFacade, sharaData, pmCode, tppCode, bankCode, orderNo, payamount);
                if (danbaocancelka_result.containsKey("false")) {
                    errmsg = danbaocancelka_result.get("false");
                    flag = "false";
                    break;
                }
                result = false;
            }
            //如果flag为true，检查下是否在库里生成了订单，如果是服务有问题，上面无法判断
            if(flag.equals("true")){
                errmsg=CommonTest_bak.check_result(orderNo);
                if(errmsg!="")
                    flag="false";
            }
            org.testng.Assert.assertTrue(flag.equals("true"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            //清理数据
            CommonTest_bak.cleanTestData(orderNo);
            String ordertype="pc";
            String payway="mob";
            if(merchantcode.equals("MLS_I_00000002")){
                ordertype="mob";
            }
            if(source.contains("3")){
                payway="pc";
            }
            CreateResult.appendResult("freightcoupon", bankCode,ordertype,payway,orderNo, flag, errmsg);
        }

    }
}
