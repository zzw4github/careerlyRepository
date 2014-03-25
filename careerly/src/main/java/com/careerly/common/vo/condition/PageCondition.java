package com.careerly.common.vo.condition;

import com.careerly.common.Constants;

/**   
* @Title: PageCondition.java 
* @Package com.careerly.common.vo.condition 
* @Description: 条件封装
* @author careerly
*/ 
public class PageCondition {

	private  String equal = Constants.PageConstants.EQUAL;//等于
	
	private  String notEqual = Constants.PageConstants.NOTEQUAL;//不等于
	
	private String greaterThan = Constants.PageConstants.GREATERTHAN;//大于
	
	private  String lessThan = Constants.PageConstants.LESSTHAN; //小于
	
	private  String greaterEqual = Constants.PageConstants.GREATEREQUAL;//大于等于
	
	private  String lessEqual = Constants.PageConstants.LESSEQUAL; //小于等于
	
	private  String isNull = Constants.PageConstants.ISNULL; //空
	
	private  String isNotNull = Constants.PageConstants.ISNOTNULL; //not null
	
	private String like = Constants.PageConstants.LIKE; 
	
	private  String notLike = Constants.PageConstants.NOTLIKE;

	public String getEqual() {
		return equal;
	}

	public void setEqual(String equal) {
		this.equal = equal;
	}

	public String getNotEqual() {
		return notEqual;
	}

	public void setNotEqual(String notEqual) {
		this.notEqual = notEqual;
	}

	public String getGreaterThan() {
		return greaterThan;
	}

	public void setGreaterThan(String greaterThan) {
		this.greaterThan = greaterThan;
	}

	public String getLessThan() {
		return lessThan;
	}

	public void setLessThan(String lessThan) {
		this.lessThan = lessThan;
	}

	public String getGreaterEqual() {
		return greaterEqual;
	}

	public void setGreaterEqual(String greaterEqual) {
		this.greaterEqual = greaterEqual;
	}

	public String getLessEqual() {
		return lessEqual;
	}

	public void setLessEqual(String lessEqual) {
		this.lessEqual = lessEqual;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getIsNotNull() {
		return isNotNull;
	}

	public void setIsNotNull(String isNotNull) {
		this.isNotNull = isNotNull;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getNotLike() {
		return notLike;
	}

	public void setNotLike(String notLike) {
		this.notLike = notLike;
	}
	
}
