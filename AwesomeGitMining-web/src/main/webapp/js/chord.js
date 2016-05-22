$(document).ready(function () {
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/statistics/repository/languageRelation",
        success: function (data) {
//1.定义数据

            var city_name = data.name;
            
            var population = data.test;

//2.转换数据，并输出转换后的数据
            var chord_layout = d3.layout.chord()
                .padding(0.03)		//节点之间的间隔
                .sortSubgroups(d3.descending)	//排序
                .matrix(population);	//输入矩阵

            var groups = chord_layout.groups();
            var chords = chord_layout.chords();

            console.log(groups);
            console.log(chords);

//3.SVG，弦图，颜色函数的定义
            var width = 1000;
            var height = 1000;
            var innerRadius = width / 2 * 0.7;
            var outerRadius = innerRadius * 1.1;

            var color20 = d3.scale.category20();

            var svg = d3.select("#local").append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
            var title = "Relationships between languages";
           
//4.绘制节点（即分组，有多少个城市画多少个弧形），及绘制城市名称
            var outer_arc = d3.svg.arc()
                .innerRadius(innerRadius)
                .outerRadius(outerRadius);

            var g_outer = svg.append("g");

            g_outer.selectAll("path")
                .data(groups)
                .enter()
                .append("path")
                .style("fill", function (d) {
                    return color20(d.index);
                })
                .style("stroke", function (d) {
                    return color20(d.index);
                })
                .attr("d", outer_arc);

            g_outer.selectAll("text")
                .data(groups)
                .enter()
                .append("text")
                .each(function (d, i) {
                    d.angle = (d.startAngle + d.endAngle) / 2;
                    d.name = city_name[i];
                })
                .attr("dy", ".35em")
                .attr("transform", function (d) {
                    return "rotate(" + ( d.angle * 180 / Math.PI ) + ")" +
                        "translate(0," + -1.0 * (outerRadius + 10) + ")" +
                        ( ( d.angle > Math.PI * 3 / 4 && d.angle < Math.PI * 5 / 4 ) ? "rotate(180)" : "");
                })
                .text(function (d) {
                    return d.name;
                });


//5.绘制内部弦（即所有城市人口的来源，即有5*5=25条弧）
            var inner_chord = d3.svg.chord()
                .radius(innerRadius);

            svg.append("g")
                .attr("class", "chord")
                .selectAll("path")
                .data(chords)
                .enter()
                .append("path")
                .attr("d", inner_chord)
                .style("fill", function (d) {
                    return color20(d.source.index);
                })
                .style("opacity", 0.5)
                .on("mouseover", function (d, i) {
                    d3.select(this)
                        .style("fill", "yellow");
                })
                .on("mouseout", function (d, i) {
                    d3.select(this)
                        .transition()
                        .duration(1000)
                        .style("fill", color20(d.source.index));
                });
            //添加标题
            svg.append("g")
                .append("text")
                .text(title)
                .attr("class","title")
                .attr("x",0)
                .attr("y",-450);
        }
    })

});