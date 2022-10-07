package com.bjpn.service;

import com.bjpn.bean.ATM;

public interface ATMServiceTx {
    // 这是service 主要是保护dao
    ATM atmLoginService(String atmCode, String atmPwd);

    // 注册
    String atmRegService(String atmName, String atmPwd);

    // 转账 atmCode 减钱账户 atmMoney 减钱账户减钱后的余额
    // transferCode 收款人的账户 transferMoney 转账金额
    boolean transferMoneyService(String atmCode, double atmMoney, String transferCode, double transferMoney);

    // 验证卡号是否存在
    boolean checkCodeService(String code);
}
