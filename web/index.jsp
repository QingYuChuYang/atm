<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <center>
    <form action="atmLogin.action" method="post">
      <table>
        <tr>
          <td>卡号：</td>
          <td><input type="text" name="atmCode" value="${code}"></td>
        </tr>
        <tr>
          <td>密码：</td>
          <td><input type="password" name="atmPwd" value=""></td>
        </tr>
      </table>
      <input type="submit" value="登入">
      <font color="red">${errorMagess}</font>
      没有账号请重新<a href="reg.jsp">注册</a>
    </form>
  </center>
  </body>
</html>
