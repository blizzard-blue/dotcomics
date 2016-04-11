<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 4/2/2016
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dotComics | Create Account</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/scroll.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <div id="signupformbox">
        <h1>Sign up!</h1>
        <form action="/signup">
            Enter desired username: <input type="text" name="desired-un"value="">
            <input type="submit" value="Join"/>
        </form>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
</body>
</html>
