<%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 26.03.2024
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
    <style>
        <%@ include file="/WEB-INF/css/deleteTicket.css" %>
    </style>
</head>
<body>
<h1 class="purples">Ваш билет</h1>
<img src="logotip/ROSSEJL_LOGO_ITOG.png" alt="" class="imageLogo">
<div class="t1">
    <span class="t2">Дата отправления : ${requestScope.flightDto.getDepartureDate()}</span>
    <br/>
    <br/>
    <span class="t2">Город отправления : ${requestScope.flightDto.getDepartureAirportCode()}</span>
    <br/>
    <br/>
    <span class="t2">Дата прибытия : ${requestScope.flightDto.getArrivalDate()}</span>
    <br/>
    <br/>
    <span class="t2">Город прибытия : ${requestScope.flightDto.getArrivalAirportCode()}</span>
    <br/>
    <br/>
    <span class="t2">Модель самолёта : ${requestScope.flightDto.getModelOfAircraft()}</span>
    <br/>
    <br/>
    <span class="t2">Место : ${requestScope.seatNo}</span>
</div>

<form action="${pageContext.request.contextPath}/deleteTicket?flightId=${requestScope.flightDto.getId()}&seatNo=${requestScope.seatNo}"
      method="post">
    <button type="submit" class="t10">Сдать билет</button>
</form>
<a href="${pageContext.request.contextPath}/personalProfile" class="t5"> Вернуться в личный кабинет </a>

</body>
</html>
