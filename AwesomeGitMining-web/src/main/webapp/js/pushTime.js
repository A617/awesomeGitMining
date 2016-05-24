$(document).ready(function () {
    $.ajax({
        //请求方式为get
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
                    data: ['push hour'],
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
                               
                            }
                        },
                        lineStyle: {
                            normal: {
                                width: 1
                            }
                        },
                        data: count
                    }
                ]
            };

            var myChart1 = echarts.init(document.getElementById('pushTime'),'macarons');
            myChart1.setOption(option);
            
        }

    })

});