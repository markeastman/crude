<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns = "http://www.w3c.org/1999/xhtml" xmlns:jsp = "http://java.sun.com/JSP/Page">
    <head>
    <style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
    </style>
    </head>
    <body>
        <h2>List of ${entityName} Entities</h2>

        <table width="100%">
            <tr>
            <c:forEach var="attribute" items="${entityMetaData.attributes}">
                <th>${attribute.name}</th>
            </c:forEach>
            </tr>
        <c:forEach var="row" items="${entityList}">
            <tr>
            <c:forEach var="attribute" items="${entityMetaData.attributes}">
            <c:set var="cellData" value="${row[attribute.name]}" />
                <td>
                <c:choose>
                <c:when test="${attribute.name == entityMetaData.identifierAttributeName}">
                    <c:url value="./displayEntity" var="displayURL">
                        <c:param name="entityName" value="${entityName}" />
                        <c:param name="entityId" value="${cellData}" />
                    </c:url>
                    <a href="${displayURL}">${cellData}</a>
                </c:when>
                <c:otherwise>
                    ${cellData}
                </c:otherwise>
                </c:choose>
                </td>
            </c:forEach>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>