<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns = "http://www.w3c.org/1999/xhtml" xmlns:jsp = "http://java.sun.com/JSP/Page">
    <body>
        <h2>Entities</h2>

        <c:forEach var="anEntity" items="${entities}">
            <li><a href="listEntity?entityName=${anEntity}">${anEntity}</a></li>
        </c:forEach>
    </body>
</html>