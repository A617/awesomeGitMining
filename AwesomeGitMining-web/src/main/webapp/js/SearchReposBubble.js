$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/searchRecord",
        //请求成功完成后要执行的方法
        success: function (obj) {
            var data = {"children": obj};
            // snippet.log(data.obj.name);
            var width = 800;	//SVG绘制区域的宽度
            var height = 800;	//SVG绘制区域的高度
            var svg = d3.select("#local")			//选择<body>
                .append("svg")			//在<body>中添加<svg>
                .attr("width", width)	//设定<svg>的宽度属性
                .attr("height", height)
                ;

            var pack = d3.layout.pack()
                .size([width, height])
                .sort(null)
                .value(function (d) {
                    return d.weight;
                })
                .padding(2);


            var nodes = pack.nodes(data);
            console.log(nodes);

            var color = d3.scale.category20c();

            var bubbles = svg.selectAll(".bubble")
                .data(nodes.filter(function (d) {
                    return !d.children;
                }))
                .enter()
                .append("g")
                .attr("class", "bubble");

            bubbles.append("circle")
                .on("mouseover", function (d, i) {
                    d3.select(this)
                        .style("fill", "yellow");
                })
                .on("mouseout", function (d, i) {
                    d3.select(this)
                        .transition()
                        .duration(500)
                        .style("fill", function () {
                            return color(i);
                        });
                    
                })
                .style("fill", function (d, i) {
                    return color(i);
                })
                .style("opacity", "0.0")
                .attr("r", 0)
                .transition()
                .duration(2000)
                .ease("sin")
                .style("opacity","0.5")
                .attr("cx", function (d) {
                    return d.x;
                })
                .attr("cy", function (d) {
                    return d.y;
                })
                .attr("r", function (d) {
                    return d.r;
                });


            bubbles.append("text")
                .on("mouseover", function (d) {
                    d3.select(this)
                        .text(function () {
                            return d.weight;
                        });
                })
                .on("mouseout", function (d) {
                    d3.select(this)
                        .transition()
                        .duration(500)
                        .text(function () {
                            return d.word;
                        });

                })
                .attr("x", function (d) {
                    return d.x;
                })
                .attr("y", function (d) {
                    return d.y;
                })
                .style("opacity", function (d, i) {
                    return "0.0";
                })
                .transition()
                .duration(2000)
                .style("opacity", function (d, i) {
                    return "0.8";
                })
                .text(function (d) {
                    return d.word;
                });
        }
    })

});