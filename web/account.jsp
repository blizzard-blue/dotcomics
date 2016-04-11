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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
    <link href="/css/account.css" rel="stylesheet">
    <link href="/js/editBio.js" rel="script">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <header class="intro-header" id="nameContainer">
        <div class="container">
            <div class="row" >
                <div id="profilePicture">
                    <img src="/img/mckenna.jpg">
                </div>
                <div class="site-heading">
                    <h2 id="name">${user.username}</h2>
                </div>
            </div>
        </div>
    </header>

    <header id="bioContainer">
        <button id="Edit" class="btn btn-primary">Edit</button>
        <button id="submit" class="btn btn-primary" style="display: none">Submit</button>
        <div class="container">
            <div class="row" id="bioRow">
                <p id="bioText">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eu lacus at augue tristique dignissim. Nunc vitae porta lorem. Nullam eu nunc sit amet arcu dictum convallis. Vestibulum quis purus quis sem rhoncus imperdiet eget at nisl. Fusce non metus libero, vel viverra purus. Quisque ullamcorper congue risus vel adipiscing. Quisque vehicula ante in quam malesuada at porta diam gravida. Aenean sagittis, ipsum eget egestas malesuada, turpis neque aliquam metus, lobortis congue ligula nisi quis purus. Integer nec dui et elit suscipit ultrices et sit amet enim. Nulla euismod commodo metus, eget luctus urna dignissim in.</p>
            </div>
        </div>



        <script>
            var newHTML;

            $('#Edit').click(function(){

                $(this).hide();
                $('#submit').show();

                $('#bioRow').html("<textarea id=\"newBio\" class=\"form-control\" onchange='biochange()' rows=\"4\" >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eu lacus at augue tristique dignissim. Nunc vitae porta lorem. Nullam eu nunc sit amet arcu dictum convallis. Vestibulum quis purus quis sem rhoncus imperdiet eget at nisl. Fusce non metus libero, vel viverra purus. Quisque ullamcorper congue risus vel adipiscing. Quisque vehicula ante in quam malesuada at porta diam gravida. Aenean sagittis, ipsum eget egestas malesuada, turpis neque aliquam metus, lobortis congue ligula nisi quis purus. Integer nec dui et elit suscipit ultrices et sit amet enim. Nulla euismod commodo metus, eget luctus urna dignissim in.</textarea>");
                newHTML = $("#newBio").val();
            });

            function biochange(){
                newHTML = $("#newBio").val();
                console.log(newHTML);
            }

            $('#submit').click(function(){

//                newHTML = $('#newBio').html();
                console.log(newHTML);

                $(this).hide();
                $('#Edit').show();
                $('#bioRow').html("<p id=\"bioText\">" + newHTML + "</p>");

            });

        </script>




    </header>


    <div class="col-md-12 text-center"><h3>Published Comics</h3></div>
    <div class="col-md-6 col-md-offset-3">
        <div class="carousel slide" id="myCarousel-1">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3"><a href="/comic?series=Deadpool"><img src="series/Deadpool/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic?series=Guardians%20of%20the%20Galaxy"><img src="series/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic?series=Judge%20Dredd"><img src="series/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic?series=The%20Avengers"><img src="series/The%20Avengers/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
</body>
</html>
