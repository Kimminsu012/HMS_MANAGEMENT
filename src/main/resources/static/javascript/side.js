
$(function(){
    $(".inventory").on("click",function(){
        $(this).next($(".inventory_sub_menu")).slideToggle();
    });
    $(".designer").on("click",function(){
        $(this).next($(".designer_sub_menu")).slideToggle();
    });
    $(".customer").on("click",function(){
        $(this).next($(".customer_sub_menu")).slideToggle();
    });
    $(".shop").on("click",function(){
        $(this).next($(".shop_sub_menu")).slideToggle();
    });

});