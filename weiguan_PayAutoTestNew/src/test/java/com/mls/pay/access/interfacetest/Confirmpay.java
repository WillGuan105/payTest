package com.mls.pay.access.interfacetest;
import com.mls.pay.access.common.CommonMethod;
import com.mls.pay.access.common.DESede;
import com.mls.pay.access.common.DefaultHttpRequest;
import org.testng.annotations.Test;

/**
 * Created by whuyi123 on 15-5-12.
 */
public class Confirmpay {
@Test
//    @Test(dataProvider = "dp", dataProviderClass = com.testdata.Dataprovider.class)
public void confirmpay() throws Exception {

    String access_token = CommonMethod.get_access_token();
    System.out.println("access_token================" + access_token);
    String link = "http://mpay.yicheng.qalab.meilishuo.com/payaccess/quickpay/confirmpay";
    String r = "consumechekcard";

    String payid = "BILLPAY201507071446079510085583";
    String boundcardid = "1661467";
    String bankcode = "CEB";


    String usedamount = "0.00";
    String payamount = "0.02";
//当前没用
    String isrealname = "0";
    String vcode="101084";
    String cardno = "6214920201169891";
    String mobile = "18811154633";
    String identityno = "421223198701092528";
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
    String token="20150626172456100021229";
   //
    //String para = "access_token=" + access_token + "&r=" + r + "&payid=" + payid   +"&bankcode="+bankcode+ "&cardno="+cardno+"&cardholder="+cardholder+"&mobile="+mobile+"&cardtype="+cardtype+"&identitytype="+identitytype+"&identityno="+identityno+"&isrealname=" + isrealname + "&vcode=" + vcode + "&token=" + token + "&source="+source+"&version="+version;
    //信用卡
    //String para = "access_token=" + access_token + "&r=" + r + "&payid=" + payid   +"&bankcode="+bankcode+ "&cardno="+cardno+"&cardholder="+cardholder+"&mobile="+mobile+"&cardtype="+cardtype+"&identitytype="+identitytype+"&identityno="+identityno+ "&validdate="+validdate+"&cvv="+cvv+"&vcode=" + vcode + "&token=" + token + "&source="+source+"&version="+version;

    //已绑卡支付
    String para = "access_token=" + access_token + "&r=" + r + "&payid=" + payid   +"&boundcardid="+boundcardid +"&bankcode="+ bankcode+ "&vcode=" + vcode + "&token=" + token + "&source="+source+"&version="+version;

    // ParamConstants.PAYID, ParamConstants.PASSWORD,
       //       ParamConstants.PMCODE, ParamConstants.BANKCODE,
       //     ParamConstants.CARDNO, ParamConstants.CARDHOLDER,
       //    ParamConstants.MOBILE, ParamConstants.CARDTYPE,
       //     ParamConstants.IDENTITYTYPE, ParamConstants.IDENTITYNO,
       //     ParamConstants.VALIDDATE, ParamConstants.CVV,
       //     ParamConstants.BOUNDCARDID, ParamConstants.USETYPE,
       //     ParamConstants.ISREALNAME, ParamConstants.TOKEN,
       //     ParamConstants.VCODE, ParamConstants.SOURCE,
       //     ParamConstants.VERSION);
//信用卡
   // String linkpara1 = "payid&bankcode&cardno&cardholder&mobile&cardtype&identitytype&identityno&validdate&cvv&token&vcode&source&version";
    //
    //String linkpara1 = "payid&bankcode&cardno&cardholder&mobile&cardtype&identitytype&identityno&isrealname&token&vcode&source&version";
   //已绑卡
    String linkpara1 = "payid&boundcardid&bankcode&token&vcode&source&version";

    String result = DefaultHttpRequest.access_post_method(link, para, linkpara1);
    System.out.println("支付结果====================" + result);
}
}
