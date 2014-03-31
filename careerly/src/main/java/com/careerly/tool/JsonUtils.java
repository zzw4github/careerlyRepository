package com.careerly.tool;

import java.util.HashMap;
import java.util.Map;

import com.careerly.tool.help.json.JsonHelper;

public class JsonUtils {

	
	/**
	 * @author careerly
	 * @Description: 转化为json格式的msg对象
	 * @returnType String
	 * @throws
	 */ 
	public static String jsonMsg( boolean status,String msg){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isSuccess", status);
		map.put("type", status==true?"info":"error");
		map.put("msg", msg);
		return JsonHelper.toJsonString(map);
	}
	
}
