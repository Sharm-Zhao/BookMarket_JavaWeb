<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--    String basePath = request.getScheme()--%>
<%--            + "://"--%>
<%--            + request.getServerName()--%>
<%--            + ":"--%>
<%--            + request.getServerPort()--%>

<%--/bookmarket_war_exploded--%>
<%--            + request.getContextPath()--%>
<%--            + "/"--%>
<%--            ;--%>

<%--    basePath= URLDecoder.decode(basePath, StandardCharsets.UTF_8);--%>

<%--    pageContext.setAttribute("basePath",basePath);--%>
<%--%>--%>

<!--写base标签，永远固定相对路径跳转的结果。如果你的工程名和我的不一样，那么就需要修改成你自己的工程名-->
<%--<base href="<%=basePath%>">--%>
<base href="http://localhost:8080/bookmarket_war_exploded/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>