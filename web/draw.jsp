<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>dotComics | Draw</title>

        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/nav.css" rel="stylesheet">
        <link href="/css/literallycanvas.css" rel="stylesheet">
        <link href="/css/toolbar.css" rel="stylesheet">
        <link href="/css/main.css" rel="stylesheet">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
    </head>
    <body>
        <jsp:directive.include file="/nav.jsp" />

        <!-- Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-body">
                        <p>Page successfully uploaded.</p>
                        <a href="/draw" class="btn btn-info" role="button">Draw Another Page</a>
                        <a href="/account?author=${user.username}" class="btn btn-success" role="button">I'm Done</a>
                    </div>
                </div>

            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div id="draw-form" class="col-md-3">
                    <form name="draw-form" class="controls export">
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
                                <option ${genre == 'scifi' ? 'selected' : ''}>Sci-Fi</option>
                                <option ${genre == 'Superhero' ? 'selected' : ''}>Superhero</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea type="text" class="form-control" id="description" name="description">${description}</textarea>
                        </div>
                        <div class="form-group">
                            <button id="upload" data-action="export-as-png" type="button" class="btn btn-primary">Save Page</button>
                        </div>
                    </form>
                </div>

                <!-- toolbar -->
                <div id="toolbar-wrapper" class="col-md-1">
                    <ul class="toolbar-nav">
                        <li>
                            <a id="tool-pan"><img src="/img/tools/pan.png" alt="pan"></a>
                        </li>
                        <li>
                            <a id="tool-pencil" class="active"><img src="/img/tools/pencil.png" alt="pencil"></a>
                        </li>
                        <li>
                            <a id="tool-eraser"><img src="/img/tools/eraser.png" alt="erasser"></a>
                        </li>
                        <li>
                            <a id="tool-speechbub"><img src="/img/tools/round-speechbub.png" alt="speech bubble"></a>
                            <div class="tool-slideout minimized" id="speechbub">
                                <span class="tool-header">Shape</span>
                                <div class="tool-wrapper">
                                    <div class="tool-item active" id="tool-speechbub1"><img src="/img/tools/square-speechbub.png"></div>
                                    <div class="tool-item" id="tool-speechbub2"><img src="/img/tools/round-speechbub.png"></div>
                                    <div class="tool-item" id="tool-speechbub3"><img src="/img/tools/thoughtbub.png"></div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <a id="tool-colors"><img src="/img/tools/saturation.png" alt="colors"></a>
                            <div class="tool-slideout minimized" id="colors">
                                <span class="tool-header">Shape</span>
                                <div class="tool-wrapper">
                                    <a class='tool' id="colorTool-black"><img src="/img/colors/black.png" alt="black" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-red"><img src="/img/colors/red.png" alt="red" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-blue"><img src="/img/colors/blue.png" alt="blue" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-white"><img src="/img/colors/white.png" alt="white" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-pink"><img src="/img/colors/pink.png" alt="pink" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-orange"><img src="/img/colors/orange.png" alt="orange" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-yellow"><img src="/img/colors/yellow.png" alt="yellow" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-green"><img src="/img/colors/green.png" alt="green" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-indigo"><img src="/img/colors/indigo.png" alt="indigo" height="50" width="50"></a>
                                    <a class='tool' id="colorTool-violet"><img src="/img/colors/violet.png" alt="violet" height="50" width="50"></a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <a id="tool-template"><img src="/img/tools/template.png" alt="templates"></a>
                        </li>
                        <li>
                            <a id="tool-text"><img src="/img/tools/text.png" alt="text"></a>
                        </li>
                        <li>
                            <a id="tool-eyedropper"><img src="/img/tools/eyedropper.png" alt="eyedropper"></a>
                        </li>
                        <li>
                            <a id="tool-fillcolor"><img src="/img/tools/polygon-closed.png" alt="fill color"></a>
                        </li>
                        <li>
                            <a id="tool-line"><img src="/img/tools/line.png" alt="line"></a>
                        </li>
                        <li>
                            <a id="tool-rectangle"><img src="/img/tools/rectangle.png" alt="rectangle"></a>
                        </li>
                        <li>
                            <a id="tool-polygon"><img src="/img/tools/polygon.png" alt="polygon"></a>
                        </li>
                        <li>
                            <a id="tool-circle"><img src="/img/tools/ellipse.png" alt="ellipse"></a>
                        </li>
                        <li>
                            <a id="tool-select"><img src="/img/tools/dashed-line.png" alt="select"></a>
                        </li>
                        <li class="two">
                            <a id="tool-zoomin"><img src="/img/tools/zoom-in.png" alt="zoom in"></a>
                            <a id="tool-zoomout"><img src="/img/tools/zoom-out.png" alt="zoom out"></a>
                        </li>
                        <li class="two">
                            <a id="undo"><img src="/img/tools/undo.png" alt="undo"></a>
                            <a id="redo"><img src="/img/tools/redo.png" alt="redo"></a>
                        </li>
                        <li class="clear">
                            <a id="clear"><font size="3" color="white">Clear</font></a>
                        </li>
                    </ul>
                </div>

                <div id="canvas-wrapper" class="col-md-8">
                    <div class="literally core"></div>
                </div>

                <div id="resizeBar-wrapper" style="display: none;">
                    <ul class="toolbar-at-top">
                        <li>
                            <a id="firstSize">
                                <svg width="52" height="52" version="1.1">
                                    <circle r="0.5" cx="26" cy="26" fill="white"></circle>
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a id="secondSize">
                                <svg width="52" height="52" version="1.1">
                                    <circle r="1.0" cx="26" cy="26" fill="white"></circle>
                                </svg>
                            </a>
                        </li>
                        <li class="active">
                            <a id="thirdSize">
                                <svg width="52" height="52" version="1.1">
                                    <circle r="2.5" cx="26" cy="26" fill="black"></circle>
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a id="fourthSize">
                                <svg width="52" height="52" version="1.1">
                                    <circle r="5.0" cx="26" cy="26" fill="white"></circle>
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a id="fifthSize">
                                <svg width="52" height="52" version="1.1">
                                    <circle r="10.0" cx="26" cy="26" fill="white"></circle>
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a id="sixthSize">
                                <svg width="52" height="52" version="1.1">
                                    <circle r="15.0" cx="26" cy="26" fill="white"></circle>
                                </svg>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>

        <div class="popup-window" id="template-popup" style="display: none">
            <table id="template-table">
                <tr>
                    <td><a id="template1" class="template-ref"><img src="img/tools/paneltemplates/template1.png"></a> </td>
                    <td><a id="template2" class="template-ref"><img src="img/tools/paneltemplates/template2.png"></a></td>
                </tr>
                <tr>
                    <td><a id="template3" class="template-ref"><img src="img/tools/paneltemplates/template3.png"></a></td>
                    <td><a id="template4" class="template-ref"><img src="img/tools/paneltemplates/template4.png"></a></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="none" class="template-ref"><p id="template-none">None</p></a></td>
                </tr>
            </table>
        </div>

        <jsp:directive.include file="/jslibs.jsp" />
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="js/literallycanvas-core.js"></script>
        <script src="js/lc-custom.js"></script>
        <script>
//            function uploadDrawing(){
//                var image = LC.getImage();
//                console.log(image);
//
//                var series = document.forms["draw-form"].series.value;
//                var title = document.forms["draw-form"].title.value;
//                var genre = document.forms["draw-form"].genre.value;
//                var description = document.forms["draw-form"].description.value;
//
//                $.post(
//                        "/drawupload",
//                        {"series":series,"title":title, "genre":genre, "description":description},
//                        function(data){
//                            console.log("data: " + data);
//                        },
//                        "json"
//                );
//            }
        </script>
    </body>
</html>
