package com.mls.pay.access.interfacetest;
import com.mls.pay.access.common.CommonMethod;
import com.mls.pay.access.common.DESede;
import com.mls.pay.access.common.DefaultHttpRequest;
import org.testng.annotations.Test;

/**
 * Created by a2013048 on 15-6-2.
 */
public class Checknum {
    @Test
    public void checknum() throws Exception {

        String access_token = CommonMethod.get_access_token();
        System.out.println("access_token================" + access_token);
        String link = "http://mpay.yicheng.qalab.meilishuo.com/payaccess/quickpay/getvcode";
        String r = "getvcode";
        String payid = "BILLPAY201507071446079510085583";
        String boundcardid = "1661467";
        String bankCode = "CEB";
        String cardno = "6214920201169891";
        String mobile="18811154633";
        String identityno = "421223198701092528";
        //String identityno = "410526198910154898";
       // String key = CashierSecurityUtils.getKeyCipher();
        cardno = DESede.encryptToBase64(cardno, "12345678qwertyui12345678");
        mobile = DESede.encryptToBase64(mobile, "12345678qwertyui12345678");
        identityno = DESede.encryptToBase64(identityno, "12345678qwertyui12345678");
        String cardholder = "";

        String cardtype = "0";
        String identitytype = "0";

        String validdate = "0916";
        String cvv= "801";

        String source = "2-1.0";
        String version = "20131111";
        //信用卡
        //String para = "access_token=" + access_token + "&r=" + r + "&payid=" + payid + "&bankcode=" + bankCode  + "&usetype=" + usetype  + "&cardno=" + cardno + "&cardholder=" + cardholder + "&mobile=" + mobile + "&cardtype=" + cardtype+"&identitytype="+identitytype+"&identityno="+identityno+"&validdate="+validdate+"&cvv="+cvv+"&source="+source+"&version="+version;
       //已有储蓄卡
        String para = "access_token=" + access_token + "&r=" + r + "&payid=" + payid + "&bankcode=" + bankCode + "&boundcardid=" + boundcardid +"&source="+source+"&version="+version;
       //新增储蓄卡
       // String para = "access_token=" + access_token + "&r=" + r + "&payid=" + payid + "&bankcode=" + bankCode  + "&usetype=" + usetype + "&isrealname=" + isrealname + "&cardno=" + cardno + "&cardholder=" + cardholder + "&mobile=" + mobile + "&cardtype=" + cardtype+"&identitytype="+identitytype+"&identityno="+identityno+"&source="+source+"&version="+version;
        //信用卡
        //String linkpara1 = "payid&bankcode&usetype&cardno&cardholder&mobile&cardtype&identitytype&identityno&validdate&cvv&source&version";
        //新增储蓄卡
       //String linkpara1 = "payid&bankcode&usetype&isrealname&cardno&cardholder&mobile&cardtype&identitytype&identityno&source&version";
        //已有卡
        String linkpara1 = "payid&boundcardid&bankcode&source&version";
        String result = DefaultHttpRequest.access_post_method(link, para, linkpara1);
        System.out.println("generate checksum result====================" + result);
    }
    }
