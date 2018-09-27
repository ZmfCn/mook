<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的回复</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

    <link rel="stylesheet" href="/statics/styles/form.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="stylesheet" href="/statics/styles/edit-course.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <link rel="stylesheet" href="/statics/styles/mystdy-msg.css">
    <script src="/statics/scripts/comment.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body class="bg-color">
<jsp:include page="navigateBar.jsp" flush="true"/>
<div style="width: 100%; height: 100px"></div>
<div class="container allcontent">
    <div class="card">
        <div class="card-header">
            <h3 class="title--center" style="font-size: 25px">我的回复</h3>
        </div>
        <div class="card-body" style="height: 1300px">
            <c:forEach items="${pageModel.comments}" var="comment">
                <div>
                    <hr>
                    <p style="color: dodgerblue;">
                            ${pageContext.session.getAttribute("username")}
                        <span class="float-right time--msg">${comment.time}</span>
                    </p>
                    <p style="font-size: 18px">${comment.chapterComment.content}</p>
                    <div style="padding: 10px;background-color: #e1e1e1">
                        <p class="p--reply" style="font-size: 15px"><a
                                href="${pageContext.request.contextPath}/course/course/${comment.course.courseId}">${comment.course.courseName}</a>&nbsp;&gt;&nbsp;<a
                                href="${pageContext.request.contextPath}/chapter/chapter/${comment.chapter.chapterId}">${comment.chapter.chapterTitle}</a>
                        </p>
                    </div>
                    <form style="display: inline" class="float-right" method="post" onsubmit="return validateDel()"
                          action="${pageContext.request.contextPath}/comment/my/delete/${comment.chapterComment.commentId}/${pageModel.currentPage}">
                        <button type="submit" class="showa float-right"
                                style="margin-left: 10px;color: blue;)">删除
                        </button>
                    </form>
                    <br>
                    <hr>
                </div>
            </c:forEach>
            <c:if test="${pageModel.comments.size()==0}">
                <p style="font-size: 30px">您未对任何章节发表评论 :)</p>
            </c:if>
        </div>
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
                    href="${pageContext.request.contextPath}/comment/my/${pageModel.currentPage-1}">&larr;上一页</a>
            </li>
        </c:if>
        <c:if test="${pageModel.currentPage >= pageModel.totalPage-1}">
            <li class="next  disabled"><a href="#">下一页&rarr;</a></li>
        </c:if>
        <c:if test="${pageModel.currentPage < pageModel.totalPage -1}">
            <li class="next"><a
                    href="${pageContext.request.contextPath}/comment/my/${pageModel.currentPage+1}">下一页&rarr;</a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>