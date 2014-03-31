<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>个人信息系统</title>
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 异常信息</h2>
	</div>
	<span style="font-size: 20px;color: red;">
	<c:out value ="${errorMsg}"/>
	</span>
    <div class="btnBar">
         	<ul class="clearfix">
        	<li><a href="javascript:history.go(-1)" class="btnNormal">返回</a></li>
        </ul>
    </div>
</div>
</body>
</html>
    