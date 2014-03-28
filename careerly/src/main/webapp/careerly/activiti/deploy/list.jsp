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
<script src="<%=path%>/js/vendor/lhgdialog/lhgcore.lhgdialog.min.js"></script>
<script src="<%=path%>/js/vendor/jquery.msgbox.7.1/jquery.msgbox.js"></script>
<script src="<%=path%>/js/vendor/lhgdialog/openDialog.js"></script>
<script src="<%=path%>/js/user/process_deploy.js"></script>
</head>
<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 部署管理</h2>
	</div>
	<form id="form-search" name="form-search" action="" method="post">
    </form>
    <!--//commonTableSearch-->
    <div class="btnBar">
    	<ul class="clearfix">
        	<li><a id ="toadd" title="流程部署" class="btnNormal">部署</a></li>
        </ul>
    </div>
    
    <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
  		<tr>
			<th>流程编号</th>
			<th>部署编号</th>
			<th>名称</th>
			<th>KEY</th>
			<th>版本号</th>
			<th>XML</th>
			<th>图片</th>
			<th>部署时间</th>
			<th>是否挂起</th>
			<th class="editColDefault">操作</th>
		</tr>
     <c:forEach var="item" items="${list}">
   		<c:set var="process" value="${item[0] }" />
		<c:set var="deployment" value="${item[1] }" />
     	 <tr>
     	 	<td align="center">${process.id }</td>
			<td align="center">${process.deploymentId }</td>
			<td align="center">${process.name }</td>
			<td align="center">${process.key }</td>
			<td align="center">${process.version }</td>
			<td align="center">${process.resourceName }</td>
			<td align="center">${process.diagramResourceName }</td>
			<td align="center">${deployment.deploymentTime }</td>
			<td align="center">${process.suspended }</td>
            <td align="center">
				<a href="#" class="btnIconEdit" title="更新"></a>
                <a href="#" class="btnIconDel" title="删除"></a>            
            </td>
        </tr>
     </c:forEach>
    </table>
   	<%@ include file="../../common/pageBar.jsp"%>
</div>
<script language="javascript">
$(function() {
	ProcessDeploy.init();
})
</script>
</body>
</html>
    