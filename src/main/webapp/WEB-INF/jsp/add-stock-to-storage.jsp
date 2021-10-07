<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Stock</title>
</head>
<body>

<h1>Добавить акцию в базу данных</h1>

<div>
    <form action="${pageContext.request.contextPath}/add-stock" method="post">
        <label for="tickerId">Введи тикер акции:</label>
        <input type="text" name="ticker" id="tickerId">
        <br>
        <br>

        <label for="nameId">Введи имя акции:</label>
        <input type="text" name="name" id="nameId">
        <br>
        <br>

        <label for="countId">Введи количество акций в обращении:</label>
        <input type="text" name="count" id="countId">
        <br>
        <br>

        <label for="costId">Введи цену 1 акции:</label>
        <input type="text" name="costOneStock" id="costId">
        <br>
        <br>

        <label for="countryId">Введи страну эмитента:</label>
        <input type="text" name="country" id="countryId">
        <br>
        <br>

        <label for="dividendId">Введи размер дивиденда акции:</label>
        <input type="text" name="dividend" id="dividendId">
        <br>
        <br>

        <label for="currencyId">Выберите валюту акции:</label>
        <select name="currency" id="currencyId">
            <c:forEach var="currency" items="${requestScope.currency}">
                <option value="${currency.id}">${currency.name}, ${currency.ticker}</option>
            </c:forEach>
        </select>
        <br>
        <br>

        <button type="submit">Добавить</button>

    </form>
</div>

</body>
</html>
