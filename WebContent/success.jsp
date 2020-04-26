<%@page import="com.vvs.pojo.ExpressionImpl"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>网页运算</title>

<link rel="stylesheet" href="static/css/style.css">

</head>
<body>

<script src='static/js/snowflakes.min.js'></script>
<script src="static/js/index.js"></script>
<h1>题目:</h1>
<br>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#ffffff">
	<form action="alert" method="post">
		<%
			List<ExpressionImpl> list = (List<ExpressionImpl>)session.getAttribute("list");
		
			for(ExpressionImpl expressionImpl:list){
				out.print(expressionImpl.toString()+"=");
				out.print("<input type=\"text\" style=\"width:100px\" id=\"num\" name=\"num\">");
				out.print("<br><br>");
			}
		%>
		<input type="submit" value="提交">
	</form>
</div>
</body>
</html>