package com.careerly.common.vo.condition;

import com.careerly.common.Constants;

/**   
* @Title: SearchCondition.java 
* @Package com.careerly.common.vo.condition 
* @Description:查询条件封装
* @author careerly
* 注：只有在condition值不为空时才会进行条件拼接
*/ 
public class SearchModel {

	private String name;
	
	private String value;
	
	private String condition;
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
}
