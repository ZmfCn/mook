<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加课程</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body class="bg">
<jsp:include page="navigateBar.jsp" flush="true"/>
<div style="width: 100%; height: 100px"></div>
<jsp:include page="showMessage.jsp" flush="true"/>
<div class="container">
    <div class="card  form-style">
        <div class="card-header">
            <h3>添加课程</h3>
        </div>
        <div class="card-body">
            <form role="form" action="${pageContext.request.contextPath}/course/addCourse" method="post"
                  enctype="multipart/form-data">
                <div class="form-group">
                    <label>选择将添加的课程的类别</label>
                    <select class="form-control" style="height: 35px" name="parentTypeId">
                        <c:forEach items="${pageModel.courseTypes}" var="courseType">
                            <option value="${courseType.typeId}">${courseType.typeName}</option>
                        </c:forEach>
                    </select>
                    <label class="label-position">将添加的课程的名字</label>
                    <input name="courseName" type="text" class="form-control" placeholder="课程的全称">
                    <label class="label-position">将添加的课程的讲师名字</label>
                    <input name="teacherName" type="text" class="form-control" placeholder="讲师的姓名">
                    <label class="label-position">选择要上传的课程的封面图</label>
                    <input type="file" name="img" placeholder="选择图片地址" class="form-control" style="margin-bottom: 10px">
                </div>
                <div class="card-footer">
                    <button class="btn btn-primary" type="submit">确定添加</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>