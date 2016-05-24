<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Repository Statistics</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <style>
        .bubble circle {
            stroke:black;
            stroke-width: 0px;
        }

        .bubble text {
            fill: black;
            font-size: 14px;
            font-family: arial;
            text-anchor: middle;
        }
        .title{
            font-family:Arial,微软雅黑;
            font-size:18px;
            text-anchor:middle;
            text-align:center;
        }
        .chord path {
            fill-opacity: 0.67;
            stroke: #000;
            stroke-width: 0.5px;
        }
    </style>
</head>
<body>
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                <li><a href="/user/users?pager.offset=0">User</a></li>
                <li><a href="#" class="active">Repository Statistics</a></li>
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
            <div id="local" class="templatemo-content-widget white-bg col-1 animated fadeInLeft">
                <h2>Local</h2><hr>
                <label for = "lineChart" class="title">
                    Language usage<br />
                    <canvas id="lineChart" width="800" height="700"></canvas>
                </label>
                <label for = "barChart" class="title">
                    Year and Size relation<br />
                    <canvas id="barChart" width="800" height="700"></canvas>
                </label>
                <div id="forkStarRelation" style="width: 100%;height:500px;"></div>
                <hr size="2">
                <div id="languageTrend" style="width: 100%;height:500px;"></div>
                <hr size="2">
                <div id="lan_size"style="width: 100%;height:500px;"></div>
                <hr size="2">
                <div id="lan_fork"style="width: 100%;height:500px;"></div>
            </div>
        </div>
    </div>
</div>

<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js" charset="utf-8"></script>
<script src="/js/SearchReposBubble.js"></script>
<script src="/js/library/d3.min.js" charset="utf-8"></script>
<script src ="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.js"></script>
<script src="/js/languageTrend.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/EchartPoint.js"></script>
<script src="/js/barChart.js"></script>
<script src="/js/chord.js"></script>
<script src="/js/macarons.js"></script>
<script src="/js/dataTool.js"></script>
<script src="/js/repostatistics.js"></script>
</body>
</html>
