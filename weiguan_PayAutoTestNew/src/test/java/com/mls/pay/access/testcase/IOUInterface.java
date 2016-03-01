package com.mls.pay.access.testcase;

import com.mls.pay.access.common.CommonMethod;
import com.mls.pay.access.common.DESede;
import com.mls.pay.access.common.DefaultHttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

/**
 * Created by whuyi123 on 15-8-27.
 */
public class IOUInterface {
    /**
     * payaccess接口
     */
    @Test(dataProvider = "dp", dataProviderClass = com.mls.pay.access.testdata.Dataprovider.class)
    public void ioupay(String name,String link,String expect,String para,String linkpara) throws SQLException {
        link="http://mpay."+ CommonMethod.labn+".meilishuo.com"+link;
        if(para.contains("get_access_token")){
            String access_token= CommonMethod.get_access_token();
            para=para.replace("get_access_token",access_token);
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
        //光大银行绑卡id
        if(para.contains("get_boundcardid")){
            para=para.replace("get_boundcardid","1661687");
        }

        try {
            String result = DefaultHttpRequest.access_post_method(link, para, linkpara);
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
            Assert.assertTrue(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
