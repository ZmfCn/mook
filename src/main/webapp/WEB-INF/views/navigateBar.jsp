<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark navbar-fixed-top shadow">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><strong>ICourse</strong></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <!--导航栏“所有课程”的链接-->
            <li class="nav-item" style="margin-right: 320px;">
                <a href="${pageContext.request.contextPath}/course/all/0" class="nav-link">所有课程</a>
                <!--用于测试其他角色的页面跳转测试-->
            </li>

            <!--导航栏的搜索框以及按钮-->
            <form class="form-inline" action="${pageContext.request.contextPath}/course/search/0"
                  style="margin-right: 120px;" method="get">
                <input name="string" class="form-control" type="text" placeholder="搜索课程">
                <input class="btn btn-success" type="submit" value="搜索">
            </form>

            <!--导航栏“我的学习”的链接-->
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/course/myCourse/0" class="nav-link">
                    <span class="glyphicon glyphicon-book"></span>我的学习</a>
            </li>
            <!--导航栏“我的消息的链接”-->
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/comment/my/0" class=nav-link><span
                        class="glyphicon glyphicon-comment"></span>我的回复</a>
            </li>
            <!--如果为登录-->
            <c:if test='${pageContext.session.getAttribute("username")==null}'>
                <li class="nav-item">
                    <!--链接触发模态框-->
                    <a href="${pageContext.request.contextPath}/user/register" class="nav-link">
                        <span class="glyphicon glyphicon-user"></span>注册</a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/user/login" class="nav-link">
                        <span class="glyphicon glyphicon-log-in"></span>登录</a>
                </li>
            </c:if>
            <!--如果已经登录-->
            <c:if test='${pageContext.session.getAttribute("username")!=null}'>
                <c:if test='${pageContext.session.getAttribute("permissionCode")=="1"}'>
                    <!--导航栏“增删课程”的链接-->
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/course/manage/0" class="nav-link">
                            <span class="glyphicon glyphicon-edit"></span>管理课程</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/course/addCourse" class="nav-link">
                            <span class="glyphicon glyphicon-plus" style="color: orange;">添加课程</span>
                        </a>
                    </li>
                    <!--修改课程的链接在这-->
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/course/alterCourse" class="nav-link">
                            <span class="glyphicon glyphicon-wrench" style="color: rgb(60, 228, 237);">修改课程信息</span>
                        </a>
                    </li>
                </c:if>
                <c:if test='${pageContext.session.getAttribute("permissionCode")=="2"}'>
                    <!--关键字管理-->
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/keyword/manage" class="nav-link">
                            <span class="glyphicon glyphicon-bookmark"></span>敏感词管理</a>
                    </li>
                    <!--审核用户评论管理-->
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/comment/manage/0" class="nav-link">
                            <span class="glyphicon glyphicon-th"></span>审核用户评论</a>
                    </li>

                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/courseType/manage" class="nav-link">
                            <span class="glyphicon glyphicon-edit"></span>管理课程类型</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/user/manage" class="nav-link" target="_self">
                            <span class="glyphicon glyphicon-certificate"></span>管理用户权限</a>
                    </li>
                </c:if>
                <c:if test='${pageContext.session.getAttribute("permissionCode")=="3"}'>
                    <!--导航栏“按类别统计”的链接-->
                    <li class="nav-item">
                        <!--<a href="../htmls-companyLeader/load.html" class="nav-link" target="_self">
                            <span class="glyphicon glyphicon-book"></span>我的学习</a>-->
                        <a href="${pageContext.request.contextPath}/statistics/countByType" class="nav-link"
                           target="_self">
                            <span class="glyphicon glyphicon-tags"></span>按类别统计</a>
                    </li>
                    <!--导航栏“按浏览量统计"的链接-->
                    <li class="nav-item">
                        <!--<a href="../htmls-companyLeader/load.html" target="_self" class=nav-link><span class="glyphicon glyphicon-comment"></span>我的消息</a>-->
                        <a href="${pageContext.request.contextPath}/statistics/countByClick" class="nav-link"
                           target="_self">
                            <span class="glyphicon glyphicon-eye-open"></span>按浏览量统计</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/user/logout" class="nav-link">
                        <span class="glyphicon glyphicon-log-in"></span>登出(${pageContext.session.getAttribute("username")})</a>
                </li>
                <li class="nav-item">
                    <a href="/statics/html/help.html" target="_blank" class="nav-link" title="使用帮助信息">
                        <span class="glyphicon glyphicon-question-sign">帮助文档</span></a>
                </li>
            </c:if>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/user/alterPassword" class="nav-link"
                   target="_self">
                    <span></span>修改密码</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/user/seekPassword" class="nav-link"
                   target="_self">
                    <span></span>找回密码</a>
            </li>


            <!--浮动着的返回顶部的按钮的设置在这-->
            <li class="nav-item">
                <a href="#" class="nav-link">
                    <span class="glyphicon glyphicon-arrow-up" style="color: white;">回到顶部</span></a>
            </li>
        </ul>
    </div>
</nav>