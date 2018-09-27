<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>增删课程</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/styles/edit-course.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/statics/styles/sider-menu.css">
    <link rel="stylesheet" href="/statics/styles/footer.css">
    <link rel="stylesheet" href="/statics/styles/course.css">
    <link rel="SHORTCUT ICON" href="/statics/images/favicon1.ico">

    <script>
        function validate() {
            return confirm("确认删除选中的课程吗？");
        }
    </script>
</head>

<body>

<jsp:include page="navigateBar.jsp" flush="true"/>
<div style="width: 100%; height: 100px"></div>

<div class="container allContent">

    <div class="card" style="margin-top: 70px;">
        <div class="card-header">
            <h3 style="text-align: center;">删除课程</h3>
        </div>
        <div class="card-body">
            <form role="form" name="form" onsubmit="return validate()" method="post"
                  action="${pageContext.request.contextPath}/course/delete/${pageModel.currentPage}">
                <div class="checkbox">
                    <div style="height: 530px">
                        <div class="row" style="margin-bottom: 20px;">
                            <c:forEach items="${pageModel.courses}" var="course">
                                <div class="col-sm-3">
                                    <label>
                                        <img src="${course.imagePath}"
                                             class="conf img-fluid">
                                        <input type="checkbox" name="${course.courseId}">${course.courseName}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <hr>
                <button type="submit" class="btn btn-danger btn-block">确认删除</button>
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
                    href="${pageContext.request.contextPath}/course/manage/${pageModel.currentPage-1}">&larr;上一页</a>
            </li>
        </c:if>
        <c:if test="${pageModel.currentPage >= pageModel.pages-1}">
            <li class="next  disabled"><a href="#">下一页&rarr;</a></li>
        </c:if>
        <c:if test="${pageModel.currentPage < pageModel.pages-1}">
            <li class="next"><a
                    href="${pageContext.request.contextPath}/course/manage/${pageModel.currentPage+1}">下一页&rarr;</a>
            </li>
        </c:if>
    </ul>


    <!--底部栏的设置在这-->
    <div class="return">
        <a href="#" style="color: white;font-size: 15px;">回到顶部</a>
    </div>
    <footer class="position">
        <address>
            Copyright © 2017 郑膜坊，何志颖，王秋莉 <br>联系邮箱：2804755011@qq.com 地址：云南大学软件学院
        </address>
    </footer>
</div>
</body>
</html>
