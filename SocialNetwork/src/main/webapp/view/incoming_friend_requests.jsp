<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid d-flex align-items-center justify-content-center">
    <div class="row col-xl-7 col-lg-6 col-md-8 col-sm-9 col-12">
        <table class="table-striped table-hover">
            <thead>
            <tr>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="currentUsername" scope="session" value="${username}"/>
            <c:forEach var="incomingFriendRequest" items="${incomingFriendRequests}">
                <tr>
                    <td>
                        <c:set var="displayedUsername" scope="page"
                               value="${incomingFriendRequest.requestUser.username}"/>
                        <c:out value="${displayedUsername}"/>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/friend?friendrequestid=<c:out value="${incomingFriendRequest.id}"/>" method="post">
                            <input type="submit" value="Accept">
                        </form>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/removefriendsrequest?friendrequestid=<c:out value="${incomingFriendRequest.id}"/>"
                        >Decline</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
