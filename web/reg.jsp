<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <center>
        <h1>欢迎登入ATM账户</h1>
        <form action="atmReg.action" method="post">
            <table>
                <tr>
                    <td>姓名：</td>
                    <td><input type="text" name="atmName" value=""></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input type="password" name="atmPwd" value=""></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input type="password" name="atmPwd1" value=""></td>
                </tr>
            </table>
            <input type="submit" value="注册">
            已有帐户！<a href="index.jsp">登入</a>
            <font color="red">${errormessage}</font>
        </form>
    </center>
    </body>
</html>
