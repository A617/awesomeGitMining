<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
    <form action="/repo/repos/search" method="post">
        search:<input type="text" name="name"/><br>

        <input type="submit" value = "search">
    </form>
    仓库列表
    <br>
    <c:forEach items="${repos }" var="repo">
        ${repo.id }----<a href="${repo.fullName}">${repo.fullName}</a>----<a href="/user/${repo.ownerName}">relatedUser</a><br/>
    </c:forEach>

</body>
</html>
