<%@page import="com.mycompany.db.model.Product"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<a href="">Homepage</a><br>
    <h1>Product</h1>
<table>
    <tr><th>Product_ID</th><th>Name_Product</th><th>Price</th><th>DateRelease</th><th>Rating</th></tr>
    <%
        for (Product products : (List<Product>) request.getAttribute("products")) {
    %>
    <tr>
        <td>
            <%=String.format("%d",products.getId())%>
        </td>
        <td>
            <%=String.format("%s",products.getName_Product())%>
        </td>
        <td>
            <%=String.format("%d",products.getPrice())%>
        </td>
        <td>
            <%=String.format("%tD",products.getDateRelease())%>
        </td>
        <td>
            <%=String.format("%s",products.getRating())%>
        </td>
        <td>
        <%=String.format("<a href='productsEdit.jsp?id=%d'>edit</a>",products.getId())%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
