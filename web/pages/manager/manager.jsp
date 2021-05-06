<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>

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
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">后台管理系统</span>
		<%--状态栏的三个按键--%>
		<div>
			<a href="manager/bookServlet?action=list">图书管理</a>
			<a href="orderServlet?action=list">订单管理</a>
			<a href="index.jsp">返回商城</a>
		</div>
	</div>
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>
	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>