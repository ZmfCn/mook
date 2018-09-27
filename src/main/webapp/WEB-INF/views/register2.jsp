<%--
  Created by IntelliJ IDEA.
  User: zmf
  Date: 17-11-14
  Time: 下午2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/sider-menu.css">
    <link rel="stylesheet" href="/statics/styles/footer.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>
        function validInput() {
            var value = document.forms['myform']['username'].value;
            if (value == null || value == "") {
                alert("Username can not be empty.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<jsp:include page="navigateBar.jsp" flush="true"/>
<div style="width: 100%; height: 100px"></div>
<jsp:include page="showMessage.jsp" flush="true"/>
<div class="container" style="width:600px">

    <div class="page-header">
        <h1>欢迎注册使用</h1>
    </div>
    <form:form name="myform" class="form" role="form" onsubmit="return validInput()"
               action="${pageContext.request.contextPath}/user/register"
               method="post" modelAttribute="user">
        <div class="form-group">
            <label class="control-label">邮箱</label>
            <form:input class="form-control" name="email" path="email"/>
        </div>
        <div class="form-group">
            <label class="control-label">用户名</label>
            <form:input class="form-control" name="userName" path="userName"/>
        </div>
        <div class="form-group">
            <label class="control-label">密码</label>
            <form:password class="form-control" name="password" path="password"/>
        </div>
        <div>
            <input type="submit" value="注册" class="btn btn-default"/>
        </div>
    </form:form>
</div>
</body>
</html>
