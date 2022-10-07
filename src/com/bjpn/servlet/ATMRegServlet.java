package com.bjpn.servlet;

import com.bjpn.service.ATMService;
import com.bjpn.service.impl.ATMServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ATMRegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String atmName = request.getParameter("atmName");
        String atmPwd = request.getParameter("atmPwd");
        // 调用service注册方法
        ATMService atmService = new ATMServiceImpl();
        String code = atmService.AtmRegService(atmName, atmPwd);
        if (code != null) {
            HttpSession session = request.getSession();
            session.setAttribute("code", code);
            response.sendRedirect("index.jsp");

        } else {
            request.setAttribute("errormessage", "注册失败，重新登入");
            request.getRequestDispatcher("reg.jsp").forward(request,response);
        }
    }
}
