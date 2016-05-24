$(document).ready(function () {
    var chart;
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/js/world.json",
        success: function (data) {
            echarts.registerMap('world', data);
            chart = echarts.init(document.getElementById('worldMap'),'macarons');
            chart .showLoading({
                text : 'Loading...',
                effect : 'spin',
                textStyle : {
                    fontSize : 25
                }
            });
        }
    });
    
    $.ajax({
        type: "GET",
        url: "/statistics/user/location",
        success: function (obj) {
            option = {
                title: {
                    text: 'user location',
                    left: 'center',
                    top: 'bottom'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        var value = (params.value + '').split('.');
                        value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                        return params.seriesName + '<br/>' + params.name + ' : ' + value;
                    }
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                visualMap: {
                    min: 0,
                    max: 3000,
                    text: ['High', 'Low'],
                    realtime: false,
                    calculable: true,
                    color: ['orangered', 'yellow', 'lightskyblue']
                },
                series: [
                    {
                        name: 'user number',
                        type: 'map',
                        mapType: 'world',
                        roam: true,
                        itemStyle: {
                            emphasis: {label: {show: true}}
                        },
                        data:obj
                    }
                ]
            };
            chart.hideLoading();
            chart.setOption(option);
        }
    })

});