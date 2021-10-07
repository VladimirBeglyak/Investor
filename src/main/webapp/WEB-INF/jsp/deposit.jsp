<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/deposit" method="post">
    <label for="countId">Введи сумму:
        <input type="text" id="countId" name="count">
    </label>
    <br>
    <br>

    <label for="currencyId">Выбери валюту пополнения:

        <select name="currency" id="currencyId">
            <c:forEach var="currency" items="${requestScope.currencies}">
                <option value="${currency.id}">${currency.ticker}</option>
            </c:forEach>
        </select>
    </label>

    <br>
    <br>

    <button type="submit">Пополнить</button>
</form>

</body>
</html>
