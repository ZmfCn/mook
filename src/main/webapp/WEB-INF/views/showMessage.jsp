<%--
  Created by IntelliJ IDEA.
  User: zmf
  Date: 17-12-10
  Time: 下午3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 显示服务器返回的信息 -->
<div style="width: 100%;text-align: center;">
    <c:if test="${allErrors!=null}">
        <c:forEach items="${allErrors }" var="error">
            <div class="alert alert-danger col-lg-4 col-lg-offset-4">
                <a href="#" class="close" data-dismiss="alert">
                    &times;
                </a>
                <p>${error.defaultMessage}</p>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${errors!=null}">
        <c:forEach items="${errors }" var="error">
            <div class="alert alert-danger col-lg-4 col-lg-offset-4">
                <a href="#" class="close" data-dismiss="alert">
                    &times;
                </a>
                <p>${error}</p>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${messages!=null}">
        <c:forEach items="${messages }" var="message">
            <div class="alert alert-success col-lg-4 col-lg-offset-4">
                <a href="#" class="close" data-dismiss="alert">
                    &times;
                </a>
                <p>${message}</p>
            </div>
        </c:forEach>
    </c:if>
    <div style="clear: both"></div>
</div>