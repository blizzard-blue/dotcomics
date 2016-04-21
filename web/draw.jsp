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



        <!-- toolbar -->
        <div id="toolbar-wrapper">
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
                    <a id="tool-template"><img src="/img/tools/template.png" alt="templates"></a>
                </li>
                <li class="two">
                    <a id="tool-zoomin"><img src="/img/tools/zoom-in.png" alt="zoom in"></a>
                    <a id="tool-zoomout"><img src="/img/tools/zoom-out.png" alt="zoom out"></a>
                </li>
                <li class="two">
                    <a id="undo"><img src="/img/tools/undo.png" alt="undo"></a>
                    <a id="redo"><img src="/img/tools/redo.png" alt="redo"></a>
                </li>
            </ul>
        </div>

        <div id="resizeBar-wrapper">
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


        <!-- /#toolbar-wrapper -->

        <div id="canvas-wrapper">
            <div class="literally core"></div>
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
            </table>
        </div>

        <jsp:directive.include file="/jslibs.jsp" />
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="js/literallycanvas-core.js"></script>
        <script src="js/lc-custom.js"></script>
    </body>
</html>
