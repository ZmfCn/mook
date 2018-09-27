<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>按类型统计</title>
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
<div class="container"
     style="margin-top: 5%; display: block;background-color: white;box-shadow: 8px 5px 5px rgba(0,0,0,0.5);">
    <div class="card">
        <div class="card-header">
            <h3 style="text-align: center;">按类型统计课程数量</h3>
        </div>
        <div class="card-body">
            <!-- <h4 style="text-align: center;padding-top: 10px;"><strong>按类型统计课程数量</strong></h4>-->
            <!--分类统计信息-->
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>课程类别ID</th>
                        <th>课程类别名称</th>
                        <th>该类课程的数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageModel.topTypes}" var="topType">
                        <!--课程类别的具体信息填在这-->
                        <tr>
                            <!--类别ID-->
                            <td>${topType.content.typeId}</td>
                            <!--类别名称-->
                            <td>${topType.content.typeName}</td>
                            <!--类别课程数量-->
                            <td>${topType.courseNumber}</td>
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