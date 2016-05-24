$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/js/2016events.json",
        dataType: "json",
        success: function (data) {

            var schema = [
                {name: 'month', index: 0, text: 'month'},
                {name: 'CommitCommentEvent', index: 1, text: 'CommitCommentEvent'},
                {name: 'CreateEvent', index: 2, text: 'CreateEvent'},
                {name: 'PublicEvent', index: 3, text: 'PublicEvent'},
                {name: 'IssuesEvent', index: 4, text: ' IssuesEvent'},
                {name: 'ReleaseEvent', index: 5, text: 'ReleaseEvent'},
                {name: 'PushEvent', index: 6, text: 'PushEvent'},
                {name: 'WatchEvent', index: 7, text: 'WatchEvent'},
                {name: 'ForkEvent', index: 8, text: 'ForkEvent'},
                {name: 'PullRequestEvent', index: 9, text: 'PullRequestEvent'}
            ];

            var lineStyle = {
                normal: {
                    width: 1,
                    opacity: 0.5
                }
            };

            option = {
                title: {
                    text: 'various events in 2016',
                    x: 'center',
                    y: -10
                },
                //  backgroundColor: '#333',
                legend: {
                    bottom: 30,
                    data: ['event count'],
                    itemGap: 20,
                    textStyle: {
                        // color: '#fff',
                        fontSize: 14
                    }
                },
                tooltip: {
                    padding: 10,
                    // backgroundColor: '#222',
                    //  borderColor: '#777',
                    borderWidth: 1,
                    formatter: function (obj) {
                        var value = obj[0].value;
                        return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                            + obj[0].seriesName + ' ' + value[0] + '日期：'
                            + value[7]
                            + '</div>'
                            + schema[1].text + '：' + value[1] + '<br>'
                            + schema[2].text + '：' + value[2] + '<br>'
                            + schema[3].text + '：' + value[3] + '<br>'
                            + schema[4].text + '：' + value[4] + '<br>'
                            + schema[5].text + '：' + value[5] + '<br>'
                            + schema[6].text + '：' + value[6] + '<br>'
                            + schema[7].text + '：' + value[7] + '<br>'
                            + schema[8].text + '：' + value[8] + '<br>'
                            + schema[9].text + '：' + value[9] + '<br>';


                    }
                },

                parallelAxis: [
                    {dim: 0, name: schema[0].text, inverse: true, max: 5, nameLocation: 'start'},
                    {dim: 1, name: schema[1].text},
                    {dim: 2, name: schema[2].text},
                    {dim: 3, name: schema[3].text},
                    {dim: 4, name: schema[4].text},
                    {dim: 5, name: schema[5].text},
                    {dim: 6, name: schema[6].text},
                    {dim: 7, name: schema[7].text},
                    {dim: 8, name: schema[8].text},
                    {dim: 9, name: schema[9].text}
                ],
                visualMap: {
                    show: true,
                    min: 3000,
                    max: 1200000,
                    dimension: 2,
                    inRange: {
                        color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
                    }
                },
                parallel: {
                    left: '5%',
                    right: '18%',
                    bottom: 100,
                    parallelAxisDefault: {
                        type: 'value',
                        name: 'event',
                        nameLocation: 'end',
                        nameGap: 20,
                        nameTextStyle: {
                            //  color: '#fff',
                            fontSize: 12
                        },
                        axisLine: {
                            lineStyle: {
                                //      color: '#aaa'
                            }
                        },
                        axisTick: {
                            lineStyle: {
                                //   color: '#777'
                            }
                        },
                        splitLine: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                //   color: '#fff'
                            }
                        }
                    }
                },
                series: [
                    {
                        name: 'event count',
                        type: 'parallel',
                        lineStyle: lineStyle,
                        data: data
                    }
                ]
            };
            var myChart1 = echarts.init(document.getElementById('eventShow'), 'macarons');
            myChart1.setOption(option);
        }

    })

});