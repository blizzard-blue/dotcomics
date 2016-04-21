$( document ).ready(function() {

    ///////////////////////////////////////////////////////////////////////////////
    // TOOLBAR EVENTS
    ///////////////////////////////////////////////////////////////////////////////

    $(".toolbar-nav li a").click(function(){
        $('.toolbar-nav li a.active').not($(this)).removeClass('active');

        if(!($(this).hasClass("active")))
            $(this).addClass('active');

        $(".tool-slideout").addClass("minimized");
        $(this + ".active + .tool-slideout").toggleClass("minimized");
        event.stopPropagation();
    });

    $(".tool-item").click(function(){
        $('.tool-item.active').not($(this)).removeClass('active');
        if(!($(this).hasClass("active")))
            $(this).addClass('active');
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

    ///////////////////////////////////////////////////////////////////////////////
    // ELEMENT ADJUSTMENTS
    ///////////////////////////////////////////////////////////////////////////////

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
        var x, y, r, b, w, h, radius;
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

    LC.defineShape('RoundSpeechbub', {
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
            return LC.createShape('RoundSpeechbub', data);
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

    LC.defineCanvasRenderer('RoundSpeechbub', function(ctx, shape) {
        var centerX, centerY, halfHeight, halfWidth, x, y;
        halfWidth = Math.floor(shape.width / 2);
        halfHeight = Math.floor(shape.height / 2);
        centerX = shape.x + halfWidth;
        centerY = shape.y + halfHeight;
        x = 0-halfWidth;
        y = 0-halfHeight;
        x2 = shape.x;
        y2 = shape.y;

        if(x>0 && y>0){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), Math.PI * 0.4, Math.PI * 0.2);
            ctx.lineTo(x, y-(2*halfHeight));
            ctx.closePath();
            ctx.restore();
        } else if(x<0 && y>0){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), Math.PI * 0.8, Math.PI * 0.6);
            ctx.lineTo(x, y-(2*halfHeight));
            ctx.closePath();
            ctx.restore();
        } else if(x<0 && y<0){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), Math.PI * 1.4, Math.PI * 1.3);
            ctx.lineTo(x, y-(2*halfHeight));
            ctx.closePath();
            ctx.restore();
        } else if(x>0 && y<0){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), Math.PI * 1.8, Math.PI * 1.6);
            ctx.lineTo(x, y-(2*halfHeight));
            ctx.closePath();
            ctx.restore();
        }

        ctx.fillStyle = shape.fillColor;
        ctx.fill();
        ctx.lineWidth = shape.strokeWidth;
        ctx.strokeStyle = shape.strokeColor;
        return ctx.stroke();
    });

    LC.defineShape('Thoughtbub', {
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
            return LC.createShape('Thoughtbub', data);
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

    LC.defineCanvasRenderer('Thoughtbub', function(ctx, shape) {
        var centerX, centerY, halfHeight, halfWidth, x, y;
        halfWidth = Math.floor(shape.width / 2);
        halfHeight = Math.floor(shape.height / 2);
        centerX = shape.x + halfWidth;
        centerY = shape.y + halfHeight;
        x = 0-halfWidth;
        y = 0-halfHeight;
        x2 = Math.abs(halfWidth)+5;
        y2 = Math.abs(halfHeight)+5;
        r2 = 15;
        x3 = Math.abs(halfWidth)+(2*r2)+5;
        y3 = Math.abs(halfHeight)+(2*r2);
        r3 = 10;

        ctx.beginPath();
        if((x < 0) && (y < 0)){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), 0, Math.PI * 2);
            ctx.moveTo(x2+r2, y2)
            ctx.arc(x2, y2, r2, 0, Math.PI * 2);
            ctx.moveTo(x3+r3, y3)
            ctx.arc(x3, y3, r3, 0, Math.PI * 2);
            ctx.restore();
        } else if((x < 0) && (y > 0)) {
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), 0, Math.PI * 2);
            ctx.moveTo(x2+r2, 0-y2)
            ctx.arc(x2, 0-y2, r2, 0, Math.PI * 2);
            ctx.moveTo(x3+r3, 0-y3)
            ctx.arc(x3, 0-y3, r3, 0, Math.PI * 2);
            ctx.restore();
        } else if((x>0) && (y<0)){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), 0, Math.PI * 2);
            ctx.moveTo(0-x2+r2, y2)
            ctx.arc(0-x2, y2, r2, 0, Math.PI * 2);
            ctx.moveTo(0-x3+r3, y3)
            ctx.arc(0-x3, y3, r3, 0, Math.PI * 2);
            ctx.restore();
        } else if((x>0) && (y>0)){
            ctx.save();
            ctx.translate(centerX, centerY);
            ctx.scale(1, Math.abs(shape.height / shape.width));
            var y = Math.abs(shape.height / shape.width);
            ctx.beginPath();
            ctx.arc(0, 0, Math.abs(halfWidth), 0, Math.PI * 2);
            ctx.moveTo(0-x2+r2, 0-y2)
            ctx.arc(0-x2, 0-y2, r2, 0, Math.PI * 2);
            ctx.moveTo(0-x3+r3, 0-y3)
            ctx.arc(0-x3, 0-y3, r3, 0, Math.PI * 2);
            ctx.restore();
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

    var RoundSpeechbub = function(lc) {  // take lc as constructor arg
        var self = this;

        return {
            usesSimpleAPI: false,  // DO NOT FORGET THIS!!!
            name: 'RoundSpeechbub',
            iconName: 'line',
            strokeWidth: lc.opts.defaultStrokeWidth,
            optionsStyle: 'stroke-width',

            didBecomeActive: function(lc) {
                var onPointerDown = function(pt) {
                    self.currentShape = LC.createShape('RoundSpeechbub', {
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

    var Thoughtbub = function(lc) {  // take lc as constructor arg
        var self = this;

        return {
            usesSimpleAPI: false,  // DO NOT FORGET THIS!!!
            name: 'RoundSpeechbub',
            iconName: 'line',
            strokeWidth: lc.opts.defaultStrokeWidth,
            optionsStyle: 'stroke-width',

            didBecomeActive: function(lc) {
                var onPointerDown = function(pt) {
                    self.currentShape = LC.createShape('Thoughtbub', {
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
    //var backgroundImage = new Image();
    //backgroundImage.src = '/img/tools/paneltemplates/template1.png';
    //var backgroundShape = LC.createShape(
    //    'Image', {x: 20, y: 20, image: backgroundImage, scale: 1}
    //);
    var lc = LC.init(document.getElementsByClassName('literally core')[0], {backgroundColor: '#ffffff',
        tools: LC.defaultTools.concat([Speechbub]), backgroundShapes: []});

console.log(LC.tools);
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
        },
        {
            name: 'speechbub',
            el: document.getElementById('tool-speechbub1'),
            tool: new Speechbub(lc)
        },
        {
            name: 'roundspeechbub',
            el: document.getElementById('tool-speechbub2'),
            tool: new RoundSpeechbub(lc)
        },
        {
            name: 'thoughtbub',
            el: document.getElementById('tool-speechbub3'),
            tool: new Thoughtbub(lc)
        },
        {
            name: 'pan',
            el: document.getElementById('tool-pan'),
            tool: new LC.tools.Pan(lc)
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

    ///////////////////////////////////////////////////////////////////////////
    //BACKGROUND IMAGE (TEMPLATE)////
///////////////////////////////////////////////////////////////////////

////////////////////////////////////////////
    //SELECT A TEMPLATE
///////////////////////////////////////////
    $("#tool-template").click(function() {

            $("#template-popup").dialog( //opens popup menu
                {
                    title: "Choose a template!",
                    width: 450,
                    height: 550,
                    modal: true,
                    buttons: {
                        Choose: function () {
                            $(this).dialog('close'); //Button closes popup

                        }
                    }
                }
            );



    });

    $(".template-ref").click(function(){ //Template option clicked
        var backgroundImage = new Image();
        backgroundImage.src = '/img/tools/paneltemplates/' + this.id +  '.png';

        lc.saveShape(LC.createShape(
                'Image', {x: 20, y: 20, image: backgroundImage, scale: 1}));

        //var backgroundShape = LC.createShape(
        //        'Image', {x: 20, y: 20, image: backgroundImage, scale: 1}
        //);
        //
        //lc.backgroundShapes = backgroundShape;

        //the background image is not included in the shape list that is
        //saved/loaded here
        //var localStorageKey = 'drawing-with-background';
        //if (localStorage.getItem(localStorageKey)) {
        //    lc.loadSnapshotJSON(localStorage.getItem(localStorageKey));
        //}
        //lc.on('drawingChange', function() {
        //    localStorage.setItem(localStorageKey, lc.getSnapshotJSON());
        //});
    });



});