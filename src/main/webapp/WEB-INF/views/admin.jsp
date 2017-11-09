<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
</div>

<table class="tg">
    <tbody>
    <tr>
        <th width="80">id</th>
        <th width="120">username</th>
        <th width="120">registered</th>
        <th width="120">caloriesPerDay</th>
    </tr>
    <c:forEach items="${users}" var="item">
    <tr>
        <td><c:out value="${item.id}"/></td>
        <td><c:out value="${item.username}"/></td>
        <td><c:out value="${item.registered}"/></td>
        <td><c:out value="${item.caloriesPerDay}"/></td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteUser?id=${item.id}">Delete User</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>