<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>敏感词管理</title>
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body style="background-color: rgba(137, 161, 245,0.5);">
<jsp:include page="navigateBar.jsp" flush="true"/>
<div style="width: 100%; height: 100px"></div>
<div class="container" style="text-align: center;margin-top: 5%;width: 580px; height: 400px;">
    <!--关键字管理-->
    <div class="card">
        <div class="card-header">
            <h3>敏感词管理</h3>
            <ul class="nav nav-pills" style="background-color: rgb(204,204,204);">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/keyword/view" target="myframe">查看已有的敏感词</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/keyword/add" target="myframe">
                        增加敏感词
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/keyword/delete" target="myframe">
                        删除敏感词
                    </a>
                </li>
            </ul>
        </div>
        <div class="card-body">
            <iframe name="myframe" style="width: 500px; height: 400px;"
                    src="${pageContext.request.contextPath}/keyword/view">
            </iframe>
        </div>
    </div>
</div>
</body>
</html>