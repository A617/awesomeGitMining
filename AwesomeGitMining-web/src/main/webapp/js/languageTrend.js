$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/languageTrend",
        success: function (obj) {
            var ctx = document.getElementById("lineChart").getContext("2d");
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data : {
                    labels: obj.languageName,
                    datasets: [
                        {
                           // fillColor: "rgba(220,220,220,0.2)",
                           // strokeColor: "rgba(220,220,220,1)",
                            data: obj.language1,
                            label: "language1"
                        },
                        {
                           // fillColor: "rgba(151,187,205,0.2)",
                           // strokeColor: "rgba(151,187,205,1)",
                            data: obj.language2,
                            label:"language2"
                        }
                    ]
                }
            });
        
        }
    })
});

