<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="styles/sign_in_style.css" rel="stylesheet">
    <title>Hello, world!</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container position-relative" style="height: 100vh; width: 100vh">
    <c:forEach var="message" items="${userDialog}">
        <div class="message">
            <h5><c:out value="${message.sender.username}"/></h5>
            <p><c:out value="${message.messageText}"/></p>
        </div>
    </c:forEach>
    <div class="input-group mb-3 position-absolute bottom-0 start-0">
        <form style="display: inline-flex" action="${pageContext.request.contextPath}/message" method="post">
            <input type="text" class="form-control" placeholder="message"
                   aria-describedby="button-addon2" style="width: 500px" name="message_text">
            <input class="btn btn-outline-secondary" type="submit" id="button-addon2" value="Send"/>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
