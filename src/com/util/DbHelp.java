package com.util;/*
 *DbHelp
 *
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbHelp {
    //驱动器名称
    private static String driver;
    private static String username;
    private static String password;
    private static String url;
    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        DbHelp.driver = driver;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DbHelp.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DbHelp.password = password;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DbHelp.url = url;
    }
    private static ThreadLocal<Connection> cons = new ThreadLocal<>();
    static {
        driver  = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/myproject";
        username = "root";
        password = "root";
        /*static private String url="jdbc:oracle:thin:u2012/k@127.0.0.1:1521:ORCL";*/
        /*jdbc:oracle:thin:@hostlocal:1521:orcl*/
        /*driver = "oracle.jdbc.driver.OracleDriver";
        url  = "jdbc:oracle:thin:hou/root@localhost:1521:orcl";
        username = "hou";
        password = "root";*/
    }
    public static  Connection getConnection(){
        Connection con = cons.get();
        //如果没有，建立连接
        if(con == null){
            try {
                try {
                    Class.forName(driver);
                    cons.set(DriverManager.getConnection(url, username, password));
                    return cons.get();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return con;
    }
    public static boolean update(String sql,Connection con,Object...params){
        PreparedStatement pstm = null;
        try {
            //预先编译sql语句
            pstm= con.prepareStatement(sql);
            //值的输入
            for(int i = 0; i < params.length;i++){
                pstm.setObject(i+1,params[i]);
            }
            //执行sql语句
            int rows = pstm.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            if(pstm != null){
                try {
                    pstm.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }
    public static <T> T qurey(Connection con,ResultSetHanlder<T> hanlder,String sql,Object...params){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        T obj = null;
        //预编译SQL语句
        try {
            pstm = con.prepareStatement(sql);
            //值注入
            for(int i = 0 ; i < params.length; i++){
                pstm.setObject(i+1,params[i]);
            }
            //执行SQL语句
            rs = pstm.executeQuery();
            obj = hanlder.resultSetToBean(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  obj;
    }
}
