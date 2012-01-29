<%@page import="net.daum.devday.OAuthSignPost"
%><%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%><% 
	String p = request.getParameter("p"); 
	if (p == null) return;
	String url = new OAuthSignPost().send(p);
	out.flush();
%><%= url %>
