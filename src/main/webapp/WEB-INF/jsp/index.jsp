<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.joda.time.Days" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="resources/css/css.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="resources/js/scripts.js"></script>
    <meta charset="UTF-8">
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
    <header class="h">
        <h1 align="center">Приемное отделение</h1>
        <sec:authorize access="isAuthenticated()">
            <div class="sign-in-button">
                <a href="${pageContext.request.contextPath}/logout" class="link-button">Выйти</a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated() && hasRole('ADMIN')">
            <div class="sign-in-button">
                <a href="${pageContext.request.contextPath}/registration" class="link-button">Добавить админа</a>
            </div>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <div class="sign-up-button">
                <a href="${pageContext.request.contextPath}/registration" class="link-button">Регистрация</a>
            </div>
            <div class="sign-in-button">
                <a href="${pageContext.request.contextPath}/login" class="link-button">Войти</a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            Hello <b><sec:authentication property="principal.username"/></b>
        </sec:authorize>
    </header>
    <nav id=global class="n">
        <ul>
            <li><a href="${pageContext.request.contextPath}/index"><span>Главная</span></a></li>
            <sec:authorize access="isAuthenticated() && hasRole('ADMIN')">
                <li><a href="/hospitalList"><span>Список палат</span></a></li>
            </sec:authorize>
        </ul>
    </nav>
</div>
<div class="main">
    <sec:authorize access="isAuthenticated()">
        <h3>Список записей</h3>
        <sec:authorize access="hasRole('USER')">
            <form:form method="POST" action="/search" modelAttribute="search">
                <div><form:input type="text"  path="family" placeholder="Фамилия" autofocus="true"/></div>
                <div><form:input type="text" path="diagnos" placeholder="Диагноз" autofocus="true"/></div>
                <input type="submit" value="Submit"/>
            </form:form>
        </sec:authorize>
        <p style="color: red;">${errorString}</p>
        <table border="1" id="listRoute">
            <th>Фамилия</th>
            <th>Пол</th>
            <th>Дата рождения</th>
            <th>Дата поступления</th>
            <th>Начальный диагноз</th>
            <th>Палата</th>
            <th>Количество дней</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th>Изменить</th>
                <th>Удалить</th>
            </sec:authorize>
            <c:forEach items="${appointmentList}" var="appointment">
                <tr>
                    <td>${appointment.lastName}</td>
                    <td>${appointment.gender}</td>
                    <td>${appointment.birthDate}</td>
                    <td>${appointment.receiptDate}</td>
                    <td>${appointment.diagnosis}</td>
                    <td>${appointment.hospitalWard}</td>
                    <td>${appointment.countDay}</td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td>
                            <a href="editAppointment?id=${appointment.id}">Edit</a>
                        </td>
                        <td>
                            <a href="deleteAppointment?id=${appointment.id}">Delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="addAppointment">Добавить запись</a>
        </sec:authorize>
    </sec:authorize>
</div>
</body>
</html>