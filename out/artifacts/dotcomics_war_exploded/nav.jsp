<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><img src="/img/logo.png"></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <jsp:useBean id="user" class="models.UserAcct" scope="session">
                    <jsp:setProperty name="user" property="*"/>
                </jsp:useBean>

                <form action="/search" method="POST" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" name="search">
                    </div>
                    <button type="submit" class="btn btn-default" id="search-btn"><span class="glyphicon glyphicon-search"></span></button>
                </form>

                <c:choose>
                    <c:when test="${user.username != null}">
                        <li><a>${user.username}</a></li>

                        <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-pencil"></span>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/upload">Upload</a></li>
                            <li><a href="/draw">Draw</a></li>
                        </ul>
                        </li>

                        <li class="dropdown" id="bookmarks">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <span class="glyphicon glyphicon-star"></span></a>
                            <ul class="dropdown-menu scroll-dd"></ul>
                        </li>

                        <li class="dropdown" id="subs">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <span class="glyphicon glyphicon-thumbs-up"></span></a>
                            <ul class="dropdown-menu scroll-dd"></ul>
                        </li>

                        <li class="dropdown" id="settingsDropDown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <span class="glyphicon glyphicon-cog"></span>
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/account?author=${user.username}">Account Settings</a></li>
                                <li><a href="/logout">Logout</a></li>
                            </ul>
                        </li>

                    </c:when>
                    <c:otherwise>
                        <li><a href="/login">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>