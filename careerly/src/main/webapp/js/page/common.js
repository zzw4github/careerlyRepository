/***
 * 显示ajax请求成功的响应信息
 * **/
function showSuccessResponse(responseText) {
	var status = responseText.isSuccess;//响应状态
	var content = responseText.msg+"";//响应类容
	var responseType = "info";
	if(responseText.type!=null&&responseText.type!=''&&typeof responseText.type!='undefined')
	{
		responseType = responseText.type;
	}
	var type = typeof responseType!= 'undefined'?responseType:"info";//提示类型
	$.msgBox({
		title : "消息提示",
		content : content,
		type : type,
		buttons : [ {
			value : "确定"
		} ],
		success : function(result) {
			if (result == "确定") {
				if (status) {
					closeDialog();//关闭dialog
					frameElement.api.opener.location.reload();//刷新父页面
				}
			}
		}
	});
}


/**
 * 显示ajax请求失败的响应信息
 * **/
function showErrorResponse(XmlHttpRequest, textStatus, errorThrown) { 
	 $.msgBox({
		    title:"消息提示",
		    content:"请求失败！",
		    type:"error",
		    buttons: [{ value: "确定" }]
		 });
}