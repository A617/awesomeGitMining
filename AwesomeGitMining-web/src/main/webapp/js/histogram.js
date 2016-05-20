//fork分布
$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/forkDistribute",
        //请求成功完成后要执行的方法
        success: function (data) {
            var width = 1000;
            var height = 500;
            var title = "Fork distribution";
//添加SVG绘制区域
            var svg = d3.select("#local").append("svg")
                .attr("width", width)
                .attr("height", height);
            //添加标题
            svg.append("g")
                .append("text")
                .text(title)
                .attr("class", "title")
                .attr("x", width / 2 -100)
                .attr("y", 20);

            var dataset = data.dataset;
            console.log(dataset);

//定义布局
            var bin_num = 20;
            var max = data.max;
            var histogram = d3.layout.histogram()
                .range([0, max])
                .bins(bin_num)
                .frequency(true);

//转换数据，输出数据
            var data = histogram(dataset);
            console.log(data);

//定义比例尺
            var max_height = 400;
            var rect_step = 30;
            var heights = [];
            for (var i = 0; i < data.length; i++) {
                heights.push(data[i].y);
            }
            var yScale = d3.scale.linear()
                .domain([d3.min(heights), d3.max(heights)])
                .range([0, max_height]);

            //绘制图形
            var graphics = svg.append("g")
                .attr("transform", "translate(30,20)");
            for (var i = 0; i < data.length; i++) {
                graphics.append("text")
                    .attr("x", i * rect_step)
                    .attr("y", max_height)
                    .attr("dx", rect_step / 2 - 8)
                    .attr("dy", "15px")
                    .text(Math.floor(data[i].x));
                graphics.append("text")
                    .attr("x", i * rect_step)
                    .attr("y", max_height - yScale(data[i].y) - 20)
                    .attr("dx", rect_step / 2 - 8)
                    .attr("dy", "15px")
                    .text(Math.floor(data[i].y));
            }
            //绘制矩形
            graphics.selectAll("rect")
                .data(data)
                .enter()
                .append("rect")
                .attr("height", 0)
                .attr("y", max_height)
                .attr("class", "test")
                .transition()
                .duration(3000)
                .attr("x", function (d, i) {
                    return i * rect_step;
                })
                .attr("y", function (d, i) {
                    return max_height - yScale(d.y);
                })
                .attr("width", function (d, i) {
                    return rect_step - 2;
                })
                .attr("height", function (d) {
                    return yScale(d.y);
                })
                .attr("fill", "steelblue");

            //绘制坐标轴的直线
            graphics.append("line")
                .attr("stroke", "black")
                .attr("stroke-width", "1px")
                .attr("x1", 0)
                .attr("y1", max_height)
                .attr("x2", data.length * rect_step)
                .attr("y2", max_height);

            //绘制坐标轴的分隔符直线
            graphics.selectAll(".linetick")
                .data(data)
                .enter()
                .append("line")
                .attr("stroke", "black")
                .attr("stroke-width", "1px")
                .attr("x1", function (d, i) {
                    return i * rect_step + rect_step / 2;
                })
                .attr("y1", max_height)
                .attr("x2", function (d, i) {
                    return i * rect_step + rect_step / 2;
                })
                .attr("y2", max_height + 5);
        }
    })

});
//star分布
$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/starDistribute",
        //请求成功完成后要执行的方法
        success: function (data) {
            var width = 1000;
            var height = 500;
            var title = "Star distribution";
//添加SVG绘制区域
            var svg = d3.select("#local").append("svg")
                .attr("width", width)
                .attr("height", height);
            //添加标题
            svg.append("g")
                .append("text")
                .text(title)
                .attr("class", "title")
                .attr("x", width / 2 -100)
                .attr("y", 20);

            var dataset = data.dataset;
            console.log(dataset);

//定义布局
            var bin_num = 20;
            var max = data.max;
            var histogram = d3.layout.histogram()
                .range([0, max])
                .bins(bin_num)
                .frequency(true);

//转换数据，输出数据
            var data = histogram(dataset);
            console.log(data);

//定义比例尺
            var max_height = 400;
            var rect_step = 30;
            var heights = [];
            for (var i = 0; i < data.length; i++) {
                heights.push(data[i].y);
            }
            var yScale = d3.scale.linear()
                .domain([d3.min(heights), d3.max(heights)])
                .range([0, max_height]);

            //绘制图形
            var graphics = svg.append("g")
                .attr("transform", "translate(30,20)");
            for (var i = 0; i < data.length; i++) {
                graphics.append("text")
                    .attr("x", i * rect_step)
                    .attr("y", max_height)
                    .attr("dx", rect_step / 2 - 8)
                    .attr("dy", "15px")
                    .text(Math.floor(data[i].x));
                graphics.append("text")
                    .attr("x", i * rect_step)
                    .attr("y", max_height - yScale(data[i].y) - 20)
                    .attr("dx", rect_step / 2 - 8)
                    .attr("dy", "15px")
                    .text(Math.floor(data[i].y));
            }
            //绘制矩形
            graphics.selectAll("rect")
                .data(data)
                .enter()
                .append("rect")
                .attr("height", 0)
                .attr("y", max_height)
                .attr("class", "test")
                .transition()
                .duration(3000)
                .attr("x", function (d, i) {
                    return i * rect_step;
                })
                .attr("y", function (d, i) {
                    return max_height - yScale(d.y);
                })
                .attr("width", function (d, i) {
                    return rect_step - 2;
                })
                .attr("height", function (d) {
                    return yScale(d.y);
                })
                .attr("fill", "steelblue");

            //绘制坐标轴的直线
            graphics.append("line")
                .attr("stroke", "black")
                .attr("stroke-width", "1px")
                .attr("x1", 0)
                .attr("y1", max_height)
                .attr("x2", data.length * rect_step)
                .attr("y2", max_height);

            //绘制坐标轴的分隔符直线
            graphics.selectAll(".linetick")
                .data(data)
                .enter()
                .append("line")
                .attr("stroke", "black")
                .attr("stroke-width", "1px")
                .attr("x1", function (d, i) {
                    return i * rect_step + rect_step / 2;
                })
                .attr("y1", max_height)
                .attr("x2", function (d, i) {
                    return i * rect_step + rect_step / 2;
                })
                .attr("y2", max_height + 5);
        }
    })

});
//subscriber分布
$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/subscribeDistribute",
        //请求成功完成后要执行的方法
        success: function (data) {
            var width = 1000;
            var height = 500;
            var title = "Subscriber distribution";
//添加SVG绘制区域
            var svg = d3.select("#local").append("svg")
                .attr("width", width)
                .attr("height", height);
            //添加标题
            svg.append("g")
                .append("text")
                .text(title)
                .attr("class", "title")
                .attr("x", width / 2 -100)
                .attr("y", 20);

            var dataset = data.dataset;
            console.log(dataset);

//定义布局
            var bin_num = 20;
            var max = data.max;
            var histogram = d3.layout.histogram()
                .range([0, max])
                .bins(bin_num)
                .frequency(true);

//转换数据，输出数据
            var data = histogram(dataset);
            console.log(data);

//定义比例尺
            var max_height = 400;
            var rect_step = 30;
            var heights = [];
            for (var i = 0; i < data.length; i++) {
                heights.push(data[i].y);
            }
            var yScale = d3.scale.linear()
                .domain([d3.min(heights), d3.max(heights)])
                .range([0, max_height]);

            //绘制图形
            var graphics = svg.append("g")
                .attr("transform", "translate(30,20)");
            for (var i = 0; i < data.length; i++) {
                graphics.append("text")
                    .attr("x", i * rect_step)
                    .attr("y", max_height)
                    .attr("dx", rect_step / 2 - 8)
                    .attr("dy", "15px")
                    .text(Math.floor(data[i].x));
                graphics.append("text")
                    .attr("x", i * rect_step)
                    .attr("y", max_height - yScale(data[i].y) - 20)
                    .attr("dx", rect_step / 2 - 8)
                    .attr("dy", "15px")
                    .text(Math.floor(data[i].y));
            }
            //绘制矩形
            graphics.selectAll("rect")
                .data(data)
                .enter()
                .append("rect")
                .attr("height", 0)
                .attr("y", max_height)
                .attr("class", "test")
                .transition()
                .duration(3000)
                .attr("x", function (d, i) {
                    return i * rect_step;
                })
                .attr("y", function (d, i) {
                    return max_height - yScale(d.y);
                })
                .attr("width", function (d, i) {
                    return rect_step - 2;
                })
                .attr("height", function (d) {
                    return yScale(d.y);
                })
                .attr("fill", "steelblue");

            //绘制坐标轴的直线
            graphics.append("line")
                .attr("stroke", "black")
                .attr("stroke-width", "1px")
                .attr("x1", 0)
                .attr("y1", max_height)
                .attr("x2", data.length * rect_step)
                .attr("y2", max_height);

            //绘制坐标轴的分隔符直线
            graphics.selectAll(".linetick")
                .data(data)
                .enter()
                .append("line")
                .attr("stroke", "black")
                .attr("stroke-width", "1px")
                .attr("x1", function (d, i) {
                    return i * rect_step + rect_step / 2;
                })
                .attr("y1", max_height)
                .attr("x2", function (d, i) {
                    return i * rect_step + rect_step / 2;
                })
                .attr("y2", max_height + 5);
        }
    })

});