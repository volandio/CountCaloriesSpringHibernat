<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditMeal</title>
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

<form action="${pageContext.servletContext.contextPath}/editMeal" method="POST">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td align="right">Время приема еды :</td>
            <td>
                <input type="datetime-local" name="dateTime" value="${meal.dateTime}">
            </td>
        </tr>
        <tr>
            <td align="right">Наименование приема еды :</td>
            <td>
                <input type="text" name="description" value="${meal.description}">
            </td>
        </tr>
        <tr>
            <td align="right">Количество калорий в еде :</td>
            <td>
                <input type="number" name="calories" value="${meal.calories}">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Изменить"/></td>
        </tr>
    </table>
</form>
</body>
</html>
