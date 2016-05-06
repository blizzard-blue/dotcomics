<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<html>
<head>
    <title>dotComics | MyAccount</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
    <link href="/css/upload.css" rel="stylesheet">

</head>
<body>
<jsp:directive.include file="/nav.jsp" />

<div class="col-md-2" id="toolbar-wrapper">
    <form action="<%= blobstoreService.createUploadUrl("/uploadcomic") %>" method="POST" enctype="multipart/form-data">
        <div id="leftDiv" class="toolbar-nav">
            <div class="form-group">
                <label for="series">Series Title:</label>
                <input type="text" class="form-control" id="series" name="series" value="${param.series}">
            </div>
            <div class="form-group">
                <label for="title">Issue Title:</label>
                <input type="text" class="form-control" id="title" name="title" value="${param.issue}">
            </div>
            <div class="form-group">
                <label for="genre">Genre:</label>
                <select class="form-control" id="genre" name="genre">
                    <option>Select One</option>
                    <option ${genre == 'Action' ? 'selected' : ''}>Action</option>
                    <option ${genre == 'Comedy' ? 'selected' : ''}>Comedy</option>
                    <option ${genre == 'Horror' ? 'selected' : ''}>Horror</option>
                    <option ${genre == 'Romance' ? 'selected' : ''}>Romance</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea type="text" class="form-control" id="description" name="description">${description}</textarea>
            </div>
            <div class="form-group">
                <label for="browse">File Upload:</label>
                <br>
                <input type="file" class="form-control-file" id="browse" name="page" accept="image/*">
            </div>
            <br>

            <button id="upload" type="submit" class="btn btn-primary">Upload</button>

        </div>
    </form>
</div>

<div class="col-md-10" id="page-wrapper">

    <div id="page-numbers">

    </div>

    <div id="images">


    </div>


</div>


<jsp:directive.include file="/jslibs.jsp" />

<script>
    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }

    $.ajax({
        url : "/upload?loadpage=true&series=" + getQueryVariable("series") + "&issue=" + getQueryVariable("issue"),
        dataType : 'json',
        error : function() {
            console.log("Error Occured");
        },
        success : function(data) {
            var pages = data.page_urls;
            console.log(pages);

            for(var i=0; i<pages.length; i++){
                $('#page-numbers').append("<label id=\"lbl" + (i+1) + "\">Page " + (i + 1) + "</label>")
                $('#images').append("<img id=\"img" + (i+1) + "\" src=\"" + pages[i] + "\">");

                var width = $('#img' + (i+1)).width();
                console.log($('#img1').outerWidth());
                $('#lbl' + (i+1)).attr("margin-left" , (width/2));
                $('#lbl' + (i+1)).attr("margin-right" , (width/2));
            }
        }
    });

    $(function() {
        $( "#images" ).sortable();
        $( "#images" ).disableSelection();
    });

</script>
</body>
</html>
