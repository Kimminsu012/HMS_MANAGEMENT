
$(function() {
    $("#modal_wrap").hide();
    $(".buyBt, .sortBt, .editBt").on("click", function() {
        $("#modal_wrap").show();
        var buttonText = $(this).text();
        $(".modal_text").text(buttonText);
    });


    $(".modal_bt").on("click", function() {
        $("#modal_wrap").hide();
    });

    $(".deleteBt").on("click",function(){
        $(".list_chk").show();
        $(".buyBt, .sortBt, .editBt").hide();

        $(".all_chk").on("click",function(){
           $(".list_chk").prop("checked" , $(this).prop("checked"));

        });
    });

    var allCheck = $(".all_chk");
    var listCheck = $(".list_content_text .list_chk");

    allCheck.on("click", function() {
        if($(this).is(":checked")) {
            listCheck.prop("checked", true);

        }else{
            listCheck.prop("checked", false);
        }
    });
    listCheck.on("click", function() {
        var allCheckedBox = true;
        listCheck.each(function(){
            if (!$(this).is(":checked")) {
                allCheckedBox = false;
                return false;
            }
        });
        allCheck.prop("checked", allCheckedBox);
    });

    $(".deleteGo").on("click",function(){
       if($(".list_chk:checked").length > 0){
           if(confirm("정말 삭제 하시겠습니까?")){
               $(this).parents('.list_content_text').remove();
               alert("삭제 되었습니다.");
               $(".buyBt, .sortBt, .editBt").show();
               $(".list_chk").hide();
           }
       }else{
           alert("삭제할 데이터를 체크 해주세요.");
       }

    });


});