package com.careerly.common.tag.ref;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TryCatchFinally;

public class BaseTag extends BodyTagSupport implements DynamicAttributes,TryCatchFinally{
	
	private static final long serialVersionUID = 112315311579939996L;
	
	Map<String, Object> attributeMap = new LinkedHashMap<String, Object>();
	 
	 @SuppressWarnings("unchecked")
	public <T>T getAttribute(String name) {
		 return (T) attributeMap.get(name);
	 }
	
	public Map<String, Object> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, Object> attributeMap) {
		this.attributeMap = attributeMap;
	}
	
	/**
	 * @author careerly
	 * @Description 支持动态传递参数
	 * @throws @param arg0
	 * @throws @param name
	 * @throws @param value
	 * @throws @throws JspException 
	 */
	public void setDynamicAttribute(String arg0, String name, Object value)
			throws JspException {
		attributeMap.put(name, value);
	}

	public void doCatch(Throwable arg0) throws Throwable {
		throw arg0;
		
	}

	public void doFinally() {
		attributeMap.clear();
		
	}

}
