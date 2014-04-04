<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
<%@ include file="/common/common.jsp"%>
<css:tag type="custom-style"/>
<css:tag type="careerly"/>
<js:tag type="jquery"/>
<js:tag type="careerly"/>
<js:tag type="date"/>
<script src="${ctx}/js/user/process.deploy.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/user/process.deploy.js" type="text/javascript"></script>
</head>
<body class="content-pages-body">
<div class="content-pages-wrap">
    <div class="commonTitle">
      <h2>&gt;&gt; 流程部署</h2>
    </div>
  <form id="addForm" name="addForm"  method="post" enctype="multipart/form-data">
    <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
       <tr>
          <td width="26%" align="right" class="title"><span class="required">*</span>流程名称：</td>
          <td width="74%" align="left"><input type="text" name="processName" value=""/></td>
        </tr>
        <tr>
          <td align="right" class="title"><span class="required">*</span>创建日期：</td>
          <td align="left"><input type="text" id="createDate" name="createDate" value="" /></td>
        </tr>
		<tr>
          <td align="right" class="title"><span class="required">*</span> 备注：</td>
          <td align="left"><textarea name="remark" id="remark" ></textarea></td>
        </tr>
		 <tr>
         <td align="right" class="title">上传附件：</td>
	 	<td colspan="5">
           <input  type="file" name="file" id="processFile" class="fileFieldCommon fileFieldM" />
        </td>
        </tr>
    </table>
 </form>
 
    <!--//commonTable-->
    <div id="formPageButton">
    	<ul>
        	<li><a id="save" title="保存" class="btnShort">部署</a></li>
        	<li><a id="cancel" title="取消" class="btnShort">取消</a></li>
        </ul>
    </div>
    <!--//commonToolBar-->   
</div>
<script language="javascript">
$(function() {
	ProcessDeploy.add();
})
</script>
</body>
</html>

    