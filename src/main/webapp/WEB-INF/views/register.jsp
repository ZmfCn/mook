<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<jsp:include page="showMessage.jsp" flush="true"/>
<form:form class="form-horizontal form-style" role="form"
           action="${pageContext.request.contextPath}/user/register"
           method="post" modelAttribute="user">
    <h3 style="text-align: center;">注册<!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>--></h3>
    <hr>
    <div class="form-group">
        <div class="col-sm-10">
            <!--输入邮箱框在这-->
            <form:input type="text" class="form-control" id="email"
                        placeholder="请输入注册邮箱" path="email"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10">
            <form:input type="text" class="form-control" id="username"
                        placeholder="请输入用户名" path="userName"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10">
            <!--输入密码框在这-->
            <form:input type="password" class="form-control" id="password"
                        placeholder="请输入密码" path="password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10">
            <!--确认密码框在这-->
            <input type="password" class="form-control" id="password_again"
                   placeholder="请确认密码">
        </div>
    </div>
    <div class="form-group">
        <div style="margin-left: 15px;margin-right: 15px;">
            <div class="checkbox">
                <label>
                    <input type="checkbox" id="agree"> 同意爱课程注册协议
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">注册</button>
    </div>
</form:form>

</body>
</html>