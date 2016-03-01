package com.mls.pay.access.common;

/**
 * Created by whuyi123 on 15-4-3.
 */
public class HttpRequest {
    //发送post请求,access接口
//    public static String access_post_method(String link, String para, String linkpara) throws Exception {
//        System.out.println("get cookie start========");
//        String meilishuo_mm=CommonMethod.get_meilishuocookie();
//        System.out.println("cookie============"+meilishuo_mm);
//
//        HttpClient client = new HttpClient();
//        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
//
//        StringBuilder strb = new StringBuilder();
//        InputStream ins = null;
//        //将para放入map，排序指定的参数，生成sign
//        Map<String, String> sParaTemp = CommonMethod.getMap(para);
//        String[] linkedpara = linkpara.split("&");
//        String orginmsg = CashierSecurityUtils.linkParam(sParaTemp,
//                linkedpara);
//        System.out.println("orginmsg===============" + orginmsg);
//        String key = CashierSecurityUtils.getKeyCipher();
//        String sign = CashierSecurityUtils.signatureCashierData(orginmsg,
//                key);
//        //发送post请求
//        PostMethod method = new PostMethod(link.toString());
//        method.setRequestHeader("Content-Type",
//                "application/x-www-form-urlencoded;charset=utf-8");
//
//        List<NameValuePair> lists = CommonMethod.getNVPairList(para);
//        NameValuePair sign_nv = new NameValuePair("sign", sign);
//        NameValuePair key_nv = new NameValuePair("key", key);
//        lists.add(sign_nv);
//        lists.add(key_nv);
//        NameValuePair[] param2 = new NameValuePair[lists.size()];
//        for (int j = 0; j < lists.size(); j++) {
//            param2[j] = lists.get(j);
//        }
//        method.setRequestBody(param2);
//        method.setRequestHeader("Cookie", meilishuo_mm);
//
//        int statusCode = client.executeMethod(method);
//        ins = method.getResponseBodyAsStream();
//        byte[] b = new byte[1024];
//        int r_len = 0;
//        while ((r_len = ins.read(b)) > 0) {
//            strb.append(new String(b, 0, r_len, "utf-8"));
//        }
//        method.releaseConnection();
//        return strb.toString();
//    }
//
//    public static String server_post_method(String link ,String para) throws Exception {
//        if (para.contains("今天") || para.contains("明天")) {
//            para = para.replaceAll("今天", CommonMethod.getToday());
//            para = para.replaceAll("明天", CommonMethod.getTomorrow());
//        }
//        HttpClient client = new HttpClient();
//        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
//        StringBuilder strb = new StringBuilder();
//        InputStream ins = null;
//        String hmac = CommonMethod.getHMAC(para);
//        System.out.println("hmac========"+hmac);
//
//        PostMethod method = new PostMethod(link.toString());
//        method.setRequestHeader("Content-Type",
//                "application/x-www-form-urlencoded;charset=utf-8");
//
//        List<NameValuePair> lists = CommonMethod.getNVPairList(para);
//        NameValuePair nv = new NameValuePair("HMAC", hmac);
//        lists.add(nv);
//        NameValuePair[] param = new NameValuePair[lists.size()];
//        for (int j = 0; j < lists.size(); j++) {
//            System.out.println(lists.get(j).getName()+":"+lists.get(j).getValue());
//            param[j] = lists.get(j);
//        }
//        method.setRequestBody(param);
//        int statusCode=client.executeMethod(method);
//        ins = method.getResponseBodyAsStream();
//        byte[] b = new byte[1024];
//        int r_len = 0;
//        while ((r_len = ins.read(b)) > 0) {
//            strb.append(new String(b, 0, r_len,"utf-8"));
//        }
//        method.releaseConnection();
//        System.out.println("result=========="+strb.toString());
//        return strb.toString();
//    }
//
//    /**
//     * 创建orderinfo
//     */
//    public static void create_orderinfo(String orderNo,String orderTime,int orderAmount,String sharaData,String busiTypeId,String tppCode,String bankCode,String pmCode,String pageRetUrl,String bgRetUrl){
//        String sql="INSERT INTO orderinfo (version, busiTypeId, transTypeId, merchantCode, orderNo, orderTime,orderAmount,productName,sharaData,tppCode,bankCode,pmCode,curId,pageRetUrl,bgRetUrl,userIp,createTime,updateTime)\n" +
//                "VALUES\n" +
//                "('20131111', '"+busiTypeId+"','DANBAO', 'MLS_I_00000002','"+orderNo+"','"+orderTime+"',"+orderAmount+",'美丽说订单','"+sharaData+"','"+tppCode+"','"+bankCode+"','"+pmCode+"','CNY','"+pageRetUrl+"','"+bgRetUrl+"','172.16.13.205','"+orderTime+"','"+orderTime+"');";
//        try {
//            Connection conn = ConnectDB.getConn();
//            Statement stmt = conn.createStatement();
//            int rows=stmt.executeUpdate(sql);
//            if(stmt != null){   // 关闭声明
//                try{
//                    stmt.close() ;
//                }catch(SQLException e){
//                    e.printStackTrace() ;
//                }
//            }
//            if(conn != null) {  // 关闭连接对象
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("insert success============"+rows);
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
