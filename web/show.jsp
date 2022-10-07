<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <center>
      <table width="800px" border="1" cellspacing="0">
        <tr>
          <td>卡号：</td>
          <td><input type="text" name="atmCode" value="${atm.atmCode}"></td>
        </tr>
        <tr>
          <td>姓名：</td>
          <td><input type="text" name="atmName" value="${atm.atmName}"></td>
        </tr>
        <tr>
          <td>余额：</td>
          <td><input type="text" name="atmMoney" value="${atm.atmMoney}"></td>
        </tr>
      </table>
      <a href="main.jsp">回到注册界面</a>
  </center>
  </body>
</html>
