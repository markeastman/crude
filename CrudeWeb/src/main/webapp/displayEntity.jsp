<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns = "http://www.w3c.org/1999/xhtml" xmlns:jsp = "http://java.sun.com/JSP/Page">
    <body>
        <h2>Entity of type ${entityName} and identifier ${entityId}</h2>

        <table border="1" width="100%">
            <c:forEach var="attribute" items="${entityAttributes}">
            <tr>
                <th>${attribute.name}</th>
                <td>${entity[attribute.name]}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>