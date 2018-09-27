<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的学习</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="stylesheet" href="/statics/styles/edit-course.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <link rel="stylesheet" href="/statics/styles/mystdy-msg.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body class="bg-color">
<jsp:include page="navigateBar.jsp" flush="true"/>
<div class="container allcontent">
    <div class="card">
        <div class="card-header">
            <!--添加用户名-->
            <h3 class="title--center">我的学习--(${pageContext.session.getAttribute("username")})</h3>
        </div>
        <div class="card-body">
            <div style="height: 560px">
                <div class="row" style="margin-bottom: 20px;">
                    <c:forEach items="${myCoursePageModel.courses}" var="course">
                        <div class="col-sm-3">
                            <a href="${pageContext.request.contextPath}/course/course/${course.course.courseId}">
                                <img src="${course.course.imagePath}" class="conf img-fluid">
                                    ${course.course.courseName}
                                <!--学习时间设置在这-->
                                <span data-toggle="tooltip" data-placement="right" title="开始学习的时间">
                                    <span class="glyphicon glyphicon-time time--study">${course.date}</span>
                                </span>
                            </a>
                        </div>
                    </c:forEach>
                    <c:if test="${myCoursePageModel.courses.size()==0}">
                        <p style="font-size: 30px">快选择你心仪的课程学习吧 :)</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div>
        <!--设置分页-->
        <ul class="pager" style="margin-top: 40px;">
            <c:if test="${myCoursePageModel.currentPage <= 0}">
                <li class="previous disabled"><a href="#">&larr;上一页</a></li>
            </c:if>
            <c:if test="${myCoursePageModel.currentPage > 0}">
                <li class="previous"><a
                        href="${pageContext.request.contextPath}/course/myCourse/${myCoursePageModel.currentPage-1}">&larr;上一页</a>
                </li>
            </c:if>
            <c:if test="${myCoursePageModel.currentPage >= myCoursePageModel.pages-1}">
                <li class="next  disabled"><a href="#">下一页&rarr;</a></li>
            </c:if>
            <c:if test="${myCoursePageModel.currentPage < myCoursePageModel.pages-1}">
                <li class="next"><a
                        href="${pageContext.request.contextPath}/course/myCourse/${myCoursePageModel.currentPage+1}">下一页&rarr;</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>