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
    用户列表
    <br>
    <c:forEach items="${users }" var="umap">
        ${umap.value.id }----<a href="${umap.value.login }">${umap.value.login }</a>----${umap.value.company }<br/>
    </c:forEach>
</body>
</html>
