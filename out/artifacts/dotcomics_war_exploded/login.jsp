<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 3/16/2016
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />
    <h1>Login</h1>

    <form action="/login" method="post">
        <p>Username: <input type="text" name="username" placeholder="Username"></p>
        <p>Password: <input type="password" name="password" placeholder="password"></p>
        <input type="submit" value="Submit">
    </form>

    <jsp:directive.include file="/jslibs.jsp" />
</body>
</html>
