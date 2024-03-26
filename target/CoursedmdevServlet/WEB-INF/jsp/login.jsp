<%--
  Created by IntelliJ IDEA.
  User: Дэнич
  Date: 15.03.2024
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login page</title>
    <%--Подключение стилей--%>
    <style>
        <%@ include file="/WEB-INF/css/login.css"%>
    </style>
</head>
<body class="body">

<%-- Форма для входа по логину и паролю --%>
<div class='container'>
    <div class='window'>
        <div class='overlay'></div>
        <div class='content'>
            <div class='welcome'>Добро пожаловать!</div>
            <div class='subtitle'>Для начала работы с сервисом зайдите в свой аккаунт, или создайте новый.</div>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class='input-fields'>

                    <label>Почта
                        <input type='text' class='input-line full-width' name="email" id="email" required>
                    </label>
                    <label>Пароль
                        <input type='password' class='input-line full-width' name="password" id="password" required>
                    </label>

                </div>
                <div>
                    <button class='ghost-round full-width' type="submit">Войти</button>
                </div>
                <div class="errorLogin">
                    <c:if test="${param.error != null}">
                        <div style="color: red">
                            <span>Пароль или почта были введёны неверно</span>
                        </div>
                    </c:if>
                </div>

                <div class="registrationHref">
                    <a href="${pageContext.request.contextPath}/registration" class="registrationHref">
                        Зарегистрироваться
                    </a>
                </div>


            </form>
        </div>
    </div>
</div>


</body>
</html>