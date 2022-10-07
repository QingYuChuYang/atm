package com.bjpn.dao;

import com.bjpn.bean.ATM;

import java.sql.Connection;

public interface ATMDaoTx {
    // 登录 查询操作 不需要事务 但是Connection一律交给service管理
    ATM atmlogin(Connection conn, String atmCode, String atmPwd);

    // 注册 添加操作 需要事务 但是dao的事务操作由service提供
    // dao中需要的Connection交给service提供
    String atmReg(Connection conn, String atmCode, String atmName, String atmPwd);

    // 验证卡号是否存在
    boolean checkCode(Connection conn, String code);

    //存取款
    boolean updateMoney(Connection conn, String atmCode, double atmMoney);

    // 转账
    boolean transferMoney(Connection conn, String transferCode, double transferMoney);

}
