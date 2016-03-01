package com.mls.pay.access.common;

import com.mls.pay.access.databean.ModuleResult;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by whuyi123 on 15-9-10.
 */
public class CreateResult {
    public static int passed_case;
    public static int failed_case;

    public static String fileName = "result.html";
    public static File myFile;

    public static HashMap<String,ModuleResult> total_result; //保存统计结果


    static {
        total_result=new HashMap<String, ModuleResult>();

        myFile = new File(fileName);
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //清空文件
            FileWriter fw = null;
            try {
                fw = new FileWriter(myFile);
                fw.write("");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建html文件，用于写入测试报告
     */
    public static void createFile() throws IOException {
        FileOutputStream out = new FileOutputStream(myFile, false);//如果追加方式用true
        StringBuilder strb = new StringBuilder();
        strb.append("<html>");
        strb.append("<head>");
        strb.append("<meta charset=\"UTF-8\">");
        strb.append("</head>");
        strb.append("<title>PayAutoTest Result</title>");

        //table样式
        strb.append("<style type=\"text/css\">table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}th,td {border:1px solid #009;padding:.25em .5em}th {vertical-align:bottom}td {vertical-align:top}table a {font-weight:bold}.stripe td {background-color: #E6EBF9}.num {text-align:right}.passedodd td {background-color: #3F3}.passedeven td {background-color: #0A0}.skippedodd td {background-color: #DDD}.skippedeven td {background-color: #CCC}.failedodd td,.attn {background-color: #F33}.failedeven td,.stripe .attn {background-color: #D00}.stacktrace {white-space:pre;font-family:monospace}.totop {font-size:85%;text-align:center;border-bottom:2px solid #000}</style>");

        strb.append("<body>");

        strb.append("<div style=\"position:absolute; left:100px; top:300px;\">");
        strb.append("<table>");
        strb.append("<thead><tr><th>测试类</th><th>支付渠道</th><th>下单方式</th><th>支付方式</th><th>订单号</th><th>测试结果</th><th>错误信息</th></tr></thead>");
        out.write(strb.toString().getBytes("utf-8"));
        out.close();

    }

    /**
     * 创建一个tbody,每个模块调用一次
     */
    public static void create_tbody(String id) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream(myFile, true);//如果追加方式用true
        StringBuilder strb = new StringBuilder();
        strb.append("<tbody id="+id+">");
        strb.append("<tr><th colspan='7'>"+id+"</th><tr>");
        try {
            out.write(strb.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束一个tbody,每个模块调用一次
     */
    public static void end_tbody() throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream(myFile, true);//如果追加方式用true
        StringBuilder strb = new StringBuilder();
        strb.append("</tbody>");
        try {
            out.write(strb.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 追加内容到结果文件
     */
    public static void appendResult(String module, String casename,String ordertype,String paytype,String orderno, String result, String errmsg) throws FileNotFoundException {
        //统计总结果
        if(result.equals("false")){
            failed_case+=1;
        }else{
            passed_case+=1;
        }

        //判断total_result中是否已有该module，如果没有，创建；如果有，更新内容
        if(total_result.containsKey(module)){
            ModuleResult mr=total_result.get(module);
            if (result.equals("true")) {

                mr.setPassedcase(mr.getPassedcase()+1);
            }else{
                mr.setFailedcase(mr.getFailedcase()+1);
            }
            total_result.put(module,mr);
        }
        else{
            ModuleResult mr=new ModuleResult(module);
            if (result.equals("true")) {
                mr.setPassedcase(1);
            }else{
                mr.setFailedcase(1);
            }
            total_result.put(module,mr);
        }


        FileOutputStream out = new FileOutputStream(myFile, true);//如果追加方式用true
        StringBuilder strb = new StringBuilder();
        if (result.equals("true")) {
            strb.append("<tr ><td>" + module + "</td><td>" + casename + "</td><td>" + ordertype + "</td><td>" + paytype + "</td><td>"+orderno+"</td><td bgcolor='#009100'>" + result + "</td><td>" + errmsg + "</td></tr>");
        } else {
            strb.append("<tr ><td>" + module + "</td><td>" + casename + "</td><td>" + ordertype + "</td><td>" + paytype + "</td><td>"+orderno+"</td><td bgcolor='#FF0000'>" + result + "</td><td>" + errmsg + "</td></tr>");
        }
        try {
            out.write(strb.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束html
     */
    public static void endResult() throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream(myFile, true);//如果追加方式用true
        StringBuilder strb = new StringBuilder();
        strb.append("</table>");
        strb.append("</div>");
        strb.append("<div style=\"position:absolute; left:100px; top:20px;\">");
        strb.append("<table>");
        strb.append("<tbody>");
        strb.append("<thead><tr><th>Test</th><th>#Passed</th><th>#Skipped</th><th>#Failed</th></tr></thead>");
        strb.append("<tr><th colspan='4'>meilishuo</th></tr>");
        //遍历结果
        Iterator iter=total_result.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String,ModuleResult> entry=(Map.Entry)iter.next();
            String key=entry.getKey();
            ModuleResult mr=entry.getValue();
            strb.append("<tr><td><a href=\"#"+key+"\">"+mr.getModulename()+"</a></td><td class=\"num\">"+mr.getPassedcase()+"</td><td>0</td><td class=\"num attn\">"+mr.getFailedcase()+"</td></tr>");
        }
        if(failed_case==0){
            strb.append("<tr><td>Total</td><td class=\"num\">"+passed_case+"</td><td>0</td><td class=\"num\">"+failed_case+"</td></tr>");
        }else {
            strb.append("<tr><td>Total</td><td class=\"num\">" + passed_case + "</td><td>0</td><td class=\"num attn\">" + failed_case + "</td></tr>");
        }

        strb.append("</tbody>");
        strb.append("</table>");

        strb.append("</div>");

        strb.append("</body></html>");

        try {
            out.write(strb.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        CreateResult.createFile();
        CreateResult.appendResult("normalorder", "mob支付宝", "pc", "mob","", "true", "null");
        CreateResult.endResult();
    }
}
