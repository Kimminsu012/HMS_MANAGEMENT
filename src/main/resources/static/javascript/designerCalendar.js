

$(function(){
    // calendar element 취득
    var calendarEl = $('#calendar')[0];
    // full-calendar 생성하기
    var calendar = new FullCalendar.Calendar(calendarEl, {
        height: '700px', // calendar 높이 설정
        expandRows: true, // 화면에 맞게 높이 재설정
        slotMinTime: '08:00', // Day 캘린더에서 시작 시간
        slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
        // 해더에 표시할 툴바
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
        },
        initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
        navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
        editable: true, // 수정 가능?
        selectable: true, // 달력 일자 드래그 설정가능
        nowIndicator: true, // 현재 시간 마크
        dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
        locale: 'ko', // 한국어 설정

        select: function(arg) {
            var eventType = prompt('입력할 유형을 선택하세요: (휴가, 휴무, 메모)');
            if (eventType && (eventType === '휴가' || eventType === '휴무'  || eventType === '메모')) {
                var title = prompt('일정을 입력하세요:');
                var whoareyou = prompt("이름 입력 ");
                if (title) {
                    var eventColor=getColor(eventType);
                    calendar.addEvent({
                        title: title,
                        start: arg.start,
                        end: arg.end,
                        allDay: arg.allDay,
                        className: eventType,
                        backgroundColor: eventColor // 배경색 설정
                    });
                    // 서버에 이벤트 추가 데이터 전송
                    var eventData = {
                        title: title,
                        start: arg.start,
                        end: arg.end,
                        allDay: arg.allDay,
                        eventType: eventType,
                        name :whoareyou
                    };
                    $.ajax({
                        type: "POST",
                        url: "/designer/calendarSave",
                        data: JSON.stringify(eventData),
                        contentType: "application/json",
                        success: function(response) {
                            console.log("이벤트가 성공적으로 추가되었습니다.");
                            location.reload(true);
                        },
                        error: function(xhr, status, error) {
                            console.error("이벤트 추가 중 오류가 발생했습니다: " + error);
                        }
                    });
                }
            } else {
                alert('올바른 유형을 선택하세요.');
            }
            calendar.unselect();
        },
        eventClick: function(info) {
            if (confirm("삭제는 확인, 수정은 아니요")) {
                // 삭제 요청 보내기
                $.ajax({
                    type: "POST",
                    url: "/designer/calendarDelete",
                    data: JSON.stringify({id: info.event.id}),
                    contentType: "application/json",
                    success: function(response) {
                        console.log("이벤트가 성공적으로 삭제되었습니다.");
                        // 이벤트를 캘린더에서 제거
                        info.event.remove();
                    },
                    error: function(xhr, status, error) {
                        console.error("이벤트 삭제 중 오류가 발생했습니다: " + error);
                    }
                });
            } else {
                var newTitle = prompt('수정할 일정을 입력하세요:');
                if (newTitle) {
                    // 수정 요청 보내기
                    var eventData = {
                        id: info.event.id,
                        title: newTitle,
                        start: info.event.start,
                        end: info.event.end,
                        allDay: info.event.allDay,
                        eventType: info.event.extendedProps.className
                    };
                    $.ajax({
                        type: "POST",
                        url: "/designer/calendarUpdate",
                        data: JSON.stringify(eventData),
                        contentType: "application/json",
                        success: function(response) {
                            console.log("이벤트가 성공적으로 수정되었습니다.");
                            // 캘린더에서 이벤트 제목 수정
                            info.event.setProp('title', newTitle);
                        },
                        error: function(xhr, status, error) {
                            console.error("이벤트 수정 중 오류가 발생했습니다: " + error);
                        }
                    });
                }
            }
        }
    });
    // 캘린더 랜더링
    calendar.render();


    fetch('/designer/calendar')
        .then(response => {
            if (!response.ok) {
                throw new Error('서버 확인');
            }
            return response.json(); // JSON 형식의 응답을 파싱하여 반환
        })
        .then(data => {
            for(var i=0; i<data.length; i++) {
                calendar.addEvent({
                    id:data[i].id,
                    title: data[i].title,
                    start: data[i].start,
                    end: data[i].end,
                    allDay: data[i].allDay,
                    className: data[i].eventType,
                    backgroundColor: getColor(data[i].eventType) // 배경색 설정
                });
            }
            calendar.render();
            console.log(data); // 받은 데이터를 콘솔에 출력하는 예시
        })
        .catch(error => {
            console.error('실패 서버확인', error);
        });

});

function getColor(eventType){
    switch(eventType) {
        case '휴가':
            return  'blue';
        case '휴무':
            return  'red';
        case '예약':
            return  'green';
        case '메모':
            return  'mediumslateblue'
        default:
            return  'mediumslateblue'; // 기본값
    }
}


function write(){

}
