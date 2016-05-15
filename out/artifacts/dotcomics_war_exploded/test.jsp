<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 5/13/2016
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TestUpload</title>
</head>
<body>
    <form action="/index.html" enctype="multipart/form-data" method="get" name="putFile" id="putFile">
        <input type="file" name="image" id="imgfile">
        <input type="submit" onclick='uploadFile(this)' value="Upload Content" />
    </form>
    <script>
        function uploadFile() {
            var file = document.getElementById("imgfile");

            var request = new XMLHttpRequest();
            request.open("POST", "/test", false);
            request.setRequestHeader("Content-Type", "multipart/form-data");
            request.send(file);
        }
    </script>
</body>
</html>
