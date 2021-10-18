<%@page import="com.mycompany.db.model.Acc_Customer"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Acc_Customer</title>
</head>
<body>
<a href="">Homepage</a><br>
    <h1>Acc_Customer</h1>
<table>
    <tr><th>Acc_CustomerID</th><th>Count_Characters</th><th>Max_Level_Character</th><th>Name_Max_Level_Character</th><th>Forum_Posts</th><th>Achievements_Completed</th></tr>
    <%
        for (Acc_Customer acc_customers : (List<Acc_Customer>) request.getAttribute("acc_customers")) {
    %>
    <tr>
        <td>
            <%=String.format("%d",acc_customers.getId())%>
        </td>
        <td>
            <%=String.format("%d",acc_customers.getCount_Characters())%>
        </td>
        <td>
            <%=String.format("%d",acc_customers.getMax_Level_Character())%>
        </td>
        <td>
            <%=String.format("%s",acc_customers.getName_Max_Level_Character())%>
        </td>
        <td>
            <%=String.format("%d",acc_customers.getForum_Posts())%>
        </td>
        <td>
            <%=String.format("%d",acc_customers.getAchievements_Completed())%>
        </td>
        <td>
        <%=String.format("<a href='acc_customersEdit.jsp?id=%d'>edit</a>",acc_customers.getId())%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>



