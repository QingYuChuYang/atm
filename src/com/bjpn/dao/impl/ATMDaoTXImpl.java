package com.bjpn.dao.impl;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDaoTx;
import com.bjpn.util.DBUtill;
import com.bjpn.util.DBUtillTx;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMDaoTXImpl implements ATMDaoTx {

    @Override
    public ATM atmlogin(Connection conn, String atmCode, String atmPwd) {
        return null;
    }

    // 注册的事务是手动开启的 需要手动提交
    @Override
    public String atmReg(Connection conn, String atmCode, String atmName, String atmPwd) {
        PreparedStatement psta = null;
        try {
            String sql = "insert into atm(atm_name,atm_code,atm_pwd,atm_money) values(?,?,?,?)";
            psta = conn.prepareStatement(sql);
            psta.setString(1,atmName);
            psta.setString(2,atmCode);
            psta.setString(3,atmPwd);
            psta.setDouble(4,0);
            int num = psta.executeUpdate();
            if (num > 0) {
                // 添加成功 需要手动提交
                conn.commit();
            } else {
                conn.rollback();
                return null;
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtillTx.getClose(psta);
        }
        return null;
    }

    // 验证卡号是否存在
    @Override
    public boolean checkCode(Connection conn, String code) {
        PreparedStatement psta = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from atm where atm_code =?";
            psta = conn.prepareStatement(sql);
            psta.setString(1, code);
            resultSet = psta.executeQuery();
            // 如果有值 说明这个卡号已经存在 需要重新生成
            if (!resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtillTx.getCloseRs(resultSet);
            DBUtillTx.getClose(psta);
        }
        return false;
    }

    // 存取款 事务合并 没有提交事务 等待事务合并
    @Override
    public boolean updateMoney(Connection conn, String atmCode, double atmMoney) {
        PreparedStatement psta = null;
        try {
            conn = DBUtill.getConn();
            String sql = "update atm set atm_money=? where atm_code=?";
            psta = conn.prepareStatement(sql);
            psta.setDouble(1, atmMoney);
            psta.setString(2, atmCode);
            int num = psta.executeUpdate();
            if (num > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtillTx.getClose(psta);
        }
        return false;
    }

    // 转账
    @Override
    public boolean transferMoney(Connection conn, String transferCode, double transferMoney) {
        PreparedStatement psta = null;
        try {
            String sql = "update atm set atm_money = atm_money + ? where atm_code = ?";
            psta = conn.prepareStatement(sql);
            psta.setDouble(1, transferMoney);
            psta.setString(2, transferCode);
            int num = psta.executeUpdate();
            if (num > 0) {
                // 不提交事务 等待事务合并
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtillTx.getClose(psta);
        }
        return false;
    }
}
