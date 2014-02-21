package com.careerly.tool.help;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class BeanHelper {

	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午5:31:29
	 * @Description: 如果构造方式不是public，对构造方法设置访问权限
	 * @param ctor（构造器）
	 * @throws 
	 */
	public static void makeAccessible(Constructor<?> ctor) {
		if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers()))
				&& !ctor.isAccessible()) {
			ctor.setAccessible(true);
		}
	}
}
