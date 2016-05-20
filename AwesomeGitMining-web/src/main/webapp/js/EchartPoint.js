$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/forkVSstar",
        success: function (data) {
            var d = [];
            var xList = data.xList;
            var yList = data.yList;
            for(var i = 0;i<data.xList.length;i++){
                d.push([xList[i],yList[i]]);
            }
            // var markLineOpt = {
            //     label: {
            //         normal: {
            //             formatter: 'y = 0.5 * x + 3',
            //             textStyle: {
            //                 align: 'right'
            //             }
            //         }
            //     },
            //     lineStyle: {
            //         normal: {
            //             type: 'solid'
            //         }
            //     },
            //     tooltip: {
            //         formatter: 'y = 0.5 * x + 3'
            //     },
            //     data: [[{
            //         coord: [0, 3],
            //         symbol: 'none'
            //     }, {
            //         coord: [20, 13],
            //         symbol: 'none'
            //     }]]
            // };

            option = {
                title: {
                    text: 'Star vs Fork',
                    x: 'center',
                    y: 0
                },
                
                tooltip: {
                    formatter: 'Group {a}: ({c})'
                },
                xAxis: [
                    {gridIndex: 0, min: 0, max: 20}
                ],
                yAxis: [
                    {gridIndex: 0, min: 0, max: 15}
                ],
                
                series: [
                    {
                        name: 'I',
                        type: 'scatter',
                        xAxisIndex: [0],
                        yAxisIndex: [0],
                        data: d
                      //  markLine: markLineOpt
                    }
                ]
            };
            var myChart1 = echarts.init(document.getElementById('test'));
            myChart1.setOption(option);
        }
    })

});