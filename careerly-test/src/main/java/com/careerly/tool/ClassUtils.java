package com.careerly.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClassUtils {
	
	private static Logger log = LoggerFactory.getLogger(ClassUtils.class);
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:08:09
	 * @Description: 获取默认类加载器
	 * @return ClassLoader
	 * @throws 
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			log.error("get ClassLoader is error！",ex.getMessage());
		}
		if (cl == null) {
			cl = ClassUtils.class.getClassLoader();
		}
		return cl;
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 上午11:10:37
	 * @Description:获取实际线程使用的类加载器
	 * @param classLoaderToUse
	 * @return ClassLoader
	 * @throws 
	 */
	public static ClassLoader overrideThreadContextClassLoader(ClassLoader classLoaderToUse) {
		Thread currentThread = Thread.currentThread();
		ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
		if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
			currentThread.setContextClassLoader(classLoaderToUse);
			return threadContextClassLoader;
		}
		else {
			return null;
		}
	}
	
	
}
