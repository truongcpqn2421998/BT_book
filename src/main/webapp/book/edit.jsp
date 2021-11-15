<%--
  Created by IntelliJ IDEA.
  User: HI
  Date: 11/14/2021
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${book.id}">
    <fieldset>
        <legend>Edit Book</legend>
        <table>
            <tr>
                <td>name</td>
                <td><input type="text" name="name" value="${book.name}" ></td>
            </tr>
            <tr>
                <td>price</td>
                <td><input type="text" name="price" value="${book.price}"></td>
            </tr>
            <tr>
                <td>description</td>
                <td><input type="text" name="description" value="${book.description}"></td>
            </tr>
            <tr>
                <td>category</td>
                <td><select name="category" multiple>
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select> </td>
            </tr>
            <tr>
                <td><input type="submit" value="Edit"></td>
                <td><a href="/books">cancel</a> </td>
            </tr>
        </table>
    </fieldset>
    </form>
</body>
</html>
