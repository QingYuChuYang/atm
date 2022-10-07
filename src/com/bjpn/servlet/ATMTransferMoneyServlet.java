package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.service.ATMService;
import com.bjpn.service.ATMServiceTx;
import com.bjpn.service.impl.ATMServiceImpl;
import com.bjpn.service.impl.ATMServiceTxImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ATMTransferMoneyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String transferCode = request.getParameter("transferCode");
        String money = request.getParameter("transferMoney");
        double transferMoney = Double.parseDouble(money);
        // 需要对收款人的卡号进行验证 连接数据库查看
        ATMServiceTx atmServiceTx = new ATMServiceTxImpl();
        boolean b = atmServiceTx.checkCodeService(transferCode);
        if (!b) {
            // 验证金额
            HttpSession session = request.getSession();
            ATM atm = (ATM) session.getAttribute("atm");
            if (atm.getAtmMoney() > transferMoney) {
                // 开始转账
                // 计算减钱账户减钱后的数据库余额
                double atmMoney = atm.getAtmMoney() - transferMoney;
                boolean b1 = atmServiceTx.transferMoneyService(atm.getAtmCode(), atmMoney, transferCode, transferMoney);
                if (b1) {
                    // 转账成功
                    // 把更新后的对象还回去
                    atm.setAtmMoney(atmMoney);
                    session.setAttribute("atm", atm);
                    request.setAttribute("message", "转账成功");
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                } else {
                    // 转账失败
                    request.setAttribute("message", "转账失败");
                    request.getRequestDispatcher("fail.jsp").forward(request, response);

                }
            } else {
                // 余额不足
                request.setAttribute("errorMessage", "余额不足， 请求重新操作");
                request.getRequestDispatcher("fail.jsp").forward(request,response);
            }
        } else {
            // 卡号不存在 回到首页
            request.setAttribute("errorMessage", "收款账号错误，请重新操作");
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
    }
}