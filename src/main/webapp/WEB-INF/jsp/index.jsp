<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/index-style.scss"%>
    </style>
</head>
<%@include file="header.jsp" %>

<body>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang !=null ? param.lang:'en_RU')}"/>
<fmt:setBundle basename="translations"/>

<div class="container">

        <section class="first row gy-3">
            <div class="col">
                <div class="block">

                    <ul><h1>Инвестируйте в ценные бумаги</h1>
                        <li>Открытие брокерского счета за 5 минут без визита в офис</li>
                        <li>Доступ к ценным бумагам с любого устройства</li>
                        <li>Поддержка в чате 24/7</li>
                    </ul>

                    <a href="/register"><button type="button" class="btn btn-outline-warning"><fmt:message key="page.index.button.create-count"/></button></a>
                </div>
            </div>
        </section>

    <section class="second row gy-3">
        <div class="col">
            <div class="block">
                <span>Миллионы клиентов уже инвестируют с нами</span>
            </div>
        </div>
    </section>

    <section class="third row">
        <div class="col row">

            <div class="col-6">
                <div class="block">Лучший инвестиционный сервис в мире</div>
            </div>
            <div class="col-6">
                <div class="block">Обслуживание от 0 ₽ в месяц</div>
            </div>
        </div>
    </section>

    <section class="fourth row">
        <div class="col row">

            <div class="col-6">
                <div class="block">Мгновенный вывод средств на карту</div>
            </div>
            <div class="col-6">
                <div class="block">Более 11 000 ценных бумаг</div>
            </div>
        </div>
    </section>
</div>











<br>
<br>
<br>
<br>

<div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Тикер</th>
            <th scope="col">Цена</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stock" items="${requestScope.storage_stocks}">
            <tr>
                <td>${stock.name}</td>
                <td>${stock.ticker}</td>
                <td>${stock.costOneStock}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>

<%@include file="footer.jsp" %>

</html>
