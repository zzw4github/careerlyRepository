package com.careerly.tool;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


import com.careerly.tool.help.BeanHelper;

/**   
* @Title: BeanUtils.java 
* @Package com.careerly.tool 
* @Description: bean工具类，用来处理bean对象
* @author careerly
* @date 2014-2-21 下午5:12:33 
* @version V1.0   
*/ 
public class BeanUtils {

	
  public static String DATA_TIME_CONSTANT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午5:19:59
	 * @Description: 通过class构造一个对象
	 * @param clazz
	 * @return 对象
	 * @throws Exception T
	 */
	public static <T> T instantiate(Class<T> clazz) throws Exception {
		if (clazz.isInterface()) {
			throw new Exception("Specified class is an interface");
		}
		try {
			return clazz.newInstance();
		}
		catch (InstantiationException ex) {
			throw new Exception("Is it an abstract class?", ex);
		}
		catch (IllegalAccessException ex) {
			throw new Exception("Is the constructor accessible?", ex);
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午5:28:09
	 * @Description: 通过传入构造器的参数构造一个对象
	 * @param clazz 
	 * @param args（传入构造器的参数）
	 * @return object
	 * @throws Exception T
	 */
	public static <T> T instantiateClass(Class<T> clazz,Object... args) throws Exception {
		if (clazz.isInterface()) {
			throw new Exception("Specified class is an interface");
		}
		try {
			return instantiateClass(clazz.getDeclaredConstructor());
		}
		catch (NoSuchMethodException ex) {
			throw new Exception("No default constructor found", ex);
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午5:33:29
	 * @Description: 通过构造器和参数创建对象
	 * @param ctor
	 * @param args
	 * @return
	 * @throws Exception T
	 * @throws 
	 */
	public static <T> T instantiateClass(Constructor<T> ctor, Object... args) throws Exception {
		try {
			BeanHelper.makeAccessible(ctor);//设置访问权限
			return ctor.newInstance(args);
		}
		catch (InstantiationException ex) {
			throw new Exception("Is it an abstract class?", ex);
		}
		catch (IllegalAccessException ex) {
			throw new Exception("Is the constructor accessible?", ex);
		}
		catch (IllegalArgumentException ex) {
			throw new Exception("Illegal arguments for constructor", ex);
		}
		catch (InvocationTargetException ex) {
			throw new Exception("Constructor threw exception", ex.getTargetException());
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午5:35:12
	 * @Description: 通过方法名称和参数类型在class下查找方法
	 * @param clazz
	 * @param methodName（方法名称）
	 * @param paramTypes（参数类型）
	 * @return Method
	 * @throws 
	 */
	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		try {
			/***获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。**/
			return clazz.getMethod(methodName, paramTypes);
		}
		catch (NoSuchMethodException ex) {
			/****获取的是类自身声明的所有方法，包含public、protected和private方法。***/
			return findDeclaredMethod(clazz, methodName, paramTypes);
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午5:38:19
	 * @Description: 获取的是类自身声明的所有方法，包含public、protected和private方法。
	 * @param clazz
	 * @param methodName
	 * @param paramTypes
	 * @return Method
	 * @throws 
	 */
	public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, paramTypes);
		}
		catch (NoSuchMethodException ex) {
			if (clazz.getSuperclass() != null) {
				return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
			}
			return null;
		}
	}
	
	/**
	 * @author huangshengya@rockontrol.com
	 * @date 2013-10-10
	 * @time 下午3:26:04
	 * @Description: bean对象转化为map对象
	 * @param obj
	 * @return Map<String,Object>
	 * @throws
	 */
	public static void bean2Map(Object paramObject, Map paramMap)
			throws Exception {
		String[] arrayOfString = getAtrributeNames(paramObject.getClass());
		for (int i = 0; i < arrayOfString.length; i++)
			if (canAccess(paramObject, arrayOfString[i]))
				paramMap.put(
						arrayOfString[i],
						getAttributeValueAsString(paramObject, arrayOfString[i]));
	}
	


	public static Object map2Bean(Map map, Class cls) {
		Object obj = null;
		boolean temp = false;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取出bean里的所有方法
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			// 取方法名
			String method = methods[i].getName();
			// 取出方法的类型
			Class[] cc = methods[i].getParameterTypes();
			if (cc.length != 1)
				continue;

			// 如果方法名没有以set开头的则退出本次for
			if (method.indexOf("set") < 0)
				continue;
			// 类型
			String type = cc[0].getSimpleName();

			try {
				// 转成小写
				Object value = method.substring(3, 4).toLowerCase()
						+ method.substring(4);
				// 如果map里有该key
				if (map.containsKey(value) && map.get(value) != null) {
					setValue(type, map.get(value), i, methods, obj);// 调用其底层方法
					temp = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (temp == true) ? obj : null;
	}

	/***
	 * 调用底层方法设置值
	 */
	private static void setValue(String type, Object value, int i,
			Method[] method, Object bean) {
		if (value != null && !value.equals("")) {
			try {
				if (type.equals("String")) {
					// 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
					method[i].invoke(bean, new Object[] { value });
				} else if (type.equals("int") || type.equals("Integer")) {
					method[i].invoke(bean, new Object[] { new Integer(""
							+ value) });
				} else if (type.equals("double") || type.equals("Double")) {
					method[i].invoke(bean,
							new Object[] { new Double("" + value) });
				} else if (type.equals("long") || type.equals("Long")) {
					method[i].invoke(bean,
							new Object[] { new Long("" + value) });
				} else if (type.equals("boolean") || type.equals("Boolean")) {
					method[i].invoke(bean,
							new Object[] { Boolean.valueOf("" + value) });
				} else if (type.equals("BigDecimal")) {
					method[i].invoke(bean, new Object[] { new BigDecimal(""
							+ value) });
				} else if (type.equals("Date")) {
					Date date = null;
					if (value.getClass().getName().equals("java.util.Date")) {
						date = (Date) value;
					} else {
						String format = ((String) value).indexOf(":") > 0 ? "yyyy-MM-dd hh:mm:ss"
								: "yyyy-MM-dd";
						date = DateUtil.getInstance().strToDate(
								(String) (value), format);
					}
					if (date != null) {
						method[i].invoke(bean, new Object[] { date });
					}
				} else if (type.equals("byte[]")) {
					method[i].invoke(bean,
							new Object[] { new String(value + "").getBytes() });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Object getAttributeValue(Object obj, String s)
			throws Exception {
		if (!StringUtils.isNotEmpty(s))
			return null;
		Object obj1 = "";
		try {
			Field field = obj.getClass().getField(s);
			obj1 = field.get(obj).toString();
		} catch (Exception exception) {
			Method method = null;
			if (getAttributeType(obj.getClass(), s) == Boolean.TYPE)
				method = getIsMethod(obj, s);
			if (method == null)
				method = getGetterMethod(obj, s);
			if (method == null) {
				String s1 = (new StringBuilder(
						"\u53CD\u5C04\u83B7\u53D6Bean\u7684\u5C5E\u6027\u503C\u5931\u8D25\uFF01\u3000"))
						.append(obj.getClass().getName())
						.append(" \u7684\u5C5E\u6027\u201C")
						.append(s)
						.append("\u201D \u4E0D\u662F Public\u7C7B\u578B\uFF0C\u4E5F\u6CA1\u6709getter\u65B9\u6CD5\uFF1A")
						.append(a(s)).toString();
				throw new Exception(s1);
			}
			obj1 = method.invoke(obj, null);
		}
		return obj1;
	}

	public static Class getAttributeType(Class class1, Class class2, String s)
			throws SecurityException, NoSuchFieldException {
		class2 = ((Class) (class2 != null ? class2 : java.lang.Object.class));
		ArrayList arraylist = new ArrayList();
		Class class3 = class1;
		String as[] = (String[]) null;
		for (; isSubClass(class3, class2) || class3 == class2; class3 = class3
				.getSuperclass()) {
			Field afield[] = class3.getDeclaredFields();
			for (int i = 0; i < afield.length; i++)
				if (afield[i].getName().equals(s.trim()))
					return afield[i].getType();

		}

		return null;
	}

	public static boolean isSubClass(Class class1, Class class2) {
		return class2.isAssignableFrom(class1);
	}

	public static Class getAttributeType(Object obj, String s)
			throws SecurityException, NoSuchFieldException {
		return getAttributeType(obj.getClass(), s);
	}

	public static Method getIsMethod(Object obj, String s) {
		Method amethod[] = obj.getClass().getMethods();
		String s1 = _mthif(s);
		for (int i = 0; i < amethod.length; i++)
			if (amethod[i].getName().equals(s1))
				return amethod[i];

		return null;
	}

	public static Method getGetterMethod(Object obj, String s) {
		Method amethod[] = obj.getClass().getMethods();
		String s1 = a(s);
		for (int i = 0; i < amethod.length; i++)
			if (amethod[i].getName().equals(s1))
				return amethod[i];

		return null;
	}

	private static String _mthif(String s) {
		String s1 = (new StringBuilder()).append(s.charAt(0)).toString()
				.toUpperCase();
		String s2 = (new StringBuilder("is")).append(s1)
				.append(s.substring(1, s.length())).toString();
		return s2;
	}

	private static String a(String s) {
		String s1 = (new StringBuilder()).append(s.charAt(0)).toString()
				.toUpperCase();
		String s2 = (new StringBuilder("get")).append(s1)
				.append(s.substring(1, s.length())).toString();
		return s2;
	}

	public static Class getAttributeType(Class class1, String s)
			throws SecurityException, NoSuchFieldException {
		Field afield[] = class1.getDeclaredFields();
		for (int i = 0; i < afield.length; i++)
			if (afield[i].getName().equals(s.trim()))
				return afield[i].getType();

		return recursiveAtrribType(class1.getSuperclass(), s);
	}

	public static Class recursiveAtrribType(Class class1, String s)
			throws SecurityException, NoSuchFieldException {
		Field afield[] = class1.getDeclaredFields();
		for (int i = 0; i < afield.length; i++)
			if (afield[i].getName().equals(s.trim()))
				return afield[i].getType();

		String s1 = class1.getSuperclass().getName();
		if (s1.startsWith("com.isoftstone."))
			return recursiveAtrribType(class1.getSuperclass(), s);
		else
			return null;
	}



	public static String getAttributeValueAsString(Object paramObject,
			String paramString) throws Exception {
		if (!StringUtils.isNotEmpty(paramString))
			return null;
		Object localObject = getAttributeValue(paramObject, paramString);
		if ((localObject instanceof Number)) {
			NumberFormat localNumberFormat = NumberFormat.getInstance();
			localNumberFormat.setGroupingUsed(false);
			localNumberFormat.setMaximumIntegerDigits(30);
			localNumberFormat.setMaximumFractionDigits(8);
			return localNumberFormat.format(localObject);
		}
		return object2Str(localObject, null);
	}

	public static String object2Str(Object paramObject) {
		return object2Str(paramObject, null);
	}

	public static int strToInt(String paramString) {
		if (!isInt(paramString))
			return 0;
		return Integer.parseInt(paramString);
	}

	public static boolean isInt(String paramString) {
		if (paramString == null)
			return false;
		Pattern localPattern = Pattern.compile("[-+]{0,1}[0-9]+");
		return localPattern.matcher(paramString).matches();
	}

	public static String dateToStr(Date paramDate) {
		if (paramDate == null)
			return "";
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				DATA_TIME_CONSTANT);
		return localSimpleDateFormat.format(paramDate);
	}

	public static String object2Str(Object paramObject, String paramString) {
		if (paramObject == null)
			return "";
		if ((paramObject instanceof Date))
			return dateToStr((Date) paramObject);
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

	public static boolean canAccess(Object paramObject, String paramString) {
		if ((paramObject == null) || (!StringUtils.isNotEmpty(paramString)))
			return false;
		String str = "";
		try {
			Field localField = paramObject.getClass().getField(paramString);
			str = localField.get(paramObject).toString();
			return true;
		} catch (Exception localException) {
			Method localMethod = getGetterMethod(paramObject, paramString);
			if (localMethod == null) {
				localMethod = getIsMethod(paramObject, paramString);
				return localMethod != null;
			}
		}
		return true;
	}

	public static String[] getAtrributeNames(Class paramClass1,
			Class paramClass2) throws Exception {
		ArrayList localArrayList = new ArrayList();
		Class localClass = paramClass1;
		String[] arrayOfString1 = (String[]) null;
		while ((isSubClass(localClass, paramClass2))
				|| (localClass == paramClass2)) {
			arrayOfString1 = getAtrributeNames(localClass);
			for (String str : arrayOfString1)
				localArrayList.add(str);
			localClass = localClass.getSuperclass();
		}
		arrayOfString1 = new String[localArrayList.size()];
		localArrayList.toArray(arrayOfString1);
		return arrayOfString1;
	}

	public static String[] getAtrributeNames(Object paramObject)
			throws Exception {
		return getAtrributeNames(paramObject.getClass());
	}

	public static String[] getAtrributeNames(Class paramClass) throws Exception {
		String[] arrayOfString = (String[]) null;
		ArrayList localArrayList = new ArrayList();
		recursiveClassAtrrib(paramClass, localArrayList);
		arrayOfString = new String[localArrayList.size()];
		localArrayList.toArray(arrayOfString);
		return arrayOfString;
	}

	public static List recursiveClassAtrrib(Class paramClass, List paramList)
			throws Exception {
		for (int i = 0; i < paramClass.getDeclaredFields().length; i++)
			paramList.add(paramClass.getDeclaredFields()[i].getName());
		String str = paramClass.getSuperclass().getName();
		return paramList;
	}

	/**
	 * @author huangshengya@rockontrol.com
	 * @date 2013-10-10
	 * @time 上午11:10:51
	 * @Description: 比较配置信息是否一致
	 * @param smsConfigInfo
	 * @return boolean true 为相等 false 为不等
	 * @throws
	 */
	public static boolean compareObject(Object newObj, Object oldObj) {
		boolean flag = false;
		try {
			int temp = 0;
			if (oldObj != null && newObj != null)
			{
				Map<String, Object> oldMap = new HashMap<String, Object>();// 原有的配置信息
				BeanUtils.bean2Map(oldObj, oldMap);
				Map<String, Object> newMap = new HashMap<String, Object>();// 新配置信息
				BeanUtils.bean2Map(newObj, newMap);
				Set<String> oldKeys = oldMap.keySet();
				String key = null;
				Object oldParam = null;// 原有值
				Object newParam = null;// 新值
				for (Iterator<String> it = oldKeys.iterator(); it.hasNext();) {
					key = it.next();
					oldParam = oldMap.get(key);
					newParam = newMap.get(key);
					if (oldParam instanceof java.lang.String) {
						String oldParam_temp = (String) (oldParam == null ? ""
								: oldParam);
						String newParam_temp = (String) (newParam == null ? ""
								: newParam);
						if (!oldParam_temp.equals(newParam_temp)) {
							temp++;
						}
					} else {
						if (oldParam != newParam) {
							temp++;
						}
					}
				}

				if (temp == 0) {
					flag = true;
				}
			}else if(oldObj == null && newObj == null)
			{
				 flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
