<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HI
  Date: 11/14/2021
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Danh sách sách </h1>
    <h2>
        <a href="/books?action=create">Add new book</a>
    </h2>
</center>
<div align="center">
    <table border="1px">
        <caption>List of book</caption>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>description</th>
            <th>category</th>
            <th>action</th>
        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td>
                    <c:out value="${book.id}"/>
                </td>
                <td>
                    <c:out value="${book.name}"/>
                </td>
                <td>
                    <c:out value="${book.price}"/>
                </td>
                <td>
                    <c:out value="${book.description}"/>
                </td>
                <td><c:forEach var="category" items="${book.categoryList}">
                    <c:out value="${category.name}"/><br>
                </c:forEach> </td>
                <td>
                    <a href="books?action=edit&id=${book.id}">edit</a>
                    <a href="books?action=delete&id=${book.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
