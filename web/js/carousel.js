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