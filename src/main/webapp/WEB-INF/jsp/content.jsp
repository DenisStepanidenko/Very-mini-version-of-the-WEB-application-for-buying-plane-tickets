<%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 13.03.2024
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<div>
    <span>Content. Русский</span>
    <p>Size of List: ${requestScope.flights.size()}</p>
    <p>First object in Map: ${sessionScope.flightMap.get(1)}</p>
    <p>JSESSION id : ${cookie.get("JSESSIONID")}</p>
    <p>Header : ${header.get("Cookie")}</p>
    <p>Param id: ${param.id}</p>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
