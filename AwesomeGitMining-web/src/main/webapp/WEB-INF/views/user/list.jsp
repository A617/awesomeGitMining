<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
    用户列表
    <br>
    <c:forEach items="${users }" var="u">
        ${u.id }----<a href="${u.login }">${u.login }</a>----${u.company }<br/>
    </c:forEach>

    ${pageUrl}<br>


    <pg:pager url="/user/users" items="${total}">
        <pg:first>
            <a href="${pageUrl}">首页</a>
        </pg:first>
        <pg:prev>
            <a href="${pageUrl }">前页</a>
        </pg:prev>
        <pg:pages>
            <a href="${pageUrl }">${pageNumber }</a>
        </pg:pages>
        <pg:next>
            <a href="${pageUrl }">后页</a>
        </pg:next>
        <pg:last>
            <a href="${pageUrl }">尾页</a>
        </pg:last>
    </pg:pager>




</body>
</html>

