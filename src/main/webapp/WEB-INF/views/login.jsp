<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<!-- 显示服务器返回的信息 -->
<jsp:include page="navigateBar.jsp" flush="true"/>
<div>
    <c:if test="${allErrors!=null}">
        <c:forEach items="${allErrors }" var="error">
            <div class="alert alert-danger col-lg-4 col-lg-offset-4">
                <a href="#" class="close" data-dismiss="alert">
                    &times;
                </a>
                <p>${error.defaultMessage}</p>
            </div>
        </c:forEach>
    </c:if>
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
</div>

<form:form class="form-horizontal form-style" role="form"
           action="${pageContext.request.contextPath}/user/login"
           method="post" modelAttribute="user">
    <h3 style="text-align: center;">登录</h3>
    <hr>
    <!--邮箱输入框-->
    <div class="form-group">
        <div class="col-sm-10">
            <form:input type="text" class="form-control" id="firstname"
                   placeholder="请输入登录邮箱" path="email"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10">
            <!--密码输入框-->
            <form:input type="password" class="form-control" id="lastname"
                        placeholder="6-16位密码，区分大小写，不能使用空格" path="password"/>
        </div>
    </div>
    <div class="form-group">
        <div style="margin-left: 15px;margin-right: 15px;">
            <div class="checkbox">
                <label>
                    <input type="checkbox">7天内自动登录
                </label>
                <a href="${pageContext.request.contextPath}/user/register" style="margin-left: 110px;" target="_blank">去注册</a>
            </div>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-danger btn-block">登录</button>
    </div>
</form:form>

</body>
</html>