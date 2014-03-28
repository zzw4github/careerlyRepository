$(document)
		.ready(
				function() {
					/* 设置默认属性 */
					$.validator.setDefaults({
						submitHandler : function(form) {
							form.submit();
						}
					});

					// 中文字两个字节
					jQuery.validator.addMethod("byteRangeLength", function(
							value, element, param) {
						var length = value.length;
						for ( var i = 0; i < value.length; i++) {
							if (value.charCodeAt(i) > 127) {
								length++;
							}
						}
						return this.optional(element)
								|| (length >= param[0] && length <= param[1]);
					}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

					/* 追加自定义验证方法 */
					// 身份证号码验证
					jQuery.validator.addMethod("isIdCardNo", function(value,
							element) {
						return this.optional(element) || isIdCardNo(value);
					}, "请正确输入您的身份证号码");

					// 手机号码验证
					jQuery.validator
							.addMethod(
									"isMobile",
									function(value, element) {
										var length = value.length;
										return this.optional(element)
												|| (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(18[0-9]{1}))+\d{8})$/
														.test(value));
									}, "请正确填写您的手机号码");

					// 电话号码验证
					jQuery.validator.addMethod("isPhone", function(value,
							element) {
						var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
						// var tel = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
						return this.optional(element) || (tel.test(value));
					}, "请正确填写您的电话号码");

					// 手机号码与电话号码同时验证
					jQuery.validator
							.addMethod(
									"isMobileOrphone",
									function(value, element) {
										var tel = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
										return this.optional(element)
												|| (tel.test(value));

									}, "请输入正确手机号或电话号码");

					// 传真号码验证
					jQuery.validator.addMethod("isFax",
							function(value, element) {
								// return this.optional(element) ||
								// (/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[
								// ]){1,12})+$/.test(value));
								var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
								return this.optional(element)
										|| (tel.test(value));
							}, "请正确填写您的传真号码");

					// 邮政编码验证
					jQuery.validator.addMethod("isZipCode", function(value,
							element) {
						var tel = /^[0-9]{6}$/;
						return this.optional(element) || (tel.test(value));
					}, "请正确填写您的邮政编码");
					jQuery.validator.addMethod("isTime", function(value,
							element) {
						var bvalid = true;
						var time = value.match(/^(\d{1,2})(:)(\d{1,2})$/);
						if (time == null) {
							// alert("time null");
							bvalid = false;
						} else if (time[1] >= 24 || time[3] >= 60) {
							// alert("time err");
							bvalid = false;
						}

						return this.optional(element) || bvalid;
					}, "请正确输入时间格式");
					// IP验证
					jQuery.validator
							.addMethod(
									"ip",
									function(value, element) {
										var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
										return this.optional(element)
												|| (ip.test(value) && (RegExp.$1 < 256
														&& RegExp.$2 < 256
														&& RegExp.$3 < 256 && RegExp.$4 < 256));
									}, "Ip地址格式错误");
					jQuery.validator.addMethod("stringCheck", function(value,
							element) {
						return this.optional(element)
								|| /^[a-zA-Z0-9]+$/.test(value);
					}, "请输入数字、英文字母");
					// 邮箱验证
					jQuery.validator
							.addMethod(
									"isEmail",
									function(value, element) {
										var tel = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
										return this.optional(element)
												|| (tel.test(value));

									}, "请输入正确邮箱");

					jQuery.validator.addMethod("isDigits", function(value,
							element) {
						var tel = /^\d*\.{0,1}\d*$/;
						return this.optional(element) || (tel.test(value));
					}, "请填写正确的数字");

					jQuery.validator.addMethod("ptInteger", function(value,
							element) {
						var tel = /^[1-9][0-9]*$/;
						return this.optional(element) || (tel.test(value));
					}, "请输入正整数");

					jQuery.validator.addMethod("isMobile", function(value,
							element) {
						var length = value.length;
						return this.optional(element)
								|| (length == 11 && /^1+\d{10}$/.test(value));
					}, "请填写正确的手机号");

					jQuery.validator.addMethod("loginId", function(value,
							element) {
						var patrn = /^[a-zA-Z_][a-zA-Z0-9_]{3,15}$/;
						if (!patrn.exec(value)) {
							return false;
						} else {
							return true;
						}
					}, "4-16个英文字母、下划线或数字组成，不能以数字开头");

					jQuery.validator.addMethod("passwdformat", function(value,
							element) {
						var patrn = /^[a-zA-Z0-9_]+$/;
						if (!patrn.exec(value)) {
							return false;
						} else {
							return true;
						}
					}, "密码由数字、字母、下划线组成");

					jQuery.validator
							.addMethod(
									"ipv4",
									function(value, element, param) {
										return this.optional(element)
												|| /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i
														.test(value);
									}, "请输入有效的IPv4地址");

					jQuery.validator
							.addMethod(
									"ipv6",
									function(value, element, param) {
										return this.optional(element)
												|| /^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/i
														.test(value);
									}, "请输入有效的IPv6地址");

					jQuery.validator.addMethod("instanceNameReg", function(
							value, element) {
						var patrn = /^[a-zA-Z_][a-zA-Z0-9_]{3,15}$/;
						if (!patrn.exec(value)) {
							return false;
						} else {
							return true;
						}
					}, "4-16位，英文字母、下划线或数字组成，不能以数字开头");

					jQuery.validator
							.addMethod(
									"cloneImageNameReg",
									function(value, element) {
										var patrn = /^[a-zA-Z_][\u4e00-\u9fa5a-zA-Z0-9_]{3,15}$/;
										if (!patrn.exec(value)) {
											return false;
										} else {
											return true;
										}
									}, "4-16位，英文字母、下划线或数字组成，不能以数字开头");

					jQuery.validator.addMethod("hostIdNull", function(value,
							element) {
						if (value == '') {
							return false;
						} else {
							return true;
						}
					}, "当前选择集群无可用物理主机！");

					jQuery.validator.addMethod("islarger", function(value,
							element, param) {
						arr_value = value.split('.');
						arr_param = $(param).val().split('.');
						if (arr_value[0] == arr_param[0]
								&& arr_value[1] == arr_param[1]
								&& arr_value[2] == arr_param[2]) {
							value_num = arr_value.join('') * 1;
							param_num = arr_param.join('') * 1;
							return value_num > param_num;
						} else
							return false;
						return value >= $(param).val();
					}, "与开始IP不在一个网段，或者没有大于开始IP");

					jQuery.validator.addMethod("timeCompare", function(value,
							element, param) {
						var s = value.split(" ");
						var s1 = s[0].split("-"); 
						var s2 = s[1].split(":");
						var endDate = new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
					    var e= $(param).val().split(" ")
					    var e1 = e[0].split("-"); 
						var e2 = e[1].split(":");
						var startDate = new Date(e1[0],e1[1]-1,e1[2],e2[0],e2[1],e2[2]);
						var result=endDate-startDate;
						if (result <= 0) {  
						    return false; 
						}  
						return true;
					}, "开始时间不能大于结束时间");

					jQuery.validator.addMethod("rateFormate", function(value,
							element) {
						var patrn = /^(1+)(:\d{1})+$/;
						if (!patrn.exec(value)) {
							return false;
						} else {
							var arr = value.split(":");
							if (parseInt(arr[0]) > parseInt(arr[1])) {
								return false;
							} else {
								return true;
							}
						}
					}, "请输入(1:x)形式的比例数据 x小于10");

					jQuery.validator.addMethod("imageNameReg", function(value,
							element) {
						var patrn = /^[a-zA-Z_.-][a-zA-Z0-9_.-]{3,15}$/;
						if (!patrn.exec(value)) {
							return false;
						} else {
							return true;
						}
					}, "4-16位，英文字母、下划线或数字组成，不能以数字开头");

					jQuery.validator.addMethod("notZeroFirst", function(value,
							element) {
						var patrn = /^0.*$/;
						if (patrn.exec(value)) {
							return false;
						} else {
							return true;
						}
					}, "不能以数字0开头");

				});

// Load JQuery Validate Public Function
var formValidate = function(formId, options) {
	this.formId = formId;
	this.settings = $.extend(true, {}, options);
	this.init = function() {
		$.validator.setDefaults({});
		$("#" + this.formId + "").validate(this.settings);
	}
	this.init();
}
