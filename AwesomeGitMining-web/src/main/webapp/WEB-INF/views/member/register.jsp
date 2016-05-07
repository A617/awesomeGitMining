<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>sign up</title>
    <link href="<c:url value="/css/listcss.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/templatemo-style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
</head>
<body  class="light-gray-bg">
    <div class="templatemo-top-nav-container">
        <div class="row">
            <nav class="templatemo-top-nav">
                <ul>
                    <li><a href="/index.jsp">Home</a></li>
                    <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                    <li><a href="/user/users?pager.offset=0">User</a></li>
                    <li><a href="#">Repository Statistics</a></li>
                    <li><a href="#">User Statistics</a></li>
                    <li><a href="#">Recommended</a> </li>
                </ul>
            </nav>

            <div class="dropdown navbar-right">
                <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
                    Visitors <b class="caret"></b>
                </a>
                <ul class="dropdown-menu animated fadeInRight">
                    <li>
                        <a href="/login.jsp">Sign in</a>
                    </li>
                    <li>
                        <a href="#">Sign up</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="templatemo-content-widget templatemo-login-widget white-bg">
        <header class="text-center">
            <div class="square"></div>
            <h1>Sign up</h1>
        </header>
        <form modelAttribute="member" method="post" class="templatemo-login-form">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>
                    <input type="text" name="username" class="form-control" placeholder="username">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>
                    <input type="password" name="password" class="form-control" placeholder="password">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>
                    <input type="email" name="member_email" class="form-control" id="inputEmail" placeholder="email">
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="templatemo-blue-button width-100">Sign up</button>
            </div>
        </form>
    </div>

    <footer class="text-right">
        <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
    </footer>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>