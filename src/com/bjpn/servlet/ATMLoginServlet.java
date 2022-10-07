package com.bjpn.servlet;

import com.bjpn.bean.ATM;
import com.bjpn.service.ATMService;
import com.bjpn.service.impl.ATMServiceImpl;

import javax.lang.model.element.NestingKind;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ATMLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收参数
        String atmCode = request.getParameter("atmCode");
        String atmPwd = request.getParameter("atmPwd");
        // 调用数据库查询 service中对外提供有操作数据库中的方法
        ATMService atmService = new ATMServiceImpl();
        ATM atm = atmService.atmLoginService(atmCode, atmPwd);
        // 判断登入情况
        if (atm != null) {
//            登入成功  使用重定向
//            后面的页面要显示登录的信息  登入的对象放在session域中
            HttpSession session = request.getSession();
            session.setAttribute("atm", atm);
            response.sendRedirect("main.jsp");
        } else {
            // 登入失败
            request.setAttribute("errorMagess", "账号或密码错误");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
