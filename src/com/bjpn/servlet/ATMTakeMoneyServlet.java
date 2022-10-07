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

public class ATMTakeMoneyServlet extends HttpServlet {
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
//        判断当前金额是否小于取出金额 如果小返回金额不足
        HttpSession session = request.getSession();
        ATM atm = (ATM) session.getAttribute("atm");
        if (atm.getAtmMoney() > money) {
            double takeMoney = atm.getAtmMoney() - money;
            ATMService atmService = new ATMServiceImpl();
            boolean b = atmService.takeMoneyService(atm.getAtmCode(), takeMoney);
            if (b) {
                atm.setAtmMoney(takeMoney);
                session.setAttribute("atm", atm);
                request.setAttribute("message", "取钱成功");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "余额不足");
                request.getRequestDispatcher("fail.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "余额不足");
        }
    }
}
