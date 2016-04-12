<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 4/2/2016
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dotComics | MyAccount</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <h1>Account Settings</h1>

    <jsp:directive.include file="/jslibs.jsp" />
    <script>
        $( document ).ready(function() {
            $.ajax({
                url : "/userinfo",
                dataType : 'json',
                error : function() {
                    console.log("Error Occured");
                },
                success : function(data) {
//                    var receivedData = [];
//
//                    $.each(data.jsonArray, function(index) {
//                        $.each(data.jsonArray[index], function(key, value) {
//                            var point = [];
//
//                            point.push(key);
//                            point.push(value);
//                            receivedData.push(point);
//
//                        });
//                    });
                    console.log(data);
                }
            });
        });
    </script>
</body>
</html>
