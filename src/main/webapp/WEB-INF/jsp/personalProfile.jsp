<%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 19.03.2024
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>PersonalProfile</title>
    <style>
        <%@ include file="/WEB-INF/css/personalProfile.css"%>
    </style>
</head>
<body>
<img src="${pageContext.request.contextPath}/images?pathToPhoto=${requestScope.pathToPhoto}"
     alt="Ошибка при загрузке картинки" class="profilePhoto">
<img src="logotip/ROSSEJL_LOGO_ITOG.png" alt="" class="imageLogo">
<h1 class="purples">Приветствуем Вас, ${sessionScope.user.getName()}</h1>

<h1 class="profileInfoName"> Имя : ${sessionScope.user.getName()}</h1>
<h1 class="profileInfoEmail">Почта : ${sessionScope.user.getEmail()}</h1>
<h1 class="profileInfoAge">Дата рождения : ${sessionScope.user.getBirthday()}</h1>

<c:if test="${not empty requestScope.allTickets}">
    <h1 class="t1">Ваши билеты</h1>
    <div class="t2">
        <c:forEach var="ticket" items="${requestScope.allTickets}">
            <a class="t3" href="${pageContext.request.contextPath}/deleteTicket?flightId=${ticket.getFlightId()}&seatNo=${ticket.getSeatNo()}">Место : ${ticket.getSeatNo()}, Рейс : ${ticket.getDescription()}</a>
            <br/>
        </c:forEach>
    </div>
</c:if>


<c:if test="${empty requestScope.allTickets}">
    <h1 class="t1">У вас нет билетов</h1>
    <div class="t2">
        <br/>
        <br/>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit" class="t4">Выйти из аккаунта</button>
</form>



<a href="${pageContext.request.contextPath}/flights" class="t5"> Купить билеты </a>


</body>
</html>
