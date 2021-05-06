<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
<%--    <img class="logo_img" alt="" src="static/img/logo.gif" >--%>
    <span class="wel_word">我的订单</span>
    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>书名</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
        </tr>

        <c:forEach items="${requestScope.page2.items}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.total_price}</td>
            </tr>
        </c:forEach>
    </table>
    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>