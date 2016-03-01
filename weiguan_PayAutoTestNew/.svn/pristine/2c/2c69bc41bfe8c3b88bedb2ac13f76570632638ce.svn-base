package com.mls.pay.access.testcase;

import com.mls.pay.access.common.CreateResult;
import com.mls.pay.account.BaseTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by whuyi123 on 15-9-11.
 */
public class TestConfig extends BaseTest {
    @BeforeSuite
    public void createResultFile(){
        //创建结果文件
        try {
            CreateResult.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void endResultFile(){
        try {
            CreateResult.endResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
