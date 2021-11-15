<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HI
  Date: 11/14/2021
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <fieldset>
        <legend>Create Book</legend>
        <table>
            <tr>
                <td>name</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>price</td>
                <td><input type="text" name="price"></td>
            </tr>
            <tr>
                <td>description</td>
                <td><input type="text" name="description"></td>
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
                <td><input type="submit" value="ADD"></td>
                <td><a href="/books">cancel</a> </td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>
