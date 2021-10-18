<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Acc_Customer editing</title>
</head>
<body>
<h3>Edit Acc_Customer</h3>
<form method="post" action="http://localhost:8080/lab5/acc_Customer">
    <input type="hidden" value="${acc_customers.id}" name="id" />
    <label>Count_Characters</label><br>
    <input type="number" name="count_Characters" value="${acc_customers.count_Characters}" /><br><br>
    <label>Max_Level_Character</label><br>
    <input type="number" name="max_Level_Character" value="${acc_customers.max_Level_Character}" /><br><br>
    <label>Name_Max_Level_Character</label><br>
    <input type="text" name="name_Max_Level_Character" value="${acc_customers.name_Max_Level_Character}" /><br><br>
    <label>Forum_Posts</label><br>
    <input type="number" name="forum_Posts" value="${acc_customers.forum_Posts}" /><br><br>
    <label>Achievements_Completed</label><br>
    <input type="number" name="achievements_Completed" value="${acc_customers.achievements_Completed}" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>