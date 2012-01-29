<%@page import="net.daum.devday.OAuthSignPost"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<% 
	String p = request.getParameter("p"); 
	new OAuthSignPost().send(p);
%>
</body>
</html>