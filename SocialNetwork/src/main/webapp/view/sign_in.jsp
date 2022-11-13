<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container-fluid d-flex align-items-center justify-content-center" style="height: 70vh">
    <div class="row col-lg-3 col-md-6 col-xl-3">
        <h2 class="mb-3">Sign in</h2>
        <form method="post" action="${pageContext.request.contextPath}/signin">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input class="form-control" id="username" aria-describedby="emailHelp" name="username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="btns">
                <a href="${pageContext.request.contextPath}/signup" class="btn btn-outline-primary">Sign up</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
