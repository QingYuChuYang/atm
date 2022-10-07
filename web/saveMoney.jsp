<%--
  Created by IntelliJ IDEA.
  User: BOOS
  Date: 2022/4/22
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>欢迎${atm.atmName}来存钱</h1>
        <form action="saveMoney.action" method="post">
            存入金额：<input type="text" name="atmMoney" value="" placeholder="金额为正数"><br/>
            <input type="submit" value="存入">
        </form>
    </body>
</html>
