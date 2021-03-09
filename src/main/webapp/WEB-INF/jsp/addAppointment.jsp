<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Добавить запись</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    </head>
    <body>
        <h3>Добавить запись</h3>
        <p style="color: red;">${error}</p>
        <form:form method="POST"  modelAttribute="appointment">
            <table>
                <tr>
                    <td>Фамилия</td>
                    <td><form:input type="text" path="lastName" placeholder="Фамилия" autofocus="true"/></td>
                </tr>
                <tr>
                    <td>Пол</td>
                    <td>
                        <form:select path="gender">
                            <form:option value="M" label="Мужчина"/>
                            <form:option value="F" label="Женщина"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>Дата рождения</td>
                    <td><form:input type="date" cssClass="control" path="birthDate" placeholder="Дата рождения" autofocus="true"/></td>
                </tr>
                <tr>
                    <td>Дата поступления</td>
                    <td><form:input type="date" cssClass="control" path="receiptDate" placeholder="Дата поступления" autofocus="true"/></td>
                </tr>
                <tr>
                    <td>Начальный диагноз</td>
                    <td><form:input type="text" path="diagnosis" placeholder="Начальный диагноз" autofocus="true"/></td>
                </tr>
                <tr>
                    <td>Палата</td>
                    <td>
                        <form:select path="hospitalWard">
                            <c:forEach var="hospital" items="${hospitalList}">
                                <form:option value="${hospital}" label="${hospital}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Добавить"/>
                        <a href="index">Cancel</a>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>