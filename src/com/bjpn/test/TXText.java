package com.bjpn.test;

import com.bjpn.dao.ATMDao;
import com.bjpn.dao.impl.ATMDaoImpl;

public class TXText {
    public static void main(String[] args) {
        ATMDao atmDao = new ATMDaoImpl();
        boolean b = atmDao.addATM();
        if (b) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }
}
