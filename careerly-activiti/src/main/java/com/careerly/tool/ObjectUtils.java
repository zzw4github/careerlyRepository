package com.careerly.tool;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**   
* @Title: ObjectUtils.java 
* @Package com.careerly.tool 
* @Description: 对象工具类
* @author careerly
* @date 2014-2-24 上午11:29:23 
* @version V1.0   
*/ 
public abstract class ObjectUtils {


	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:29:59
	 * @Description: 判断异常是否是可检查异常
	 * @param ex
	 * @return boolean
	 * @throws 
	 */
	public static boolean isCheckedException(Throwable ex) {
		return !(ex instanceof RuntimeException || ex instanceof Error);
	}


	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:31:01
	 * @Description: 判断对象是否是array（集合）
	 * @param obj
	 * @return boolean
	 * @throws 
	 */
	public static boolean isArray(Object obj) {
		return (obj != null && obj.getClass().isArray());
	}

	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:31:36
	 * @Description: 是否是空的对象
	 * @param array
	 * @return boolean
	 * @throws 
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	
	
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:36:29
	 * @Description: object 转换为Object[]数组
	 * @param source
	 * @return Object[]
	 * @throws 
	 */
	public static Object[] toObjectArray(Object source) {
		if (source instanceof Object[]) {
			return (Object[]) source;
		}
		if (source == null) {
			return new Object[0];
		}
		if (!source.getClass().isArray()) {
			throw new IllegalArgumentException("Source is not an array: " + source);
		}
		int length = Array.getLength(source);
		if (length == 0) {
			return new Object[0];
		}
		Class wrapperType = Array.get(source, 0).getClass();
		Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
		for (int i = 0; i < length; i++) {
			newArray[i] = Array.get(source, i);
		}
		return newArray;
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午2:28:58
	 * @Description: Object对象转换为string对象
	 * @param paramObject
	 * @param paramString
	 * @return String
	 * @throws 
	 */
	public static String object2Str(Object paramObject, String paramString) {
		if (paramObject == null)
			return "";
		if ((paramObject instanceof Date))
			return DateUtils.dateToStr((Date) paramObject);
		if ((paramObject instanceof Number))
			try {
				DecimalFormat localDecimalFormat = (DecimalFormat) NumberFormat
						.getPercentInstance();
				if ((paramString != null) && (!"".equals(paramString))) {
					int i = strToInt(paramString);
					String str1 = "";
					if ((paramString != null) && (!paramString.equals("")))
						for (int j = 0; j < i; j++)
							str1 = str1 + "0";
					String str2 = "";
					if (str1.equals(""))
						str2 = "0";
					else
						str2 = "0." + str1;
					BigDecimal localBigDecimal = new BigDecimal(
							String.valueOf(paramObject)).setScale(i, 4);
					localDecimalFormat.applyPattern(str2);
					return localDecimalFormat.format(localBigDecimal);
				}
				NumberFormat localNumberFormat = NumberFormat.getInstance();
				localNumberFormat.setGroupingUsed(false);
				localNumberFormat.setMaximumIntegerDigits(30);
				localNumberFormat.setMaximumFractionDigits(8);
				return localNumberFormat.format(paramObject);
			} catch (Exception localException) {
				return String.valueOf(paramObject);
			}
		return paramObject.toString();
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午2:29:24
	 * @Description: Object对象转换为string对象
	 * @param paramObject
	 * @return String
	 */
	public static String object2Str(Object paramObject) {
		return object2Str(paramObject, null);
	}
	
	


	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午2:30:43
	 * @Description: String转换为int型数据
	 * @param paramString
	 * @return int
	 * @throws 
	 */
	public static int strToInt(String paramString) {
		if (!isInt(paramString))
			return 0;
		return Integer.parseInt(paramString);
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午2:29:58
	 * @Description: 判断是否可转换为int型数据
	 * @param paramString
	 * @return boolean
	 * @throws 
	 */
	public static boolean isInt(String paramString) {
		if (paramString == null)
			return false;
		Pattern localPattern = Pattern.compile("[-+]{0,1}[0-9]+");
		return localPattern.matcher(paramString).matches();
	}

	

}
