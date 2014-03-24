<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>个人信息系统</title>
<style>
html,body { overflow-x:hidden;}
</style>
<link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/vendor/jquery.onlyforindex.js" type="text/javascript"></script>
</head>

<body>
<div id="header-wrap">
	<iframe allowtransparency="true" frameborder="0" id="header-box" scrolling="no" src="inc-header.jsp"></iframe>
</div>
<div id="main-wrap">
	<div id="main-nav">
    	<iframe frameborder="0" id="siderbar-box" scrolling="no" src="inc-nav.jsp"></iframe>
    </div>
    <div id="main-content">
        <table border="0" cellpadding="0" cellspacing="0" id="main-content-box">
            <tr>
                <td class="toggle"></td>
                <td class="content-wrap"><iframe frameborder="0" id="content-box" src="welcome.jsp" scrolling="auto"></iframe></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>