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
            <!--<form method="post" id="imgSubmitForm" enctype="multipart/form-data" action="/" style="display: none;">
                <input type="file" class="form-control-file" id="profilePic" name="profilePic" accept="image/*">
                <input type="submit" value="Submit">
            </form>-->
            <div class="row" id="bioRow">
                <p id="bioText">${author.about}</p>
            </div>
        </div>
    </div>

    <div class="container" id="published-comics">
        <h3>Published Comics</h3>
        <div class="row">
            <div class="col-md-3 user-published-item"><a href="/comic?series=Deadpool"><img src="series/Deadpool/thumbnail.jpg" class="img-responsive"></a></div>
            <div class="col-md-3 user-published-item"><a href="/comic?series=Guardians%20of%20the%20Galaxy"><img src="series/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a></div>
            <div class="col-md-3 user-published-item"><a href="/comic?series=Judge%20Dredd"><img src="series/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a></div>
            <div class="col-md-3 user-published-item"><a href="/comic?series=The%20Avengers"><img src="series/The%20Avengers/thumbnail.jpg" class="img-responsive"></a></div>
        </div>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
    <script>
        var newHTML = document.getElementById("bioText").textContent;

//        $( document ).ready(function() {
//            $.ajax({
//                url : "/userinfo",
//                dataType : 'json',
//                error : function() {
//                    console.log("Error Occured");
//                },
//                success : function(data) {
//                    console.log(data.userinfo);
//                    $("#name").html(data.userinfo.username);
//                    $("#profilePicture").html("<img src=\"" + data.userinfo.img +"\">");
//                    $("#bioText").html(data.userinfo.about);
//                    newHTML = data.userinfo.about;
//                }
//            });
//        });

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

        $('#Subscribe').click(function(){
            var author = document.getElementById("#name");
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
    </script>
</body>
</html>
