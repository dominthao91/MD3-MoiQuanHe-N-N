<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/15/2021
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Book List</title>
</head>
<body>
<center>
    <h2><a href="/Books">List Book</a></h2>
    <h2><a href="/Books?action=create">Add New Book</a></h2>
    <form method="get">
        <h2>Tim Theo Ten Sach:</h2>
        <input type="text" name="names">
        <input type="submit" value="Search">
    </form>
</center>
<div align="center">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Note</th>
            <th>Category</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${bl}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.note}</td>
                <td>
                    <c:forEach items="${book.getCategories()}" var="c">
                        ${c.name}<br>
                </c:forEach></td>
                <td><a href="/Books?action=edit&id=${book.id}">Edit</a></td>
                <td><a href="/Books?action=delete&id=${book.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
