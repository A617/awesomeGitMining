<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Star List</title>
    <link href="<c:url value="/css/listcss.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <style type="text/css">
        span {
            width: 30%;
            display:inline-block;
        }
    </style>
</head>
<body>
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0" class="active">Repository</a></li>
                <li><a href="/user/users?pager.offset=0">User</a></li>
                <li><a href="#">Repository Statistics</a></li>
                <li><a href="#">User Statistics</a></li>
                <li><a href="#">Recommended</a> </li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri","/repo/stars?pager.offset=0");%>
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
                    <a href="#">Favorite Users</a>
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
            <!--tag-->
            <div class="templatemo-content-widget white-bg col-1 animated fadeInUp">
                <h2>Tags</h2><hr>
                <!--search-->
                <div>
                    <form action="/repo/search" method="get">
                        <input type="text" class="search-query form-control col-md-10" name="name" placeholder="Search keyword..."><br>
                        <div class="form-group text-right">
                            <button type="submit" class="fa-align-center templatemo-blue-button">Search</button>
                        </div>
                    </form>
                </div>
                <!--tag-->
                <div>
                    <h3 class="text-uppercase"> Languages</h3>
                    <div class="box-content buttons">
                        </p>
                        <a href="#" class="btn btn-s-md btn-warning"> All</a>
                        <a href="#" class="btn btn-s-md btn-warning"> Scala</a>
                        <a href="#" class="btn btn-s-md btn-warning"> C</a>
                        <a href="#" class="btn btn-s-md btn-warning"> Ruby</a>
                        <a href="#" class="btn btn-s-md btn-warning"> Java</a>
                        <a href="#" class="btn btn-s-md btn-warning"> Python</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-warning"> JavaScript</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Perl</a>
                            <a href="#" class="btn btn-s-md btn-warning"> PHP</a>
                            <a href="#" class="btn btn-s-md btn-warning"> HTML</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Shell</a>

                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-warning"> Objective-C</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Haskell</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Clojure</a>
                            <a href="#" class="btn btn-s-md btn-warning"> C#</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-warning"> CSS</a>
                            <a href="#" class="btn btn-s-md btn-warning"> C++</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Go</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Lua</a>
                            <a href="#" class="btn btn-s-md btn-warning"> Prolog</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-warning"> CoffeeScript</a>
                        </p>
                    </div>
                </div>

                <div>
                    <h3 class="text-uppercase">  Keywords</h3>
                    <div class="box-content buttons">
                        <p>
                            <a href="#" class="btn btn-s-md btn-primary"> API</a>
                            <a href="#" class="btn btn-s-md btn-primary"> Django</a>
                            <a href="#" class="btn btn-s-md btn-primary"> MySQL</a>
                            <a href="#" class="btn btn-s-md btn-primary"> jQuery</a>
                            <a href="#" class="btn btn-s-md btn-primary"> XML</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-primary"> Web</a>
                            <a href="#" class="btn btn-s-md btn-primary"> Plugin</a>
                            <a href="#" class="btn btn-s-md btn-primary"> database</a>
                            <a href="#" class="btn btn-s-md btn-primary"> IRC</a>
                            <a href="#" class="btn btn-s-md btn-primary"> iOS</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-primary"> Git</a>
                            <a href="#" class="btn btn-s-md btn-primary"> Emacs</a>
                            <a href="#" class="btn btn-s-md btn-primary"> JSON</a>
                            <a href="#" class="btn btn-s-md btn-primary"> Linux</a>
                            <a href="#" class="btn btn-s-md btn-primary"> vim</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-primary"> toolkit</a>
                            <a href="#" class="btn btn-s-md btn-primary"> .NET</a>
                            <a href="#" class="btn btn-s-md btn-primary"> Apache</a>
                            <a href="#" class="btn btn-s-md btn-primary"> Android</a>
                            <a href="#" class="btn btn-s-md btn-primary"> OS</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-primary"> Maven</a>
                            <a href="#" class="btn btn-s-md btn-primary"> gem</a>
                            <a href="#" class="btn btn-s-md btn-primary"> MVC</a>
                        </p>
                    </div>
                </div>

                <div>
                    <h3 class="text-uppercase"> Updated Time</h3>
                    <div class="box-content buttons">
                        <p>
                            <a href="#" class="btn btn-s-md btn-success"> All</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2007</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2008</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2009</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2010</a>
                        </p>
                        <p>
                            <a href="#" class="btn btn-s-md btn-success"> 2011</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2012</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2013</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2014</a>
                            <a href="#" class="btn btn-s-md btn-success"> 2015</a>
                        </p>
                    </div>
                </div>
            </div>
            <!--list-->
            <div class="col-2 panel panel-default margin-10">
                <div class="panel-heading">
                    <ul class="nav nav-tabs" id="maintab">
                        <li>
                            <a href="#general" onclick='showPage("repos")'>
                                <h3>General</h3>
                            </a>
                        </li>
                        <li>
                            <a href="#forks" onclick='showPage("forks")'>
                                <h3>Fork</h3>
                            </a>
                        </li>
                        <li class="active">
                            <a href="#stars" onclick='showPage("stars")'>
                                <h3>Star</h3>
                            </a>
                        </li>
                        <li>
                            <a href="#contributers" onclick='showPage("cons")'>
                                <h3>Contributer</h3>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade" id="general"></div>
                    <div class="tab-pane fade" id="forks"></div>
                    <div class="tab-pane fade in active" id="stars">
                        <div class="panel-body">
                            <ul class="dashboard-list">
                                <c:forEach items="${stars }" var="repo">
                                    <li>
                                        <h3><strong><a href="${repo.fullName }">${repo.fullName }</a></strong></h3>
                                        <p style="text-align: right"><strong>${repo.language}</strong></p>
                                        <p><span><strong>Subscribers:</strong>&nbsp;${repo.subscribersCount}</span>
                                            <span><strong>Forks:</strong>&nbsp;${repo.forksCount}</span>
                                            <span><strong>Stargazers:</strong>&nbsp;${repo.stargazersCount}</span>
                                        </p>
                                        <p class="blue-text">${repo.description}</p>
                                        <strong>Last Updated:</strong>&nbsp;${repo.updatedAt}<br>
                                    </li>
                                </c:forEach>
                            </ul>

                            <ul class="pagination  pagination-centered">
                                ${pageUrl}<br>
                                <pg:pager url="/repo/stars" items="${total}">
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
                    <div class="tab-pane fade" id="contributers"></div>
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
<script language="javascript">

    function showPage(tabId){
        newurl = "/repo/"+tabId+"?pager.offset=0";
        window.location.href=newurl;
    }

</script>
</body>
</html>

