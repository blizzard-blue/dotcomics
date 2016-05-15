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
    <link href="/css/search.css" rel="stylesheet">
</head>
<body>

<jsp:directive.include file="/nav.jsp" />



<div class="container" id="searched-comics">
    <h3 id="searchheader">Search Results</h3>

    <div class="panel-group">
        <div class="panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">
                    <a data-toggle="collapse" href="#collapse1">Filter results</a>
                </h2>
            </div>
            <div id="collapse1" class="panel-collapse collapse">
                <form action="/search" method="POST">

                    <div id="sortbydropdown">
                        <label for="sortby">Sort by:</label>
                        <select id="sortby" name="sortby">
                            <option value="AZ" ${param.sortby == 'AZ' ? 'selected' : ''}>Title A-Z</option>
                            <option value="ZA" ${param.sortby == 'ZA' ? 'selected' : ''}>Title Z-A</option>
                            <option value="MostRecent" ${param.sortby == 'MostRecent' ? 'selected' : ''}>Date (Most Recent)</option>
                            <option value="LeastRecent" ${param.sortby == 'LeastRecent' ? 'selected' : ''}>Date (Least Recent)</option>
                        </select>
                    </div>
                    <div id="genreBoxes">
                        <ul id="firstColumn">
                            <li>
                                <label for="superhero">Superhero</label>
                                <input type="checkbox"  name="genres" value="Superhero" id="superhero"  />
                            </li>
                            <li>
                                <label for="action">Action</label>
                                <input type="checkbox"  name="genres" value="Action" id="action"  />
                            </li>
                            <li>
                                <label for="romance">Romance</label>
                                <input type="checkbox"  name="genres" value="Romance" id="romance"  />
                            </li>
                        </ul>
                        <ul id="secondColumn">
                            <li>
                                <label for="comedy">Comedy</label>
                                <input type="checkbox"  name="genres" value="Superhero" id="comedy"  />
                            </li>
                            <li>
                                <label for="sci-fi">Sci-Fi</label>
                                <input type="checkbox"  name="genres" value="Action" id="sci-fi"  />
                            </li>
                            <li>
                                <label for="horror">Horror</label>
                                <input type="checkbox"  name="genres" value="Romance" id="horror"  />
                            </li>
                        </ul>
                    </div>
                    <input name="search" type="text" value="${search}"/>
                    <input id="applyfilter" type="submit" value="Apply">
                </form>
            </div>
        </div>
    </div>
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
            console.log(data);
            var sarray = data.series;
            for(var i = 0; i < sarray.length; i++){
                $("#searched-comics").append("<div class=\"col-md-3 col-sm-4 user-published-item\">" +
                        "<a href=\"/comic?series="+ sarray[i].title + "\"><img src=\"" + sarray[i].img + "\" class=\"img-responsive\"></a></div>");
            }
        }

    });
</script>
</body>
</html>
