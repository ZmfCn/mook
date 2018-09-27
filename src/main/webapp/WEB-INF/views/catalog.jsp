<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>catalog</title>
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

    <style>
        /*冻结布局*/
        .allContent {
            width: 1500px;
            background-color: white;
            margin-left: auto;
            margin-right: auto;
        }

        /*控制轮播图片的自适应性*/
        .carousel-inner img {
            width: auto;
            height: auto;
        }

        /*设置边框为圆角*/
        .border-radius {
            border-radius: 20px;
        }

        span {
            margin-right: 5px;
        }

        /*鼠标悬浮在链接上时，改变样式*/
        a:hover {
            color: red;
            text-decoration: none;
        }
    </style>

</head>

<body style="width: 335px">
<div class="container allContent">
    <!--目录设置在这-->
    <div class="col-sm-9 card">
        <div class="card-header">
            <h4>目录</h4>
        </div>
        <div class="card-body title">
            <c:forEach items="${pageModel.chapters}" var="chapter">
                <div>
                    <a href="${pageContext.request.contextPath}/chapter/chapter/${chapter.chapterId}" target="_top">
                        <c:if test="${!chapter.isPdf}">
                            <span class="glyphicon glyphicon-expand" style="color: rgb(79, 184, 232);"></span>
                            ${chapter.duration}
                        </c:if>
                        <c:if test="${chapter.isPdf}">
                            <span class="glyphicon glyphicon-file" style="color:  rgb(140, 229, 127);"></span>
                        </c:if>
                    </a>
                    <span>课时${chapter.chapterOrder} ${chapter.chapterTitle}</span>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>