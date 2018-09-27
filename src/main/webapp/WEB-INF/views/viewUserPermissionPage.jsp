<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>查看用户权限</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js">
    </script>

</head>
	
<body>
    <div class="container" >
        <!--查看用户的权限信息-->
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>用户ID</th>
                        <th>用户名</th>
                        <th>评论权限</th>
                    </tr>
                </thead>
                <tbody>
                <!--用户的权限的具体信息填在这-->
                <c:forEach items="${pageModel.users}" var="user">
                    <tr>
                        <!--用户ID-->
                        <td>${user.userId}</td>
                        <!--用户名-->
                        <td>${user.userName}</td>
                        <!--评论权限-->
                        <td>${!user.isCommentExpired}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</body>
</html>