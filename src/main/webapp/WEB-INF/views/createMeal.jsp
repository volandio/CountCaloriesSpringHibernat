<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateUser</title>
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>

    </c:if>

</div>

<form action="${pageContext.servletContext.contextPath}/createMeal" method="POST">
    <table>
        <tr>
            <td align="right">Время приема еды :</td>
            <td>
                <input type="datetime-local" required value="" name="dateTime">
            </td>
        </tr>
        <tr>
            <td align="right">Наименование приема еды :</td>
            <td>
                <input type="text" required value="" name="description">
            </td>
        </tr>
        <tr>
            <td align="right">Количество калорий в еде :</td>
            <td>
                <input type="text" required value="" name="calories">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Создать"/></td>
        </tr>
    </table>
</form>
</body>
</html>