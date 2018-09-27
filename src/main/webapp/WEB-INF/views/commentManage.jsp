<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>审核用户评论</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/catalogue.css">
    <link rel="stylesheet" href="/statics/styles/mystdy-msg.css">
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
<div style="width: 100%; height: 100px"></div>
<div class="container allContent">
    <div class="card">
        <div class="card-header" style="margin-top: 50px;text-align: center;">
            <h1>审核用户评论</h1>
        </div>
        <div class="card-body title"
             style="border-style: groove;margin: 5px;border-color: lightskyblue;border-width: 1px;">

            <!--表单设置在这-->
            <form action="${pageContext.request.contextPath}/comment/manage/${pageModel.currentPage}"
                  method="post">
                <c:forEach items="${pageModel.comments}" var="comment">
                    <div class="comments">
                        <!--设置用户名-->
                        <p style="color: dodgerblue;">
                                ${comment.userName}
                            <!--设置讨论时间-->
                            <span class="float-right" style="color: lightblue">${comment.time}</span>
                        </p>
                        <!--设置评论内容-->
                        <p>${comment.chapterComment.content}</p>
                        <!--通过和禁止按钮的设置在这-->
                        <span>
                        <input type="radio" name="${comment.chapterComment.commentId}" value="true">通过
                    </span>
                        <span class="float-right">
                        <input type="radio" name="${comment.chapterComment.commentId}" value="false">禁止
                    </span>
                    </div>
                    <br>
                </c:forEach>
                <c:if test="${pageModel.comments.size()==0}">
                    <p style="font-size: 30px">暂无评论需要审核，请休息吧 :)</p>
                </c:if>
                <hr>
                <!--提交按钮以及重置按钮的设置在这-->
                <input type="submit" class="btn btn-primary">
                <input type="reset" class="btn btn-warning float-right">
            </form>
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
                    href="${pageContext.request.contextPath}/comment/manage/${pageModel.currentPage-1}">&larr;上一页</a>
            </li>
        </c:if>
        <c:if test="${pageModel.currentPage >= pageModel.totalPage-1}">
            <li class="next  disabled"><a href="#">下一页&rarr;</a></li>
        </c:if>
        <c:if test="${pageModel.currentPage < pageModel.totalPage -1}">
            <li class="next"><a
                    href="${pageContext.request.contextPath}/comment/manage/${pageModel.currentPage+1}">下一页&rarr;</a>
            </li>
        </c:if>
    </ul>
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