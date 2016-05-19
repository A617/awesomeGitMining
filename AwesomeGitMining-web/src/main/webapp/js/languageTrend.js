$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/languageTrend",
        success: function (obj) {
            var ctx = document.getElementById("lineChart").getContext("2d");
            var names = obj.name;
            
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data : {
                    labels: obj.year,
                    datasets: [
                        {
                            data: obj.Ruby,
                            label: names[0]
                        },
                        {
                            data: obj.Python,
                            label:names[1]
                        },
                        {
                            data: obj.JavaScript,
                            label: names[2]
                        },
                        {
                            data: obj.PHP,
                            label:names[3]
                        },
                        {
                            data: obj.C,
                            label: names[4]
                        },
                        {
                            data: obj.Perl,
                            label:names[5]
                        },
                        {
                            data: obj.Java,
                            label: names[6]
                        },
                        {
                            data: obj.C++,
                            label:names[7]
                        },
                        {
                            data: obj.HTML,
                            label: names[8]
                        }
                    ]
                }
            });
        
        }
    })
});

