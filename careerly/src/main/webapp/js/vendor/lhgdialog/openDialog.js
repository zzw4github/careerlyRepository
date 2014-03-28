var dg;
/**
 * 打开弹出窗口 (自定义)
 */
function openDialog(url,t,w,h){
	var opid = "t"+Math.round(Math.random()*100000000)+"x";
	dg=$.dialog({
		   id:opid,
		   title:t,
		   content:'url:'+url,
		   width:w,
		   height:h,
		   lock: true
	});
}

function openDialogWithoutClose(url,t,w,h){
	var opid = "t"+Math.round(Math.random()*100000000)+"x";
	dg=$.dialog({
		   id:opid,
		   title:t,
		   content:'url:'+url,
		   width:w,
		   height:h,
		   lock: true,
		   cancel: false
	});
}

function openDialogCloseHandle(url,t,w,h,fun){
	var opid = "t"+Math.round(Math.random()*100000000)+"x";
	dg=$.dialog({
		   id:opid,
		   title:t,
		   content:'url:'+url,
		   width:w,
		   height:h,
		   lock: true,
		   cancel: function(){var b =fun();return b;}
	});
}

function openViewDialog(url,t,w,h){
	var opid = "t"+Math.round(Math.random()*100000000)+"x";
	dg=$.dialog({id:opid,title:t,content:'url:'+url,max:false,min:false,width:w,height:h,
		resize:false,drag:true,cover:false,
		button: [ 
		         { 
		              name: '关闭'
		         } 
		     ] 
	});
}

function openBlankDialog(url,t,w,h){
	var opid = "t"+Math.round(Math.random()*100000000)+"x";
	dg=$.dialog({id:opid,title:t,content:'url:'+url,max:false,min:false,width:w,height:h,
		resize:false,drag:true,cover:false
	});
}

/*
 * 指定frame id 打开对话 
 * 
 */
function openFrameDialog(url,t,w,h,frameId){ 
	var opid = "t"+Math.round(Math.random()*100000000)+"x";  
	if(!frameId){ 
		opid = frameId
	}
	var FrameDialog=$.dialog({id:opid,title:t,content:"url:"+url ,max:false,min:false,width:w,height:h});
	return FrameDialog;
}

/**
 * 关闭弹出窗口
 */
function closeDialog(){
	frameElement.api.close();//opener.location.reload();
}
