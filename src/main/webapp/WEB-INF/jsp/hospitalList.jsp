<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Список палат</title>
    </head>
    <body>
        <h3>Список палат</h3>
        <p style="color: red;">${error}</p>
        <table border="1"  id="listHospital"> 
            <th>Этаж</th>
            <th>Номер</th>
            <th>Изменить</th>
            <th>Удалить</th>
                <c:forEach items="${hospitalList}" var="hospital" >
                <tr>
                    <td>${hospital.floor}</td>
                    <td>${hospital.number}</td>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication var="loggedUser" property="principal" />
                        <c:if test="${(loggedUser.username eq rating.user.username) or (loggedUser.role.name eq 'ROLE_ADMIN') }">
                            <td>
                                <a href="editHospital?id=${hospital.id}">Edit</a>
                            </td>

                            <td>
                                <a href="deleteHospital?id=${hospital.id}">Delete</a>
                            </td>
                        </c:if>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <a href="/addHospital"><span>Добавить палату</span></a>
        <br/>
        <a href="index"><span>Отмена</span></a>
    </body>
</html>
