/**
 * Created by Dora on 2016/5/16.
 */
$(function() {
    var chart;

    $(document).ready(function() {

        var url = "/statistics/user/companyLocal";
        $.ajax(url, {
            type: 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
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

                var ctx = document.getElementById("company-pie-local").getContext("2d");
                chart = new Chart(ctx, pieConfig);
            }
        });

        var url = "/statistics/user/typeLocal";
        $.ajax(url, {
            type: 'GET',
            success: function (data, textStatus) {
                var data = {
                    labels: data.typeName,
                    datasets: [{
                        data: data.typeCount,
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

                var ctx = document.getElementById("type-pie-local").getContext("2d");
                chart = new Chart(ctx, pieConfig);
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