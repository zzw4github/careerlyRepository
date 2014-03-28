<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>个人信息系统</title>
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="<%=path%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/vendor/lhgdialog/lhgcore.lhgdialog.min.js"></script>
<script src="<%=path%>/js/vendor/lhgdialog/openDialog.js"></script>
<script src="<%=path%>/js/vendor/jquery.msgbox.7.1/jquery.msgbox.js"></script>
<script src="<%=path%>/js/vendor/My97DatePicker/WdatePicker.js"></script>
<script src="<%=path%>/js/vendor/jquery.form/jquery.form.min.js"></script>
<script src="<%=path%>/js/user/process_deploy.js" type="text/javascript"></script>
</head>
<body class="content-pages-body">
<div class="content-pages-wrap">
    <div class="commonTitle">
      <h2>&gt;&gt; 流程部署</h2>
    </div>
  <form id="addForm" name="addForm" action="/" method="post">
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
           <input  type="file" name="processFile" id="processFile" class="fileFieldCommon fileFieldM" />
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

    