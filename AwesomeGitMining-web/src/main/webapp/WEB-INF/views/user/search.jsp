<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Repository List</title>
    <link href="<c:url value="/css/listcss.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
</head>
<body class="light-gray-bg">
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                <li><a href="/user/users?pager.offset=0" class="active">User</a></li>
                <li><a href="#">Repository Statistics</a></li>
                <li><a href="#">User Statistics</a></li>
                <li><a href="/recommend">Recommended</a> </li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri","/user/search");%>
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

<br><div class="col-md-6 center">
    <form action="/user/users/search" method="get">
        <input type="text" class="search-query form-control col-md-10" name="name" placeholder="Search keyword..."><br>
        <div class="form-group text-right">
            <button type="submit" class="fa-align-center templatemo-blue-button">Search</button>
        </div>
    </form>
</div>

<div class="templatemo-flex-row animated fadeInRight">
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="col-1 panel panel-default margin-10">
            <div class="panel-heading"><h2>Search Result List</h2></div>
            <div class="panel-body">
                <ul class="dashboard-list">
                    <c:forEach items="${users }" var="u">
                        <li>
                            <h3><strong><a href="${u.login }">${u.login }</a></strong></h3>
                            <p style="text-align: right"><strong>Followers:</strong>&nbsp;${u.followers}</p>
                            <p><span><strong>Location:</strong>&nbsp;${u.location}</span>
                                <strong>Company:</strong>&nbsp;${u.company} </span>
                            </p>
                        </li>
                    </c:forEach>
                </ul>

                <ul class="pagination  pagination-centered">
                    ${pageUrl}<br>
                    <pg:pager url="/user/search" items="${total}">
                        <li>
                            <pg:first>
                                <a href="${pageUrl}">Begin</a>
                            </pg:first>
                        </li>
                        <li>
                            <pg:prev>
                                <a href="${pageUrl }">Pre</a>
                            </pg:prev>
                        </li>
                        <li>
                            <pg:pages>
                                <a href="${pageUrl }">${pageNumber}</a>
                            </pg:pages>
                        </li>
                        <li>
                            <pg:next>
                                <a href="${pageUrl }">Next</a>
                            </pg:next>
                        </li>
                        <li>
                            <pg:last>
                                <a href="${pageUrl }">End</a>
                            </pg:last>
                        </li>
                    </pg:pager>
                </ul>
            </div>
        </div>
    </div>
</div>
<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
