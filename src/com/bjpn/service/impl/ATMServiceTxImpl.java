package com.bjpn.service.impl;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDaoTx;
import com.bjpn.dao.impl.ATMDaoTXImpl;
import com.bjpn.service.ATMServiceTx;
import com.bjpn.util.DBUtill;
import com.bjpn.util.DBUtillTx;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

public class ATMServiceTxImpl implements ATMServiceTx {
    ATMDaoTx atmDaoTx = new ATMDaoTXImpl();

    @Override
    public ATM atmLoginService(String atmCode, String atmPwd) {
        Connection conn = null;

        try {
            // Service要给到提供Connection 且设置事务
            conn = DBUtill.getConn();// 获取连接
            ATM atm = atmDaoTx.atmlogin(conn, atmCode, atmPwd);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 连接是service提供的 关闭连接的操作也要在service中完成
            DBUtillTx.getCloseConn(conn);
        }
        return null;
    }

    // 注册需要手动提交事务
    @Override
    public String atmRegService(String atmName, String atmPwd) {
        Connection conn = null;
        try {
            // 创建Connection
            conn = DBUtill.getConn();
            // 注册 需要开启手动提交事务
            conn.setAutoCommit(false);
            // 注册需要生成卡号
            String atmCode = getCode();
            String code = atmDaoTx.atmReg(conn, atmCode, atmName, atmPwd);
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtillTx.getCloseConn(conn);
        }
        return null;
    }
    // 转账 要进行事务合并
    @Override
    public boolean transferMoneyService(String atmCode, double atmMoney, String transferCode, double transferMoney) {
        // 转账要调用两个方法 两个方法必须都成功 用同一个connection
        Connection conn = null;
        try {
            conn = DBUtillTx.getConn();
            conn.setAutoCommit(false);// 事务开启手动方式
            // 先调用减钱方法 更新操作
            boolean b = atmDaoTx.updateMoney(conn, atmCode, atmMoney);
            // 调用转账方法 使用的是一个conn
            boolean b1 = atmDaoTx.transferMoney(conn, transferCode, transferMoney);
            if (b && b1) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtillTx.getCloseConn(conn);
        }
        return false;
    }

    // 对外提供的验证卡号
    @Override
    public boolean checkCodeService(String code) {
        Connection conn = null;
        try {
            conn = DBUtillTx.getConn();
            boolean b = atmDaoTx.checkCode(conn, code);
            return b;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtillTx.getCloseConn(conn);
        }
        return false;
    }

    // 私有方法 生成卡号的 查询数据库 提供Connection
    private String getCode(){
        Connection conn = null;
        try {
            Random random = new Random();
            while (true) {
                int num = random.nextInt(900) + 100;
                String code = "888" + num;
                // 证明卡号是否在数据库中
                boolean b = atmDaoTx.checkCode(conn, code);
                if (b) {
                    // 卡号
                    return code;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtillTx.getCloseConn(conn);
        }
        return null;
    }
}
