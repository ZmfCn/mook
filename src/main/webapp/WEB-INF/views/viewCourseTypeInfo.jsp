<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>查看课程类别</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js">
    </script>

</head>

<body>
<div class="container" style="width: 1000px">
    <!--查看课程类别-->
    <div class="table-responsive" style="width: 950px">
        <table class="table">
            <thead>
            <tr>
                <th>课程类别ID</th>
                <th>课程类别名称</th>
                <th>该类课程的数量</th>
                <th>所属类别</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageModel.courseTypes}" var="course">
                <!--课程类别的具体信息填在这-->
                <tr>
                    <!--类别ID-->
                    <td>${course.courseType.typeId}</td>
                    <!--类别名称-->
                    <td>${course.courseType.typeName}</td>
                    <!--类别课程数量-->
                    <td>${course.subCourseNumber}</td>
                    <td>${course.topTypeName}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>