package com.careerly.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.careerly.common.page.PageBean;
import com.careerly.tool.JsonUtils;
import com.careerly.tool.help.json.JsonHelper;

/**   
* @Title: WebUtils.java 
* @Package com.careerly.business.utils 
* @Description: Controller公用处理
* @author careerly
*/ 
public class WebUtils {

	private static final Logger log = LoggerFactory.getLogger(WebUtils.class);
	
	private static final String ENCODING_PREFIX = "encoding:";
	private static final String NOCACHE_PREFIX = "no-cache:";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;
	
	/**
	 * @author careerly
	 * @Description: page参数设置
	 * @returnType void
	 * @throws
	 */ 
	public static void setPageParams(PageBean pageBean,ModelAndView mv,String requestUrl)
	{
		pageBean.setUrl(requestUrl);
		mv.addObject("page",pageBean);
	}
	
	
	/**
	 * @author careerly
	 * @Description: 直接输出xml文件
	 * @returnType void
	 * @throws
	 */ 
	public static void renderXml(HttpServletResponse response, String xml, String... headers) {
		render(response, "text/xml", xml, headers);
	}

	/**
	 * @author careerly
	 * @Description: 直接输出html文件
	 * @returnType void
	 * @throws
	 */ 
	public static void renderHtml(HttpServletResponse response, String html, String... headers) {
		render(response, "text/html", html, headers);
	}
	
	/**
	 * @author careerly
	 * @Description: 直接输出文本
	 * @returnType void
	 * @throws
	 */ 
	public static void renderText(HttpServletResponse response, String text, String... headers) {
		render(response, "text/plain", text, headers);
	}
	
	
	/**
	 * @author careerly
	 * @Description: json输出
	 * @returnType void
	 * @throws
	 */ 
	public static void renderJson(HttpServletResponse response, String string, String... headers) {
		render(response, "application/json", string, headers);
	}
	

	/**
	 * @author careerly
	 * @Description: json格式的数据输出
	 * @returnType void
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.      
	 */ 
	public static void render(HttpServletResponse response, String contentType, String content, String... headers) {
		try {
			//分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else {
					throw new IllegalArgumentException(headerName + "The type of illegal");
				}
			}

			//设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	 /**
	 * @author careerly
	 * @Description: 从cookie中获取值
	 * @returnType String
	 * @throws
	 */ 
	public static String getValueByCookieName(HttpServletRequest request, String cookieName){
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null){
	            for(Cookie cookie: cookies){
	                if (cookie.getName().equalsIgnoreCase(cookieName)){
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null;
	 }
	

	
	/**
	 * @author careerly
	 * @Description: 从url中获取json数据转换map对象
	 * @returnType Map<String,Object>
	 * @throws
	 */ 
	public static Map<String, Object> getMapByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return JsonHelper.parseJSON2Map(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * @author careerly
	 * @Description: 从url中获取json对象转换为list集合
	 * @returnType List<Map<String,Object>>
	 * @throws
	 */ 
	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return JsonHelper.parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
