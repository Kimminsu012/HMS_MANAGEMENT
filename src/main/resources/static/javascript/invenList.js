$(function() {
    $(".modal_bt").hide();
    $("#modal_wrap").hide();

    // 작성, 구매, 판매, 수정 버튼 클릭 시 모달 띄우기
    $(".writeBt, .buyBt, .sortBt, .modifyBt").on("click", function() {
        var operationType = $(this).text().trim();
        $("#modal_wrap").show().data("operationType", operationType);
        $(".modal_text").text(operationType);
        $("#operationType").val(operationType); // hidden input에 운영 유형 설정

        // 작성, 구매, 판매 버튼 클릭 시 작성완료 버튼 숨기기
        if (operationType === "작성" || operationType === "구매" || operationType === "판매") {
            $(".modal_bt").hide();
        }

        // 선택된 운영 유형에 따라 입력 필드 설정
        var invenStatusSelect = $(".inven_status_select");
        invenStatusSelect.find("option").show(); // 모든 옵션을 먼저 표시
        var invenCodeSelect = $(".code_select_box");
        invenCodeSelect.find("option").show();

        if (operationType === "구매") {
            invenStatusSelect.val("BUY");
            invenStatusSelect.find("option:not(:selected)").hide();
            $(".code_select_box option[value*='직접 입력']").hide();
            $(".basic_text").show();
            $("#count").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
            $("#cash").prop("readonly", true);
            $("#itemL").prop("readonly", true);
            $("#idClass").prop("readonly", true);
            $("#idCode").prop("readonly", true);
            $("#itemNm").prop("readonly", true);
            $(".sellCash").val('0');
        } else if (operationType === "판매") {
            invenStatusSelect.val("SELL");
            invenStatusSelect.find("option:not(:selected)").hide();
            $(".code_select_box option[value*='직접 입력']").hide();
            $(".basic_text").show();
            $("#count").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
            $("#cash").prop("readonly", true);
            $("#itemL").prop("readonly", true);
            $("#idClass").prop("readonly", true);
            $("#idCode").prop("readonly", true);
            $("#itemNm").prop("readonly", true);
            $(".buyCash").val('0');
        } else if (operationType === "작성") {
            invenStatusSelect.val("BASIC");
            invenStatusSelect.find("option:not(:selected)").hide();
            $(".code_select_box option").hide();
            $(".code_select_box option[value*='직접 입력']").show();
            $(".basic_text").show();
            $("#count").prop("readonly", true);
            $("#cash").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
            $("#itemL").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
            $("#idClass").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
            $("#idCode").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
            $("#itemNm").prop("readonly", false).on("keydown",function(){
                if(event.key === "Enter")
                    event.preventDefault();
            });
        }
    });

    // 입력 필드에 값이 입력될 때 작성완료 버튼 표시 여부 설정
    $(".inven_input_box").on("keyup", function() {
        var allFieldsFilled = true;
        $(".inven_input_box").each(function() {
            if ($(this).val() === "") {
                allFieldsFilled = false;
                return false; // 반복문 탈출
            }
        });
        if (allFieldsFilled) {
            $(".modal_bt").show();
        } else {
            $(".modal_bt").hide();
        }
    });

    // 모달에서 작성완료 버튼 클릭 시 모달 숨기기
    $(".modal_bt").on("click", function() {
        $("#modal_wrap").hide();
        $(".basic_text").hide();
    });

    // 삭제 버튼 클릭 시 체크박스 표시
    $(".deleteBt").on("click",function(){
        $(".list_chk").show();
        $(".buyBt, .sortBt, .writeBt").hide();
        $(".backBt").show();

        $(".all_chk").on("click",function(){
            $(".list_chk").prop("checked" , $(this).prop("checked"));
        });
    });

    // 전체 체크박스 관리
    var allCheck = $(".all_chk");
    var listCheck = $(".list_content_text .list_chk");

    allCheck.on("click", function() {
        if($(this).is(":checked")) {
            listCheck.prop("checked", true);
        } else {
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

    // 취소 버튼 클릭 시 알림 후 화면 설정 초기화
    $(".backBt").on("click",function(){
        if(confirm("취소 하시겠습니까?")){
            alert("취소 되었습니다.");
            $(".buyBt, .sortBt, .writeBt").show();
            $(".list_chk, .backBt").hide();
        }
    });

    // 모달에서 취소 버튼 클릭 시 입력 필드 초기화
    $(".modal_back_bt").on("click",function (){
        $(".inven_class input").val('');
        $(".inven_l input").val('');
        $(".result_n input").val('');
        $(".inven_nm input").val('');
        $(".code_select_box").val($(".code_select_box option:first").val());
        $(".selectInput").hide();
        $(".status_s select").val($(".status_s option:first").val());
        $(".cash_n input").val('');
        $("#modal_wrap").hide();
    });

    $(".list_input").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $(".list_content_text").each(function () {
            var itemNmText = $(this).find(".itemNm-list").text().toLowerCase();
            var idCodeText = $(this).find(".idCode-list").text().toLowerCase();
            var itemLText = $(this).find(".itemL-list").text().toLowerCase();
            var idClassText = $(this).find(".idClass-list").text().toLowerCase();
            var dateString = $(this).find(".date-list").text(); // 수정된 부분
            var date = new Date(dateString);
            var formattedDate = (date.getMonth() + 1) + "." + date.getFullYear(); // 예: "4.2024"

            // 검색어와 비교
            if (idClassText.indexOf(value) !== -1 ||
                itemNmText.indexOf(value) !== -1 ||
                idCodeText.indexOf(value) !== -1 ||
                itemLText.indexOf(value) !== -1 ||
                dateString.indexOf(value) !== -1 ||
                formattedDate.indexOf(value) !== -1) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
});

// 코드 선택 옵션 처리
function handleCodeSelection(select) {
    var selectInput = select.nextElementSibling;
    var selectedCode = select.value;
    var idx = 0;
    if (selectedCode !== "직접 입력") {
        $(".itemNm-list").each(function (i, v) {
            if ($(this).text() === $(".code_select_box").val()) {
                idx = i;
                return false;
            }
        });
        $("#id").val($(".idClass-list").eq(idx).attr("data-id"));
        $("#itemNm").val(selectedCode);
        $("#idClass").val($(".idClass-list").eq(idx).text());
        $("#itemL").val($(".itemL-list").eq(idx).text());
        $("#cash").val($(".cash-list").eq(idx).text());
        $("#count").val($(".count-list")).focus().on("keyup", function () {
            var saleCount = parseInt($(this).val());
            var maxAvailableCount = parseInt($(".count-list").eq(idx).text());

            // 판매일 때 작성완료 버튼 활성화 또는 비활성화
            if ($("#operationType").val() === "판매") {
                if (saleCount > maxAvailableCount || saleCount <= 0 || $(this).val() === null) {
                    $(".modal_bt").hide();
                } else {
                    $(".modal_bt").show();
                }
            }

            // 구매일 때 작성완료 버튼 활성화 또는 비활성화
            if ($("#operationType").val() === "구매") {
                if (saleCount === 0 || $(this).val() === null) {
                    $(".modal_bt").hide();
                } else {
                    $(".modal_bt").show();
                }
            }
        });
        $("#idCode").val($(".idCode-list").eq(idx).text());

        selectInput.style.display = "none";
    } else {
        selectInput.style.display = "block";
        $(".inven_class input").val('');
        $(".inven_l input").val('');
        $(".result_n input").val('0');
        $(".inven_nm input").val('');
        $(".selectInput").val('').focus();
        $(".cash_n input").val('');
        $(".sellCash").val('0');
        $(".buyCash").val('0');

        // 작성완료 버튼 비활성화
        $(".modal_bt").hide();
    }
}

// 선택된 항목 삭제 처리
function deleteItems() {
    var selectedItems = document.querySelectorAll('.list_chk:checked');
    var selectedIds = [];
    selectedItems.forEach(function(item) {
        var idElement = item.parentNode.querySelector('.idClass-list');
        if (idElement) {
            selectedIds.push(idElement.getAttribute('data-id'));
        }
    });

    if (selectedIds.length === 0) {
        alert("삭제할 항목을 선택하세요.");
        return;
    }

    if (selectedIds.length === 1) {
        // If only one item selected, directly delete it without asking for confirmation
        var idToDelete = selectedIds[0];
        deleteItem(idToDelete);
        return;
    }

    if (confirm("선택된 항목을 삭제하시겠습니까?")) {
        selectedIds.forEach(function(id) {
            deleteItem(id);
        });
    }
}

function deleteItem(id) {
    fetch('/inven/delete/' + id, {
        method: 'DELETE'
    }).then(function(response) {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        location.reload();
    }).catch(function(error) {
        console.error('There has been a problem with your fetch operation:', error);
    });
}