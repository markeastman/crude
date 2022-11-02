<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns = "http://www.w3c.org/1999/xhtml" xmlns:jsp = "http://java.sun.com/JSP/Page">
    <body>
        <h2>Entity of type ${entityName} and identifier ${entityId}</h2>

        <table border="1" width="100%">
            <c:forEach var="attribute" items="${entityMetaData.attributes}">
            <tr>
                <th>${attribute.name}</th>
                <c:set var="cellData" value="${entity[attribute.name]}" />
                <td>
                <c:choose>
                <c:when test="${attribute.association}">
                    <c:url value="./displayEntity" var="displayURL">
                        <c:param name="entityName" value="AssociationName" />
                        <c:param name="entityId" value="id" />
                    </c:url>
                    <a href="${displayURL}">${cellData}</a>
                </c:when>
                <c:otherwise>
                    ${cellData}
                </c:otherwise>
                </c:choose>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>