package com.careerly.tool.help.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class JsonHelper {

	/**
	 * json对象转换成实例对象 (包含将指定格式的字符串转换成日期)
	 * 
	 * @param obj
	 * @param json
	 * @param dateFormat
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object jsonToObject(Class<?> cls, JSONObject json,
			final String dateFormat) {
		try {
			Object obj = cls.newInstance();
			ConvertUtils.register(new Converter() {
			
				public Object convert(Class type, Object value) {
					if (value == null)
						return null;
					SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
					Date dt = null;
					try {
						dt = sdf.parse((String) value);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return dt;
				}
			}, Date.class);
			for (Object k : json.keySet()) {
				Object v = json.get(k);
				BeanUtils.setProperty(obj, k.toString(), v.toString());
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json对象转换成实例对象
	 * 
	 * @param obj
	 * @param json
	 * @param dateFormat
	 * @return
	 */
	public static Object jsonToObject(Class<?> cls, JSONObject json) {
		try {
			Object obj = cls.newInstance();
			for (Object k : json.keySet()) {
				Object v = json.get(k);
				BeanUtils.setProperty(obj, k.toString(), v.toString());
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换对象到json
	 * 
	 * @param o
	 *            待转换对象
	 * @param excludeProperty
	 *            要排除的属性
	 * @return
	 */
	private static JSON jsonExclude(Object o, String dateFormat,
			List<String> excludeProperty) {
		JsonConfig config = new JsonConfigHelper(dateFormat);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		PropertyFilterHelper filter = new PropertyFilterHelper(excludeProperty);
		filter.setExclude(true);
		config.setJsonPropertyFilter(filter);
		JSON json = JSONSerializer.toJSON(o, config);
		return json;
	}

	/**
	 * 转换对象到JOSN
	 * 
	 * @param o
	 * @param includeProperty
	 *            ,要包含的属性
	 * @return
	 */
	private static JSON jsonInclude(Object o, String dateFormat,
			List<String> includePropertys) {
		JsonConfig config = new JsonConfigHelper(dateFormat);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		PropertyFilterHelper filter = new PropertyFilterHelper(includePropertys);// 按名称过滤属性
		filter.setExclude(false);
		config.setJsonPropertyFilter(filter);
		JSON json = JSONSerializer.toJSON(o, config);
		return json;
	}

	/**
	 * @author careerly
	 * @Description:list对象转换为jsonArray对象
	 * @returnType JSONArray
	 * @throws
	 */ 
	public static JSONArray toJSONArrayString(Object obj, boolean excludeFlag,
			List<String> includePropertys) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		PropertyFilterHelper filter = new PropertyFilterHelper(includePropertys);// 按名称过滤属性
		filter.setExclude(excludeFlag);// false 不过滤 true过滤
		jsonConfig.setJsonPropertyFilter(filter);
		return JSONArray.fromObject(obj, jsonConfig);
	}

	/**
	 * Object => JSON 指定的属性进行JSON转换
	 * 
	 * @param object
	 *            待转换对象
	 * @param flag
	 *            标识 true 进行排除操作 false 进行包含操作
	 * @param attributes
	 *            需进行转换的属性
	 * @return
	 */
	public static String toJsonString(Object object, String dateFormat,
			boolean excludeFlag, List<String> attributes) {
		if (excludeFlag) {
			JSON json = jsonExclude(object, dateFormat, attributes);
			return json.toString();
		} else {
			JSON json = jsonInclude(object, dateFormat, attributes);
			return json.toString();
		}
	}

	/**
	 * @author careerly
	 * @Description: 对象转坏为json对象
	 * @returnType String
	 * @throws
	 */ 
	public static String toJsonString(Object object, boolean excludeFlag,
			List<String> attributes) {
		return toJsonString(object, "", excludeFlag, attributes);
	}

	/**
	 * @author careerly
	 * @Description: 对象转坏为json对象
	 * @returnType String
	 * @throws
	 */ 
	public static String toJsonString(Object object, String dateFormat) {
		return toJsonString(object, dateFormat, true, null);
	}

	
	/**
	 * @author careerly
	 * @Description: 对象转坏为json对象
	 * @returnType String
	 * @throws
	 */ 
	public static String toJsonString(Object object) {
		return toJsonString(object, "", true, null);
	}


	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

}
