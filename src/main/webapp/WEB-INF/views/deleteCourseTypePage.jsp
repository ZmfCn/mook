<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>删除课程类别</title>
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
<jsp:include page="showMessage.jsp" flush="true"/>
<div class="container" style="text-align: center;margin-top: 5%;width: 700px">
    <!--删除课程类别-->
    <form action="${pageContext.request.contextPath}/courseType/deleteType" method="post">
        <div class="form-group">
            <label>将删除的课程类别ID</label>
            <input type="text" class="form-control" placeholder="课程类别ID" name="courseTypeId">
        </div>
        <button type="submit" class="btn btn-danger btn-block">确认删除</button>
    </form>
</div>
</body>
</html>