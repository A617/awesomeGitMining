<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Detail</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/jquery.pagination.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".intro").mouseenter(function () {
                var repoName =$(this).children(".head").text();
                var child = $(this).children(".blue-bg");
                $.ajax({
                    async:false,
                    type: "GET",
                    url: "/getSearch",
                    data: {"repoName": repoName},
                    success: function (data,status) {
                        child.html("Recommend from "+data.result).show();
                   }
                });

            });
            $(".intro").mouseleave(function () {
                $(this).children(".blue-bg").hide();
            });
        });
    </script>

    <script type="text/javascript">
        //公共配置
        var pageOpt = {
            prev_text:'Prev',
            next_text:'Next',
            items_per_page:10,//因为第一次获取的数据,并没有传这个每页的记录数过去,所以这里应从后台获取.
            num_display_entries:5,
            num_edge_entries:1
        };
    </script>
</head>
<body class="light-gray-bg">
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                <li><a href="/user/users?pager.offset=0">User</a></li>
                <li><a href="/statistics/repository">Repository Statistics</a></li>
                <li><a href="/statistics/user">User Statistics</a></li>
                <li><a href="#" class="active">Recommended</a></li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri", "/");%>
            <%
                if (session.getAttribute("loginMember") == null) {
            %>
            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
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
            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
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
    <div class="templatemo-content col-1 light-gray-bg templatemo-flex-row"> 
        <div class="col-2 panel panel-default margin-10 animated fadeInUp">
            <div class="panel-heading"><h2>Recommendation from your search records</h2></div>
            <div class="panel-body">
                <div class="dashboard-list">
                    <c:forEach items="${search}" var="repo">
                        <li class="intro">
                            <h3 class="head"><strong><a href="/repo/${repo.full_name }">${repo.full_name}</a></strong>
                            </h3>
                            <p class="blue-bg"></p>
                            <p style="text-align: right"><strong>${repo.language}</strong></p>
                            <p><span><strong>Subscribers:</strong>&nbsp;${repo.subscribers_count}</span>
                                <span><strong>Forks:</strong>&nbsp;${repo.forks_count}</span>
                                <span><strong>Stargazers:</strong>&nbsp;${repo.stargazers_count}</span>
                            </p>
                            <p class="blue-text">${repo.description}</p>
                            <strong>Last Updated:</strong>&nbsp;${repo.updated_at}<br>
                        </li>
                        <hr size="2"/>
                    </c:forEach>
                </div>

                <div id="Pagination"></div>
                 
            </div>
              
        </div>

        <div class="col-2 panel panel-default margin-10 animated fadeInUp">
            <div class="panel-heading"><h2>Recommendation from your favorite records</h2></div>
            <div class="panel-body">
                <ul class="dashboard-list">
                    <c:forEach items="${star}" var="repo">
                        <li>
                            <h3><strong><a href="/repo/${repo.full_name }">${repo.full_name}</a></strong></h3>
                            <p style="text-align: right"><strong>${repo.language}</strong></p>
                            <p><span><strong>Subscribers:</strong>&nbsp;${repo.subscribers_count}</span>
                                <span><strong>Forks:</strong>&nbsp;${repo.forks_count}</span>
                                <span><strong>Stargazers:</strong>&nbsp;${repo.stargazers_count}</span>
                            </p>
                            <p class="blue-text">${repo.description}</p>
                            <strong>Last Updated:</strong>&nbsp;${repo.updated_at}<br>
                        </li>
                        <hr size="2"/>
                    </c:forEach>
                </ul>
                 
            </div>
              
        </div>
    </div>
</div>
 

<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>

<script src="/js/bootstrap.min.js"></script>
</body>
</html>
