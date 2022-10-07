package com.bjpn.util;

import java.sql.*;

// 获取数据库连接的工具类
public class DBUtill {
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

    //    关闭代码
    public static void getClose(ResultSet resultSet, PreparedStatement psta, Connection conn) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psta != null) {
            try {
                psta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
