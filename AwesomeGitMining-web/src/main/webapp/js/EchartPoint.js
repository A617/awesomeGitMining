//fork和star的分布关系
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
                    coord: [6000, 30000],
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
                    {gridIndex: 0, min: 0, max: 6000, name: "fork"}
                ],
                yAxis: [
                    {gridIndex: 0, min: 0, max: 30000, name: "star"}
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
            var myChart1 = echarts.init(document.getElementById('forkStarRelation'),'macarons');
            myChart1.setOption(option);
        }
    })

});
//year和size的分布关系
// $(document).ready(function () {
//     $.ajax({
//         //请求方式为get
//         type: "GET",
//         url: "/statistics/repository/yearSizeRelation",
//         success: function (data) {
//             var d = [];
//             var xList = data.xList;
//             var yList = data.yList;
//             for (var i = 0; i < data.xList.length; i++) {
//                 d.push([xList[i], yList[i]]);
//             }
//             var markLineOpt = {
//                 lineStyle: {
//                     normal: {
//                         type: 'solid'
//                         //  color:'#6495ED'
//                     }
//                 },
//                 tooltip: {
//                     formatter: 'year vs size'
//                 },
//                 data: [[{
//                     coord: [2007, 0],
//                     symbol: 'none'
//                 }, {
//                     coord: [2010, 36996],
//                     symbol: 'none'
//                 }]]
//             };
//
//             option = {
//                 title: {
//                     text: 'Year vs Size',
//                     x: 'center',
//                     y: 0
//                 },
//
//                 tooltip: {
//                     formatter: '({c})'
//                 },
//                 xAxis: [
//                     {gridIndex: 0, min: 2007, max: data.Xmax, name: "year"}
//                 ],
//                 yAxis: [
//                     {gridIndex: 0, min: 0, max: data.Ymax, name: "size"}
//                 ],
//
//                 series: [
//                     {
//                         name: 'I',
//                         type: 'scatter',
//                         xAxisIndex: [0],
//                         yAxisIndex: [0],
//                         data: d,
//                         markLine: markLineOpt
//                     }
//                 ]
//             };
//             var myChart1 = echarts.init(document.getElementById('yearSizeRelation'));
//             myChart1.setOption(option);
//         }
//     })
//
// });