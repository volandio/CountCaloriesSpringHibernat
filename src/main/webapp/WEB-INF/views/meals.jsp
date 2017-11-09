<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
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

<form method="post" action="/editUserCalories">
    <label>Максимальное количество калорий в день = </label>
    <input type="number" name="calories" value="${user.caloriesPerDay}"/>
    <button>Изменить</button>
</form>

<a href="${pageContext.servletContext.contextPath}/createMeal">Create Meal</a>
<table class="tg">
    <tbody>
    <tr>
        <th width="80">dateTime</th>
        <th width="120">description</th>
        <th width="120">calories</th>
    </tr>
    <c:forEach items="${meals}" var="item">
    <tr>
        <c:choose>
            <c:when test="${item.exceed}">
                <td style="color:red"><c:out value="${item.dateTime}"/></td>
                <td style="color:red"><c:out value="${item.description}"/></td>
                <td style="color:red"><c:out value="${item.calories}"/></td>
            </c:when>
            <c:otherwise>
                <td><c:out value="${item.dateTime}"/></td>
                <td><c:out value="${item.description}"/></td>
                <td><c:out value="${item.calories}"/></td>
            </c:otherwise>
        </c:choose>
        <td><a href="${pageContext.servletContext.contextPath}/editMeal?id=${item.id}">Edit Meal</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteMeal?id=${item.id}">Delete Meal</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
