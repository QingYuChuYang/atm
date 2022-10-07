package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.service.ATMService;
import com.bjpn.service.impl.ATMServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ATMSaveMoneyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String atmMoney = request.getParameter("atmMoney");
        double money = Double.parseDouble(atmMoney);
        if (money < 0) {
            request.setAttribute("errorMessage", "存钱失败");
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
        // 根据余额和要存入的余额计算数据库最终的余额
        // 获取当前余额和卡号 登入时在session中存了一个对象
        HttpSession session = request.getSession();
        ATM atm = (ATM) session.getAttribute("atm");
        double saveMoney = atm.getAtmMoney() + money;
        // 调用存钱方法
        ATMService atmService = new ATMServiceImpl();
        boolean b = atmService.saveMoneyService(atm.getAtmCode(), saveMoney);
//        判断成功
        if (b) {
            // 存钱成功 当前余额发生改变
            // 此时session中的atm对象时登入时存入的对象 余额是登入时的余额
//            改变session中atm对象的当前余额
            atm.setAtmMoney(saveMoney);
            session.setAttribute("atm", atm);
            request.setAttribute("message", "存款成功");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
//          存钱失败

        }
    }
}