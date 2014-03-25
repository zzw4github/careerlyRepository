<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="<%=path%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/user/user.js" type="text/javascript"></script>
</head>
<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 用户管理</h2>
	</div>
	<form id="form-search" name="form-search" action="" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
        <tr>
             <td ><div align="right">用户名称：</div></td>
             <td >
            	 <input name="search[0].name" type="hidden" class="inputTextNormal" value ="userName" />
            	 <input name="search[0].condition" type="hidden" class="inputTextNormal" value="${pageCondition.like}"/>
            	 <input name="search[0].value" type="text" class="inputTextNormal" value="${page.search[0].value}"/>
             </td>
             <td ><div align="right">登录名称：</div></td>
             <td >
             	 <input name="search[1].name" type="hidden" class="inputTextNormal" value ="loginName" />
            	 <input name="search[1].condition" type="hidden" class="inputTextNormal" value="${pageCondition.equal}"/>
            	 <input name="search[1].value" type="text" class="inputTextNormal" value="${page.search[1].value}" />
             </td>
             <td ><div align="right">电话号码：</div></td>
            <td >
            	 <input name="search[2].name" type="hidden" class="inputTextNormal" value ="telephone" />
            	 <input name="search[2].condition" type="hidden" class="inputTextNormal" value="${pageCondition.notEqual}"/>
           		 <input name="search[2].value" type="text" class="inputTextNormal" value="${page.search[2].value}" />
            </td>
            <td align="right"><button onclick ="userActionConfirm('<%=path%>/user/list')">检索</button></td>
          </tr>
       	
    </table>
    </form>
    <!--//commonTableSearch-->
    <div class="btnBar">
    	<ul class="clearfix">
			<li><a href="#" title="导入" class="btnNormal">导入Excel模板</a></li>
        	<li><a href="风险警示创建.html" title="创建检查题类别" class="btnNormal">新增</a></li>
        </ul>
    </div>
    
    <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
    	 <tr>
            <th>用户名称</th>
            <th>登录名称</th>
            <th>角色</th>
            <th>有效期</th>
			<th>到期停用</th>
            <th class="editColDefault">操作</th>
        </tr>
     <c:forEach var="item" items="${list}">
     	 <tr>
            <td align="center">${item.userName}</td>
            <td align="center">${item.loginName}</td>
            <td align="center">${item.roleId}</td>
            <td align="center">${item.endDate}</td>
			<td align="center">${item.isenable}</td>
            <td align="center">
            	<a href="风险警示详细.html" class="btnIconView" title="详细"></a>
				<a href="风险警示修改.html" class="btnIconEdit" title="更新"></a>
                <a href="#" class="btnIconDel" title="删除"></a>            </td>
        </tr>
     </c:forEach>
    </table>
   	<%@ include file="../common/pageBar.jsp"%>
</div>
</body>
</html>
    