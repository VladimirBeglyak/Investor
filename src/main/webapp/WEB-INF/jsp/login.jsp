<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
    <style>
        <%@include file="/WEB-INF/css/private-cabinet-style.scss"%>
    </style>
</head>
<body>

<%@include file="header.jsp" %>


<%--<div class="mb-3 row">--%>
<%--    <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>--%>
<%--    <div class="col-sm-10">--%>
<%--        <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="email@example.com">--%>
<%--    </div>--%>
<%--</div>--%>
<h2>Please, enter your data for private cabinet</h2>

<br>

<div class="container">

    <form action="${pageContext.request.contextPath}/login" method="post">

        <div class="email col row">
            <label for="emailId">Email</label>
            <div class="col">
                <input type="text" id="emailId" name="email" value="${param.email}" placeholder="Enter your email">
            </div>
        </div>

        <div class="password col row">
            <label for="passwordId">
                <fmt:message key="page.login.password"/>
                <div class="col">
                    <input type="password" id="passwordId" name="password" placeholder="Enter your password">
                </div>
        </div>


        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-outline-success"><fmt:message key="page.login.button.login"/></button>
            <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/register'"><fmt:message key="page.login.button.register"/></button>
        </div>

        <div class="valid row">
            <c:if test="${param.error!=null}">
                <div style="color: red">
                    <span>Email or password is not correct</span>
                </div>
            </c:if>
        </div>

</div>
</form>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
