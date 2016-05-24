$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/js/codeFrequency.json",
        dataType: "json",
        success: function (obj) {
            var data = [];
            var week = [];
            for (var i = 1; i < obj.length + 1; i++) {
                
                week.push(i+" week");
                data.push(obj[i-1][1]-obj[i-1][2]);
            }

            option = {
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                title: {
                    left: 'center',
                    text: 'code frequency',
                },
                legend: {
                    top: 'bottom',
                    data: ['意向']
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: week
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%']
                },
                dataZoom: [{
                    type: 'inside',
                    start: 0,
                    end: 10
                }, {
                    start: 0,
                    end: 10
                }],
                series: [
                    {
                        name: 'additions',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',
                        areaStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(255, 158, 68)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(255, 70, 131)'
                                }])
                            }
                        },
                        data: data
                    }
                ]
            };
            var myChart1 = echarts.init(document.getElementById('codeFrequency'), 'macarons');
            myChart1.setOption(option);
        }

    })

});