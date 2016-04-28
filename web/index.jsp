<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 3/16/2016
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>dotComics</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/scroll.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
  </head>

  <body>
    <jsp:directive.include file="/nav.jsp" />

    <!--<header class="intro-header">
        <div class="container">
            <div class="row">
                <div class="site-heading">
                    <h2>Featured Comic</h2>
                    <span>Description of featured comic</span>
                </div>
            </div>
        </div>
    </header>-->

    <div class="col-md-12 text-center"><h3>Featured Series</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-1">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Deadpool"><img src="series/Deadpool/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-1">Dead Pool</p>
                            <p class="imgDesc" id="scroll2-1">By Stan Lee</p>


                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Guardians%20of%20the%20Galaxy"><img src="series/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-2">Guardians of the Galaxy</p>
                            <p class="imgDesc" id="scroll2-2">By Stan Lee</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Judge%20Dredd"><img src="series/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-3">Judge Dredd</p>
                            <p class="imgDesc" id="scroll2-3">By Stan Lee</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=The%20Avengers"><img src="series/The%20Avengers/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-4">The Avengers</p>
                            <p class="imgDesc" id="scroll2-4">By Stan Lee</p>
                        </div>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <div class="col-md-12 text-center"><h3>Featured Series</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-1">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Deadpool"><img src="series/Deadpool/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-1">Dead Pool</p>
                            <p class="imgDesc" id="scroll2-1">By Stan Lee</p>


                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Guardians%20of%20the%20Galaxy"><img src="series/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-2">Guardians of the Galaxy</p>
                            <p class="imgDesc" id="scroll2-2">By Stan Lee</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Judge%20Dredd"><img src="series/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-3">Judge Dredd</p>
                            <p class="imgDesc" id="scroll2-3">By Stan Lee</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=The%20Avengers"><img src="series/The%20Avengers/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-4">The Avengers</p>
                            <p class="imgDesc" id="scroll2-4">By Stan Lee</p>
                        </div>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <div class="col-md-12 text-center"><h3>Featured Series</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-1">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Deadpool"><img src="series/Deadpool/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-1">Dead Pool</p>
                            <p class="imgDesc" id="scroll2-1">By Stan Lee</p>


                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Guardians%20of%20the%20Galaxy"><img src="series/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-2">Guardians of the Galaxy</p>
                            <p class="imgDesc" id="scroll2-2">By Stan Lee</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=Judge%20Dredd"><img src="series/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-3">Judge Dredd</p>
                            <p class="imgDesc" id="scroll2-3">By Stan Lee</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="col-xs-3">
                        <div class="img-wrap">
                            <a href="/comic?series=The%20Avengers"><img src="series/The%20Avengers/thumbnail.jpg" class="img-responsive"></a>
                            <p class="imgDesc" id="scroll1-4">The Avengers</p>
                            <p class="imgDesc" id="scroll2-4">By Stan Lee</p>
                        </div>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />
    <script src="js/carousel.js"></script>
    <script>
        $.ajax({
            url : "/dashboardcontent",
            dataType : 'json',
            error : function() {
                console.log("Error Occured");
            },
            success : function(data) {
                console.log(data.all_series);
            }
        });
    </script>
  </body>
</html>
