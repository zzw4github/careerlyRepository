package com.careerly.tool;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Title: CollectionUtils.java
 * @Package com.careerly.tool
 * @Description: 集合工具类
 * @author careerly
 * @date 2014-2-24 上午11:27:05
 * @version V1.0
 */
public abstract class CollectionUtils {

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:26:55
	 * @Description: 判断集合是否为空
	 * @param collection
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:27:54
	 * @Description: 判断map是否为空
	 * @param map
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:01:13
	 * @Description:数组转换为list
	 * @param source
	 * @return List
	 * @throws
	 */
	public static List arrayToList(Object source) {
		return Arrays.asList(ObjectUtils.toObjectArray(source));
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:02:32
	 * @Description: 更新数组到collection中
	 * @param array
	 * @param collection
	 *            void
	 * @throws
	 */
	public static void mergeArrayIntoCollection(Object array,
			Collection collection) {
		if (collection == null) {
			throw new IllegalArgumentException("Collection must not be null");
		}
		Object[] arr = ObjectUtils.toObjectArray(array);
		for (Object elem : arr) {
			collection.add(elem);
		}
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:03:16
	 * @Description: 更新Properties到map中
	 * @param props
	 * @param map
	 *            void
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static void mergePropertiesIntoMap(Properties props, Map map) {
		if (map == null) {
			throw new IllegalArgumentException("Map must not be null");
		}
		if (props != null) {
			for (Enumeration en = props.propertyNames(); en.hasMoreElements();) {
				String key = (String) en.nextElement();
				Object value = props.getProperty(key);
				if (value == null) {
					// Potentially a non-String value...
					value = props.get(key);
				}
				map.put(key, value);
			}
		}
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:04:22
	 * @Description: 判断element是否包含于collection中
	 * @param collection
	 * @param element
	 * @return boolean
	 * @throws
	 */
	public static boolean containsInstance(Collection collection, Object element) {
		if (collection != null) {
			for (Object candidate : collection) {
				if (candidate == element) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:05:45
	 * @Description:查询第一个匹配的对象
	 * @param source
	 * @param candidates
	 * @return Object
	 * @throws
	 */
	public static Object findFirstMatch(Collection source, Collection candidates) {
		if (isEmpty(source) || isEmpty(candidates)) {
			return null;
		}
		for (Object candidate : candidates) {
			if (source.contains(candidate)) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:08:57
	 * @Description: 是否有唯一的对象
	 * @param collection
	 * @return boolean
	 * @throws
	 */
	public static boolean hasUniqueObject(Collection collection) {
		if (isEmpty(collection)) {
			return false;
		}
		boolean hasCandidate = false;
		Object candidate = null;
		for (Object elem : collection) {
			if (!hasCandidate) {
				hasCandidate = true;
				candidate = elem;
			} else if (candidate != elem) {
				return false;
			}
		}
		return true;
	}

}
