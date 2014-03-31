package com.careerly.tool.help.json;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

/**   
* @Title: JsonConfigHelper.java 
* @Package com.careerly.tool.help.json 
* @Description: json配置帮助类
* @author careerly
*/ 
public class JsonConfigHelper extends JsonConfig {

	public JsonConfigHelper(String datePatten) {
		DateJsonValueProcessor mdbp = StringUtils.isNotBlank(datePatten) ? new DateJsonValueProcessor(datePatten)
				: new DateJsonValueProcessor();
		registerJsonValueProcessor(Date.class, mdbp);
		registerJsonValueProcessor(Timestamp.class, mdbp);
		registerJsonValueProcessor(java.sql.Date.class, mdbp);
	}
}
