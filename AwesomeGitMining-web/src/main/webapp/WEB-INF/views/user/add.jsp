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
    <form:form method="post" modelAttribute="user">
        id:<form:input path="id"/><br>
        login:<form:input path="login"/><br>
        company:<form:input path="company"/><br>

        <input type="submit" value = "添加用户">
    </form:form>
</body>
</html>