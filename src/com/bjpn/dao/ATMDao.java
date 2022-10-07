package com.bjpn.dao;

import com.bjpn.bean.ATM;

// 接口只有抽象方法  是对外暴露的规则
public interface ATMDao {
    // 登入
    ATM atmLogin(String atmCode, String atmPwd);

    // 注册  卡号自动生成  返回卡号  余额为0
    String atmReg(ATM atm);

    // 验证卡号是否存在
    boolean checkCode(String code);

    // 存取款
    boolean updateMoney(String atmCode, double money);

    //    测试案例  添加
    boolean addATM();
}
