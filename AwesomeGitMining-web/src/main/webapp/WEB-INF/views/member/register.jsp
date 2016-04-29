<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
    <form:form method="post" modelAttribute="member">
        username:<form:input path="username"/><form:errors path="username"/><br>
        password:<form:input type="password" path="password"/><form:errors path="password"/><br>

        <input type="submit" value = "提交">
    </form:form>
</body>
</html>