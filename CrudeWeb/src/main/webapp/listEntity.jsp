<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns = "http://www.w3c.org/1999/xhtml" xmlns:jsp = "http://java.sun.com/JSP/Page">
    <body>
        <h2>List of ${entityName} Entities</h2>

        <table border="1" width="100%">
            <tr>
            <c:forEach var="attribute" items="${entityAttributes}">
                <th>${attribute.name}</th>
            </c:forEach>
            </tr>
        <c:forEach var="row" items="${entityList}">
            <tr>
            <c:forEach var="attribute" items="${entityAttributes}">
                <td>${row[attribute.name]}</td>
            </c:forEach>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>