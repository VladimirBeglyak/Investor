<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add News To Stock</title>
</head>
<body>


<form action="${pageContext.request.contextPath}/add-news" method="post">

    <label for="newsId">Text:
        <input type="text" id="newsId">
    </label>
    <br>
    <br>
    <button type="submit" name="addNews">Save News</button>
</form>



</body>
</html>
