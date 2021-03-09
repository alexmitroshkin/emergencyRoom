<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Регистрация</title>
    </head>
    <body>
        <div>
            <form:form method="POST" modelAttribute="user">
                <h2>Регистрация</h2>
                <div>
                    <form:input type="text" path="username" placeholder="Имя" autofocus="true"/>
                </div>
                <div>
                    <form:input type="password" path="password" placeholder="Пароль"/>
                </div>
                <div>
                    <form:select path="gender">
                        <form:option value="M" label="Мужчина"/>
                        <form:option value="F" label="Женщина"/>
                    </form:select>
                </div>
                <sec:authorize access="isAuthenticated() && hasRole('ADMIN')">
                    <div>
                        <form:select path="role">
                            <form:options items="${roles}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>
                </sec:authorize>
                <button type="submit">Зарегистрироваться</button>
            </form:form>
            <a href="/">Cancel</a>
        </div>
    </body>
</html>
