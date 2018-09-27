<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>所有课程</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/sider-menu.css">
    <link rel="stylesheet" href="/statics/styles/footer.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
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

<body>

<div class="container allContent">
    <!--顶部导航栏的相关设置-->
    <jsp:include page="navigateBar.jsp" flush="true"/>
    <div style="width: 100%; height: 200px"></div>

    <div style="height: 500px">
        <!--课程-->
        <div class="row" style="margin-bottom: 20px;">
            <c:forEach items="${pageModel.courses}" var="course">
                <div class="col-sm-3">
                    <a href="/course/course/${course.courseId}">
                        <img src="${course.imagePath}" class="conf img-fluid"><br/>
                            ${course.courseName}
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>


    <div>
        <!--设置分页-->
        <ul class="pager" style="margin-top: 40px;">
            <c:if test="${pageModel.currentPage <= 0}">
                <li class="previous disabled"><a href="#">&larr;上一页</a></li>
            </c:if>
            <c:if test="${pageModel.currentPage > 0}">
                <li class="previous"><a
                        href="${pageContext.request.contextPath}/course/all/${pageModel.currentPage-1}">&larr;上一页</a>
                </li>
            </c:if>
            <c:if test="${pageModel.currentPage >= pageModel.pages-1}">
                <li class="next  disabled"><a href="#">下一页&rarr;</a></li>
            </c:if>
            <c:if test="${pageModel.currentPage < pageModel.pages -1}">
                <li class="next"><a
                        href="${pageContext.request.contextPath}/course/all/${pageModel.currentPage+1}">下一页&rarr;</a>
                </li>
            </c:if>
        </ul>
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