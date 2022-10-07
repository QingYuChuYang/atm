<%--
  Created by IntelliJ IDEA.
  User: BOOS
  Date: 2022/4/22
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <h1>欢迎${atm.atmName}使用取款功能</h1>
    <form action="takeMoney.action" method="post">
        取出金额：<input type="text" name="atmMoney" value="" placeholder="金额只能是正数"><br/>
        <input type="submit" value="取钱">
    </form>
    </body>
</html>
