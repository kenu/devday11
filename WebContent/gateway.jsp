<%@page import="net.daum.devday.UserDefineUtil"
%><%@page import="net.daum.devday.OAuthSignPost"
%><%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%><%
	String p = request.getParameter("p"); 
	String s = UserDefineUtil.unescape(request.getParameter("s")); 
	System.out.println("qqq"+s);
	if (p == null) return;
	String url = new OAuthSignPost().send(p,s);
	out.flush();
%><%= url %>
