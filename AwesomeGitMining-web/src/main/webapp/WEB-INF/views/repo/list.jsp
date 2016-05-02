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
    仓库列表
    <br>
    <c:forEach items="${repos }" var="repo">
        ${repo.id }----<a href="${repo.id}">${repo.fullName}</a>----<a href="/user/${repo.ownerName}">relatedUser</a><br/>
    </c:forEach>
</body>
</html>
