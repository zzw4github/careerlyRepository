package com.careerly.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**   
* @Title: DateUtil.java 
* @Package com.careerly.tool 
* @Description: 对日期进行处理公用类
* @author careerly
* @date 2014-2-21 下午4:22:52 
* @version V1.0   
*/ 
public class DateUtil {

	private static DateUtil dateUtil;
	
    public static Locale DEFAULT_LOCALE = Locale.CHINESE; //语言包
    
	private DateUtil()
	{
		
	}
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午4:24:10
	 * @Description: 单例方式构造DateUtil对象
	 * @return DateUtil
	 * @throws 
	 */
	public static DateUtil getInstance(){
		if(dateUtil == null){
			dateUtil = new DateUtil();
		}
		return dateUtil;
	}


	   /**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午4:31:30
	 * @Description: 日期类型转换为string类型 
	 * @param date
	 * @param format （转换格式）
	 * @return String
	 * @throws 
	 */
	public  String dateToStr(Date date, String format) {
	        try {
	            if (date == null) {
	                return "";
	            }
	            if (format == null || format.length() <= 0) {
	                format = "yyyy-MM-dd";
	            }
	            SimpleDateFormat sdf = new SimpleDateFormat(format);
	            return sdf.format(date);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午4:34:15
	 * @Description: String类型转换为日期类型的数据
	 * @param strDate
	 * @param format
	 * @return Date
	 * @throws 
	 */
	public  Date strToDate(String strDate, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, DEFAULT_LOCALE);
            sdf.setLenient(true);
            return sdf.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
