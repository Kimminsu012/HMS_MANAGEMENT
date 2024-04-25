$(function () {
    var next = $(".com_nt");
    var prev = $(".com_pt");
    var currentDate = new Date(); // 현재 날짜 가져오기
    var currentDay = currentDate.getDate(); // 현재 날짜에서 일 가져오기
    var currentMonth = currentDate.getMonth() + 1; // 현재 날짜에서 월 가져오기 (JavaScript의 월은 0부터 시작하므로 1을 더함)
    var currentYear = currentDate.getFullYear(); // 현재 날짜에서 연도 가져오기

    // 날짜 업데이트 함수 정의
    function updateDate(year, month, day) {
        $(".now-date").text(year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day));
    }

    // 이전 버튼 클릭 이벤트 처리
    next.on("click", function () {
        currentDay--; // 하루 전 날짜로 이동
        if (currentDay === 0) { // 이전 달로 이동해야 하는 경우
            currentMonth--; // 이전 달로 변경
            if (currentMonth === 0) { // 1월에서 이전을 클릭한 경우
                currentYear--; // 연도를 이전 연도로 변경
                currentMonth = 12; // 12월로 설정
            }
            currentDay = new Date(currentYear, currentMonth, 0).getDate(); // 이전 달의 마지막 날짜로 설정
        }
        updateDate(currentYear, currentMonth, currentDay); // 날짜 업데이트
    });

    // 다음 버튼 클릭 이벤트 처리
    prev.on("click", function () {
        var lastDayOfMonth = new Date(currentYear, currentMonth, 0).getDate(); // 현재 달의 마지막 날짜 가져오기
        currentDay++; // 다음 날짜로 이동
        if (currentDay > lastDayOfMonth) { // 다음 달로 이동해야 하는 경우
            currentDay = 1; // 다음 달의 첫 번째 날짜로 설정
            currentMonth++; // 다음 달로 변경
            if (currentMonth > 12) { // 12월에서 다음을 클릭한 경우
                currentYear++; // 연도를 다음 연도로 변경
                currentMonth = 1; // 1월로 설정
            }
        }
        updateDate(currentYear, currentMonth, currentDay); // 날짜 업데이트
    });

    $(".now-date").on("click", function () {

        // 현재 날짜로 변수들을 갱신하고 화면을 업데이트
        currentDate = new Date();
        currentDay = currentDate.getDate();
        currentMonth = currentDate.getMonth() + 1;
        currentYear = currentDate.getFullYear();
        updateDate(currentYear, currentMonth, currentDay);
    });


    // 초기 날짜 표시
    updateDate(currentYear, currentMonth, currentDay);
});