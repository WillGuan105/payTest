package com.mls.pay.access.common;



import com.mls.pay.access.databean.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by whuyi123 on 15-3-31.
 */
public class  CheckData {
    //查询orderinfo，用于校验结果
    public static ArrayList<OrderInfo> get_orderinfo(String orderNo) throws SQLException {
        ArrayList<OrderInfo> list=new ArrayList<OrderInfo>();
        String sql="select * from trade_orderinfo where orderNo='"+orderNo+"'";
        Connection conn = ConnectDB.getConn_trade();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setStatus(rs.getString("status"));
            orderInfo.setOrderAmount(rs.getString("orderAmount"));
            orderInfo.setPayAmount(rs.getString("payAmount"));
//            orderInfo.setRefundAmount(rs.getString("refundAmount"));
//            orderInfo.setOrderTime(rs.getString("orderTime"));
            list.add(orderInfo);
        }
        if(rs!=null){
            try{
                rs.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    /**
     * 获取orderId
     */
    public static String get_orderId(String orderNo) throws SQLException {
        String sql="select * from trade_orderinfo where orderNo='"+orderNo+"'";
        Connection conn = ConnectDB.getConn_trade();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String orderId="";
        if(rs.next()){
            orderId=rs.getString("orderId");
        }
        if(rs!=null){
            try{
                rs.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderId;
    }

    /**
     * 获取payinfo
     * @param orderNo
     * @return
     * @throws java.sql.SQLException
     */
    public static ArrayList<PayInfo> get_payinfo(String orderNo) throws SQLException {
        ArrayList<PayInfo> list=new ArrayList<PayInfo>();
        String sql="select * from pay_payinfo where orderNo='"+orderNo+"'";
        Connection conn = ConnectDB.getConn_pay();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            PayInfo payInfo=new PayInfo();
            payInfo.setStatus(rs.getString("status"));
//            payInfo.setOrderAmount(rs.getString("orderAmount"));
            payInfo.setPayAmount(rs.getString("payAmount"));
            payInfo.setPayId(rs.getString("payId"));
//            payInfo.setSharaData(rs.getString("sharaData"));
            list.add(payInfo);
        }
        if(rs!=null){
            try{
                rs.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    /**
     * 获取merchantaccountdetail
     */
    public static ArrayList<MerchantAccountDetail> get_merchantaccountdetail(String orderNo) throws SQLException {
        ArrayList<MerchantAccountDetail> list=new ArrayList<MerchantAccountDetail>();
        String sql="select * from merchantaccountdetail where orderNo='"+orderNo+"'";
        Connection conn = ConnectDB.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            MerchantAccountDetail merchantAccountDetail=new MerchantAccountDetail();
            merchantAccountDetail.setPayAmount(rs.getString("payAmount"));
            merchantAccountDetail.setAccountId(rs.getString("accountId"));
            merchantAccountDetail.setIncomeExpenseType(rs.getString("IncomeExpenseType"));
            merchantAccountDetail.setMemo(rs.getString("memo"));
            merchantAccountDetail.setMerchantCode(rs.getString("merchantCode"));
            merchantAccountDetail.setRealAmount(rs.getString("realAmount"));
            list.add(merchantAccountDetail);
        }
        if(rs!=null){
            try{
                rs.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    //获取acctransinfo
    public static ArrayList<AcctransInfo> get_acctransinfo(String orderNo) throws SQLException {
        ArrayList<AcctransInfo> list=new ArrayList<AcctransInfo>();
        String sql="select * from acctransinfo where orderNo='"+orderNo+"'";
        Connection conn = ConnectDB.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            AcctransInfo acctransInfo=new AcctransInfo();
            acctransInfo.setRealAmount(rs.getString("realAmount"));
            acctransInfo.setMemo(rs.getString("memo"));
            acctransInfo.setIncomeExpenseType(rs.getString("incomeExpenseType"));
            acctransInfo.setAccountType(rs.getString("accountType"));
            acctransInfo.setOriginalAmount(rs.getString("originalAmount"));
            acctransInfo.setPayAmount(rs.getString("payAmount"));
            list.add(acctransInfo);
        }
        if(rs!=null){
            try{
                rs.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    //获取danbaoconfirm表数据
    public static ArrayList<DanbaoConfirm> get_danbaoconfirm(String orderNo) throws SQLException {
        ArrayList<DanbaoConfirm> list=new ArrayList<DanbaoConfirm>();
        String sql="select * from trade_danbaoconfirm where orderNo='"+orderNo+"'";
        Connection conn = ConnectDB.getConn_trade();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            DanbaoConfirm danbaoConfirm=new DanbaoConfirm();
            danbaoConfirm.setPayAmount(rs.getString("payAmount"));
            danbaoConfirm.setConfirmAmount(rs.getString("confirmAmount"));
            danbaoConfirm.setOrderNo(rs.getString("orderNo"));
            danbaoConfirm.setShareData(rs.getString("shareData"));
            danbaoConfirm.setStatus(rs.getString("status"));
            danbaoConfirm.setTotalNo(rs.getString("totalNo"));
            list.add(danbaoConfirm);
        }
        if(rs!=null){
            try{
                rs.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 获取danbaocancel
     */
    public static ArrayList<DanbaoCancel> get_danbaocancel(String orderNO) throws SQLException {
        ArrayList<DanbaoCancel> list=new ArrayList<DanbaoCancel>();
        String sql="select * from trade_danbaocancel where orderNo='"+orderNO+"'";
        Connection conn=ConnectDB.getConn_trade();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            DanbaoCancel danbaoCancel=new DanbaoCancel();
            danbaoCancel.setTotalNo(rs.getString("totalNo"));
            danbaoCancel.setStatus(rs.getString("status"));
            danbaoCancel.setShareData(rs.getString("shareData"));
            danbaoCancel.setOrderNo(rs.getString("orderNo"));
            danbaoCancel.setCancelNo(rs.getString("cancelNo"));
            danbaoCancel.setCancleAmount(rs.getString("cancelAmount"));
            danbaoCancel.setPayAmount(rs.getString("payAmount"));
            list.add(danbaoCancel);
        }
        return list;
    }



    /**
     * 获取refundorder
     */
    public static ArrayList<RefundOrder> get_refundorder(String orderNo) throws SQLException {
        String orderId=get_orderId(orderNo);
        ArrayList<RefundOrder> list=new ArrayList<RefundOrder>();
        String sql="select * from trade_refundorder where orderId='"+orderId+"'";
        Connection conn=ConnectDB.getConn_trade();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            RefundOrder refundOrder=new RefundOrder();
//            refundOrder.setSharaData(rs.getString("sharaData"));
            refundOrder.setStatus(rs.getString("status"));
//            refundOrder.setGateStatus(rs.getString("gateStatus"));
//            refundOrder.setRefundAmount(rs.getString("refundAmount"));
//            refundOrder.setRefundId(rs.getString("refundId"));
            list.add(refundOrder);
        }
        return list;
    }

    /**
     * 获取refundpay
     */
    public static ArrayList<RefundPay> get_refundpay(String orderNo) throws SQLException {
        ArrayList<RefundPay> list=new ArrayList<RefundPay>();
        String sql="select * from refund_refundpay where orderNo='"+orderNo+"'";
        Connection conn=ConnectDB.getConn_refund();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            RefundPay refundPay=new RefundPay();
            refundPay.setPayId(rs.getString("payId"));
            refundPay.setStatus(rs.getString("status"));
            refundPay.setRefundAmount(rs.getString("refundAmount"));
            refundPay.setRefundId(rs.getString("refundId"));
            list.add(refundPay);
        }
        return list;
    }

    /**
     * update orderinfo
     */
    public static void update_orderinfo(String orderno,String payamount) throws SQLException {
        String payAmount=String.valueOf(Double.parseDouble(payamount)*100);
        String sql="update trade_orderinfo set status=1,payAmount='"+payAmount+"' where orderNo='"+orderno+"'";
        Connection conn = ConnectDB.getConn_trade();
        Statement stmt = conn.createStatement();
        int rows=stmt.executeUpdate(sql);
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("update orderinfo success============"+rows);
    }

    /**
     * update payinfo
     */
    public static void update_payinfo(String orderno) throws SQLException {
        String paytime=CommonMethod.getNow();
        String sql="update pay_payinfo set status=1,payTime='"+paytime+"' where orderNo='"+orderno+"'";
        Connection conn = ConnectDB.getConn_pay();
        Statement stmt = conn.createStatement();
        int rows=stmt.executeUpdate(sql);
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("update payinfo success============"+rows);
    }



    /**
     * 保证测试账号走新支付
     * @param userid
     */
    public static void check_userAccount(String userid) throws SQLException {
        //先清除账号信息
        String sql = "delete from trade_flowcontroll where controllId='" + userid + "'";
        Connection conn = ConnectDB.getConn();
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate(sql);
        //添加进分流器
        String orderTime = CommonMethod.getOrderTime();
        String sql2 = "INSERT INTO trade_flowcontroll (controllId, bussitype, source,weight, status, createTime,updateTime)\n" +
                "VALUES\n" +
                "('" + userid + "', 'DOOTA','PC','100', '1','" + orderTime + "','" + orderTime + "');";
        String sql3 = "INSERT INTO trade_flowcontroll (controllId, bussitype, source,weight, status, createTime,updateTime)\n" +
                "VALUES\n" +
                "('" + userid + "', 'DOOTA','MOB','100', '1','" + orderTime + "','" + orderTime + "');";

        int rows2 = stmt.executeUpdate(sql2);
        int rows3 = stmt.executeUpdate(sql3);
        if (stmt != null) {   // 关闭声明
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("insert success============" + rows2);
    }


    /**
     * 确保测试账户钱包的钱足够
     */
    public static void check_accountinfo(String userid) throws SQLException {
        String sql="update accountinfo set totalBalance=200,availableBalance=200 where custNo='"+userid+"'";
        Connection conn = ConnectDB.getConn();
        Statement stmt = conn.createStatement();
        int rows=stmt.executeUpdate(sql);
        if(stmt != null){   // 关闭声明
            try{
                stmt.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("update accountinfo success============"+rows);
    }



    /**
     * 清除数据
     */
    public static void clear_data(String sql,String database){
        try {
            Connection conn=null;
            if(database.equals("mlspay_server")){
                conn=ConnectDB.getConn();
            }
            if(database.equals("pay_trade")){
                conn=ConnectDB.getConn_trade();
            }
            if(database.equals("pay_pay")){
                conn=ConnectDB.getConn_pay();
            }
            if(database.equals("pay_refund")){
                conn=ConnectDB.getConn_refund();
            }
            Statement stmt = conn.createStatement();
            int rows=stmt.executeUpdate(sql);
            if(stmt != null){   // 关闭声明
                try{
                    stmt.close() ;
                }catch(SQLException e){
                    e.printStackTrace() ;
                }
            }
            if(conn != null) {  // 关闭连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("delete success============"+rows);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
