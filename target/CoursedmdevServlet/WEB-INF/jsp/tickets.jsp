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
    <title>Информация о рейсе</title>
    <style>
        <%@ include file="/WEB-INF/css/tickets.css"%>
    </style>
</head>
<body>
<h1 class="purples">Информация о рейсе</h1>
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
</div>
<%--<ul>--%>
<%--    <c:forEach var = "ticket" items="${requestScope.tickets}">--%>
<%--        <li>${fn:toLowerCase(ticket.seatNo)}</li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>
<a href="${pageContext.request.contextPath}/flights" class="t3"> Все рейсы </a>

<a href="${pageContext.request.contextPath}/personalProfile" class="t10"> Вернуться в личный кабинет </a>

<c:if test="${not empty requestScope.boughtTickets}">
    <div class="boughtTickets">
        <h1 class="t5">Занятые места</h1>
        <ul>
            <c:forEach var="ticket" items="${requestScope.boughtTickets}">
                <li class="t9">${ticket.getSeatNo()}</li>
                <br/>
            </c:forEach>
        </ul>
    </div>
</c:if>
<c:if test="${empty requestScope.boughtTickets}">
    <div class="boughtTickets">
        <h1 class="t5">Занятые места</h1>
        <br/>
        <span class="t9">Все места свободны</span>
    </div>
</c:if>

<c:if test="${not empty requestScope.freeTickets}">
    <div class="freeTickets">
        <h1 class="t5">Свободные места</h1>
        <ul>
            <c:forEach var="ticket" items="${requestScope.freeTickets}">
                <li class="t9">
                    <a href="${pageContext.request.contextPath}/buyingTicket?flightId=${requestScope.flightDto.getId()}&seatNo=${ticket.getSeatNo()}" class="t9">${ticket.getSeatNo()}</a>
                </li>
                <br/>
            </c:forEach>
        </ul>
    </div>
</c:if>


<c:if test="${empty requestScope.freeTickets}">
    <div class="freeTickets">
        <h1 class="t5">Свободные места</h1>
        <br/>
        <span class="t9">Свободных мест нет</span>
    </div>
</c:if>
</body>
</html>

