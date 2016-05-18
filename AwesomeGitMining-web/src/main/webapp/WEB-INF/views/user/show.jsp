<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Detail</title>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/Chart.bundle.js"></script>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/simplify.min.css"/>" rel="stylesheet" type="text/css" media="all">
</head>
<body class="light-gray-bg">
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                <li><a href="/user/users?pager.offset=0" class="active">User</a></li>
                <li><a href="/statistics/repository">Repository Statistics</a></li>
                <li><a href="/statistics/user">User Statistics</a></li>
                <li><a href="/recommend">Recommended</a> </li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri","/");%>
            <%
                if(session.getAttribute("loginMember")==null){
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
            }else{
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
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-flex-row flex-content-row">
            <div class="templatemo-content-widget white-bg col-1 animated fadeInUp">
                <h1>${user.login}</h1><hr>

                <div class="col-lg-3 col-md-6">
                    <div class="user-profile-pic col-sm-10">
                        <img src=${user.avatar_url} alt="">
                    </div>

                    <div class="col-sm-6 col-md-12">
                        <div class="user-name m-top-sm"><h3 class="blue-text">${user.name}</h3></div>
                        <div class="m-top-sm">
                            <p><strong class="text-purple">Followers:</strong>&nbsp;${user.followers}</p>
                            <p><strong class="text-purple">Followings:</strong>&nbsp;${user.following}</p>
                        </div>

                        <h4 class="m-top-md m-bottom-sm">Basic info</h4>
                        <div class="m-top-sm">
                            <p><strong class="text-purple">Location:</strong>&nbsp;${user.location}</p>
                            <p><strong class="text-purple">Join Time:</strong>&nbsp;${user.created_at}</p>
                        </div>

                        <h4 class="m-top-md m-bottom-sm">Links</h4>
                        <p><strong class="text-purple">Email:</strong>&nbsp;${user.email}</p>
                        <p><strong class="text-purple">Blog:</strong>&nbsp;<a href=${user.blog}>${user.blog}</a></p>
                        <p><strong class="text-purple">HTML:</strong>&nbsp;<a href=${user.html_url}>${user.html_url}</a></p>
                    </div>
                </div>

                <div class="col-lg-6 col-md-6 row">
                    <div style="height: 160px" class="row">
                        <h4 class="header-text m-bottom-md">Languages</h4>
                        <div>
                            <c:forEach items="${languages }" var="u">
                                <div class="col-lg-3 col-md-6">
                                    <h3 class="no-margin text-purple">${u}</h3>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <br><br><br>
                    <div class="panel panel-default no-border">
                        <div class="panel-heading border-radius-10" ">
                            <h2>Evaluation</h2>
                        </div>
                        <div class="panel-body" >
                            <canvas id="canvas"></canvas>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <h4 class="m-top-md m-bottom-sm">Related Repositories</h4>
                    <div style="height:400px; overflow-y: auto">
                        <table class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td>Contributed Projects</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${contributions}" var="u">
                               <tr>
                                   <td>
                                       <a href="/repo/${u}">${u}</a>
                                   </td>
                               </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <div style="height:400px; overflow:auto">
                        <table style="overflow-y: auto" class="table table-striped table-bordered templatemo-user-table">
                            <thead>
                            <tr>
                                <td>Collaborated Projects</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${collaborations }" var="u">
                                <tr>
                                    <td>
                                        <a href="/repo/${u}">${u}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var sc = function(factor) {
        return Math.round(factor * 10);
    };
    var config = {
        type: 'radar',
        data: {
            labels: ["popular","teamwork","liveness","experience","quantity"],
            datasets: [{
                label: "${user.login}",
                backgroundColor: "rgba(220,220,220,0.2)",
                pointBackgroundColor: "#99CCFF",
                pointBorderColor: "#6699CC",
                data: [sc(${user.popular_score}),sc(${user.teamwork_score}),sc(${user.liveness_score}),
                sc(${user.experience_score}),sc(${user.quantity_score})],
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

    window.onload = function() {
        window.myRadar = new Chart(document.getElementById("canvas"), config);
    };
    </script>

<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>


</body>
</html>