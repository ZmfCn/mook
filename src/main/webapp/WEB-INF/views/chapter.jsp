<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${pageModel.courseName}</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/statics/styles/video.css">
    <script src="/statics/scripts/comment.js"></script>

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
</head>
<body>
<jsp:include page="navigateBar.jsp" flush="true"/>
<div style="width: 100%; height: 50px"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 bg shadow">
            <!--返回课程首页的链接-->
            <div class="back">
                <a href="${pageContext.request.contextPath}/course/course/${pageModel.chapter.ownerCourseId}">
                        <span class="glyphicon glyphicon-chevron-left" style="color: rgb(103, 100, 102);">
                        </span>
                    返回课程首页
                </a>
            </div>
            <h3 class="title">课时${pageModel.chapter.chapterOrder} ${pageModel.chapter.chapterTitle}</h3>
            <div>
                <video width="650" height="450" controls preload="metadata" class="video">
                    <source src="${pageModel.chapter.filepath}" type="video/mp4">
                    您的浏览器不支持 HTML5 video 标签,视频暂不可播放。
                </video>
            </div>
            <!--播放下一个视频的链接在这-->
            <c:if test="${pageModel.encryptedNextChapterId != 'none'}">
                <div>
                    <a href="${pageContext.request.contextPath}/chapter/chapter/${pageModel.encryptedNextChapterId}"
                       data-toggle="tooltip" data-placement="top" title="下一个课程">
                    <span class="glyphicon glyphicon-chevron-down"
                          style="color: rgb(103, 100, 102);font-size: 25px;margin-left: 50px;margin-top: 50px;"></span>
                    </a>
                </div>
            </c:if>
            <!--播放下一个视频的链接在这-->
            <c:if test="${pageModel.encryptedNextChapterId == 'none'}">
                <div>
                    <a href="#"
                       data-toggle="tooltip" data-placement="top" title="下一个课程">
                    <span class="glyphicon glyphicon-chevron-down"
                          style="color: rgb(103, 100, 102);font-size: 25px;margin-left: 50px;margin-top: 50px;"></span>
                    </a>
                </div>
            </c:if>
        </div>
        <div class="col-sm-4" style="height: 635px">

            <div style="background-color: rgba(102,102,102,0.2);display: block;z-index: 5;padding-top: 10px;"
                 class="fixed-up">
                <nav class="nav nav-pills">
                    <li><a href="${pageContext.request.contextPath}/comment/${pageModel.chapter.chapterId}/0"
                           target="myframe" id="comment"><span
                            class="glyphicon glyphicon-road" style="color: rgb(142, 174, 237);"> 讨论</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/chapter/catalog/${pageModel.chapter.ownerCourseId}"
                           target="myframe" id="catelogue"><span
                            class="glyphicon glyphicon-th-list" style="color: rgb(142, 174, 237);"> 目录</span></a></li>
                </nav>
            </div>
            <iframe src="${pageContext.request.contextPath}/comment/${pageModel.chapter.chapterId}/0" scrolling="auto" width="350px"
                    height="590px" name="myframe">
                <p>您的浏览器不支持 iframe 标签。</p>
            </iframe>
        </div>
    </div>
</div>
</body>
</html>