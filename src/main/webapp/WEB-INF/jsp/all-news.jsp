<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Новости эмитентов</h2>

<table>
    <c:forEach var="news" items="${requestScope.news}">
        <tr>
            <p>${news.text}</p>
            <p>${news.date.dayOfMonth}.${news.date.month}.${news.date.year} ${news.date.hour}:${news.date.minute}</p>
            <p>${news.storageStock.name}</p>
        </tr>
    </c:forEach>
</table>

</body>
</html>
