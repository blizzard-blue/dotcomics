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
      <link href="/css/scroll.css" rel="stylesheet">

  </head>

  <body>
    <jsp:directive.include file="/nav.jsp" />

    <h1>Dashboard</h1>

    <jsp:directive.include file="/jslibs.jsp" />

    <div class="col-md-12 text-center"><h3>Featured Series</h3></div>
    <div class="col-md-6 col-md-offset-3">
        <div class="carousel slide" id="myCarousel-1">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3"><a href="/comic"><img src="img/Deadpool/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic"><img src="img/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic"><img src="img/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic"><img src="img/The%20Avengers/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <div class="col-md-12 text-center"><h3>Action</h3></div>
    <div class="col-md-6 col-md-offset-3">
        <div class="carousel slide" id="myCarousel-2">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3"><a href="/comic"><img src="img/Deadpool/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic"><img src="img/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic"><img src="img/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="/comic"><img src="img/The%20Avengers/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-2" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-2" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <div class="col-md-12 text-center"><h3>Action</h3></div>
    <div class="col-md-6 col-md-offset-3">
        <div class="carousel slide" id="myCarousel-3">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-xs-3"><a href="WEB-INF/comic.jsp"><img src="img/Deadpool/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="WEB-INF/comic.jsp"><img src="img/Guardians%20of%20the%20Galaxy/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="WEB-INF/comic.jsp"><img src="img/Judge%20Dredd/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-xs-3"><a href="WEB-INF/comic.jsp"><img src="img/The%20Avengers/thumbnail.jpg" class="img-responsive"></a></div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel-3" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel-3" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
    </div>

    <script>
        $('#myCarousel').carousel({
            interval: false
        })

        $('.carousel .item').each(function(){
            var next = $(this).next();
            if (!next.length) {
                next = $(this).siblings(':first');
            }
            next.children(':first-child').clone().appendTo($(this));

            for (var i=0;i<2;i++) {
                next=next.next();
                if (!next.length) {
                    next = $(this).siblings(':first');
                }

                next.children(':first-child').clone().appendTo($(this));
            }
        });
    </script>
  </body>
</html>
