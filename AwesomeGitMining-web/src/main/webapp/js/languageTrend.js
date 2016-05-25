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
                data: {
                    labels: obj.year,
                    datasets: [
                        {
                            label: names[0],
                            backgroundColor: "rgba(255,250,240,0.2)",
                            data: obj.Ruby
                        },
                        {
                            data: obj.Python,
                            backgroundColor: "rgba(	253 ,245 ,230,0.2)",
                            label: names[1]
                        },
                        {
                            data: obj.JavaScript,
                            backgroundColor: "rgba(250 ,240, 230,0.2)",
                            label: names[2]
                        },
                        {
                            data: obj.PHP,
                            backgroundColor: "rgba(250 ,235, 215,0.2)",
                            label: names[3]
                        },
                        {
                            data: obj.C,
                            backgroundColor: "rgba(255 ,239 ,213,0.2)",
                            label: names[4]
                        },
                        {
                            data: obj.Perl,
                            backgroundColor: "rgba(255, 235 ,205,0.2)",
                            label: names[5]
                        },
                        {
                            data: obj.Java,
                            backgroundColor: "rgba(255, 228 ,196,0.2)",
                            label: names[6]
                        },
                        {
                            data: obj.C++,
                            backgroundColor: "rgba(255, 218, 185,0.2)",
                            label: names[7]
                        },
                        {
                            data: obj.HTML,
                            backgroundColor: "rgba(255, 222, 173,0.2)",
                            label: names[8]
                        }
                    ]
                }
            });

        }
    })
});
$(document).ready(function () {
    var myChart1 = echarts.init(document.getElementById('languageTrend'));
    myChart1 .showLoading({
        text : 'Loading...',
        effect : 'spin',
        textStyle : {
            fontSize : 25
        }
    });
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/languageTrendDynamic",
        success: function (data) {
            myChart1.hideLoading();
            var dataMap = {};

            function dataFormatter(obj) {
                var pList = data.name;
                var temp;
                for (var year = 2007; year <= 2017; year++) {

                    temp = obj[year];
                    for (var i = 0, l = temp.length; i < l; i++) {

                        obj[year][i] = {
                            name: pList[i],
                            value: temp[i]
                        }
                    }

                }
                return obj;
            }

            dataMap.dataGDP = dataFormatter({
                //max : 60000,
                2007: data.year1,
                2008: data.year2,
                2009: data.year3,
                2010: data.year4,
                2011: data.year5,
                2012: data.year6,
                2013: data.year7,
                2014: data.year8,
                2015: data.year9,
                2016: data.year10,
                2017: data.forecast
            });


            option = {
                baseOption: {
                    timeline: {
                        axisType: 'category',
                        autoPlay: true,
                        playInterval: 2000,
                        data: [
                            '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016','2017'
                        ],
                        label: {
                            formatter: function (s) {
                                return (new Date(s)).getFullYear();
                            }
                        }
                    },

                    tooltip: {},
                    legend: {
                        x: 'right',
                        data: ['language']
                    },
                    calculable: true,
                    grid: {
                        top: 80,
                        bottom: 100
                    },
                    xAxis: [
                        {
                            'type': 'category',
                            'axisLabel': {'interval': 0},
                            'data': data.name,
                            splitLine: {show: false}
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: 'repository number',
                            // max: 53500
                            max: 400
                        }
                    ],
                    series: [
                        {name: 'language', type: 'bar'},

                    ]
                },
                options: [
                    {
                        title: {
                            text: '2007 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }
                        },
                        series: [
                            {
                                data: dataMap.dataGDP['2007'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2008 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2008'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2009 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2009'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2010 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2010']
                                , itemStyle: {
                                normal: {
                                    color: '#7EC0EE',
                                    opacity: 0.5
                                }
                            }
                            }

                        ]
                    },
                    {
                        title: {text: '2011 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2011'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2012 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2012'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2013 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2013'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2014 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2014'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2015 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2015'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2016 language usage',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2016'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    },
                    {
                        title: {text: '2017 language usage(forecast)',
                            x:'center',
                            textStyle: {
                                fontWeight: 'normal',
                                color: '#008acd'
                            }},
                        series: [
                            {
                                data: dataMap.dataGDP['2017'],
                                itemStyle: {
                                    normal: {
                                        color: '#7EC0EE',
                                        opacity: 0.5
                                    }
                                }
                            }

                        ]
                    }
                ]
            };
            myChart1.setOption(option);
        }
    })
});