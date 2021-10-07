<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stocks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/stocks-table.scss"%>
    </style>

</head>
<body>

<%@include file="header.jsp" %>

<h1>Список акций:</h1>

<div id="table">
    <table class="table">
        <thead>
        <tr>
            <th scope="col" class="sort" data-sort="name">Name</th>
            <th scope="col" class="sort" data-sort="price">Price</th>
            <th scope="col" class="sort" data-sort="currency">Currency</th>
            <th scope="col" class="sort" data-sort="dividend">Dividend</th>
            <th scope="col" class="sort" data-sort="country">Country</th>
        </tr>
        </thead>
        <tbody class="list">
        <c:forEach var="stock" items="${requestScope.stocks}">
        <tr>
            <td><a href="/stock?id=${stock.id}">${stock.name}</a></td>
            <td>${stock.costOneStock}</td>
            <td>${stock.currency.ticker}</td>
            <td>${stock.dividend}</td>
            <td>${stock.country}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div>
    <c:if test="${sessionScope.client.role=='ADMIN'}">
        <button type="button" class="btn btn-outline-success" onclick="window.location.href='/add-stock'">Add Stock</button>
    </c:if>
</div>

<%@include file="footer.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
<script src="/WEB-INF/js/stocks-table.js"></script>

</body>
</html>
