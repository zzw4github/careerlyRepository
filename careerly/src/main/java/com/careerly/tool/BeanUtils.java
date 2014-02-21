package com.careerly.tool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
	
}
