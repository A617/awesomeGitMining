<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello</title>
    <link href="<c:url value="/css/listcss.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all">
</head>
<body>
<div class="row">
    <form action="/repo/repos/search" method="post">
        <input type="text" name="name"/><br>
        <input type="submit" value = "search">
    </form>

    <div class="box col-md-4">
        <div class="box-inner">
            <div class="box-header well">
                <h2> Languages</h2>
            </div>
            <div class="box-content buttons">
                <p>
                    <button class="btn btn-default btn-sm"> All</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> JavaScript</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> C</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Perl</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Java</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> C#</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> CoffeeScript</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Go</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> PHP</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> C++</button>
                </p>
                <p>
                        <button class="btn btn-default btn-sm"> Prolog</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Clojure</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> CSS</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Shell</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> HTML</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Ruby</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Python</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Haskell</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Scala</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Lua</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Objective-C</button>
                </p>
            </div>
        </div>

        <div class="box-inner">
            <div class="box-header well">
                <h2> Keywords</h2>
            </div>
            <div class="box-content buttons">
                <p>
                    <button class="btn btn-default btn-sm"> API</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Django</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> MySQL</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> jQuery</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> XML</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Web</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Plugin</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> database</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> IRC</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> IOS</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Git</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Emacs</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> JSON</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Linux</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> vim</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> toolkit</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> .NET</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Apache</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Android</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> OS</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Maven</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> gem</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> MVC</button>
                </p>
            </div>
        </div>

        <div class="box-inner">
            <div class="box-header well">
                <h2> Updated Time</h2>
            </div>
            <div class="box-content buttons">
                <p>
                    <button class="btn btn-default btn-sm"> All</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2007</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2008</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2009</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2010</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> 2011</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2012</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2013</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2014</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> 2015</button>
                </p>
            </div>
        </div>
    </div>

    <div class="box col-md-8">
        <div class="box-inner">
            <div class="box-header well">
                <h2> 仓库列表</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <ul class="dashboard-list">
                        <c:forEach items="${repos }" var="repo">
                            <li>
                                <h3><i class="glyphicon glyphicon-th"><strong>${repo.id }&nbsp;<a href="${repo.fullName }">${repo.fullName }</a></strong></i></h3>
                                <p><strong>${repo.language}</strong></p>
                                <p><strong>Subscribers:</strong>&nbsp;${repo.subscribersCount}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <strong>Forks:</strong>&nbsp;${repo.forksCount}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <strong>Stargazers:</strong>&nbsp;${repo.stargazersCount}</p>
                                <p>${repo.description}</p>
                                <strong>Last Updated:</strong>&nbsp;${repo.updatedAt}<br>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <ul class="pagination pagination-centered">
            ${pageUrl}<br>
            <pg:pager url="/user/users" items="${total}">
                <li>
                    <pg:first>
                        <a href="${pageUrl}">首页</a>
                    </pg:first>
                </li>
                <li>
                    <pg:prev>
                        <a href="${pageUrl }">前页</a>
                    </pg:prev>
                </li>
                <li>
                    <pg:pages>
                        <a href="${pageUrl }">${pageNumber }</a>
                    </pg:pages>
                </li>
                <li>
                    <pg:next>
                        <a href="${pageUrl }">后页</a>
                    </pg:next>
                </li>
                <li>
                    <pg:last>
                        <a href="${pageUrl }">尾页</a>
                    </pg:last>
                </li>
            </pg:pager>
        </ul>
    </div>
</div>
</body>
</html>

