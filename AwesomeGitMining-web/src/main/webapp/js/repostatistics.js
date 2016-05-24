$(function() {
	var chart;

	$(document).ready(function() {

		var myChart1 = echarts.init(document.getElementById('lan_size'),'macarons');
		var url = "/statistics/repository/lan_size";
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
				var d = echarts.dataTool.prepareBoxplotData(
					data.Count
				);
				myChart1.hideLoading();
				myChart1.setOption({
					title: [
						{
							text: 'Distribution Of Language And Size',
							left: 'center'
						}
					],
					tooltip: {
						trigger: 'item',
						axisPointer: {
							type: 'shadow'
						}
					},
					grid: {
						left: '10%',
						right: '10%',
						bottom: '15%'
					},
					xAxis: {
						type: 'category',
						data: data.Name,
						boundaryGap: true,
						nameGap: 30,
						splitArea: {
							show: false
						},
						axisLabel: {
							formatter: '{value}'
						},
						splitLine: {
							show: false
						}
					},
					yAxis: {
						type: 'value',
						splitArea: {
							show: true
						}
					},
					series: [
						{
							name: 'boxplot',
							type: 'boxplot',
							data: d.boxData
						},
						{
							name: 'outlier',
							type: 'scatter',
							data: d.outliers
						}
					]
				});
			}
		});

		var myChart2 = echarts.init(document.getElementById('lan_fork'),'macarons');
		var url = "/statistics/repository/lan_fork";
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
				var d = echarts.dataTool.prepareBoxplotData(
					data.Count
				);
				myChart2.hideLoading();
				myChart2.setOption({
					title: [
						{
							text: 'Distribution Of Language And Fork Number',
							left: 'center'
						}
					],
					tooltip: {
						trigger: 'item',
						axisPointer: {
							type: 'shadow'
						}
					},
					grid: {
						left: '10%',
						right: '10%',
						bottom: '15%'
					},
					xAxis: {
						type: 'category',
						data: data.Name,
						boundaryGap: true,
						nameGap: 30,
						splitArea: {
							show: false
						},
						axisLabel: {
							formatter: '{value}'
						},
						splitLine: {
							show: false
						}
					},
					yAxis: {
						type: 'value',
						splitArea: {
							show: true
						}
					},
					series: [
						{
							name: 'boxplot',
							type: 'boxplot',
							data: d.boxData
						},
						{
							name: 'outlier',
							type: 'scatter',
							data: d.outliers
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