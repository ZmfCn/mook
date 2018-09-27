<%--
  Created by IntelliJ IDEA.
  User: zmf
  Date: 17-11-20
  Time: 下午7:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>激活账号</title>
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
</head>
<body>
<jsp:include page="navigateBar.jsp"/>
<div style="width: 100%; height: 200px"></div>
<!-- 显示服务器返回的信息 -->
<div>
    <c:if test="${errors!=null}">
        <c:forEach items="${errors }" var="error">
            <div class="alert alert-danger col-lg-4 col-lg-offset-4">
                <a href="#" class="close" data-dismiss="alert">
                    &times;
                </a>
                <p>${error}</p>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${messages!=null}">
        <c:forEach items="${messages }" var="message">
            <div class="alert alert-success col-lg-4 col-lg-offset-4">
                <a href="#" class="close" data-dismiss="alert">
                    &times;
                </a>
                <p>${message}</p>
            </div>
        </c:forEach>
    </c:if>
</div>
<div class="container" style="width:600px">

    <div class="page-header">
        <h1>欢迎注册使用</h1>
    </div>
</div>
</body>
</html>
