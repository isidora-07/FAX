<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <link href="CSS/bootstrap-5.1.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="CSS/bootstrap-5.1.1-dist/js/bootstrap.min.js"></script>
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>
<%
    if (request.getParameter("fail") != null) {
        out.println("<p>Wrong username or password</p>");
    }
%>


<div id="login">
    <h3 class="text-center  pt-5">Login form</h3>

    <div class="container">

        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">

                    <a class="text-center   btn btn-danger" href="help.jsp">Help page</a>
                    <form method="post" action="ActionHandlers/LoginHandler.jsp">
                        <div class="form-group">
                            <label for="username" class="text-info">Email:</label><br>
                            <input type="text" name="email" id="username" class="form-control">
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <br>
                        <button class="btn btn-info">Sign in</button>
                        </br><label>Dont have account? <a href="register.jsp">Create one</a></label>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>