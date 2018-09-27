<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${coursePageModel.course.courseName}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/sider-menu.css">
    <link rel="stylesheet" href="/statics/styles/footer.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="stylesheet" href="/statics/styles/catalogue.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<div class="container allContent">
    <jsp:include page="navigateBar.jsp" flush="true"/>

    <!--课程导航栏在这-->
    <ol class="breadcrumb" style="margin-top: 70px; ">
        <li><a href="${pageContext.request.contextPath}/">首页
        </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/course/all/0">所有课程</a>
        </li>
        <li><a href="#">课程详情
        </a>
        </li>
    </ol>

    <%--<<!doctype html>--%>
    <%--<html lang="en">--%>
    <%--<head>--%>
    <%--<meta charset="UTF-8">--%>
    <%--<meta name="viewport"--%>
    <%--content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
    <%--<meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
    <%--<title>Document</title>--%>
    <%--</head>--%>
    <%--<body>--%>
    <%----%>
    <%--</body>--%>
    <%--</html>--%>
    <div class="course-introduce">
        <div class="row">
            <div class="col-sm-5">
                <img src="${coursePageModel.course.imagePath}" alt="${coursePageModel.course.courseName}"
                     class="img-responsive img-rounded">
            </div>
            <div class="col-sm-7">
                <h3>${coursePageModel.course.courseName}</h3>
                <p>讲师：${coursePageModel.course.teacherName}</p>
                <p>课程简介</p>
                <br>
                <br>
                <c:if test="${!coursePageModel.userAddedToLearn}">
                    <form action="${pageContext.request.contextPath}/course/addToLearn/${coursePageModel.course.courseId}"
                          method="post">
                        <button type="sub" class="btn btn-warning btn-lg"
                        >加入学习
                        </button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>


    <!--目录设置在这-->
    <div class="row">
        <div class="col-sm-12 card">
            <div class="card-header">
                <h2>目录</h2>
            </div>
            <div class="card-body title">
                <%--<h3>章节1 Python可不仅仅是条大蟒蛇</h3>--%>
                <c:forEach items="${coursePageModel.chapters}" var="chapter">
                    <div>
                        <span>课时${chapter.chapterOrder} ${chapter.chapterTitle}</span>
                        <a href="${pageContext.request.contextPath}/chapter/chapter/${chapter.chapterId}"
                           class="float-right" style="float: right">
                            <c:if test="${!chapter.isPdf}">
                                <span class="glyphicon glyphicon-expand" style="color: rgb(79, 184, 232);"></span>
                                ${chapter.duration}
                            </c:if>
                            <c:if test="${chapter.isPdf}">
                                <span class="glyphicon glyphicon-file" style="color:  rgb(140, 229, 127);"></span>
                            </c:if>
                        </a>
                        <c:if test='${pageContext.session.getAttribute("permissionCode")=="1"}'>
                            <form action="${pageContext.request.contextPath}/chapter/deleteChapter/${coursePageModel.course.courseId}/${chapter.chapterId}"
                                  method="post" onsubmit="return confirm('确认删除吗，该操作不可撤销')"
                                  style="display: inline;background-color:#00000000">
                                <button type="submit"
                                        style="float:right;background-color: transparent;border: transparent"><span
                                        class="glyphicon glyphicon-trash span--delete"></span></button>
                            </form>
                            <a href="${pageContext.request.contextPath}/chapter/alterChapter/${chapter.chapterId}"
                               class="float-right">
                                <span class="glyphicon glyphicon-wrench" style="color: rgb(60, 228, 237);">修改章节信息</span>
                            </a>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!--添加章节的设置在这-->
        <c:if test='${pageContext.session.getAttribute("permissionCode")=="1"}'>
            <div>
                <a href="${pageContext.request.contextPath}/chapter/addChapter/${coursePageModel.course.courseId}"
                   class="nav-link" target="_self">
                    <span class="glyphicon glyphicon-plus" style="color: orange;">添加章节</span>
                </a>
            </div>
        </c:if>
    </div>

</div>

<!--底部栏的设置在这-->
<div class="return">
    <a href="#" style="color: white;font-size: 15px;">回到顶部</a>
</div>
<footer class="position">
    <address>
        Copyright © 2017 郑膜坊，何志颖，王秋莉 <br>联系邮箱：2804755011@qq.com 地址：云南大学软件学院
    </address>
</footer>

</body>
</html>