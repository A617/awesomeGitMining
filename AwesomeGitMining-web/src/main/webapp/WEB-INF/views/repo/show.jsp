    <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Repository Detail</title>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/Chart.bundle.js"></script>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">

    <script type="text/javascript">
        $(document).ready(function(){
            $("button").click(function(){
                var fullName = $("#fullName").text();
                $("#p1").html("stared!").hide(3000);
                $.ajax({
                    type: "POST",
                    url: "/starRepos",
                    data:{"repoName":fullName}
                });
            });
        });
    </script>
</head>
<body class="light-gray-bg">
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0" class="active">Repository</a></li>
                <li><a href="/user/users?pager.offset=0">User</a></li>
                <li><a href="/statistics/repository">Repository Statistics</a></li>
                <li><a href="/statistics/user">User Statistics</a></li>
                <li><a href="/statistics/bigQuery">Big Query</a></li>
                <li><a href="/recommend">Recommended</a> </li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri","/");%>
            <%
                if(session.getAttribute("loginMember")==null){
            %>
            <a href="#" id="drop_a" data-toggle="dropdown">
                Visitors <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
                <li>
                    <a href="/login.jsp">Sign in</a>
                </li>
                <li>
                    <a href="/register">Sign up</a>
                </li>
            </ul>
            <%
            }else{
            %>
            <a href="#" id="drop_a" data-toggle="dropdown">
                <%=session.getAttribute("loginMember")%><b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
                <li>
                    <a href="/favouriteRepos">Favorite Repositories</a>
                </li>
                <li>
                    <a href="/logout">Log out</a>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </div>
</div>
<div class="templatemo-flex-row">
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-flex-row flex-content-row">
            <div class="templatemo-content-widget white-bg col-1 animated fadeInUp">
                <h1 id="fullName">${repo.full_name}</h1><hr>
                <div class="col-lg-6 col-md-6">
                    <p><strong>${repo.description}</strong></p><br>
                    <p><strong class="text-purple">Project URL:</strong>&nbsp;<a href=${repo.clone_url}>${repo.clone_url}</a></p>
                    <div class="m-top-sm text-centers">
                        <%session.setAttribute("backuri","/");%>
                        <%
                            if(session.getAttribute("loginMember")!=null){
                        %>
                        <button class="btn btn-success" method="post">Star</button>
                        <p id="p1"></p>
                        <%
                            }
                        %>
                    </div>

                    <h4 class="m-top-md m-bottom-sm">Repository Statistics</h4>
                    <div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper">Fork</big><br>
                            <h3 class="no-margin">${repo.forks_count}</h3>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper">Star</big>
                            <h3 class="no-margin">${repo.stargazers_count}</h3>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper text-center">Watch</big>
                            <h3 class="no-margin">${repo.watchers_count}</h3>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper text-center">Followers</big>
                            <h3 class="no-margin">${repo.subscribers_count}</h3>
                        </div>
                    </div>
                    <br><br><br><br>
                    <h4 class="m-top-md m-bottom-sm">Related Users</h4>
                    <div>
                        <div class="col-lg-6 col-md-6" style="height:300px; overflow:auto">
                            <table style="overflow-y: auto" class="table table-striped table-bordered templatemo-user-table">
                                <thead>
                                <tr>
                                    <td>Contributers</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${contributors }" var="u">
                                    <tr>
                                        <td>
                                            <a href="/user/${u}">${u}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-lg-6 col-md-6" style="height:300px; overflow:auto">
                            <table style="overflow-y: auto" class="table table-striped table-bordered templatemo-user-table">
                                <thead>
                                <tr>
                                    <td>Collaborators</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${collaborators }" var="u">
                                    <tr>
                                        <td>
                                            <a href="/user/${u}">${u}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6" style="height:300px; overflow:auto">
                        <h4 class="m-top-md m-bottom-sm">Evaluation</h4>
                        <div style="width:50%; margin:0 auto">
                            <canvas id="radar-chart" height=200px />
                        </div>
                </div>
                <div class="col-lg-6 col-md-6" style="height:300px; overflow:auto">
                        <h4 class="m-top-md m-bottom-sm">Languages</h4>
                        <div style="width:50%; margin:0 auto">
                            <canvas id="pie-chart" height=200px />
                        </div>
                </div>
                <div class="col-lg-12 col-md-12" style="width:100%; overflow:auto">
                    <h4 class="m-top-md m-bottom-sm">Related repositories</h4>
                    <div style="width:60%; margin:0 auto">
                         <div id="force-chart" height=400px width=600px />
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<%--<footer class="text-right">--%>
    <%--<p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>--%>
<%--</footer>--%>

    <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<script>
    var sc = function(factor) {
        return Math.round(factor * 10);
    };
    var config1 = {
        type: 'radar',
        data: {
            labels: ["size","scale","promising","participation","hot"],
            datasets: [{
                label: "${repo.full_name}",
                backgroundColor: "rgba(220,220,220,0.2)",
                pointBackgroundColor: "#99CCFF",
                pointBorderColor: "#6699CC",
                data: [sc(${repo.size_score}),sc(${repo.scale_score}),sc(${repo.promising_score}),
                sc(${repo.participation_score}),sc(${repo.hot_score})]
            }]
        },
        options: {
            title: {
                display: false
            },
            scale: {
              reverse: false,
              ticks: {
                beginAtZero: true
              }
            }
        }
    };


    var labels = new Array();
    var datas = new Array();
    <c:forEach items="${languages}" var="mm">
        labels.push("${mm.key}");
        datas.push("${mm.value}");
    </c:forEach>


    var data = {
    labels: labels,
    datasets: [{
            data:datas,
            backgroundColor: [
                "#FFFFCC","#CCFFFF","#FFCCCC",
                "#99CCCC","#FFCC99","#FFCCCC",
                "#FF9999","#996699","#FFFF99",
                "#0099CC","#FF6666","#99CC66"
            ],
            hoverBackgroundColor: [
                "#FFFFCC","#CCFFFF","#FFCCCC",
                "#99CCCC","#FFCC99","#FFCCCC",
                "#FF9999","#996699","#FFFF99",
                "#0099CC","#FF6666","#99CC66"
            ]
        }]
    };

    var config2 = {
        type: 'pie',
        data: data,
        options: {
            responsive: true
        }
    };

    window.onload = function() {
            window.myRadar = new Chart(document.getElementById("radar-chart"), config1);
            var ctx = document.getElementById("pie-chart").getContext("2d");
                    window.myPie = new Chart(ctx, config2);
        };


    $(function() {
    var chart;

        $(document).ready(function() {

            var url = "/repo/"+'${repo.full_name}'+"/json";

            <%--alert('<%${repo.full_name}%>');--%>
            $.ajax(url, {
                type: 'GET',
                success: function (data, textStatus) {

                    var nodes = data.nodes;
                    <%--alert(data.nodes);--%>

                    var edges = data.lines;
                        <%--alert(data.lines);--%>

                    var width = 600;
                    var height = 400;


                    var svg = d3.select("#force-chart")
                    .append("svg")
                    .attr("width",width)
                    .attr("height",height);

                    var force = d3.layout.force()
                    .nodes(nodes)		//指定节点数组
                    .links(edges)		//指定连线数组
                    .size([width,height])	//指定范围
                    .linkDistance(150)	//指定连线长度
                    .charge(-400);	//相互之间的作用力

                    force.start();	//开始作用

                    console.log(nodes);
                    console.log(edges);

                    //添加连线
                    var svg_edges = svg.selectAll("line")
                    .data(edges)
                    .enter()
                    .append("line")
                    .style("stroke","#ccc")
                    .style("stroke-width",1);

                    var color = d3.scale.category20();

                    //添加节点
                    var svg_nodes = svg.selectAll("circle")
                    .data(nodes)
                    .enter()
                    .append("circle")
                    .attr("r",20)
                    .style("fill",function(d,i){
                    return color(i);
                    })
                    .call(force.drag);	//使得节点能够拖动

                    //添加描述节点的文字
                    var svg_texts = svg.selectAll("text")
                    .data(nodes)
                    .enter()
                    .append("text")
                    .style("fill", "black")
                    .attr("dx", 20)
                    .attr("dy", 8)
                    .text(function(d){
                    return d.name;
                    });


                    force.on("tick", function(){	//对于每一个时间间隔

                    //更新连线坐标
                    svg_edges.attr("x1",function(d){ return d.source.x; })
                    .attr("y1",function(d){ return d.source.y; })
                    .attr("x2",function(d){ return d.target.x; })
                    .attr("y2",function(d){ return d.target.y; });

                    //更新节点坐标
                    svg_nodes.attr("cx",function(d){ return d.x; })
                    .attr("cy",function(d){ return d.y; });

                    //更新文字坐标
                    svg_texts.attr("x", function(d){ return d.x; })
                    .attr("y", function(d){ return d.y; });
                    });


                }
            });
        });
    });

</script>

    <script>


    </script>

    </body>
</html>
