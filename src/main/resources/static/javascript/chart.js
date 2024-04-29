var ctx = document.getElementById('myChart').getContext('2d');
let chartData = new Array(3);

$(".salesMonthNow").text(function(){
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var currentMonth = currentDate.getMonth()+1;
    currentMonth =currentMonth<10 ? "0"+currentMonth:currentMonth;
    getData(currentYear+"-"+currentMonth+"-01");
    return currentYear+"-"+currentMonth;}
);
$(".salesMonthPre").on("click",function(){
    var nowDate = $(".salesMonthNow").text();
    var currentYear = parseInt( nowDate.split("-")[0]);
    var currentMonth = parseInt(nowDate.split("-")[1]);

    currentMonth--;
    if(currentMonth<1){ currentMonth=12; currentYear--;}
    currentMonth =currentMonth<10 ? "0"+currentMonth:currentMonth;

    $(".salesMonthNow").text(currentYear+"-"+currentMonth);
    if(window.myChart){ window.myChart.destroy();window.myChart1.destroy(); window.myChart2.destroy();}
    getData(currentYear+"-"+currentMonth+"-01");
});
$(".salesMonthNext").on("click",function(){
    var nowDate = $(".salesMonthNow").text();
    var currentYear = parseInt( nowDate.split("-")[0]);
    var currentMonth = parseInt(nowDate.split("-")[1]);

    currentMonth++;
    if(currentMonth>12){ currentMonth=1; currentYear++;}
    currentMonth =currentMonth<10 ? "0"+currentMonth:currentMonth;

    $(".salesMonthNow").text(currentYear+"-"+currentMonth);
    if(window.myChart){ window.myChart.destroy(); window.myChart1.destroy(); window.myChart2.destroy();}
    getData(currentYear+"-"+currentMonth+"-01");
});

function getData(nowDate){

    fetch('/sales/getData?date='+nowDate)
        .then(response => {
            if (!response.ok) {
                throw new Error('서버 확인');
            }
            return response.json(); // JSON 형식의 응답을 파싱하여 반환
        })
        .then(data => {
            chartData= data;
            makeChart(nowDate);
            console.log(chartData);
            console.log(data); // 받은 데이터를 콘솔에 출력하는 예시
        })
        .catch(error => {
            console.error('실패 서버확인', error);
        });
}
function makeChart(nowDate) {
    var start = new Date(nowDate.split("-")[0],nowDate.split("-")[1],0);
    var month = start.getMonth() + 1;
    var lastDay = start.getDate();
    var daysIn = [];
    var weeksIn = [];
    for (var i = 1; i <= lastDay; i++) daysIn.push(month + "." + i);
    for(var i=1; i<=chartData[0][1].length; i++) weeksIn.push(i+"주차");


    window.myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: daysIn, // X축 라벨
            datasets: [{
                label: 'DayIncome',
                data: chartData[0][0], // 첫 번째 데이터 세트
                yAxisID: 'yAxes',
                backgroundColor: '#0d6efd', // 첫 번째 데이터 세트의 배경 색상
                borderColor: '#0d6efd', // 첫 번째 데이터 세트의 테두리 색상
                borderWidth: 1
            },
                {
                    label: 'DayExpense',
                    data: chartData[1][0],
                    yAxisID: 'yAxes',
                    backgroundColor: '#dc3545',
                    borderColor: '#dc3545',
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        callback: function (value, index, values) {
                            return value.toLocaleString('en-US', {
                                style: 'currency',
                                currency: 'USD'
                            });
                        }
                    },
                }]
            }
        }
    });



    const ctx1 = document.getElementById('myChart1');
    window.myChart1 = new Chart(ctx1, {
        type: 'line',
        data: {
            labels: weeksIn,
            datasets: [{
                label: 'WeekIncome',
                data: chartData[0][1],
                borderColor: '#0d6efd',
                backgroundColor: '#0d6efd',
                borderWidth: 1
            },{
                label: 'WeekExpense',
                data: chartData[1][1],
                borderColor: '#dc3545',
                backgroundColor: '#dc3545',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    min: -10000000,
                    max: 10000000,
                    beginAtZero: true
                }
            },
            layout: {
                padding: 10
            }
        }
    });

    const ctx2 = document.getElementById('myChart2');
    window.myChart2 = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June',
                'July', 'August', 'September', 'October', 'November', 'December'],

            datasets: [{
                label: 'MonthIncome',
                data: chartData[0][2],
                borderWidth: 1,
                backgroundColor: '#0d6efd',
            },{
                label: 'MonthExpense',
                data: chartData[1][2],
                borderWidth: 1,
                backgroundColor: '#dc3545',
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}