package com.mls.pay.access.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Created by whuyi123 on 15-8-24.
 * 修改hessian接口配置
 */
public class EditHessian {
    public static void modiryHessian(String labn) throws JDOMException, IOException {
        String testngfile = System.getProperty("user.dir")
                + "/src/main/resources/spring/spring-hessian-test.xml";
        SAXBuilder builder = new SAXBuilder(false);
        Document document = builder.build(testngfile);
        Element beans = document.getRootElement();
        //修改bean
        List<Element> allbeans=beans.getChildren("bean");
        int length=allbeans.size();
        //修改bean syncKeepAccountBusinessFacade的服务路径
        for(int i=0;i<length;i++){
            Element e=allbeans.get(i);
            if(e.getAttributeValue("id").equals("syncKeepAccountBusinessFacade")){
                System.out.println("ok");
                List<Element> allproperty=e.getChildren();
                //service url
                Element serviceUrl=allproperty.get(0);
                String url="http://account-web."+labn+".meilishuo.com/hessian/syncKeepAccountBusinessFacade";
                System.out.println("hessian配置==========="+url);
                serviceUrl.setAttribute("value",url);
            }
        }
        Format format = Format.getRawFormat();
        format.setEncoding("UTF-8");
        // XMLOutputter类提供了将JDOM树输出为字节流的能力
        XMLOutputter output = new XMLOutputter(format);
        output.output(document, new FileOutputStream(testngfile));
    }
}

