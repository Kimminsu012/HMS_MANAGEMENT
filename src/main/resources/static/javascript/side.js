
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


    $(document).ready(function() {

        $(document).on('click', 'a', function(e) {

            if ($(this).attr('href').startsWith('#') || $(this).attr('href').startsWith('http')) {
                return;
            }

            e.preventDefault();

            var url = $(this).attr('href');

            $.ajax({
                url: url,
                success: function(data) {
                    $('#content').html($(data).find('#content').html());
                    $(".sub_menu a").style("")
                    $('.sub_menu a[href="' + url + '"]').parent().show().parent().show();
                }
            });
        });

    });

});
