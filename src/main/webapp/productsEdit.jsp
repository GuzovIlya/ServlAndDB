<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product editing</title>
</head>
<body>
<h3>Edit Product</h3>
<form method="post" action="http://localhost:8080/lab5/product">
    <input type="hidden" value="${products.id}" name="id" />
    <label>Name_Product</label><br>
    <input type="text" name="name_Product" value="${products.name_Product}" /><br><br>
    <label>Price</label><br>
    <input type="number" name="price" value="${products.price}" /><br><br>
    <label>Rating</label><br>
    <input type="number" name="rating" value="${products.rating}" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>