package com.mls.pay.access.interfacetest;
import com.mls.pay.access.common.CommonMethod;
import com.mls.pay.access.common.DESede;
import com.mls.pay.access.common.DefaultHttpRequest;
import org.testng.annotations.Test;

/**
 * Created by a2013048 on 15-6-3.
 */
public class VerifyCard {
    @Test
    public void verifycard() throws Exception {
        String access_token = CommonMethod.get_access_token();
        System.out.println("access_token================" + access_token);
        String link = "http://mpay.yicheng.qalab.meilishuo.com/payaccess/quickpay/consumechekcard";
        String r = "consumechekcard";
        String busiTypeId = "DOOTA";
        String merchantcode = "MLS_I_00000002";
        String orderNo = "15822808352716";
        String paytype = "3";
        //支付密码
        String password = "";
        String usedamount = "0";
        String payamount = "0.01";
        String pmCode = "WAPQCCARD";
        String bankCode = "CEB";
        String cardno = "6214920201169891";
        cardno = DESede.encryptToBase64(cardno, "12345678qwertyui12345678");
        String source = "3-3.0";
        String version = "20131111";
        String para = "access_token=" + access_token + "&r=" + r + "&busiTypeId=" + busiTypeId + "&merchantcode=" + merchantcode + "&orderno=" + orderNo + "&paytype=" + paytype + "&payamount=" + payamount + "&usedamount=" + usedamount + "&version=" + version + "&source=" + source + "&pmcode=" + pmCode + "&bankcode=" + bankCode + "&cardno=" + cardno;
        String linkpara = "merchantcode&usedamount&orderno&paytype&payamount&cardno&version&source";
        String result = DefaultHttpRequest.access_post_method(link, para, linkpara);
        System.out.println("验卡结果====================" + result);
    }
    }
