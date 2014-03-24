package com.careerly.tool;

import java.text.DateFormatSymbols;
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
public class DateUtils {


	public static Locale DEFAULT_LOCALE = Locale.CHINESE; // 语言包
	
	public static String DATA_TIME_CONSTANT = "yyyy-MM-dd HH:mm:ss";

	private DateUtils() {

	}


	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午4:31:30
	 * @Description: 日期类型转换为string类型
	 * @param date
	 * @param format
	 *            （转换格式）
	 * @return String
	 * @throws
	 */
	public static String dateToStr(Date date, String format) {
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
	public static Date strToDate(String strDate, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, DEFAULT_LOCALE);
			sdf.setLenient(true);
			return sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:31:06
	 * @Description: 日期转换为年份
	 * @param paramDate
	 * @return int
	 * @throws
	 */
	public static int convertDateToYear(Date paramDate) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy",
				new DateFormatSymbols());
		return Integer.parseInt(localSimpleDateFormat.format(paramDate));
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:31:29
	 * @Description: 日期转换为年月
	 * @param paramDate
	 * @param format
	 *            (yyyyMM)
	 * @return String
	 * @throws
	 */
	public static String convertDateToYearMonth(Date paramDate, String format) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(format,
				new DateFormatSymbols());
		return localSimpleDateFormat.format(paramDate);
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午10:32:37
	 * @Description: 日期转换为年月日
	 * @param paramDate
	 * @param format
	 *            (例：yyyyMMdd)
	 * @return String
	 * @throws
	 */
	public static String convertDateToYearMonthDay(Date paramDate, String format) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(format,
				new DateFormatSymbols());
		return localSimpleDateFormat.format(paramDate);
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午2:28:15
	 * @Description: date转换为string
	 * @param paramDate
	 * @return String
	 * @throws 
	 */
	public static String dateToStr(Date paramDate) {
		return dateToStr(paramDate,DATA_TIME_CONSTANT);
	}
	

}
