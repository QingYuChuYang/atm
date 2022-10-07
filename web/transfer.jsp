<%--
  Created by IntelliJ IDEA.
  User: BOOS
  Date: 2022/4/23
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <h1>欢迎${atm.atmName}使用转账功能</h1>
    <form action="transferMoney.action" method="post">
        收款人卡号：<input type="text" name="transferCode" value=""><br/>
        取出金额：<input type="text" name="transferMoney" value="" placeholder="金额只能是正数"><br/>
        <input type="submit" value="转账">
    </form>
    </body>
</html>
