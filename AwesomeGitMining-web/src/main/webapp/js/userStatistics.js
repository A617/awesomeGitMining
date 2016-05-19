/**
 * Created by Dora on 2016/5/16.
 */
$(function() {
    var chart;

    $(document).ready(function() {

        var myChart1 = echarts.init(document.getElementById('company-pie-local'));
        myChart1.setOption({
            title : {
                text: 'Number of users of each company',
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
                    radius : '50%',
                    center: ['80%', '60%'],
                    data:[],
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
        var url = "/statistics/user/companyLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart1.setOption({
                    legend:{
                        orient: 'vertical',
                        left: 'left',
                        data: data.companyName
                    },
                    series:[{
                        name: 'test',
                        data: [
                            {name:data.companyName[0],value:data.companyCount[0]},
                            {name:data.companyName[1],value:data.companyCount[1]},
                            {name:data.companyName[2],value:data.companyCount[2]},
                            {name:data.companyName[3],value:data.companyCount[3]},
                            {name:data.companyName[4],value:data.companyCount[4]},
                            {name:data.companyName[5],value:data.companyCount[5]},
                            {name:data.companyName[6],value:data.companyCount[6]},
                            {name:data.companyName[7],value:data.companyCount[7]},
                            {name:data.companyName[8],value:data.companyCount[8]},
                            {name:data.companyName[9],value:data.companyCount[9]},
                            {name:data.companyName[10],value:data.companyCount[10]},
                            {name:data.companyName[11],value:data.companyCount[11]},
                            {name:data.companyName[12],value:data.companyCount[12]},
                            {name:data.companyName[13],value:data.companyCount[13]},
                            {name:data.companyName[14],value:data.companyCount[14]},
                            {name:data.companyName[15],value:data.companyCount[15]},
                            {name:data.companyName[16],value:data.companyCount[16]},
                            {name:data.companyName[17],value:data.companyCount[17]},
                            {name:data.companyName[18],value:data.companyCount[18]},
                            {name:data.companyName[19],value:data.companyCount[19]},
                            {name:data.companyName[20],value:data.companyCount[20]},
                            {name:data.companyName[21],value:data.companyCount[21]},
                            {name:data.companyName[22],value:data.companyCount[22]},
                            {name:data.companyName[23],value:data.companyCount[23]},
                            {name:data.companyName[24],value:data.companyCount[24]},
                            {name:data.companyName[25],value:data.companyCount[25]},
                            {name:data.companyName[26],value:data.companyCount[26]},
                            {name:data.companyName[27],value:data.companyCount[27]},
                            {name:data.companyName[28],value:data.companyCount[28]},
                            {name:data.companyName[29],value:data.companyCount[29]}
                        ]
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
                    center: ['50%', '60%'],
                    data:[],
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
        var url = "/statistics/user/typeLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {

                myChart2.setOption({
                    legend:{
                        orient: 'vertical',
                        left: 'left',
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