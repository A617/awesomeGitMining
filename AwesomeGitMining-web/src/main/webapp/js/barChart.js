$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/yearSizeRelation",
        //请求成功完成后要执行的方法
        success: function (obj) {
            var ctx = document.getElementById("barChart").getContext("2d");
            var myBarChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: obj.year,
                    datasets: [
                        {
                            label: "repository average size",
                            backgroundColor: "rgba(245,79,132,0.2)",
                            borderColor: "rgba(255,99,132,1)",
                            borderWidth: 1,
                            hoverBackgroundColor: "rgba(255,99,132,0.4)",
                            hoverBorderColor: "rgba(255,99,132,1)",
                            data: obj.size
                        }
                    ]
                }
            });
        }
    })

});