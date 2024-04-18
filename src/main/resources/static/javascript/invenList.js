
$(function() {
    $("#modal_wrap").hide();
    $(".buyBt, .sortBt, .editBt").on("click", function() {
        var operationType = $(this).text().trim();
        $("#modal_wrap").show().data("operationType", operationType);
        $(".modal_text").text(operationType);
        $("#operationType").val(operationType); // hidden input에 운영 유형 설정
    });


    $(".modal_bt").on("click", function() {
        $("#modal_wrap").hide();
    });

    $(".deleteBt").on("click",function(){
        $(".list_chk").show();
        $(".buyBt, .sortBt, .editBt").hide();
        $(".backBt").show();

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

    $(".backBt").on("click",function(){
        if(confirm("취소 하시겠습니까?")){
            alert("취소 되었습니다.");
            $(".buyBt, .sortBt, .editBt").show();
            $(".list_chk, .backBt").hide();
        }
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

function handleCodeSelection(select){
    var selectInput = select.nextElementSibling;

    var selectedCode = select.value;
    if(selectedCode !== "직접 입력"){
        selectInput.style.display = "none";
    }else{
        selectInput.style.display = "block";
    }
}

// function handleCodeSelection(select) {
//     var selectInput = select.nextElementSibling;
//     var token = $("meta[name='_csrf']").attr("content");
//     var header = $("meta[name='_csrf_header']").attr("content");
//
//     var selectedCode = select.value;
//     if (selectedCode !== "직접 입력") {
//         selectInput.style.display = "none";
//         $.ajax({
//             url: "/inven/invenList/",
//             type: "GET",
//             beforeSend: function(xhr) {
//                 xhr.setRequestHeader(header, token);
//             },
//             data: { code: selectedCode },
//             dataType: "json",
//             cache: false,
//             success: function(response) {
//                 console.log("서버로부터 받은 응답:", response);
//                 document.querySelector('.inven_nm input').value = response.itemNm;
//                 document.querySelector('.inven_l input').value = response.itemL;
//                 document.querySelector('.inven_class input').value = response.idClass;
//             },
//             error: function(xhr, status, error) {
//                 console.error("AJAX 요청 실패:", status, error);
//                 // 에러 메시지를 화면에 표시하거나 사용자에게 알리는 등의 처리를 추가할 수 있습니다.
//                 alert("서버에서 응답을 가져오는 데 문제가 발생했습니다.");
//             }
//         });
//     } else {
//         selectInput.style.display = "block";
//         document.querySelector('.inven_nm input').value = '';
//         document.querySelector('.inven_l input').value = '';
//         document.querySelector('.inven_class input').value = '';
//     }
// }

