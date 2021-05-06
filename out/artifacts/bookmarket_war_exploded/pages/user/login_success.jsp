<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小鸣小室会员注册页面</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
    <div id="header">
        <div>
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临小鸣小室书城</span>
            <a href="orderServlet?action=list">我的订单</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </div>
    </div>

    <div id="main">
        <h1>欢迎回来
            <a href="index.jsp">转到主页</a>
        </h1>
    </div>

    <%--静态包含页脚内容--%>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>