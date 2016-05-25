$(document).ready(function () {
    var chart;
    $.ajax({
        //请求方式为get
        type: "GET",
        url: "/js/MostActiveRepos201605.json",
        success: function (data) {
            var frequency_list = data;

            var fill = d3.scale.category20();
            var color = d3.scale.linear()
                .domain([0, 1, 2, 3, 4, 5, 6, 10, 15, 20, 100])
                .range(["#ddd", "#ccc", "#bbb", "#aaa", "#999", "#888", "#777", "#666", "#555", "#444", "#333", "#222"]);

            d3.layout.cloud().size([1500, 1500])
                .words(frequency_list)
                /// .rotate(function() { return ~~(Math.random() * 2) * 90; })
                .fontSize(function (d) {
                    return d.c / 700;
                })
                .rotate(function () {
                    return ~~(Math.random() * 2) * 90;
                })
                .padding(5)
                .font("Impact")
                .on("end", draw)
                .start();

            function draw(words) {
                var svg = d3.select("#cloud").append("svg")
                    .attr("width", 1500)
                    .attr("height", 1500)
                    .attr("class", "wordcloud")
                    .append("g")
                    // without the transform, words words would get cutoff to the left and top, they would
                    // appear outside of the SVG area
                    .attr("transform", "translate(750,750)")
                    .selectAll("text")
                    .data(words)
                    .enter().append("text")
                    .style("font-size", function (d) {
                        return d.c / 700 + "px";
                    })
                    .attr("text-anchor", "middle")
                    .style("font-family", "微软雅黑")
                    .style("fill", function (d, i) {
                        return fill(i);
                    })
                    .attr("transform", function (d) {
                        return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                    })
                    .text(function (d) {
                        return d.repo_name;
                    });
            }

            chart.setOption(option);
        }
    })

});