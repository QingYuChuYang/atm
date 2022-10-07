package com.bjpn.util;

import java.sql.*;

// 获取数据库连接的工具类
public class DBUtillTx {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");// 导入mysql的jar包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            String url = "jdbc:mysql://localhost:3306/sh2202?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "root");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 关闭Connection
    public static void getCloseConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭ResultSet
    public static void getCloseRs(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //    关闭代码
    public static void getClose(PreparedStatement psta) {
        if (psta != null) {
            try {
                psta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
