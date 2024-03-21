<%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 13.03.2024
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Перелёты</title>
    <style>
        <%@ include file="/WEB-INF/css/flights.css" %>
    </style>
</head>
<body>
<img src="logotip/ROSSEJL_LOGO_ITOG.png" alt="" class="imageLogo">
<h1 class="t7">Список перелётов</h1>
<span class = "t11">Чтобы посмотреть информацию о рейсе и взять билет, нажмите на интересующий вас полёт</span>
<div class = "t8">
    <ul>
        <c:forEach var="flight" items="${requestScope.flights}">
            <li>
                <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}" class="t9">${flight.description}</a>
            </li>
        </c:forEach>
    </ul>
</div>

<a href="${pageContext.request.contextPath}/personalProfile" class="t10"> Вернуться в личный кабинет </a>
</body>
</html>
