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
                <th>Id</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <c:set var="currentUsername" scope="session" value="${username}"/>
                    <c:set var="displayedUsername" value="${user.username}"/>
                    <td>
                        <c:if test="${displayedUsername!= currentUsername}">
                            <div style="width: 70px">
                                <form method="post"
                                      <%--action="${pageContext.request.contextPath}/friendrequest?requestUsername=<c:out value="${currentUsername}"/>&approveUsername=<c:out value="${displayedUsername}"/>">--%>
                                      action="${pageContext.request.contextPath}/friend_request/create">
                                    <input type="hidden" value="${username}" name="requestUsername" />
                                    <input type="hidden" value="${displayedUsername}" name="approveUsername" />
                                    <input type="submit" value="Add to friends">
                                </form>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
