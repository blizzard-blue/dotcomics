<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>dotComics | Draw</title>

        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/nav.css" rel="stylesheet">
        <link href="/css/literallycanvas.css" rel="stylesheet">
        <link href="/css/toolbar.css" rel="stylesheet">
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
                            <div class="tool-item"><img src="/img/tools/round-speechbub.png"></div>
                            <div class="tool-item"><img src="/img/tools/square-speechbub.png"></div>
                            <div class="tool-item"><img src="/img/tools/fluffy-thoughtbub.png"></div>
                            <div class="tool-item"><img src="/img/tools/thoughtbub.png"></div>
                        </div>
                    </div>
                </li>
                <li>
                    <a id="tool-zoomin"><img src="/img/tools/zoom-in.png" alt="zoom in"></a>
                </li>
                <li>
                    <a id="tool-zoomout"><img src="/img/tools/zoom-out.png" alt="zoom out"></a>
                </li>
            </ul>
        </div>
        <!-- /#toolbar-wrapper -->

        <div id="canvas-wrapper">
            <div class="literally core"></div>
        </div>

        <jsp:directive.include file="/jslibs.jsp" />
        <script src="js/literallycanvas-core.js"></script>
        <script src="js/lc-custom.js"></script>
    </body>
</html>
