<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dotComics | ComicName</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
    <link href="/css/comic.css" rel="stylesheet">
</head>
<body>
    <jsp:directive.include file="/nav.jsp" />

    <jsp:useBean id="issue" class="models.Issue" scope="session">
        <jsp:setProperty name="issue" property="*"/>
    </jsp:useBean>

    <jsp:useBean id="series" class="models.Series" scope="session">
        <jsp:setProperty name="series" property="*"/>
    </jsp:useBean>
    <div id="comic-info">
        <h1>${issue.seriesTitle}</h1>
        <h2>Issue: ${issue.title}</h2>
    </div>

    <div class="container-fluid well">
        <div id="comic-menu">
            <div class="dropdown pull-left">
                <label for="issuesdropdown">Issue </label>
                <button class="btn btn-default dropdown-toggle" type="button" id="issuesdropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Issue
                    <span class="caret"></span>
                </button>
                <ul id="issuesdropdown-menu" class="dropdown-menu" aria-labelledby="issuesdropdown">
                    <%--<c:forEach var="i" begin="1" end="${series.numIssues}">--%>
                        <%--<li><a href="#"><c:out value="${i}"/></a></li>--%>
                    <%--</c:forEach>--%>
                    <c:forEach var="i" items="${series.issues}">
                        <li><a href="/comic?series=${series.title}&issue=${i.key}"><c:out value="${i.key}"/></a></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="dropdown pull-left">
                <label for="pagedropdown">Page </label>
                <button class="btn btn-default dropdown-toggle" type="button" id="pagedropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Page
                    <span class="caret"></span>
                </button>
                <ul id="pagedropdown-menu" class="dropdown-menu" aria-labelledby="pagedropdown">
                    <c:forEach var="i" begin="1" end="${issue.numPages}">
                        <li><a href="#page${i}"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

    <div id="comic-page-wrapper">
        <div id="comic-tools-wrapper">
            <div class="comic-tool" id="comic-bookmark"><span class="glyphicon glyphicon-star"></span></div>
            <div class="comic-tool" id="comic-zoom-in"><span>+</span></div>
            <div class="comic-tool" id="comic-zoom-out"><span>-</span></div>
        </div>
        <div id="comic-pages">
            <c:forEach var="i" begin="1" end="${issue.numPages}">
                <img id="page${i}" src="${issue.path}/${i}.jpg">
            </c:forEach>
        </div>
    </div>

    <jsp:directive.include file="/jslibs.jsp" />

    <script>
        $(function(){
            resizeComicPanel();
            window.onresize = resizeComicPanel;

            function resizeComicPanel(){
                var topDivHeight = $(".navbar").outerHeight(true) + $("h1").height() + $("h2").height() + $(".well").outerHeight(true),
                        totalHeight = document.body.offsetHeight;
                $('#comic-page-wrapper').height(totalHeight  - topDivHeight - 50);
            }

            $("#comic-zoom-out").click(function(){
                var width = $("#comic-pages img").width() * 0.9;
                console.log(width);
                if((width <= (document.body.offsetWidth * 1.2)) && (width >= (document.body.offsetWidth * 0.2)))
                    $("#comic-pages img").css({"width":width});
            });

            $("#comic-zoom-in").click(function(){
                var width = $("#comic-pages img").width() * 1.1;
                console.log(width);
                if((width <= (document.body.offsetWidth * 1.2)) && (width >= (document.body.offsetWidth * 0.2)))
                    $("#comic-pages img").css({"width":width});
            });
        });
    </script>
</body>
</html>