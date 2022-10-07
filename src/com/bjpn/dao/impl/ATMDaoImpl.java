package com.bjpn.dao.impl;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;
import com.bjpn.util.DBUtill;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMDaoImpl implements ATMDao {
    //  登入
    @Override
    public ATM atmLogin(String atmCode, String atmPwd) {
        // 连接数据库定义为空
        Connection conn = null;
        // 预处理语句定义为空
        PreparedStatement psta = null;
        // 结果集定义为空
        ResultSet resultSet = null;
        try {
            // 连接数据库
            conn = DBUtill.getConn();
//            写出数据库预编译语句
            String sql = "select * from atm where atm_code=? and atm_pwd=?";
            psta = conn.prepareStatement(sql);
            psta.setString(1, atmCode);
            psta.setString(2, atmPwd);
            resultSet = psta.executeQuery();
            if (resultSet.next()) {
                ATM atm = new ATM();
                atm.setAtmId(resultSet.getInt("atm_id"));
                atm.setAtmCode(resultSet.getString("atm_code"));
                atm.setAtmPwd(resultSet.getString("atm_pwd"));
                atm.setAtmMoney(resultSet.getDouble("atm_money"));
                atm.setAtmName(resultSet.getString("atm_name"));
                return atm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtill.getClose(resultSet, psta, conn);
        }

        return null;
    }

    @Override
    public String atmReg(ATM atm) {
        Connection conn = null;
        PreparedStatement psta = null;
        try {
            conn = DBUtill.getConn();
            String sql = "insert into atm(atm_name,atm_code,atm_pwd,atm_money) values(?,?,?,?)";
            psta = conn.prepareStatement(sql);
            psta.setString(1,atm.getAtmName());
            psta.setString(2,atm.getAtmCode());
            psta.setString(3,atm.getAtmPwd());
            psta.setDouble(4,atm.getAtmMoney());
            int num = psta.executeUpdate();
            if (num > 0) {
                // 添加成功
                return atm.getAtmCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtill.getClose(null, psta, conn);
        }
        return null;
    }

    @Override
    public boolean checkCode(String code) {
        Connection conn = null;
        PreparedStatement psta = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtill.getConn();
            String sql = "select * from atm where atm_code =?";
            psta = conn.prepareStatement(sql);
            psta.setString(1, code);
            resultSet = psta.executeQuery();
            // 证明卡号是否存在
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtill.getClose(resultSet, psta, conn);
        }
        return false;
    }

    @Override
    public boolean updateMoney(String atmCode, double money) {
        Connection conn = null;
        PreparedStatement psta = null;
        try {
            conn = DBUtill.getConn();
            String sql = "update atm set atm_money=? where atm_code=?";
            psta = conn.prepareStatement(sql);
            psta.setDouble(1, money);
            psta.setString(2, atmCode);
            int num = psta.executeUpdate();
            if (num > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtill.getClose(null, psta, conn);
        }
        return false;
    }

    @Override
    public boolean addATM() {
        String atmCode = "888234";
        String atmName = "唐僧";
        String atmPwd = "123";
        double atmMoney = 0;
        Connection conn = null;
        PreparedStatement psta = null;
        try {
            conn = DBUtill.getConn();
            conn.setAutoCommit(false);
            String sql = "insert into atm(atm_name,atm_code,atm_pwd,atm_money) values(?,?,?,?)";
            psta = conn.prepareStatement(sql);
            psta.setString(1, atmName);
            psta.setString(2, atmCode);
            psta.setString(3, atmPwd);
            psta.setDouble(4, atmMoney);
            int num = psta.executeUpdate();
            if (num > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            try {
            conn.rollback();
            } catch (SQLException s) {
                s.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtill.getClose(null, psta, conn);
        }
        return false;
    }
}
