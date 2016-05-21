/**
 * Created by Dora on 2016/5/16.
 */
$(function() {
    var chart;

    $(document).ready(function() {

        var myChart1 = echarts.init(document.getElementById('company-pie-local'),'macarons');
        var url = "/statistics/user/companyLocal";
        myChart1 .showLoading({
            text : 'Loading...',
            effect : 'spin',
            textStyle : {
                fontSize : 25
            }
        });
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                myChart1.hideLoading();
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
                                color:'#CD919E'
                            }
                        }
                    }]
                });
            }
        });


        var myChart2 = echarts.init(document.getElementById('type-pie-local'),'macarons');
        var url = "/statistics/user/typeLocal";
        myChart2 .showLoading({
            text : 'Loading...',
            effect : 'spin',
            textStyle : {
                fontSize : 25
            }
        });
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                myChart2.hideLoading();
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
                            radius : '70%',
                            center: ['50%', '50%'],
                            data: (function(){
                                var result = [];
                                var len = data.typeName.length;
                                while (len--) {
                                    result.push({
                                        name: data.typeName[len],
                                        value: data.typeCount[len]
                                    });
                                }
                                return result;
                            })(),
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

        var myChart3 = echarts.init(document.getElementById('create_year'),'macarons');
        var url = "/statistics/user/createYear";
        myChart3 .showLoading({
            text : 'Loading...',
            effect : 'spin',
            textStyle : {
                fontSize : 25
            }
        });
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                myChart3.hideLoading();
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
                        },
                        {
                            name: 'repository',
                            type: 'bar',
                            data: data.repos
                        }
                    ]
                });
            }
        });

        var myChart4 = echarts.init(document.getElementById('email'),'macarons');
        var url = "/statistics/user/email";
        myChart4 .showLoading({
            text : 'Loading...',
            effect : 'spin',
            textStyle : {
                fontSize : 25
            }
        });
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                myChart4.hideLoading();
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
                                color:'#ffb980'
                            }
                        }
                    }]
                });
            }
        });

        var myChart5 = echarts.init(document.getElementById('blog'),'macarons');
        var url = "/statistics/user/blog";
        myChart5 .showLoading({
            text : 'Loading...',
            effect : 'spin',
            textStyle : {
                fontSize : 25
            }
        });
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                myChart5.hideLoading();
                myChart5.setOption({
                    title : {
                        text: 'Blog Counts',
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
                                color:'#7EC0EE'
                            }
                        }
                    }]
                });
            }
        });

        var myChart6 = echarts.init(document.getElementById('follow'),'macarons');
         var url = "/statistics/user/follow";
        myChart6 .showLoading({
            text : 'Loading...',
            effect : 'spin',
            textStyle : {
                fontSize : 25
            }
        });
        $.ajax(url, {
            type: 'GET', 
            success: function (data, textStatus) {
            myChart6.hideLoading();
            myChart6.setOption({             
                title : {                 
                    text: 'Followers and Followings Per User',                 
                    x:'center',                 
                    y:'bottom'             
                },             
                tooltip: {                 
                    trigger: 'axis'             
                },             
                legend: {                 
                    data:['Followers','Followings']             
                },             
                xAxis: {                 
                    type: 'category',                 
                    splitLine: {show: false},                 
                    data: data.Name             
                },             
                yAxis: {                 
                    type: 'value'             
                },             
                series: [                 
                    {                     
                        name: 'Followers',                     
                        type: 'line',                     
                        data: data.Count1,                 
                    },                 
                    {                     
                        name: 'Followings',                    
                        type: 'line',                    
                        data: data.Count2,                 
                    }             
                ]         
            }); 
        } });
    });
});

var backgroundColor = [
    "#FFFFCC", "#CCFFFF", "#FFCCCC",
    "#99CCCC", "#FFCC99", "#FFCCCC",
    "#FF9999", "#996699", "#FFFF99",
    "#0099CC", "#FF6666", "#99CC66"
];