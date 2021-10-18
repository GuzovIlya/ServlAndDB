<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer editing</title>
</head>
<body>
<h3>Edit Customer</h3>
<form method="post" action="http://localhost:8080/lab5/customer">
    <input type="hidden" value="${customers.id}" name="id" />
    <label>Login</label><br>
    <input type="text" name="login" value="${сustomers.login}" /><br><br>
    <label>Server_customer</label><br>
    <input type="text" name="server_customer" value="${customers.server_customer}" /><br><br>
    <label>Country</label><br>
    <input type="text" name="country" value="${сustomers.country}" /><br><br>
    <label>BalanceBefore</label><br>
    <input type="number" name="balanceBefore" value="${сustomers.balanceBefore}" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>