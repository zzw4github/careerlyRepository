package com.careerly.utils;

import java.util.Arrays;

import net.sf.json.JSONArray;

import com.careerly.common.GlobalConfig;
import com.careerly.common.tag.ref.RefTag;
import com.careerly.common.tag.ref.vo.StaticResourceBean;


/**   
* @Title: ResourceFunctionUtils.java 
* @Package com.careerly.utils 
* @Description: 资源文件公用类
* @author careerly
*/ 
public class ResourceFunctionUtils {
	/**web容器的上下文*/
	private static String context;
	/**页面资源的上下文*/
	private static String ctx;
	
	private static String realRoot;
	
	private static String jsRoot;
	
	private static String cssRoot;
	
	private static boolean isComp;//是否是编译环境
	
	private static String imgRoot;
	
	/**存放js文件夹名称**/
	private static final String  jsClassPath = "/js";
	
	/**存放image文件夹名称**/
	private static final String  imgClassPath = "/images";
	
	/**存放css文件夹名称**/
	private static final String cssClassPath = "/css";
	
	/**
	 * 资源文件存放
	 */
	private static StaticResourceBean resourceBean;
	
	
	public static StaticResourceBean getResourceBean() {
		return resourceBean;
	}
	
	/**
	 * @author careerly
	 * @Description: 设置静态资源(初始化时进行)
	 * @returnType void
	 * @throws
	 */
	public static void setResourceBean(StaticResourceBean resourceBean) {
		ResourceFunctionUtils.resourceBean = resourceBean;
	}

	
	
	static {
		init();
	}
	
	public static void init() {
			
	}
	
	private static String[] getValue(Object key, Object o1, Object o2, Object o3) {
		Object value = key != null ? key : (o1 != null ? o1 : (o2 != null ? o2 : o3));
		if(value instanceof String[]) {
			return (String[]) value;
		}
		if(value instanceof String) {
			return new String[]{(String) value};
		} 
		JSONArray array = (JSONArray) value;
		return Arrays.asList(array.toArray()).toArray(new String[]{});
	}
	
	
	
	public static String getRealRoot() {
		return realRoot;
	}

	public static void setRealRoot(String realRoot) {
		ResourceFunctionUtils.realRoot = realRoot;
	}

	/**
	 * @Description: 设置上下文环境
	 * @returnType void
	 */ 
	public static void setContext(String context) {
		ResourceFunctionUtils.context = context;
	}

	public static String getContext() {
		return context;
	}
	
	/**
	 * @Description: 获取上下文环境
	 * @returnType String
	 */ 
	public static String getCtx() {
		if(ctx != null) {
			return ctx;
		}
		if(ctx == null) {
			ctx = context;
		}
		return ctx;
	}
	
	
	/**
	 * @author careerly
	 * @Description: 获取js文件存放根目录
	 * @returnType String
	 */ 
	public static String getJsRoot() {
		if(jsRoot != null) {
			return jsRoot;
		}
		if(jsRoot == null) {
			jsRoot = getCtx()+jsClassPath;
		}
		
	    return jsRoot;
	}
	
	/**
	 * @author careerly
	 * @Description: 存放css存放根目录
	 * @returnType String
	 */ 
	public static String getCssRoot() {
		if(cssRoot != null) {
			return cssRoot;
		}
		
		if(cssRoot == null) {
			cssRoot = getCtx()+cssClassPath;
		}
		
	    return cssRoot;
	}
	
	public static String getStaticRoot() {
		return getCtx() + "/static";
	}
	
	/**
	 * @author careerly
	 * @Description: 获取image资源根目录
	 * @returnType String
	 * @throws
	 */ 
	public static String getImgRoot() {
		if(imgRoot != null) {
			return imgRoot;
		}
		return getCtx() + imgClassPath;
	}
	
	public static String getRealPath(String path) {
		path = path.substring(getCtx().length());
		return realRoot + path;
	}

	public static boolean isComp() {
		return isComp;
	}

	public static void setComp(boolean isComp) {
		ResourceFunctionUtils.isComp = isComp;
	}
	
	
}
