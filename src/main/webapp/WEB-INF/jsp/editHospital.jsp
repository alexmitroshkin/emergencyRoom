<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Редактировать палату</title>
    </head>
    <body>
        <h3>Редактировать палату</h3>
        <p style="color: red;">${error}</p>

        <form:form method="POST"  modelAttribute="hospitalWard">
            <table>
                <tr>
                    <td>Этаж</td>
                    <td><form:input type="text" path="floor" placeholder="Этаж" autofocus="true"/></td>
                </tr>
                <tr>
                    <td>Номер</td>
                    <td><form:input type="text" path="number" placeholder="Номер" autofocus="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit"/>
                        <a href="index">Cancel</a>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>