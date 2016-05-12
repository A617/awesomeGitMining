<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Repository Detail</title>
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
                <li><a href="/repo/repos?pager.offset=0" class="active">Repository</a></li>
                <li><a href="/user/users?pager.offset=0">User</a></li>
                <li><a href="#">Repository Statistics</a></li>
                <li><a href="#">User Statistics</a></li>
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
                    <a href="#">Favorite Repositories</a>
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
                <h1>${repo.fullName}</h1><hr>
                <div class="col-lg-6 col-md-6">
                    <p><strong>${repo.description}</strong></p><br>
                    <p><strong class="text-purple">Project URL:</strong>&nbsp;<a href=${repo.cloneUrl}>${repo.cloneUrl}</a></p>
                    <div class="m-top-sm text-centers">
                        <%session.setAttribute("backuri","/");%>
                        <%
                            if(session.getAttribute("loginMember")!=null){
                        %>
                        <a class="btn btn-success">Star</a>
                        <%
                            }
                        %>
                    </div>

                    <h4 class="m-top-md m-bottom-sm">Repository Statistics</h4>
                    <div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper">Fork</big><br>
                            <h3 class="no-margin">${repo.forksCount}</h3>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper">Star</big>
                            <h3 class="no-margin">${repo.stargazersCount}</h3>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper text-center">Watch</big>
                            <h3 class="no-margin">${repo.watchersCount}</h3>
                        </div>
                        <div class="col-lg-3 col-md-6">
                            <big class="text-upper text-center">Followers</big>
                            <h3 class="no-margin">${repo.subscribersCount}</h3>
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
                <div class="col-lg-6 col-md-6">

                </div>
            </div>
        </div>
    </div>
</div>
<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="Chart.bundle.js"></script>
</body>
</html>
