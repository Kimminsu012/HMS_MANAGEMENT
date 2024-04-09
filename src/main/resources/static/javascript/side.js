
$(function(){

    var url = location.href;
    var ex = true;
    if( url.indexOf("/inven") > 0 ) {
        $(".inventory_sub_menu").show();
        $(".inven_border").show();
    }
    else if( url.indexOf("/designer") > 0){
        $(".designer_sub_menu").show();
        $(".designer_border").show();
    }
    else if( url.indexOf("/customer") > 0 ){
        $(".customer_sub_menu").show();
        $(".customer_border").show();
    }
    else if( url.indexOf("/shop") > 0){
        $(".shop_sub_menu").show();
        $(".shop_border").show();
    }else if( url.indexOf("/sales") > 0){
        $(".sales_border").show();
    }else if( url.indexOf("/") > 0){
        $(".home_border").show();
    }

    $(".inventory").on("click",function(){
        ex = !ex;
        $(this).next($(".inventory_sub_menu")).slideToggle();
    });
    $(".designer").on("click",function(){
        ex = !ex;
        $(this).next($(".designer_sub_menu")).slideToggle();
    });
    $(".customer").on("click",function(){
        ex = !ex;
        $(this).next($(".customer_sub_menu")).slideToggle();
    });
    $(".shop").on("click",function(){
        ex = !ex;
        $(this).next($(".shop_sub_menu")).slideToggle();
    });

    // $(document).on('click', 'a', function(e) {
    //     if (!ex) {
    //         e.preventDefault();
    //     }
    // });


    // $(document).ready(function() {
    //
    //     $(document).on('click', 'a', function(e) {
    //
    //         if ($(this).attr('href').startsWith('#') || $(this).attr('href').startsWith('http')) {
    //             return;
    //         }
    //
    //         e.preventDefault();
    //
    //         var url = $(this).attr('href');
    //
    //         $.ajax({
    //             url: url,
    //             success: function(data) {
    //                 $('#content').html($(data).find('#content').html());
    //                 $('.sub_menu a[href="' + url + '"]').parent().show().parent().show();
    //             }
    //         });
    //     });
    //
    // });


});
