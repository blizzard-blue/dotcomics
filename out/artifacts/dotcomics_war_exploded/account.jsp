<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>

<html>
<head>
    <title>dotComics | MyAccount</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
    <link href="/css/account.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <jsp:useBean id="author" class="models.UserAcct" scope="session">
        <jsp:setProperty name="author" property="*"/>
    </jsp:useBean>

    <header class="intro-header" id="nameContainer">
        <div class="container">
            <div class="row" >
                <c:choose>
                    <c:when test="${author.profileImg != null}">
                        <div id="profilePicture" style="background-image:url('${author.profileImg}')"></div>
                    </c:when>
                    <c:otherwise>
                        <div id="profilePicture" style="background-image:url('img/user/default.jpg')"></div>
                    </c:otherwise>
                </c:choose>
                <div class="site-heading">
                    <h2 id="name">${author.username}</h2>
                </div>
            </div>
            <div id="subscribe-container">
                <button id="Subscribe" class="btn btn-primary">Subscribe</button>
            </div>
        </div>
    </header>

    <div id="bioContainer">
    <c:choose>
        <c:when test="${user.username == author.username}">
            <button id="Edit" class="btn btn-primary">Edit</button>
        </c:when>
    </c:choose>
        <button id="submit" class="btn btn-primary" style="display: none">Submit</button>
        <div class="container">
            <form method="post" id="imgSubmitForm" enctype="multipart/form-data" action="<%= blobstoreService.createUploadUrl("/updateprofpic") %>" style="display: none;">
                <input type="file" class="form-control-file" id="profilePic" name="profilePic" accept="image/*">
                <input type="submit" value="Submit">
            </form>
            <div class="row" id="bioRow">
                <p id="bioText">${author.about}</p>
            </div>
        </div>
    </div>

    <div class="container" id="published-comics">
        <h3>Published Comics</h3>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
    <script>
        var newHTML = document.getElementById("bioText").textContent;

        $( document ).ready(function() {
            console.log(getQueryVariable("author"));
            $.ajax({
                url : "/getwork?author=" + getQueryVariable("author"),
                dataType : 'json',
                error : function() {
                    console.log("Error Occured");
                },
                success : function(data) {
                    console.log(data.author_series);
                    var series = data.author_series;
                    for(var i=0; i<series.length; i++){
                        if(i % 4 == 0) {
                            $("#published-comics").append("<div class=\"row\">");
                        }
                        if(${user.username == author.username}){
                            $("#published-comics").append("<div class=\"col-md-3 user-published-item\"><a href=\"/comic?series=" + series[i].title + "\"><img src=\"" + series[i].cover_img + "\" class=\"img-responsive\"><button class=\"btn-success\">Edit</button></a><a href=\"#\" onclick=\"deleteSeries(this);\" value=\"" + series[i].title + "\"><button class=\"btn-danger\">Delete</button></a></div>");
                        }
                        else{
                            $("#published-comics").append("<div class=\"col-md-3 user-published-item\"><a href=\"/comic?series=" + series[i].title + "\"><img src=\"" + series[i].cover_img + "\" class=\"img-responsive\"></a></div>");
                        }
                        if(i % 4 == 3){
                            $("#published-comics").append("</div>");
                        }

                    }

                }
            });
        });

        $('#Edit').click(function(){

            $(this).hide();
            $('#imgSubmitForm').show();
            $('#submit').show();

            $('#bioRow').html("<textarea id=\"newBio\" class=\"form-control\" onchange=\"biochange()\" rows=\"4\" >" + newHTML + "</textarea>");
        });

        function biochange(){
            newHTML = $("#newBio").val();
        }

        $('#submit').click(function(){
            $(this).hide();
            $('#imgSubmitForm').hide();
            $('#Edit').show();
//
            $.ajax({
                url : "/updatebio?bio=" + newHTML,
                error : function(){
                    console.log("Update bio error occurred.");
                }
            });
            $('#bioRow').html("<p id=\"bioText\">" + newHTML + "</p>");
        });
        var author = document.getElementById("#name");
        $('#Subscribe').click(function(){

            if($('#Subscribe').hasClass("btn btn-primary")){
                $('#Subscribe').removeClass("btn btn-primary").addClass("btn btn-success");
            }
            else{
                $('#Subscribe').removeClass("btn btn-success").addClass("btn btn-primary");
            }

            $.ajax({
                url : "/subscribe?author=" + author,
                dataType : 'json',
                error : function() {
                    console.log("Error Occured");
                },
                success: function(data){
                    console.log(data.newsubscription);
                    $("#subs > ul").append("<li><a href=\"" + data.newsubscription.path + "\"> " + data.newsubscription.authorName +"</a></li>")
            }
            });
        });

        function deleteSeries(button){
            var seriestitle = button.getAttribute("value");
            $.ajax({
                url : "/deletecomic?seriestitle=" + seriestitle,
                error : function() {
                    console.log("Error Occured");
                },
                success : function(){
                    location.reload();
                }
            });
        }
    </script>
</body>
</html>
