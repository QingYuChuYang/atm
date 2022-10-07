<%@ page import="com.bjpn.dao.ATMDao" %>
<%@ page import="com.bjpn.bean.ATM" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
    <center>
        <h1>欢迎进来${atm.atmName}使用ATM系统</h1>
        <a href="saveMoney.jsp">存款</a>
        <a href="takeMoney.jsp">取款</a>
        <a href="show.jsp">查询</a>
        <a href="transfer.jsp">转账</a>
        <a href="showATMDo.jsp">查询所有用户</a>
    </center>
    </body>
</html>