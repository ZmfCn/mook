<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>按浏览量统计</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="stylesheet" href="/statics/styles/footer.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body style="background-color: rgba(137, 161, 245,0.5);">
<jsp:include page="navigateBar.jsp" flush="true"/>
<div class="container" style="margin-top: 5%;">
    <!--浏览量统计信息-->
    <div class="card">
        <div class="card-header">
            <h3 style="text-align: center;">按浏览量统计，信息如下</h3>
        </div>
        <div class="card-body" style="width: auto">
            <!--分类统计信息-->
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>课程名称</th>
                        <th>课程浏览数量</th>
                        <th>课程类别ID</th>
                        <th>课程类别名称</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageModel.courses}" var="course">
                        <tr>
                            <!--课程名称-->
                            <td>${course.content.courseName}</td>
                            <!--课程浏览数量-->
                            <td style="color: green; font-weight: bold">${course.content.courseCount}</td>
                            <!--课程类别ID-->
                            <td>${course.topType.typeId}</td>
                            <!--课程类别名称-->
                            <td>${course.topType.typeName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="card-footer">
            <address style="text-align: center;">
                Copyright © 2017 郑膜坊，何志颖，王秋莉 <br>联系邮箱：2804755011@qq.com 地址：云南大学软件学院
            </address>
        </div>
    </div>
</div>
</body>
</html>