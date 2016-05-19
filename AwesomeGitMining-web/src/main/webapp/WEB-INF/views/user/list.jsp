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
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/simplify.min.css"/>" rel="stylesheet" type="text/css" media="all">

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
                    <li><a href="/statistics/repository">Repository Statistics</a></li>
                    <li><a href="/statistics/user">User Statistics</a></li>
                    <li><a href="/recommend">Recommended</a> </li>
                </ul>
            </nav>

            <div class="dropdown navbar-right">
                <%session.setAttribute("backuri","/user/users?pager.offset=0");%>
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
                <!--tag-->
                <div class="templatemo-content-widget white-bg col-1 animated fadeInUp">
                    <h2>Tags</h2><hr>
                    <!--search-->
                    <div>
                        <form action="/user/search" method="get">
                            <input type="text" class="search-query form-control col-md-10" name="name" placeholder="Search keyword..."><br>
                            <div class="form-group text-right">
                                <button type="submit" class="fa-align-center templatemo-blue-button">Search</button>
                            </div>
                        </form>
                    </div>
                    <!--tag-->
                    <div class="col-lg-12">
                        <h3 class="text-uppercase"> Languages</h3>
                        <div class="box-content buttons">
                                <div class="col-lg-3 col-md-6"><button id="onLan" type="submit" class="blog-tag" name="lan" value="All">All</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Scala">Scala</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="C">C</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Ruby">Ruby</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Java">Java</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Python">Python</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="JavaScript">JavaScript</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Perl">Perl</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="PHP">PHP</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="HTML">HTML</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Shell">Shell</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Lua">Lua</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Haskell">Haskell</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Clojure">Clojure</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="CSS">CSS</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="C++">C++</button></div>
                                <div class="col-lg-6 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Objective-C">Objective-C</button></div>
                        </div>
                    </div>

                    <div class="col-lg-12">
                        <h3 class="text-uppercase"> Companys</h3>
                        <div class="box-content buttons">
                                <div class="col-lg-3 col-md-6"><button id="onCom" type="submit" class="blog-tag" name="com" value="All">All</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Shopify">Shopify</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Google">Google</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Github">Github</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Twitter">Twitter</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Microsoft">Microsoft</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Mozilla">Mozilla</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Xamarin">Xamarin</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Heroku">Heroku</button></div>
                                <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="com" value="Facebook">Facebook</button></div>
                                <div class="col-lg-6 col-md-6"><button type="submit" class="blog-tag" name="com" value="Red Hat">Red Hat</button></div>
                        </div>
                    </div>
                </div>
                <!--list-->
                <div class="col-2 panel panel-default margin-10">
                    <div class="panel-heading"><h2>User List</h2></div>
                        <div class="panel-body" id="current">
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
    <script src="/js/usertag.js"></script>
</body>
</html>
