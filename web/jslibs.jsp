<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
<script>
    $('#myCarousel').carousel({
        interval: false
    })

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
        }
    });

    $( document ).ready(function() {
        $.ajax({
            url : "/getbookmarks",
            dataType : 'json',
            error : function() {
                console.log("Error Occured");
            },
            success : function(data) {
                console.log(data.bookmarks_obj);
                var bm = data.bookmarks_obj.bookmarks;
                for(var i=0; i<bm.length; i++){
                    $("#bookmarks > ul").append("<li><a href=\"" + bm[i].path + "\"> " + bm[i].seriesTitle +"</a></li>");
                }


            }
        });

        $.ajax({
            url : "/getsubscriptions",
            dataType : 'json',
            error : function() {
                console.log("Error Occured");
            },
            success : function(data) {
                console.log(data.subscriptions_obj);

                var bm = data.subscriptions_obj.subscriptions;
                for(var i=0; i<bm.length; i++){
                    $("#subs > ul").append("<li><a href=\"" + bm[i].path + "\"> " + bm[i].author +"</a></li>");
                }
            }
        });
    });
</script>