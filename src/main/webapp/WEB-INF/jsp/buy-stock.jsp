<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/css/private-cabinet-style.scss"%>
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp" %>

<div class="container">

    <div class="form col row">
        <div class="block">FORM</div>
    </div>

    <div class="form col row">
        <div class="block">FORM</div>
    </div>

</div>

<form action="${pageContext.request.contextPath}/buy" method="post">
    <label for="countId">Введи количество для покупки:
        <input type="text" id="countId" name="count">
    </label>
    <button type="submit" class="btn-danger">Подтвердить</button>
</form>

<%@include file="footer.jsp" %>

</body>
</html>
