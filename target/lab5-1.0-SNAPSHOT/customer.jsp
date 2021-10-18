<%@page import="com.mycompany.db.model.Customer"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<a href="">Homepage</a><br>
    <h1>Customer</h1>
<table>
    <tr><th>Customer_ID</th><th>Login</th><th>Server_customer</th><th>Country</th><th>DateRegistration</th><th>BalanceBefore</th></tr>
    <%
        for (Customer customers : (List<Customer>) request.getAttribute("customers")) {
    %>
    <tr>
        <td>
            <%=String.format("%d",customers.getId())%>
        </td>
        <td>
            <%=String.format("%s",customers.getLogin())%>
        </td>
        <td>
            <%=String.format("%s",customers.getServer_customer())%>
        </td>
        <td>
            <%=String.format("%s",customers.getCountry())%>
        </td>
        <td>
            <%=String.format("%tD",customers.getDateRegistration())%>
        </td>
        <td>
            <%=String.format("%d",customers.getBalanceBefore())%>
        </td>
        <td>
        <%=String.format("<a href='customersEdit.jsp?id=%d'>edit</a>",customers.getId())%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
