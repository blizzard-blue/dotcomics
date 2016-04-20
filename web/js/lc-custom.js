$( document ).ready(function() {
    // toolbar events
    $(".toolbar-nav li a").click(function(){
        $('.toolbar-nav li a.active').not($(this)).removeClass('active');

        if(!($(this).hasClass("active")))
            $(this).addClass('active');

        $(".tool-slideout").addClass("minimized");
        $(this + ".active + .tool-slideout").toggleClass("minimized");
        event.stopPropagation();
    });

    $(".toolbar-at-top li").click(function(){

        console.log($('.toolbar-at-top li.active').not($(this)))

        if($('.toolbar-at-top li.active').not($(this)).length != 0){
            $('.toolbar-at-top li.active svg circle').attr("fill", "white");
            $('.toolbar-at-top li.active').not($(this)).removeClass('active');
            $(this).toggleClass('active');
            $('.toolbar-at-top li.active svg circle').attr("fill","black");
            event.stopPropagation()
        }
    });

    // adjust page elment size
    var sidebarWidth = $("#toolbar-wrapper").outerWidth() + "px";
    $("#canvas-wrapper").css({"margin-left":sidebarWidth});

    var navbarHeight = $(".navbar").outerHeight(true);
    var totalHeight = document.body.offsetHeight;
    var canvasHeight = totalHeight-navbarHeight;
    $(".literally").height(canvasHeight);
    $("#toolbar-wrapper").height(canvasHeight);

    ///////////////////////////////////////////////////////////////////////////////
    //LITERALLY CANVAS INITIALIZATION & EXTENSION
    ///////////////////////////////////////////////////////////////////////////////

    LC.defineShape('Speechbub', {
        /* initialize using the args passed to LC.createShape() */
        constructor: function(args) {
            if (args == null) {
                args = {};
            }
            this.x = args.x || 0;
            this.y = args.y || 0;
            this.width = args.width || 0;
            this.height = args.height || 0;
            this.strokeWidth = args.strokeWidth || 1;
            this.strokeColor = args.strokeColor || 'black';
            return this.fillColor = args.fillColor || 'transparent';
        },

        /* provide a bounding rectangle so getImage() can figure out the image
         bounds (semi-optional) */
        getBoundingRect: function() {
            return {
                x: this.x - this.strokeWidth / 2,
                y: this.y - this.strokeWidth / 2,
                width: this.width + this.strokeWidth,
                height: this.height + this.strokeWidth
            };
        },

        /* return a dictionary representation of the shape from which this instance
         can be reconstructed */
        toJSON: function() {
            return {
                x: this.x,
                y: this.y,
                width: this.width,
                height: this.height,
                strokeWidth: this.strokeWidth,
                strokeColor: this.strokeColor,
                fillColor: this.fillColor
            };
        },

        /* reconstruct the Speechbub from the representation given by
         toJSON */
        fromJSON: function(data) {
            return LC.createShape('Speechbub', data);
        },
        move: function(moveInfo) {
            if (moveInfo == null) {
                moveInfo = {};
            }
            this.x = this.x - moveInfo.xDiff;
            return this.y = this.y - moveInfo.yDiff;
        },
        setUpperLeft: function(upperLeft) {
            if (upperLeft == null) {
                upperLeft = {};
            }
            this.x = upperLeft.x;
            return this.y = upperLeft.y;
        }
    });

    /* Define canvas and SVG renderers */

    LC.defineCanvasRenderer('Speechbub', function(ctx, shape) {
        var x, y, r, b, width, height;
        x = shape.x;
        y = shape.y;
        w = shape.width;
        h = shape.height;
        r = x + w;
        b = y + h;
        radius = w * 0.05;

        // ctx.scale(1, Math.abs(height / width));
        ctx.beginPath();
        if((w < 0) && (h < 0)){
            ctx.moveTo(x+radius, y);
            ctx.lineTo(x+radius/2, y+20);
            ctx.lineTo(x+radius * 2, y);

            ctx.lineTo(r-radius, y);
            ctx.quadraticCurveTo(r, y, r, y+radius);
            ctx.lineTo(r, y+h-radius);
            ctx.quadraticCurveTo(r, b, r-radius, b);
            ctx.lineTo(x+radius, b);
            ctx.quadraticCurveTo(x, b, x, b-radius);
            ctx.lineTo(x, y+radius);
            ctx.quadraticCurveTo(x, y, x+radius, y);
        } else if((w < 0) && (h > 0)) {
            ctx.moveTo(x+radius, y);
            ctx.lineTo(x+radius/2, y-20);
            ctx.lineTo(x+radius * 2, y);

            ctx.lineTo(r-radius, y);
            ctx.quadraticCurveTo(r, y, r, y-radius);
            ctx.lineTo(r, b+radius);
            ctx.quadraticCurveTo(r, b, r-radius, b);
            ctx.lineTo(x+radius, b);
            ctx.quadraticCurveTo(x, b, x, b+radius);
            ctx.lineTo(x, y-radius);
            ctx.quadraticCurveTo(x, y, x+radius, y);
        } else if((w>0) && (h<0)){
            ctx.moveTo(x+radius, y);
            ctx.lineTo(x+radius/2, y+20);
            ctx.lineTo(x+radius * 2, y);

            ctx.lineTo(r-radius, y);
            ctx.quadraticCurveTo(r, y, r, y-radius);
            ctx.lineTo(r, b+radius);
            ctx.quadraticCurveTo(r, b, r-radius, b);
            ctx.lineTo(x+radius, b);
            ctx.quadraticCurveTo(x, b, x, b+radius);
            ctx.lineTo(x, y-radius);
            ctx.quadraticCurveTo(x, y, x+radius, y);
        }else if((w>0) && (h>0)){
            ctx.moveTo(x+radius, y);
            ctx.lineTo(x+radius/2, y-20);
            ctx.lineTo(x+radius * 2, y);

            ctx.lineTo(r-radius, y);
            ctx.quadraticCurveTo(r, y, r, y+radius);
            ctx.lineTo(r, y+h-radius);
            ctx.quadraticCurveTo(r, b, r-radius, b);
            ctx.lineTo(x+radius, b);
            ctx.quadraticCurveTo(x, b, x, b-radius);
            ctx.lineTo(x, y+radius);
            ctx.quadraticCurveTo(x, y, x+radius, y);
        }else{
        }

        ctx.fillStyle = shape.fillColor;
        ctx.fill();
        ctx.lineWidth = shape.strokeWidth;
        ctx.strokeStyle = shape.strokeColor;
        return ctx.stroke();
    });

    ///////////

    var Speechbub = function(lc) {  // take lc as constructor arg
        var self = this;

        return {
            usesSimpleAPI: false,  // DO NOT FORGET THIS!!!
            name: 'Speechbub',
            iconName: 'line',
            strokeWidth: lc.opts.defaultStrokeWidth,
            optionsStyle: 'stroke-width',

            didBecomeActive: function(lc) {
                var onPointerDown = function(pt) {
                    self.currentShape = LC.createShape('Speechbub', {
                        x: pt.x,
                        y: pt.y,
                        strokeWidth: self.strokeWidth,
                        strokeColor: lc.getColor('primary'),
                        fillColor: lc.getColor('secondary')
                    });
                    lc.setShapesInProgress([self.currentShape]);
                    lc.repaintLayer('main');
                };

                var onPointerDrag = function(pt) {
                    self.currentShape.width = pt.x - self.currentShape.x;
                    self.currentShape.height = pt.y - self.currentShape.y;
                    lc.drawShapeInProgress(self.currentShape);
                    lc.repaintLayer('main');
                };

                var onPointerUp = function(pt) {
                    // lc.setShapesInProgress([]);
                    lc.saveShape(self.currentShape);
                };

                var onPointerMove = function(pt) {
                    // console.log("Mouse moved to", pt);
                };

                // lc.on() returns a function that unsubscribes us. capture it.
                self.unsubscribeFuncs = [
                    lc.on('lc-pointerdown', onPointerDown),
                    lc.on('lc-pointerdrag', onPointerDrag),
                    lc.on('lc-pointerup', onPointerUp),
                    lc.on('lc-pointermove', onPointerMove)
                ];
            },

            willBecomeInactive: function(lc) {
                // call all the unsubscribe functions
                self.unsubscribeFuncs.map(function(f) { f() });
            }
        }
    };

    ///////////////

    var lc = LC.init(document.getElementsByClassName('literally core')[0], {backgroundColor: '#ffffff', tools: LC.defaultTools.concat([Speechbub])});


    var tools = [
        {
            name: 'pencil',
            el: document.getElementById('tool-pencil'),
            tool: new LC.tools.Pencil(lc)
        },
        {
            name: 'eraser',
            el: document.getElementById('tool-eraser'),
            tool: new LC.tools.Eraser(lc),
            stokeWidth: lc.strokeWidth
        },
        {
            name: 'speechbub',
            el: document.getElementById('tool-speechbub'),
            tool: new Speechbub(lc)
        }
    ];

    var activateTool = function(t) {
        lc.setTool(t.tool);
    }

    tools.forEach(function(t) {
        t.el.style.cursor = "pointer";
        t.el.onclick = function(e) {
            e.preventDefault();
            activateTool(t);
        };
    });
    activateTool(tools[0]);

    $("#firstSize").click(function(){
        tools[0].tool.strokeWidth = 1;
        tools[1].tool.strokeWidth = 1;
    });

    $("#secondSize").click(function(){
        tools[0].tool.strokeWidth = 2;
        tools[1].tool.strokeWidth = 2;
    });

    $("#thirdSize").click(function(){
        tools[0].tool.strokeWidth = 5;
        tools[1].tool.strokeWidth = 5;
    });

    $("#fourthSize").click(function(){
        tools[0].tool.strokeWidth = 10;
        tools[1].tool.strokeWidth = 10;
    });

    $("#fifthSize").click(function(){
        tools[0].tool.strokeWidth = 15;
        tools[1].tool.strokeWidth = 15;
    });

    $("#sixthSize").click(function(){
        tools[0].tool.strokeWidth = 20;
        tools[1].tool.strokeWidth = 20;
    });

    $("#undo").click(function() {
        if(lc.canUndo()){
            lc.undo();
        }
    })

    $("#redo").click(function() {
        if(lc.canRedo()){
            lc.redo();
        }
    })

    $("#tool-zoomin").click(function (){
        lc.zoom(1);
        console.log(lc.scale);
    })

    $("#tool-zoomout").click(function (){

        if(lc.scale != 1.0)
            lc.zoom(-1);
            console.log(lc.scale);
    })



});