$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/forkVSstar",
        success: function (data) {
            var d = [];
            var xList = data.xList;
            var yList = data.yList;
            for (var i = 0; i < data.xList.length; i++) {
                d.push([xList[i], yList[i]]);
            }
            var markLineOpt = {
                lineStyle: {
                    normal: {
                        type: 'solid'
                      //  color:'#6495ED'
                    }
                },
                tooltip: {
                    formatter: 'fork vs star'
                },
                data: [[{
                    coord: [0, 0],
                    symbol: 'none'
                }, {
                    coord: [9609, 36996],
                    symbol: 'none'
                }]]
            };

            option = {
                title: {
                    text: 'Star vs Fork',
                    x: 'center',
                    y: 0
                },

                tooltip: {
                    formatter: '({c})'
                },
                xAxis: [
                    {gridIndex: 0, min: 0, max: data.Xmax, name: "fork"}
                ],
                yAxis: [
                    {gridIndex: 0, min: 0, max: data.Ymax, name: "star"}
                ],

                series: [
                    {
                        name: 'I',
                        type: 'scatter',
                        xAxisIndex: [0],
                        yAxisIndex: [0],
                        data: d,
                        markLine: markLineOpt
                    }
                ]
            };
            var myChart1 = echarts.init(document.getElementById('test'));
            myChart1.setOption(option);
        }
    })

});