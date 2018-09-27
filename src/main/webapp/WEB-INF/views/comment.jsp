<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>讨论区</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/sider-menu.css">
    <link rel="stylesheet" href="/statics/styles/footer.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="stylesheet" href="/statics/styles/video.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/statics/scripts/comment.js"></script>

    <style>
        * {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body style="width: 340px;">
<h4>综合讨论区</h4>
<div class="form-group" style="z-index: 5">
    <form action="${pageContext.request.contextPath}/comment/publish/${pageModel.chapterId}/${pageModel.currentPage}"
          method="post">
        <input name="content" type="text" class="form-control" id="comment" placeholder="请在此输入您的评论，请注意文明用语！">
        <button type="submit" class="btn btn-primary" style="margin-left: 120px;margin-top: 10px;">发表评论</button>
    </form>
</div>
<div class="comment" style="height: 370px">
    <c:forEach items="${pageModel.comments}" var="comment">
        <div>
            <div>
                <span class="glyphicon glyphicon-user" style="color: rgb(142, 174, 237);"></span>${comment.userName}
                <c:if test='${pageContext.session.getAttribute("permissionCode")=="2" ||
                comment.chapterComment.commentUserId.equals(pageContext.session.getAttribute("userid"))}'>
                    <form style="display: inline" method="post" onsubmit="return validateDel()"
                          action="${pageContext.request.contextPath}/comment/delete/${comment.chapterComment.commentId}/${pageModel.chapterId}/${pageModel.currentPage}">
                        <button type="submit" class="showa float-right"
                                style="margin-left: 10px;color: blue">删除
                        </button>
                    </form>
                </c:if>
                <span class="float-right" style="font-style: italic">${comment.time}</span>
            </div>
            <p style="font-size: 20px">${comment.chapterComment.content}</p>
        </div>
        <hr>
    </c:forEach>
    <c:if test="${pageModel.comments.size()==0}">
        <p style="font-size: 30px">此处还什么都没有哦～，快来抢占前排吧～</p>
    </c:if>
</div>
<div>
    <!--设置分页-->
    <ul class="pager" style="margin-top: 20px;">
        <c:if test="${pageModel.currentPage <= 0}">
            <li class="previous disabled"><a href="#">&larr;上一页</a></li>
        </c:if>
        <c:if test="${pageModel.currentPage > 0}">
            <li class="previous"><a
                    href="${pageContext.request.contextPath}/comment/${pageModel.chapterId}/${pageModel.currentPage-1}">&larr;上一页</a>
            </li>
        </c:if>
        <c:if test="${pageModel.currentPage >= pageModel.totalPage-1}">
            <li class="next  disabled"><a href="#">下一页&rarr;</a></li>
        </c:if>
        <c:if test="${pageModel.currentPage < pageModel.totalPage -1}">
            <li class="next"><a
                    href="${pageContext.request.contextPath}/comment/${pageModel.chapterId}/${pageModel.currentPage+1}">下一页&rarr;</a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>
