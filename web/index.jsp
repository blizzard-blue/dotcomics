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
    <style type="text/css">
        a:link, a:hover, a:active, a:visited{
            color: white;
        }

    </style>

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
            <div id="inner-1" class="carousel-inner"> </div>
        </div>
    </div>

    <br>

    <div class="col-md-12 text-center"><h3>Action</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-2">
            <div id="inner-2" class="carousel-inner"> </div>
        </div>
    </div>

    <br>

    <div class="col-md-12 text-center"><h3>Comedy</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-3">
            <div id="inner-3" class="carousel-inner"> </div>
        </div>
    </div>

    <br>

    <div class="col-md-12 text-center"><h3>Super Hero</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-4">
            <div id="inner-4" class="carousel-inner"> </div>
        </div>
    </div>

    <br>

    <div class="col-md-12 text-center"><h3>Horror</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-5">
            <div id="inner-5" class="carousel-inner"> </div>
        </div>
    </div>

    <br>

    <div class="col-md-12 text-center"><h3>Romance</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-6">
            <div id="inner-6" class="carousel-inner"> </div>
        </div>
    </div>

    <br>

    <div class="col-md-12 text-center"><h3>Sci-Fi</h3></div>
    <div class="container">
        <div class="carousel slide" id="myCarousel-7">
            <div id="inner-7" class="carousel-inner"> </div>
        </div>
    </div>



    <jsp:directive.include file="/jslibs.jsp" />
    <script>
        $.ajax({
            url : "/dashboardcontent",
            dataType : 'json',
            error : function() {
                console.log("Error Occured");
            },
            success : function(data) {
                console.log(data.action_series);

                for(var i = 0; i < data.action_series.length;i++){
                    if(i == 0) {
                        $("#inner-2").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.action_series[i].title + "\"><img src=\"" + data.action_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.action_series[i].title + "\">" + data.action_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.action_series[i].author +"\">" + data.action_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $("#inner-2").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.action_series[i].title + "\"><img src=\"" + data.action_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.action_series[i].title + "\">" + data.action_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.action_series[i].author +"\">" + data.action_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-2").append("<a class=\"left carousel-control\" href=\"#myCarousel-2\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-2\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")


                for(var i = 0; i < data.comedy_series.length;i++){
                    if(i == 0) {
                        $("#inner-3").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.comedy_series[i].title + "\"><img src=\"" + data.comedy_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.comedy_series[i].title + "\">" + data.comedy_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.comedy_series[i].author +"\">" + data.comedy_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $("#inner-3").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.comedy_series[i].title + "\"><img src=\"" + data.comedy_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.comedy_series[i].title + "\">" + data.comedy_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.comedy_series[i].author +"\">" + data.comedy_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-3").append("<a class=\"left carousel-control\" href=\"#myCarousel-3\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-3\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")


                for(var i = 0; i < data.superhero_series.length;i++){
                    if(i == 0) {
                        $("#inner-4").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.superhero_series[i].title + "\"><img src=\"" + data.superhero_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.superhero_series[i].title + "\">" + data.superhero_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.superhero_series[i].author +"\">" + data.superhero_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $("#inner-4").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.superhero_series[i].title + "\"><img src=\"" + data.superhero_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.superhero_series[i].title + "\">" + data.superhero_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.superhero_series[i].author +"\">" + data.superhero_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-4").append("<a class=\"left carousel-control\" href=\"#myCarousel-4\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-4\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")


                for(var i = 0; i < data.horror_series.length;i++){
                    if(i == 0) {
                        $("#inner-5").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.horror_series[i].title + "\"><img src=\"" + data.horror_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.horror_series[i].title + "\">" + data.horror_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.horror_series[i].author +"\">" + data.horror_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $("#inner-5").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.horror_series[i].title + "\"><img src=\"" + data.horror_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.horror_series[i].title + "\">" + data.horror_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.horror_series[i].author +"\">" + data.horror_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-5").append("<a class=\"left carousel-control\" href=\"#myCarousel-5\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-5\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")


                for(var i = 0; i < data.romance_series.length;i++){
                    if(i == 0) {
                        $("#inner-6").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.romance_series[i].title + "\"><img src=\"" + data.romance_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.romance_series[i].title + "\">" + data.romance_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.romance_series[i].author +"\">" + data.romance_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $("#inner-6").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.romance_series[i].title + "\"><img src=\"" + data.romance_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.romance_series[i].title + "\">" + data.romance_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.romance_series[i].author +"\">" + data.romance_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-6").append("<a class=\"left carousel-control\" href=\"#myCarousel-6\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-6\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")

                for(var i = 0; i < data.scifi_series.length;i++){
                    if(i == 0) {
                        $("#inner-7").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.scifi_series[i].title + "\"><img src=\"" + data.scifi_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.scifi_series[i].title + "\">" + data.scifi_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.scifi_series[i].author +"\">" + data.scifi_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $("#inner-7").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.scifi_series[i].title + "\"><img src=\"" + data.scifi_series[i].cover_img + "\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.scifi_series[i].title + "\">" + data.scifi_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"/account?author="+ data.scifi_series[i].author +"\">" + data.scifi_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-7").append("<a class=\"left carousel-control\" href=\"#myCarousel-7\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-7\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")

                $('#myCarousel').carousel({
                    interval: false
                })



                $(document).on('mouseleave', '.carousel', function() {
                    $(this).carousel('pause');
                });
<<<<<<< HEAD
=======

>>>>>>> 6a898750363d71a1b9bea77d15b3951ab1275a34
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

            }
        });

    </script>
    <script src="js/carousel.js"></script>
  </body>
</html>
