package com.mls.pay.access.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by whuyi123 on 15-1-29.
 */
public class ConnectDB {
    // connect the database
    public static String DRIVER;
    public static String USERNAME;
    public static String PASSWORD;
    public static String DB_DataBase;

    //server库
    public static String DB_NAME;
    public static String IP;
    public static String PORT;
    public static String URL;
    public static String URL_SWAN;

    //pay_trade库
    public static String DB_NAME_T;
    public static String IP_T;
    public static String PORT_T;
    public static String URL_T;


    //pay_pay库
    public static String DB_NAME_P;
    public static String IP_P;
    public static String PORT_P;
    public static String URL_P;

    //pay_refund库
    public static String DB_NAME_R;
    public static String IP_R;
    public static String PORT_R;
    public static String URL_R;

    static {
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/db.properties");
        try {
            prop.load(in);
            DRIVER = prop.getProperty("DB_DRIVER").trim();
            USERNAME = prop.getProperty("DB_USERNAME").trim();
            PASSWORD = prop.getProperty("DB_PASSWORD").trim();
            DB_DataBase=prop.getProperty("DB_DataBase").trim();



            if(DB_DataBase.contains("default")){
                InputStream in_default = Object.class.getResourceAsStream("/db_qalabdefault.properties");
                prop.load(in_default);
            }

            if(DB_DataBase.contains("qalab1")){
                InputStream in_qalab1 = Object.class.getResourceAsStream("/db_qalab1.properties");
                prop.load(in_qalab1);

            }

            if(DB_DataBase.contains("qalab2")){
                InputStream in_qalab2 = Object.class.getResourceAsStream("/db_qalab2.properties");
                prop.load(in_qalab2);

            }

            if(DB_DataBase.contains("qalab3")){
                InputStream in_qalab3 = Object.class.getResourceAsStream("/db_qalab3.properties");
                prop.load(in_qalab3);

            }

            if(DB_DataBase.contains("rdlab")){
                InputStream in_rdlab = Object.class.getResourceAsStream("/db_rdlabdefault.properties");
                prop.load(in_rdlab);

            }

            //mlspay_server库
            DB_NAME = prop.getProperty("DB_NAME").trim();
            IP = prop.getProperty("DB_HOSTIP").trim();
            PORT = prop.getProperty("DB_PORT").trim();
            URL = "jdbc:mysql://" + IP + ":" + PORT + "/"
                    + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8";
            URL_SWAN="jdbc:mysql://" + IP + ":" + PORT + "/swan?useUnicode=true&characterEncoding=UTF-8";

            //pay_trade库
            DB_NAME_T= prop.getProperty("DB_NAME_T").trim();
            IP_T=prop.getProperty("DB_HOSTIP_T").trim();
            PORT_T=prop.getProperty("DB_PORT_T").trim();
            URL_T="jdbc:mysql://" + IP_T + ":" + PORT_T + "/"
                    + DB_NAME_T + "?useUnicode=true&characterEncoding=UTF-8";

            //pay_pay库
            DB_NAME_P= prop.getProperty("DB_NAME_P").trim();
            IP_P=prop.getProperty("DB_HOSTIP_P").trim();
            PORT_P=prop.getProperty("DB_PORT_P").trim();
            URL_P="jdbc:mysql://" + IP_P + ":" + PORT_P+ "/"
                    + DB_NAME_P + "?useUnicode=true&characterEncoding=UTF-8";

            //pay_refund库
            DB_NAME_R= prop.getProperty("DB_NAME_R").trim();
            IP_R=prop.getProperty("DB_HOSTIP_R").trim();
            PORT_R=prop.getProperty("DB_PORT_R").trim();
            URL_R="jdbc:mysql://" + IP_R + ":" + PORT_R+ "/"
                    + DB_NAME_R + "?useUnicode=true&characterEncoding=UTF-8";

        } catch (IOException e) {
            e.printStackTrace();
	    System.out.println("Init DB ex: " + e.getMessage());
        }
    }

    //连接server库
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME,
                    PASSWORD);//
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //连接pay_trade库
    public static Connection getConn_trade() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL_T, USERNAME,
                    PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //连接pay_pay库
    public static Connection getConn_pay() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL_P, USERNAME,
                    PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //连接pay_refund库
    public static Connection getConn_refund() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL_R, USERNAME,
                    PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    public static Connection getConn_swan(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL_SWAN, USERNAME,
                    PASSWORD);//
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //获取token
    public static String get_accesstoken(){
        String token="";
        String sql="select token from t_swan_oauth_access_token where user_id='58264839' order by id desc limit 1";
        Connection conn = getConn_swan();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                token=rs.getObject(1).toString();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }


    public static void main(String[] args){
        String sql = "select  * from orderinfo where orderNo='20131106224512PAYTEST'";//
        Connection conn = getConn();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.first());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
