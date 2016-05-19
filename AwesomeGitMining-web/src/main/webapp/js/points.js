$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
       // url: "/statistics/repository/forkDistribute",
        //请求成功完成后要执行的方法
        success: function (data) {
            
            var body = d3.select("#local");

            var width = 400;
            var height = 400;

            var svg = body.append("svg")
                .attr("width", 400)
                .attr("height", 400);
            var dataset = data.dataset;

            

// 比例尺

            var xScale = d3.scale.linear()
                .domain([0, 100])
                .range([0, 300]);

            var yScale = d3.scale.linear()
                .domain([100, 0])
                .range([0, 250]);
// 坐标轴

            var xAxis = d3.svg.axis()
                .scale(xScale)
                .orient("bottom");

            var gxAxis = svg.append("g")
                .attr("class", "axis")
                .attr("transform", "translate(30,300)")
                .call(xAxis);

            var yAxis = d3.svg.axis()
                .scale(yScale)
                .orient("left");

            var gyAxis = svg.append("g")
                .attr("class", "axis")
                .attr("transform", "translate(30,50)")
                .call(yAxis);

// 散点

            var points = svg.selectAll(".MyCircle")
                .data(dataset)
                .enter()
                .append("circle")
                .attr("class", "MyCircle")
                .attr("transform", "translate(30,50)")
                .attr("r", 2)
                .attr("cx", function (d) {
                    return xScale(d.x);
                })
                .attr("cy", function (d) {
                    return yScale(d.y);
                });
        }
    })

});