<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns = "http://www.w3c.org/1999/xhtml" xmlns:jsp = "http://java.sun.com/JSP/Page">
    <body>
        <h2>List of ${entityName}</h2>

        <table>
            <tr>
                <th>todo</th>
            <tr>
        <c:forEach var="row" items="${entityList}">
            <tr>
                <td>row</td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>