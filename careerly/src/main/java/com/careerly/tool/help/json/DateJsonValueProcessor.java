package com.careerly.tool.help.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**   
* @Title: DateJsonValueProcessor.java 
* @Package com.careerly.tool.help.json 
* @Description: 日期json处理类
* @author careerly
*/ 
public class DateJsonValueProcessor implements JsonValueProcessor {

	private static Logger logger = LoggerFactory.getLogger(DateJsonValueProcessor.class);
	
	/** * 默认的日期转换格式. */
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/** * 日期转换器. */
    private DateFormat dateFormat;
    
	public DateJsonValueProcessor() {
		dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	}

	public DateJsonValueProcessor(String datePattern) {
		try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
        	logger.error("日期格式不对");
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
	}

	
	public Object processArrayValue(Object obj, JsonConfig jsonconfig) {//转换数组
		if (obj instanceof java.sql.Date || obj instanceof java.util.Date || obj instanceof java.sql.Timestamp)
		  return process(obj);
		return "";
	}

	
	public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig) {//转换对象
		if (obj instanceof java.sql.Date || obj instanceof java.util.Date || obj instanceof java.sql.Timestamp)
			return process(obj);
		return "";
	} 
	
	/**
     * 格式化日期.
     *
     * @param value Object
     * @return Object
     */
    private Object process(Object value) {
        try {
            return dateFormat.format((Date) value);
        } catch (Exception ex) {
            return null;
        }
    }
}
