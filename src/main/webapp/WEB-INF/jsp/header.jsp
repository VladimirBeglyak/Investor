<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<%--    <style>--%>
<%--        <%@include file="/WEB-INF/css/index-style.scss"%>--%>
<%--    </style>--%>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang !=null ? param.lang:'en_RU')}"/>
<fmt:setBundle basename="translations"/>

<nav class="navbar sticky-top navbar-dark" style="background-color: #e3f2fd;">

    <a href="${pageContext.request.contextPath}/index">
        <button type="button" class="btn btn-outline-dark btn-sm"><fmt:message key="menu.main"/></button>
    </a>

    <button type="button" class="btn btn-outline-dark btn-sm" onclick="window.location.href='/stocks'"><fmt:message key="menu.catalog"/></button>

    <button type="button" class="btn btn-outline-dark btn-sm"><fmt:message key="menu.recommendations"/></button>

    <button type="button" class="btn btn-outline-dark btn-sm" onclick="window.location.href='/news'"><fmt:message key="menu.news"/></button>

    <button type="button" class="btn btn-outline-dark btn-sm"><fmt:message key="menu.tarifs"/></button>

    <button type="button" class="btn btn-outline-dark btn-sm"><fmt:message key="menu.contacts"/></button>


    <button type="button" class="btn btn-outline-dark btn-sm" onclick="window.location.href='/login'"><fmt:message key="menu.private-cabinet"/></button>

    <div id="logout">
        <c:if test="${not empty sessionScope.client}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-outline-dark btn-sm"><fmt:message key="menu.logout"/></button>
            </form>
        </c:if>
    </div>


    <div id="locale">
        <form action="${pageContext.request.contextPath}/locale" method="post">
            <button type="submit" name="lang" value="ru_RU" class="btn btn-outline-dark btn-sm">RU</button>
            <button type="submit" name="lang" value="en_RU" class="btn btn-outline-dark btn-sm">EN</button>
        </form>
    </div>

</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

</body>
</html>