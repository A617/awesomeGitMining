<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
    <link href="<c:url value="/css/listcss.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/templatemo-style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <style type="text/css">
        span {
            width: 50%;
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
                    <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                    <li><a href="#" class="active">User</a></li>
                    <li><a href="#">Repository Statistics</a></li>
                    <li><a href="#">User Statistics</a></li>
                </ul>
            </nav>

            <div class="dropdown navbar-right">
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
                        <form action="/user/users/search" method="get">
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
                            <p>
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
                                <a href="#" class="btn btn-s-md btn-warning"> Lua</a>
                            </p>
                        </div>
                    </div>

                    <div>
                        <h3 class="text-uppercase"> Companys</h3>
                        <div class="box-content buttons">
                            <p>
                                <a href="#" class="btn btn-s-md btn-primary"> All</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Shopify</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Google</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Github</a>
                            </p>
                            <p>
                                <a href="#" class="btn btn-s-md btn-primary"> Twitter</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Red Hat</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Mozilla</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Xamarin</a>
                            </p>
                            <p>
                                <a href="#" class="btn btn-s-md btn-primary"> Heroku</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Facebook</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Microsoft</a>
                            </p>
                            <p>
                                <a href="#" class="btn btn-s-md btn-primary"> C++</a>
                                <a href="#" class="btn btn-s-md btn-primary"> Lua</a>
                            </p>
                        </div>
                    </div>
                </div>
                <!--list-->
                <div class="col-2 panel panel-default margin-10">
                    <div class="panel-heading"><h2>User List</h2></div>
                    <div class="panel-body">
                        <ul class="dashboard-list">
                            <c:forEach items="${users }" var="u">
                                <li>
                                    <h3><strong>${u.id}&nbsp;<a href="${u.login }">${u.login }</a></strong></h3>
                                    <p style="text-align: right"><strong>Followers:</strong>&nbsp;${u.followers}</p>
                                    <p><span><strong>Location:</strong>&nbsp;${u.location}</span>
                                        <strong>Company:</strong>&nbsp;${u.company} </span>
                                    </p>
                                </li>
                            </c:forEach>
                        </ul>

                        <ul class="pagination pagination-centered">
                            ${pageUrl}<br>
                            <pg:pager url="/user/users" items="${total}">
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
    </div>

    <footer class="text-right">
        <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
    </footer>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>
