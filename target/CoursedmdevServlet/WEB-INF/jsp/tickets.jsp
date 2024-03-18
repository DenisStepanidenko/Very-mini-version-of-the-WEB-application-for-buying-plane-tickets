<%@ page import="service.TicketService" %>
<%@ page import="dto.TicketDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 13.03.2024
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Купленные билеты</h1>
<ul>
    <c:forEach var = "ticket" items="${requestScope.tickets}">
        <li>${fn:toLowerCase(ticket.seatNo)}</li>
    </c:forEach>
</ul>
</body>
</html>

