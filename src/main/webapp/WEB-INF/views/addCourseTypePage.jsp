<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>增加课程类别</title>
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
<div class="container" style="text-align: center;margin-top: 5%;width: 500px">
    <!--增加课程类别-->
    <form action="${pageContext.request.contextPath}/courseType/addType" method="post">
        <div class="form-group">
            <label>选择将添加的课程的类别</label>
            <select class="form-control" style="height: 35px" name="topTypeId">
                <c:forEach items="${pageModel.topTypes}" var="topType">
                    <option value="${topType.typeId}">${topType.typeName}</option>
                </c:forEach>
            </select>
            <div style="width: 100%;height: 20px"></div>
            <label>将添加的课程类别名</label>
            <input type="text" class="form-control" placeholder="课程类别名" name="courseTypeName">
        </div>
        <button type="submit" class="btn btn-primary btn-block">确认增加</button>
    </form>
</div>
</body>
</html>