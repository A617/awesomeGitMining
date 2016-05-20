$(function() {
    var chart;

    $(document).ready(function() {

        var myChart1 = echarts.init(document.getElementById('company-pie-bq'));
        var url = "/statistics/bq/companyBQ";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                myChart1.setOption({
                    title : {
                        text: 'Number of user in each company',
                        x:'center',
                        y:'bottom'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{b} : {c} ({d}%)"
                    },
                    legend:{
                        orient: 'vertical',
                        left: 'left',
                        data:data.companyName
                    },
                    series : [
                        {
                            name:'user',
                            type: 'pie',
                            radius : '80%',
                            center: ['60%', '44%'],
                            data: (function(){
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
    });
});

var backgroundColor = [
    "#FFFFCC", "#CCFFFF", "#FFCCCC",
    "#99CCCC", "#FFCC99", "#FFCCCC",
    "#FF9999", "#996699", "#FFFF99",
    "#0099CC", "#FF6666", "#99CC66"
];