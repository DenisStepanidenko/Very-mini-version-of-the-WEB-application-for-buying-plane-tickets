<%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 13.03.2024
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@ include file="/WEB-INF/css/registration.css"%>
    </style>
</head>
<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>

<%--Форма для регистрации--%>
<div class='container'>
    <div class='window'>
        <div class='overlay'></div>
        <div class='content'>
            <div class='welcome'>Регистрация</div>

            <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
                <div class='input-fields'>

                    <label for="name"> Имя </label>
                    <input type="text" name="name" id="name" class='input-line full-width' required>

                    <label for="date"> Дата рождения </label>
                    <input type="date" name="birthday" id="date" class='input-line full-width' required>

                    <label for="email"> Почта </label>
                    <input type="text" name="email" id="email" class='input-line full-width' required>


                    <label for="password">Пароль
                        <input type='password' class='input-line full-width' name="password" id="password" required>
                    </label>

                    <label for="image"> Выберите аватарку </label>
                    <input type="file" name="image" id="image" class='input-line full-width' required>

                    <c:forEach var="gender" items="${requestScope.gender}">
                        <label>
                            <input type="radio" name="gender" value="${gender}" required>
                        </label> ${gender}

                    </c:forEach>

                </div>
                <div class="buttonReg">
                    <button class='ghost-round full-width' type="submit">Создать аккаунт</button>
                </div>

                <c:if test="${not empty requestScope.errors}">
                    <div style="color: red">
                        <c:forEach var="error" items="${requestScope.errors}">
                            <span>${error.message}</span>
                        </c:forEach>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>


<%--<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">--%>
<%--    <label for="name"> Name </label>--%>
<%--    <input type="text" name="name" id="name">--%>
<%--    <br/>--%>
<%--    <label for="date"> Date </label>--%>
<%--    <input type="date" name="birthday" id="date">--%>
<%--    <br/>--%>
<%--    <label for="email"> Email </label>--%>
<%--    <input type="text" name="email" id="email">--%>
<%--    <br/>--%>
<%--    <label for="password"> Password </label>--%>
<%--    <input type="password" name="password" id="password">--%>
<%--    <br/>--%>
<%--    <label for="image"> Image </label>--%>
<%--    <input type="file" name="image" id="image">--%>
<%--    <br/>--%>
<%--    <select name = "role" id="role">--%>
<%--        <c:forEach var="role"  items="${requestScope.role}">--%>
<%--            <option value="${role}">${role}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <br/>--%>

<%--    <button type="submit">Send</button>--%>

    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>
<%--</form>--%>


</body>
</html>
