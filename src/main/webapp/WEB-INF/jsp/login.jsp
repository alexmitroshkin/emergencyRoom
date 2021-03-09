<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="resources/css/css.css"/>
        <meta charset="utf-8">
        <title>Log in with your account</title>
    </head>
    <body>
        <sec:authorize access="isAuthenticated()">
            <% response.sendRedirect("/"); %>
        </sec:authorize>
        <div>
            <form:form method="POST" action="login" modelAttribute="user">
                <h2>Вход в систему</h2>
                <div>
                    <form:input type="text" path="username" placeholder="Логин" autofocus="true"/>
                    <form:input type="password" path="password" placeholder="Пароль"/>
                    <button type="submit">Войти</button>
                    <h4><a href="/registration">Зарегистрироваться</a></h4>
                    <h4><a href="/">Cancel</a></h4>
                </div>
            </form:form>
        </div>
    </body>
</html>