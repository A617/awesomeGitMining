<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Big Query Statistics</title>
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <style>
        body {
            font-family:"Lucida Grande","Droid Sans",Arial,Helvetica,sans-serif;
        }
        .legend {
            border: 1px solid #555555;
            border-radius: 5px 5px 5px 5px;
            font-size: 0.8em;
            margin: 10px;
            padding: 8px;
        }
        .bld {
            font-weight: bold;
        }
        .title{
            font-family:Arial,微软雅黑;
            font-size:18px;
            text-anchor:middle;
            text-align:center;
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
                <li><a href="/statistics/repository">Repository Statistics</a></li>
                <li><a href="/statistics/user">User Statistics</a></li>
                <li><a href="#" class="active">Big Query</a></li>
                <li><a href="/recommend">Recommended</a></li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri", "/");%>
            <%
                if (session.getAttribute("loginMember") == null) {
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
            } else {
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
            <div class="templatemo-content-widget white-bg col-1 animated fadeInRight">
                <h2>Big Query Statistics</h2>
                <hr>
                <div id="company-pie-bq" style="width: 100%;height:400px;"></div>
                <hr size="2">
                <div id="pushTime" style="width: 100%;height:400px;"></div>
                <hr size="2">
                <div id="eventShow" style="width: 100%;height:600px;"></div>
                <hr size="2">
                <div id="mood" style="width: 100%;height:400px;"></div>
                <div style="width: 100%;height:60px;"></div>
                <div class="title">Most active repository in May </div>
                <div id="cloud" style="width: 100%;height:1500px;"></div>
            </div>
        </div>
    </div>
</div>

<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>

<script src="/js/library/d3.min.js"></script>
<script src="/js/library/d3.layout.cloud.js"></script>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/Chart.bundle.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/macarons.js"></script>
<script src="/js/bigQuery.js"></script>
<script src="/js/library/json2.js"></script>
<script src="/js/pushTime.js"></script>
<script src="/js/eventsShow.js"></script>
<script src="/js/wordcloud.js"></script>

</body>
</html>
