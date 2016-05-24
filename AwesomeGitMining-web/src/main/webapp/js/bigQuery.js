$(document).ready(function () {

    var myChart1 = echarts.init(document.getElementById('company-pie-bq'), 'macarons');
    var url = "/statistics/bq/companyBQ";
    $.ajax(url, {
        type: 'GET',
        success: function (data) {
            myChart1.setOption({
                title: {
                    text: 'Number of user in each company',
                    x: 'center',
                    y:'top'
                },
                grid:{
                    top:100
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c} ({d}%)"
                },
                legend: {
                    top:'30',
                    orient: 'vertical',
                    left: 'left',
                    data: data.companyName
                },
                series: [
                    {
                        name: 'user',
                        type: 'pie',
                        radius: '80%',
                        center: ['60%', '52%'],
                        data: (function () {
                            var result = [];
                            var len = data.companyName.length;
                            while (len--) {
                                result.push({
                                    name: data.companyName[len],
                                    value: data.companyCount[len]
                                });
                            }
                            return result;
                        })(),
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }
    });

    $.ajax({
        type: "GET",
        url: "/js/PushHour2014.json",
        dataType:"json",
        success: function (data) {
            var hours=[];
            var count=[];
            for(var i = 0;i<data.length;i++){
                hours.push(data[i].hour);
                count.push(data[i].count);
            }
            option = {
                title: {
                    text: '2014 push hour',
                    x: 'center',
                    align: 'right'
                },
                grid: {
                    bottom: 80
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        animation: false
                    }
                },
                legend: {
                    data: ['push time'],
                    x: 'left'
                },
                dataZoom: [
                    {
                        show: true,
                        realtime: true,
                        start: 60,
                        end: 80
                    },
                    {
                        type: 'inside',
                        realtime: true,
                        start: 60,
                        end: 80
                    }
                ],
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        axisLine: {onZero: false},
                        data: hours
                    }
                ],
                yAxis: [
                    {
                        name: 'time',
                        type: 'value',
                        max: 6000000
                    }
                ],
                series: [
                    {
                        name: 'push hour',
                        type: 'line',
                        hoverAnimation: false,
                        areaStyle: {
                            normal: {
                                color:'#b6a2de',
                                opacity:0.3
                            }
                        },
                        lineStyle: {
                            normal: {
                                width: 1,
                                color:'#b6a2de'
                            }
                        },
                        data: count
                    }
                ]
            };
            var myChart2 = echarts.init(document.getElementById('pushTime'),'macarons');
            myChart2.setOption(option);
        }

    })
    
    var myChart3 = echarts.init(document.getElementById('mood'), 'macarons');
    var url = "/statistics/bq/mood";
    $.ajax(url, {
        type: 'GET',
        success: function (data) {
            myChart3.setOption({
                baseOption: {
                    timeline: {
                        axisType: 'category',
                        autoPlay: true,
                        playInterval: 2000,
                        data: [
                            'all','amusement','anger','joy','surprise','swearing'
                        ]
                    },
                    tooltip: { },
                    legend: {
                        x: 'right',
                        data: ['language']
                    },
                    grid: {
                        top: 50,
                        bottom: 100
                    },
                    xAxis: [
                        {
                            'type':'category',
                            splitLine: {show: false}
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {name: 'language', type: 'bar'}
                    ]
                },
                options: [
                    {
                        xAxis :[{
                            data:data.all_name
                        }],
                        title: {
                            text: 'Commit Message With All Languages',
                            x:'center'
                        },
                        series: [{
                            data: data.all_c,
                            itemStyle:{
                                normal:{
                                    color:'#d87a80'
                                }
                            }
                        }]
                    },
                    {
                        xAxis :[{
                            data:data.amu_name
                        }],
                        title: {text: 'Commit Message With Expressions of Amusement',
                            x:'center'},
                        series: [{
                            data: data.amu_c,
                            itemStyle: {
                                normal: {
                                    color: function(params) {
                                        var colorList = [
                                            '#FF4040','#FF6A6A','#FA8072','#F08080','#FF7256','#FF8C69','#FFA07A','#FF7F24','#FF8247','#FF7F50',
                                            '#FF8C00','#FFA500','#FFA54F','#FFB90F','#FFC125','#FFFF00','#FFEC8B','#FFF68F','#FFFACD','#FFFFE0'
                                        ];
                                        return colorList[params.dataIndex]
                                    }
                                }
                            }
                        }]
                    },
                    {
                        xAxis :[{
                            data:data.anger_name
                        }],
                        title: {text: 'Commit Message With Expressions of Anger',
                            x:'center'},
                        series: [
                            {data: data.anger_c}
                        ]
                    },
                    {
                        xAxis :[{
                            data:data.joy_name
                        }],
                        title: {text: 'Commit Message With Expressions of Joy',
                            x:'center'},
                        series: [
                            {data: data.joy_c}
                        ]
                    },
                    {
                        xAxis :[{
                            data:data.sur_name
                        }],
                        title: {text: 'Commit Message With Expressions of Surprise',
                            x:'center'},
                        series: [
                            {data: data.sur_c}
                        ]
                    },
                    {
                        xAxis :[{
                            data:data.swear_name
                        }],
                        title: {text: 'Commit Message With Expressions of Swearing',
                            x:'center'},
                        series: [
                            {data: data.swear_c}
                        ]
                    }
                ]
            });
        }
    });
});

var backgroundColor = [
    "#FFFFCC", "#CCFFFF", "#FFCCCC",
    "#99CCCC", "#FFCC99", "#FFCCCC",
    "#FF9999", "#996699", "#FFFF99",
    "#0099CC", "#FF6666", "#99CC66"
]