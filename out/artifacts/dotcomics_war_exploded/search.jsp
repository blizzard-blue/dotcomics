<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 4/5/2016
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <h1>Search</h1>

    <form action="/search" method="POST">
        <select id="sortby" name="sortby">
            <option>Title A-Z</option>
            <option>Title Z-A</option>
            <option>Date (Most Recent)</option>
            <option>Date (Least Recent)</option>
        </select>

        <label for="superhero">Superhero</label>
        <input type="checkbox"  name="genres" value="Superhero" id="superhero"  />
        <label for="action">Action</label>
        <input type="checkbox"  name="genres" value="Action" id="action"  />
        <label for="romance">Romance</label>
        <input type="checkbox"  name="genres" value="Romance" id="romance"  />
        <input type="submit" value="Apply">
    </form>

    <div class="container" id="searched-comics">
        <h3>Search Results</h3>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
    <script>

        $.ajax({
            url : "/search",
            dataType : 'json',
            error : function() {
                console.log("Error Occured");
            },
            success : function(data) {
                //console.log(data);
                var sarray = data.series;
                for(var i = 0; i < sarray.length; i++){
                    $("#searched-comics").append("<div class=\"col-md-3 user-published-item\">" +
                            "<a href=\"/comic?series="+ sarray[i].title + "\"><img src=\"" + sarray[i].img + "\" class=\"img-responsive\"></a></div>");
                }
            }
        });
    </script>
</body>
</html>
