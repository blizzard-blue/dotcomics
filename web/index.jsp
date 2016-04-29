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
            <div class="carousel-inner"> </div>
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
                console.log(data.all_series);

                for(var i = 0; i < data.all_series.length;i++){
                    if(i == 0) {
                        $(".carousel-inner").append("<div class=\"item active\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.all_series[i].title + "\"><img src=\"" + data.all_series[i].cover_img + "/thumbnail.jpg\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.all_series[i].title + "\">" + data.all_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"#\">" + data.all_series[i].author + "</a></p></div></div></div>")
                    }
                    else {
                        $(".carousel-inner").append("<div class=\"item\"><div class=\"col-xs-3\"><div class=\"img-wrap\"><a href=\"/comic?series=" + data.all_series[i].title + "\"><img src=\"" + data.all_series[i].cover_img + "/thumbnail.jpg\" class=\"img-responsive\"></a><p class=\"imgDesc\" id=\"scroll1-" + (i + 1) + "\"><a href=\"/comic?series=" + data.all_series[i].title + "\">" + data.all_series[i].title + "</a></p><p class=\"imgDesc\" id =\"scroll2-" + (i + 1) + "\"><a href=\"#\">" + data.all_series[i].author + "</a></p></div></div></div>")
                    }
                }

                $("#myCarousel-1").append("<a class=\"left carousel-control\" href=\"#myCarousel-1\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a><a class=\"right carousel-control\" href=\"#myCarousel-1\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a>")

                $('#myCarousel').carousel({
                    interval: false
                })
                $(document).on('mouseleave', '.carousel', function() {
                    $(this).carousel('pause');
                });
                console.log("help")
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
                        console.log("help")
                    }
                });

            }
        });

    </script>
    <script src="js/carousel.js"></script>
  </body>
</html>
