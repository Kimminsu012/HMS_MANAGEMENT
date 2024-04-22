
function submitForm(){

    var checkDay = [];
    $(".dayBt:checked").each(function(){
        if($(this).val() !== '')
            checkDay.push($(this).val());
    });

    if (checkDay.length === 0) {
        alert("휴무 요일을 선택해주세요.");
        return;
    }


    $("#designerDto_free").val(checkDay);

    $("#designerForm input[type=hidden]").val('');

    // 저장하기 전에 쉼표 제거
    removeCommasBeforeSave("tel");
    removeCommasBeforeSave("sal");
    formatTimeBeforeSave("time");
    $("#designerForm").submit();

    var hour = document.getElementById("hourInput").value;
    var minute = document.getElementById("minuteInput").value;

    var time = hour + ":" + minute;
    document.getElementById("hiddenTimeInput").value = time;

}

// 쉼표를 제거하여 값 설정
function removeCommas(x) {
    return x.toString().replace(/,/g, "");
}

// 입력 필드에 쉼표 추가하여 값 설정
function formatInputValue(inputId) {
    var input = document.getElementById(inputId);
    var value = input.value;

    // 전화번호인 경우
    if (inputId === "tel") {
        // 입력된 값에서 숫자만 남기고 나머지는 제거합니다.
        value = value.replace(/\D/g, "");
        // 전화번호 형식에 맞게 하이픈을 삽입합니다.
        value = value.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    } else if (inputId === "sal") {
        // 월급 입력 필드인 경우
        // 쉼표 제거 후 적용합니다.
        value = value.replace(/,/g, "");
        // 숫자가 3개씩 반복되어 입력될 때마다 쉼표를 추가합니다.
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    input.value = value;
}

// 저장 전 쉼표 제거하여 값 설정
function removeCommasBeforeSave(inputId) {
    var input = document.getElementById(inputId);
    var value = input.value;

    if(inputId === "tel"){
        value = value.toString().replace(/-/g,"");
    }else{
        value = removeCommas(value); // 쉼표를 제거하여 값 설정
    }

    input.value = value; // 쉼표가 제거된 값으로 입력 필드 업데이트
}


function formatTimeInput(inputId) {
    var input = document.getElementById(inputId);
    var hours = ('0' + input.value.split(':')[0]).slice(-2);
    var minutes = ('0' + input.value.split(':')[1]).slice(-2);
    var formattedTime = hours + ':' + minutes;
    input.value = formattedTime;
}

// 저장 전 시간 형식 변환
function formatTimeBeforeSave(inputId) {
    var input = document.getElementById(inputId);
    var timeParts = input.value.split(':');
    var formattedTime = new Date(0, 0, 0, timeParts[0], timeParts[1]);
    input.value = formattedTime.toLocaleTimeString('en-US', {hour12: false});
}