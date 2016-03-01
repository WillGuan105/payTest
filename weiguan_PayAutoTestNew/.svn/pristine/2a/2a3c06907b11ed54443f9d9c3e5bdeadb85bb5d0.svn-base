package com.mls.pay.account;

import com.mls.pay.account.common.business.KeepAccountBusinessFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-hessian-test.xml"})
public class BaseTest extends AbstractTestNGSpringContextTests{
    public static KeepAccountBusinessFacade syncKeepAccountBusinessFacade;
    static{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-hessian-test.xml");
        syncKeepAccountBusinessFacade = (KeepAccountBusinessFacade) context.getBean("syncKeepAccountBusinessFacade");
    }
    @Test
    public void demon() {

    }
}
