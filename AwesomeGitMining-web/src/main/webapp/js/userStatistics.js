/**
 * Created by Dora on 2016/5/16.
 */
$(function() {
    var chart;

    $(document).ready(function() {

        var myChart1 = echarts.init(document.getElementById('company-pie-local'));
        var url = "/statistics/user/companyLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

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
                        data: data.companyName
                    },
                    yAxis: {},
                    series: [{
                        name: 'user',
                        type: 'bar',
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
        var url = "/statistics/user/typeLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

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
                    legend:{
                        orient: 'vertical',
                        center: 'center',
                        data:data.typeName
                    },
                    series : [
                        {
                            name:'test',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '50%'],
                            data:[
                                {name:data.typeName[0],value:data.typeCount[0]}
                            ],
                            itemStyle: {
                                normal:{
                                    color:'#FFDEAD'
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
            }
        });

        var myChart3 = echarts.init(document.getElementById('create_year'));
        var url = "/statistics/user/createYear";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart3.setOption({
                    title : {
                        text: 'Distribution Of Create Time',
                        x:'center',
                        y:'bottom'
                    },
                    tooltip : {},
                    legend: {
                        data:['user','repository']
                    },
                    xAxis: {
                        data: data.year
                    },
                    yAxis: {},
                    series: [
                        {
                            name: 'user',
                            type: 'bar',
                            data: data.Count,
                            itemStyle:{
                                normal: {
                                    color:'#CD919E'
                                }
                            }
                        },
                        {
                            name: 'repository',
                            type: 'bar',
                            data: data.repos,
                            itemStyle:{
                                normal: {
                                    color:'#6495ED'
                                }
                            }
                        }
                    ]
                });
            }
        });

        var myChart4 = echarts.init(document.getElementById('email'));
        var url = "/statistics/user/email";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart4.setOption({
                    title : {
                        text: 'Email Counts',
                        x:'center',
                        y:'bottom'
                    },
                    tooltip : {},
                    legend: {
                        data:['user']
                    },
                    xAxis: {
                        data: data.Name
                    },
                    yAxis: {},
                    series: [{
                        name: 'user',
                        type: 'bar',
                        data: data.Count,
                        itemStyle:{
                            normal: {
                                color:'#4682B4'
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