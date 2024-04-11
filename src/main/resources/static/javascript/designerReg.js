document.addEventListener('DOMContentLoaded', function () {
    // 폼 제출 이벤트를 감지하여 사용자가 선택한 휴무일을 폼 데이터에 추가
    document.querySelector('form').addEventListener('submit', function (event) {
        var selectedDays = []; // 선택된 휴무일을 저장할 배열

        // 각 체크박스를 확인하여 선택된 휴무일을 배열에 추가
        var checkboxes = document.querySelectorAll('.dayBt');
        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                selectedDays.push(checkbox.id.replace('Check', '')); // 체크박스의 ID에서 'Check'를 제거하여 휴무일을 배열에 추가
            }
        });

        // 선택된 휴무일을 폼 데이터에 추가
        var hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'selectedDays';
        hiddenInput.value = selectedDays.join(',');
        this.appendChild(hiddenInput); // 폼에 hiddenInput 추가

        // 폼 제출을 계속 진행
        return true;
    });
});