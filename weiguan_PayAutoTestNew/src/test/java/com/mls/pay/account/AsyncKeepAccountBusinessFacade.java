package com.mls.pay.account;

import ch.qos.logback.classic.Logger;
import com.google.common.collect.Maps;
import com.mls.pay.account.common.business.KeepAccountBusinessFacade;
import com.mls.pay.account.common.vo.ResultInfo;

import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
/**
 * Created by whuyi123 on 15-6-25.
 */
public class AsyncKeepAccountBusinessFacade extends BaseTest {
    @Resource
    private KeepAccountBusinessFacade asyncKeepAccountBusinessFacade;
    private Logger logger = (Logger) LoggerFactory.getLogger(AsyncKeepAccountBusinessFacade.class) ;


    @Test
    public void testpayaccount(){
        Map<String,Object> payinfoMap = Maps.newHashMap();
        payinfoMap.put("id",10000000000114L); //每次修改
        //merchantCode 必须真实存在
        payinfoMap.put("shareData","[{\"merchantCode\":\"MLS_D_00090001\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"\"}]");
        payinfoMap.put("pmCode","DCARD");
        payinfoMap.put("tppCode","TENPAY");
        payinfoMap.put("bankCode","CMB");
        payinfoMap.put("orderNo","15176976833211");
        payinfoMap.put("orderTime",new Date());
        payinfoMap.put("payAmount",1L);
        payinfoMap.put("payTime",new Date());
        payinfoMap.put("transTypeId","DANBAO");
        ResultInfo resultInfo= asyncKeepAccountBusinessFacade.payinfoAccount(payinfoMap);
        System.out.println(resultInfo.toString());
    }




    @Test
    public void testdanbaocancelAccount(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id","34");
        map.put("shareData","[{\"merchantCode\":\"MLS_TEST_01\",\"amount\":\"0.01\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"20151115152600PAYTEST\"}]");
        map.put("pmCode","DCARD");
        map.put("tppCode","TENPAY");
        map.put("bankCode","CMB");
        map.put("orderNo","20151115152600PAYTEST");
        map.put("orderTime",new Date());
        map.put("payAmount",1L);
        map.put("payTime",new Date());
        map.put("associateId", 1000L);
        ResultInfo resultInfo = asyncKeepAccountBusinessFacade.danbaocancelAccount(map);
        System.out.println(resultInfo.toString());
    }

    @Test
    public void testdanbaoconfirmAccount(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id","12");
        map.put("shareData", "[{\"merchantCode\":\"MLS_TEST_01\",\"amount\":\"10000.00\",\"freight\":\"0.00\",\"coupon\":\"0.00\",\"orderId\":\"\"}]");
        map.put("pmCode","WECHATMOB" );
        map.put("tppCode","MLSPAY");
        map.put("bankCode","WECHAT");
        map.put("orderNo","15121068236705");
        map.put("orderTime",new Date());
        map.put("payTime",new Date());
        map.put("payAmount",10000L);
        ResultInfo resultInfo = asyncKeepAccountBusinessFacade.danbaoconfirmAccount(map);
        System.out.println(resultInfo);
    }
}
