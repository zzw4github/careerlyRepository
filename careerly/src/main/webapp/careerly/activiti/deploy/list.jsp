<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
<%@ include file="/common/common.jsp"%>
<css:tag type="custom-style"/>
<js:tag type="jquery"/>
<script src="${ctx}/js/user/process.deploy.js"></script>
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
    