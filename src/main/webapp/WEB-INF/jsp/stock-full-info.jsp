<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stock</title>

</head>
<body>

<%@include file="header.jsp" %>

<h1>${requestScope.stock.name}</h1>
<p>Стоимость: ${requestScope.stock.costOneStock}</p>
<p>Тикер: ${requestScope.stock.ticker}</p>
<p>Количество: ${requestScope.stock.count}</p>
<p>Валюта: ${requestScope.stock.currency}</p>
<p>Дивидендная доходность: ${requestScope.stock.dividend}</p>

<c:if test="${sessionScope.client.role=='USER'}">
    <p><button type="button" class="btn btn-success" onclick="window.location.href='/buy'">Buy</button></p>
    <p><button type="button" class="btn btn-danger" onclick="window.location.href='/sell'">Sell</button></p>
</c:if>

<c:if test="${sessionScope.client.role=='ADMIN'}">
    <p><button type="button" class="btn btn-danger" onclick="window.location.href='/add-news'">Add news</button></p>
</c:if>

<%--<p><button type="button" class="btn btn-dark" onclick="window.location.href='/login'">Login</button></p>--%>

<%@include file="footer.jsp" %>

</body>
</html>
