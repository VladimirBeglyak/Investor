<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Register</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/register" method="post">

        <label for="firstNameId"><fmt:message key="page.register.first-name"/>
            <input type="text" id="firstNameId" name="firstName">
        </label>
        <br>
        <br>

        <label for="lastNameId"><fmt:message key="page.register.last-name"/>
            <input type="text" id="lastNameId" name="lastName">
        </label>
        <br>
        <br>

        <label for="fatherNameId"><fmt:message key="page.register.father-name"/>
            <input type="text" id="fatherNameId" name="fatherName">
        </label>
        <br>
        <br>

        <label for="citizenshipId"><fmt:message key="page.register.citizenship"/>
            <input type="text" id="citizenshipId" name="citizenship">
        </label>
        <br>
        <br>

        <label for="passportId"><fmt:message key="page.register.passport-code"/>
            <input type="text" id="passportId" name="passportCode">
        </label>
        <br>
        <br>

        <label for="birthdayId"><fmt:message key="page.register.birthdate"/>
            <input type="date" id="birthdayId" name="birthday">
        </label>
        <br>
        <br>


        <label for="emailId"><fmt:message key="page.register.email"/>
            <input type="text" id="emailId" name="email">
        </label>
        <br>
        <br>

        <label for="passwordId"><fmt:message key="page.register.password"/>
            <input type="password" id="passwordId" name="password">
        </label>
        <br>
        <br>

        <label for="phoneNumberId"><fmt:message key="page.register.phone-number"/>
            <input type="text" id="phoneNumberId" name="phoneNumber">
        </label>
        <br>
        <br>

<%--        <label for="imageId">Passport:--%>
<%--            <input type="file" id="imageId" name="image">--%>
<%--        </label>--%>
<%--        <br>--%>
<%--        <br>--%>


        <button type="submit"><fmt:message key="page.login.button.register"/></button>

        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${errors.message}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>

    </form>


</div>
</body>
</html>
