<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>个人信息系统</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.treeview.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/vendor/jquery.treeview.js" type="text/javascript"></script>

<script language="javascript">
$().ready(function(){
	
	//树状菜单生成 JQuery Treeview
	$("#browser").treeview({
		//animated菜单展开关闭时动画效果
		animated : "slow",
        //collapsed菜单载入时关闭还是展开
		collapsed: true
        //unique同一层次是否只允许展开一个
		//unique: true
	});
	
	
	
		  $("#nav-box").css("overflowY","auto");
		//自动添加a标签title为a标签中的内容
		for(var i=0; i<$("span.file a").length; i++ ){
			$("span.file a").eq(i).attr("title", $("span.file a").eq(i).text());
		}
	
});
	
	//链接转入index.html页面ID为content-box的iframe显示
	function urlTarget(urls) {
		$("#content-box",parent.document.body).attr("src",urls);
	}
</script>

</head>

<body class="inc-nav-body">
<div id="nav-box">

	<ul id="browser" class="filetree">
	<li><span class="folder">系统设置</span>
			<ul>
		<li><span class="file"><a onclick="urlTarget('content/风险警示列表.html');">风险警示</a></span></li>
		<li><span class="file"><a onclick="urlTarget('content/风险评估列表.html');">风险评估</a></span></li>
		<li>
		<span class="folder">权限设置</span>
		<ul>
			<li><span class="file"><a onclick="urlTarget('content/日常监管行政许可列表.html');">行政许可</a></span></li>
			<li><span class="file"><a onclick="urlTarget('content/日常监管日常监督列表.html');">日常监督</a></span></li>
			<li><span class="file"><a onclick="urlTarget('content/日常监管行政处罚列表.html');">行政处罚</a></span></li>
			<li><span class="file"><a onclick="urlTarget('content/日常监管食品召回列表.html');">食品召回</a></span></li>
			<li><span class="file"><a onclick="urlTarget('content/日常监管投诉举报列表.html');">投诉举报</a></span></li>
		</ul>
		</li>
		</ul>
	  </li>
	 </ul>
		

	</ul>
</div>
</body>
</html>