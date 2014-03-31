package com.careerly.tool.help.json;

import java.util.List;

import net.sf.json.util.PropertyFilter;


/**   
* @Title: PropertyFilterHelper.java 
* @Package com.careerly.tool.help.json 
* @Description: 属性过滤器帮助类
* @author careerly
*/ 
public class PropertyFilterHelper implements PropertyFilter {

	private List<String> names;
	private boolean exclude = true;

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	public PropertyFilterHelper(List<String> names) {
		this.names = names;
	}

	public PropertyFilterHelper(List<String> names, boolean exclude) {
		this.names = names;
		this.exclude = exclude;
	}

	public boolean apply(Object source, String property, Object value) {
		if (names == null || names.size() < 1) {
			return !exclude;
		}
		for (String name : names) {
			if (name.equals(property)) {
				return exclude;
			}
		}
		return !exclude;
	}
}
