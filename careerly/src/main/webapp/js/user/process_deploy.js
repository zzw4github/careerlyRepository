/**
 * 流程部署js
 * ***/
var ProcessDeploy = (function() {
		var path = "careerly/";//跳转前缀地址
		
		//list页面
		var init = function()  {

			//toadd 跳转到增加页面
			$('#toadd').click(function() {
				var url = path+"activiti/deploy/add.jsp";
				openDialog(url,"部署流程",700,280);
			});
			
		};
		
		//增加页面
		var add = function() {
			//js验证初始化
			if(typeof addCheck != 'undefined'){
				$("#addForm").validate(addVMCheck);
			}
			
			//取消操作
			$('#cancel').click(function() {
				cancel();
			});
			
			//日期事件
			$('#createDate').click(function() {
				WdatePicker();
			});
			
			//保存操作
			$('#save').click(function() {
				var options = {
					success : showResponse,
					timeout : 30000
				};
				var res = $("#addForm").valid(); // 表单验证
				
				// 验证通过，执行提交操作
				if (res) {
					$('#addForm').ajaxSubmit(options);
				} 
				return false;
			});
			
			// 验证
			var addCheck = {
					rules : {
						'processName':{
							required: true
						},
						'createDate':{
							required: true
						},
						'remark':{
							required: true
						},
						'processFile':{
							required: true
						}
					}
			};
		};
		
		
		//取消操作
		var cancel = function()
		{
			closeDialog();//关闭dialog
		}
		
		return  {
			init:init,
			add:add
		}
}).call(this);
