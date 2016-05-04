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
    <div class="box col-md-4">
        <div class="well">
            <form action="/user/users/search" method="post">
                <input type="text" class="search-query form-control col-md-10" name="name" placeholder="Search keyword..."><br>
                <button type="submit" class="btn btn-primary btn-sm">Search</button>
            </form>
        </div>
        <div class="box-inner">
            <div class="box-header well">
                <h2> Languages</h2>
            </div>
            <div class="box-content buttons">
                <p>
                    <button class="btn btn-default btn-sm"> All</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Scala</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> C</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Ruby</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Java</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Python</button>&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> JavaScript</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Perl</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> PHP</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> HTML</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Shell</button>&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Objective-C</button>&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Haskell</button>&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Clojure</button>&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> C#</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> CSS</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> C++</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Lua</button>
                </p>
            </div>
        </div>

        <div class="box-inner">
            <div class="box-header well">
                <h2> Companys</h2>
            </div>
            <div class="box-content buttons">
                <p>
                    <button class="btn btn-default btn-sm"> All</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Shopify</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Google</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Github</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Twitter</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Red Hat</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Mozilla</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Xamarin</button>
                </p>
                <p>
                    <button class="btn btn-default btn-sm"> Heroku</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Facebook</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-default btn-sm"> Microsoft</button>
                </p>
            </div>
        </div>
    </div>

    <div class="box col-md-8">
        <div class="box-inner">
            <div class="box-header well">
                <h2> 用户列表</h2>
            </div>
            <div class="box-content">
                <div class="box-content">
                    <ul class="dashboard-list">
                        <c:forEach items="${users }" var="u">
                            <li>
                                <h3><i class="glyphicon glyphicon-th"><strong>${u.id }&nbsp;<a href="${u.login }">${u.login }</a></strong><br></i></h3>
                                <strong>Followers:</strong>&nbsp;${u.followers}<br>
                                <strong>Location:</strong>&nbsp;${u.location}<br>
                                <strong>Company:</strong>&nbsp;${u.company}<br>
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
