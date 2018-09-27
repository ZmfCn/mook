<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改课程信息</title>
    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <link rel="stylesheet" href="/statics/styles/add-course-chapter.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
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
<div class="container div--pos">
    <div class="card  form-style">
        <div class="card-header">
            <h3>修改课程信息</h3>
        </div>
        <div class="card-body">
            <form role="form" onsubmit="return confirm('确定修改吗？')" method="post" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/course/alterCourse">
                <div class="form-group">
                    <label class="label-position">原课程的名称</label>
                    <input name="preName" type="text" class="form-control" placeholder="原课程的名称">
                    <label class="label-position">将该课程更名为</label>
                    <input name="newName" type="text" class="form-control" placeholder="课程的新名称，不更改则置空">
                    <label class="label-position">教师姓名</label>
                    <input name="teacherName" type="text" class="form-control" placeholder="教师姓名，不更改则置空">
                    <label class="label-position">选择新的课程的封面图</label>
                    <input type="file" name="img" placeholder="选择图片地址" class="form-control">
                </div>
                <div class="card-footer">
                    <button class="btn btn-primary" type="submit">确定修改</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
