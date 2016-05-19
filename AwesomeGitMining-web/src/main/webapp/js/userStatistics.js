/**
 * Created by Dora on 2016/5/16.
 */
$(function() {
    var chart;

    $(document).ready(function() {

        var myChart1 = echarts.init(document.getElementById('company-pie-local'));
        myChart1.setOption({
            title : {
                text: 'Number of user in each company',
                x:'center',
                y:'bottom'
            },
            tooltip : {},
            legend: {
                data:['user']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: 'user',
                type: 'bar',
                data: []
            }]
        });
        var url = "/statistics/user/companyLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart1.setOption({
                    xAxis: {
                        data: data.companyName
                    },
                    series: [{
                        name: 'user',
                        data: data.companyCount,
                        itemStyle:{
                            normal: {
                                color:'#4682B4'
                            }
                        }
                    }]
                });
            }
        });


        var myChart2 = echarts.init(document.getElementById('type-pie-local'));
        myChart2.setOption({
            title : {
                text: 'User Type',
                x:'center',
                y:'bottom'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },

            series : [
                {
                    name:'test',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[],
                    itemStyle: {
                        normal:{
                            color:'#388E8E'
                        },
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
        var url = "/statistics/user/typeLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart2.setOption({
                    legend:{
                        orient: 'vertical',
                        center: 'center',
                        data:data.typeName
                    },
                    series:[{
                        name:'test',
                        data:[
                            {name:data.typeName[0],value:data.typeCount[0]}
                        ]
                    }]
                });
            }
        });

        var myChart3 = echarts.init(document.getElementById('create_year'));
        myChart3.setOption({
            title : {
                text: 'User Create Year',
                x:'center',
                y:'bottom'
            },
            tooltip : {},
            legend: {
                data:['user']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: 'user',
                type: 'bar',
                data: []
            }]
        });
        var url = "/statistics/user/createYear";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart3.setOption({
                    xAxis: {
                        data: data.year
                    },
                    series: [{
                        name: 'user',
                        data: data.Count,
                        itemStyle:{
                            normal: {
                               color:'#CD919E'
                            }
                        }
                    }]
                });
            }
        });


        var url = "/statistics/user/companyBQ";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                var data = {
                    labels: data.companyName,
                    datasets: [{
                        data: data.companyCount,
                        backgroundColor: backgroundColor,
                        hoverBackgroundColor: backgroundColor
                    }]
                };

                var pieConfig = {
                    type: 'pie',
                    data: data,
                    options: {
                        responsive: true
                    }
                };

                var ctx = document.getElementById("company-pie-bq").getContext("2d");
                chart = new Chart(ctx, pieConfig);
            }
        });
    });
});

var backgroundColor = [
    "#FFFFCC", "#CCFFFF", "#FFCCCC",
    "#99CCCC", "#FFCC99", "#FFCCCC",
    "#FF9999", "#996699", "#FFFF99",
    "#0099CC", "#FF6666", "#99CC66"
];