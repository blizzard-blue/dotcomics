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
    <link href="/css/signup.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <div id="signupformbox">
        <h1 id="signupHeading">Sign up!</h1>
        <br>
        <form>

        <fieldset class="form-group">
            <label for="displayName">Display Name:</label>
            <input id="nameBox" class="form-control" id="displayName" placeholder="Name">
        </fieldset>


        <fieldset class="form-group">
            <label for="bio">Tell us about yourself:</label>
            <textarea class="form-control" id="bio" rows="3" placeholder="Your Bio"></textarea>
        </fieldset>

        <fieldset class="form-group">
            <label for="exampleInputFile">Upload a profile picture:</label>
            <input type="file" class="form-control-file" id="exampleInputFile">
            <small class="text-muted">This will be displayed on your author page.</small>
        </fieldset>

        <div id="buttonDiv">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="cancel" class="btn btn-primary">Cancel</button>
        </div>
        </form>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
</body>
</html>
