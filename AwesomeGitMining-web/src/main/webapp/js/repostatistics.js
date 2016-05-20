$(function() {
	var chart;

	$(document).ready(function() {
		// get company
		var url = "/json/company2014"
		$.ajax(url, {
			type : 'GET',
			// async : false,
			// contentType : 'application/json',
			// dataType : 'json',
			success : function(data, textStatus) {
				// Set up the chart
				var companychart = new Highcharts.Chart({
					chart : {
						renderTo : 'language',
						type : 'column',
						margin : 100,
						options3d : {
							enabled : true,
							alpha : 5,
							beta : 15,
							depth : 50,
							viewDistance : 25
						}
					},
					title : {
						text : 'Numbers of Repository in Different Languages'
					},
					plotOptions : {
						column : {
							depth : 25
						}
					},
					xAxis : {
						categories : data.language
					},
					yAxis : {
						title : {
							text : 'Numbers of Repository'
						},
					},
					tooltip : {
						valueSuffix : ''
					},
					series : [ {
						name : 'Language',
						data : data.number
					} ]
				});
			}
		});

    });

});