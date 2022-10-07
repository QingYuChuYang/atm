package com.bjpn.service.impl;

import com.bjpn.bean.ATM;
import com.bjpn.dao.ATMDao;
import com.bjpn.dao.impl.ATMDaoImpl;
import com.bjpn.service.ATMService;

import java.util.Random;

public class ATMServiceImpl implements ATMService {

    ATMDao atmDao = new ATMDaoImpl();
    @Override
    public ATM atmLoginService(String atmCode, String atmPwd) {
        ATM atm = atmDao.atmLogin(atmCode, atmPwd);
        return atm;
    }

    @Override
    public String AtmRegService(String atmName, String atmPwd) {
        // 注册的servlet调用当前service
        // 注册页面只能获取用户名和密码
        // 注册的dao 需求卡号  密码  用户名  余额  放入atm对象
        String code = getCode();
        ATM atm = new ATM();
        atm.setAtmCode(code);
        atm.setAtmName(atmName);
        atm.setAtmPwd(atmPwd);
        atm.setAtmMoney(0);
        // 调用注册方法
        String atmCode = atmDao.atmReg(atm);
        return atmCode;
    }

    @Override
    public boolean saveMoneyService(String atmCode, double saveMoney) {
        boolean b = atmDao.updateMoney(atmCode,saveMoney);
        return b;
    }

    @Override
    public boolean takeMoneyService(String atmCode, double takeMoney) {
        boolean b = atmDao.updateMoney(atmCode,takeMoney);
        return b;
    }

    // 封装一个生成卡号的方法
    private String getCode(){
        Random random = new Random();
        while (true) {
            int num = random.nextInt(900) + 100;
            String code = "888" + num;
            // 证明卡号是否在数据库中
            boolean b = atmDao.checkCode(code);
            if (b) {
                // 卡号
                return code;
            }
        }
    }
}
