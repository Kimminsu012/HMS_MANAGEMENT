
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['4.1', '4.2', '4.3', '4.4', '4.5', '4.6', '4.7',
            '4.8', '4.9', '4.10', '4.11', '4.12', '4.13', '4.14',
            '4.15', '4.16', '4.17', '4.18', '4.19', '4.20', '4.21',
            '4.22', '4.23', '4.24', '4.25', '4.26', '2.27', '4.28',
            '4.29', '4.30'],
        datasets: [{
            label: 'Day',
            data: [10000, 20000, 30000, 40000, 50000, 60000, 70000,
                70000, 60000, 50000, 40000, 30000, 20000, 10000,
                10000, 20000, 30000, 40000, 50000, 60000, 70000,
                70000, 60000, 50000, 40000, 30000, 20000, 10000,
                10000,20000],
            // 추가적인 옵션들
            yAxisID: 'y-axis-0',
            backgroundColor: '#0d6efd',
            borderColor: '#0d6efd',
            borderWidth: 4
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    callback: function(value, index, values) {
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

const ctx1=document.getElementById('myChart1');
new Chart(ctx1,{
    type: 'line',
    data:{
        labels:['1주차','2주차','3주차','4주차'],
        datasets: [{
            label:'Week',
            data:[20000,60000,30000,100000],
            borderColor: '#198754',
            backgroundColor: '#198754',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y:{
                min:0,
                max:100000,
                beginAtZero: true
            }
        },
        layout:{
            padding:10
        }
    }
});

const ctx2=document.getElementById('myChart2');
new Chart(ctx2,{
    type: 'bar',
    data:{
        labels: ['January', 'February', 'March', 'April', 'May', 'June',
            'July','August','September','October','November','December'],

        datasets: [{
            label:'Month',
            data:[10000,20000,30000,40000,50000,60000
                ,70000,80000,90000,100000,110000,120000],
            borderWidth: 1,
            backgroundColor:'#dc3545',
        }]
    },
    options: {
        scales: {
            y:{
                beginAtZero: true
            }
        }
    }
});
