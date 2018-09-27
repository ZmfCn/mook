<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>爱课程-梦想起航</title>
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
    <script>
        <c:forEach items="${pageModel.topTypes}" var="item">
        $(function () {
            /*提示隐藏*/
            $('#${item.content.typeId}-list').hide();
            /*鼠标悬浮在链接上*/
            $('#${item.content.typeId}').hover(function () {
                $('#${item.content.typeId}-list').show();
            }, function () {
                $('#${item.content.typeId}-list').hide();
            });
            /*鼠标悬浮在二级菜单上*/
            $('#${item.content.typeId}-list').hover(function () {
                $('#${item.content.typeId}-list').show();
            }, function () {
                $('#${item.content.typeId}-list').hide();
            });
        });
        </c:forEach>
        $(function () {
            $('#sign-in').modal({
                keyboard: true
            })
        });
    </script>
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
    <jsp:include flush="true" page="navigateBar.jsp"/>

    <!--整个菜单栏的设置-->
    <div class="row bg-dark border-radius shadow" style="margin-top: 90px;">
        <!--左侧导航栏菜单的设置-->
        <div class="menuContent col-sm-3">
            <nav class="navbar bg-dark">
                <ul class="navbar-nav">
                    <c:forEach items="${pageModel.topTypes}" var="item">
                        <li class="nav-item">
                            <a href="#" class="nav-link init" id="${item.content.typeId}">${item.content.typeName}<span
                                    class="glyphicon glyphicon-chevron-right float-right"></span></a>
                            <div id="${item.content.typeId}-list" class="submenu">
                                <p class="sub-title"><strong>全部</strong></p>
                                <hr>
                                <c:forEach items="${item.courseTypes}" var="subType">
                                    <a href="${pageContext.request.contextPath}/course/typeAll/${subType.typeId}/0"
                                       class="left-right">${subType.typeName}</a>
                                </c:forEach>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>

        <!--轮播图片的设置-->
        <!--指示符-->
        <div class="carousel slide col-sm-9" id="images" data-ride="carousel">
            <ul class="carousel-indicators">
                <li data-target="#images" data-slide-to="0" class="active"></li>
                <li data-target="#images" data-slide-to="1"></li>
                <li data-target="#images" data-slide-to="2"></li>
                <li data-target="#images" data-slide-to="3"></li>
                <li data-target="#images" data-slide-to="4"></li>
                <li data-target="#images" data-slide-to="5"></li>
            </ul>

            <!--轮播图片-->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <a href="#">
                        <img src="http://ozkbl6lk3.bkt.clouddn.com/17-11-19/22894757.jpg">
                        <div class="carousel-caption">
                            <h3>图一标题</h3>
                            <p>描述文字</p>
                        </div>
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="#">
                        <img src="http://ozkbl6lk3.bkt.clouddn.com/17-11-19/15681981.jpg">
                        <div class="carousel-caption">
                            <h3>图二标题</h3>
                            <p>描述文字</p>
                        </div>
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="#">
                        <img src="http://ozkbl6lk3.bkt.clouddn.com/17-11-19/97040192.jpg">
                        <div class="carousel-caption">
                            <h3>图三标题</h3>
                            <p>描述文字</p>
                        </div>
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="#">
                        <img src="http://ozkbl6lk3.bkt.clouddn.com/17-11-19/8114822.jpg">
                        <div class="carousel-caption">
                            <h3>图四标题</h3>
                            <p>描述文字</p>
                        </div>
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="#">
                        <img src="http://ozkbl6lk3.bkt.clouddn.com/17-11-19/53430078.jpg">
                        <div class="carousel-caption">
                            <h3>图五标题</h3>
                            <p>描述文字</p>
                        </div>
                    </a>
                </div>
                <div class="carousel-item">
                    <a href="#">
                        <img src="http://ozkbl6lk3.bkt.clouddn.com/17-11-19/4719404.jpg">
                        <div class="carousel-caption">
                            <h3>图六标题</h3>
                            <p>描述文字</p>
                        </div>
                    </a>
                </div>
            </div>

            <!--左右切换按钮-->
            <a class="carousel-control-prev" href="#images" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#images" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
    </div>

    <!--实战推荐的设置在这-->
    <h3 class="type-title">
        <span class="glyphicon glyphicon-tree-conifer"
              style="color: rgb(255, 140, 60);"></span><em>实</em>/<em>战</em>/<em>推</em>/荐<span
            class="glyphicon glyphicon-tree-conifer" style="color: rgb(255, 140, 60);"></span>
    </h3>
    <div class="row">
        <c:forEach items="${pageModel.recommendCourses}" var="recommend">
            <div class="col-sm-3">
                <a href="/course/course/${recommend.courseId}">
                    <img src="${recommend.imagePath}" class="conf img-fluid"><br/>
                        ${recommend.courseName}
                </a>
            </div>
        </c:forEach>
    </div>

    <!--新上好课的设置在这-->
    <h3 class="type-title">
        <span class="glyphicon glyphicon-leaf"
              style="color: rgb(255, 200, 234);"></span><em>新</em>/<em>上</em>/<em>好</em>/课<span
            class="glyphicon glyphicon-leaf" style="color: rgb(255, 200, 234); "></span>
    </h3>
    <div class="row">
        <c:forEach items="${pageModel.newCourses}" var="newCourse">
            <div class="col-sm-3">
                <a href="/course/course/${newCourse.courseId}">
                    <img src="${newCourse.imagePath}" class="conf img-fluid"><br/>
                        ${newCourse.courseName}
                </a>
            </div>
        </c:forEach>
    </div>

    <!--技能提升的设置在这-->
    <h3 class="type-title">
        <span class="glyphicon glyphicon-send" style="color: rgb(75, 177, 246);"></span><em>技</em>/<em>能</em>/<em>提</em>/升<span
            class="glyphicon glyphicon-send" style="color: rgb(75, 177, 246); "></span>
    </h3>
    <div class="row">
        <c:forEach items="${pageModel.improveCourses}" var="improveCourse">
            <div class="col-sm-3">
                <a href="/course/course/${improveCourse.courseId}">
                    <img src="${improveCourse.imagePath}" class="conf img-fluid"><br/>
                        ${improveCourse.courseName}
                </a>
            </div>
        </c:forEach>
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