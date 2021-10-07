<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients</title>
</head>
<body>

<h2>Список всех клиентов</h2>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Father Name</th>
        <th>Citizenship</th>
        <th>Birthday</th>
        <th>Passport code</th>
        <th>Phone Number</th>
    </tr>
    <c:forEach var="client" items="${requestScope.allClients}">
        <tr>
            <td>${client.detail.firstName}</td>
            <td>${client.detail.lastName}</td>
            <td>${client.detail.fatherName}</td>
            <td>${client.detail.citizenship}</td>
            <td>${client.detail.birthday}</td>
            <td>${client.detail.passportCode}</td>
            <td>${client.detail.phoneNumber}</td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
