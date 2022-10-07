package com.bjpn.service;

import com.bjpn.bean.ATM;

// 保存dao  对外提供业务逻辑处理
// service要给dao提供事物
public interface ATMService {
    // 登入
    ATM atmLoginService(String atmCode, String atmPwd);

    // 注册
    String AtmRegService(String atmName, String atmPwd);

    // 存钱
    boolean saveMoneyService(String atmCode, double saveMoney);

    // 取钱
    boolean takeMoneyService(String atmCode,double takeMoney);
}
