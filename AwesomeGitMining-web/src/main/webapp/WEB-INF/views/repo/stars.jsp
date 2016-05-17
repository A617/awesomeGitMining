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
    <link href="<c:url value="/css/simplify.min.css"/>" rel="stylesheet" type="text/css" media="all">
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
                <li><a href="/statistics/repository">Repository Statistics</a></li>
                <li><a href="/statistics/user">User Statistics</a></li>
                <li><a href="/recommend">Recommended</a> </li>
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
                <div class="col-lg-12">
                    <h3 class="text-uppercase"> Languages</h3>
                    <div class="box-content buttons">
                        <%--<form action="/repo/tag" method="get">--%>
                        <div class="col-lg-3 col-md-6"><button id="onLan" type="submit" class="blog-tag" name="lan" value="all">All</button></div>
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
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Haskell">Haskell</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Clojure">Clojure</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="CSS">CSS</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="C++">C++</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Go">Go</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Lua">Lua</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Prolog">Prolog</button></div>
                        <div class="col-lg-6 col-md-6"><button type="submit" class="blog-tag" name="lan" value="CoffeeScript">CoffeeScript</button></div>
                        <div class="col-lg-6 col-md-6"><button type="submit" class="blog-tag" name="lan" value="Objective-C">Objective-C</button></div>
                        <%--</form>--%>
                    </div>
                </div>

                <div class="col-lg-12">
                    <h3 class="text-uppercase">  Keywords</h3>
                    <div class="box-content buttons">
                        <%--<form action="/repo/tag" method="get">--%>
                        <div class="col-lg-3 col-md-6"><button id="onKey" type="submit" class="blog-tag" name="key" value="all">All</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="API">API</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Django">Django</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="MySQL">MySQL</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="jQuery">jQuery</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="XML">XML</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Web">Web</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Plugin">Plugin</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="database">database</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="IRC">IRC</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="iOS">iOS</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Git">Git</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Emacs">Emacs</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="JSON">JSON</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Linux">Linux</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="vim">vim</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="toolkit">toolkit</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value=".NET">.NET</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Apache">Apache</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Android">Android</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="OS">OS</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="Maven">Maven</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="gem">gem</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="key" value="MVC">MVC</button></div>
                        <%--</form>--%>
                    </div>
                </div>

                <div class="col-lg-12">
                    <h3 class="text-uppercase"> Updated Time</h3>
                    <div class="box-content buttons">
                        <%--<form action="/repo/tag" method="get">--%>
                        <div class="col-lg-3 col-md-6"><button id="onYear" type="submit" class="blog-tag" name="year" value="all">All</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2007">2007</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2008">2008</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2009">2009</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2010">2010</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2011">2011</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2012">2012</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2013">2013</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2014">2014</button></div>
                        <div class="col-lg-3 col-md-6"><button type="submit" class="blog-tag" name="year" value="2015">2015</button></div>
                        <%--</form>--%>
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
                            <a href="#current" onclick='showPage("stars")'>
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
                    <div class="tab-pane fade in active" id="current">
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
<script src="/js/repotag.js"></script>
<script language="javascript">

    function showPage(tabId){
        newurl = "/repo/"+tabId+"?pager.offset=0";
        window.location.href=newurl;
    }

</script>
</body>
</html>

