<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dotComics | MyAccount</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/nav.css" rel="stylesheet">
    <link href="/css/upload.css" rel="stylesheet">
</head>
<body>
<jsp:directive.include file="/nav.jsp" />

<div id="toolbar-wrapper">
    <div id="leftDiv" class="toolbar-nav">
        <br>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title">
        </div>
        <br>
        <div class="form-group">
            <label for="genre">Genre:</label>
            <select class="form-control" id="genre">
                <option>Select One</option>
                <option>Action</option>
                <option>Comedy</option>
                <option>Horror</option>
                <option>Romance</option>
            </select>
        </div>
        <br>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea type="text" class="form-control" id="description"></textarea>
        </div>
        <br>
        <div class="form-group">
            <label for="browse">File Upload:</label>
            <br>
            <button id="browse" type="button" class="btn btn-default">Browse</button>
        </div>
        <br>

        <button id="upload" type="button" class="btn btn-primary">Upload</button>

    </div>
</div>


<jsp:directive.include file="/jslibs.jsp" />
</body>
</html>
