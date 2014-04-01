package com.careerly.common.tag.ref.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "packages")
public class StaticResourceBean {
	
	
	private List<Script> scriptList;
	
	private List<Css> cssList;
	
	@XmlElement(name="script")
	public List<Script> getScriptList() {
		return scriptList;
	}


	public void setScriptList(List<Script> scriptList) {
		this.scriptList = scriptList;
	}
	
	
	@XmlElement(name="link")
	public List<Css> getCssList() {
		return cssList;
	}


	public void setCssList(List<Css> cssList) {
		this.cssList = cssList;
	}



	public static class Script
	{
		private String name;
		
		private String type;
		
		private String minjs;
		
		private String normaljs;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getMinjs() {
			return minjs;
		}

		public void setMinjs(String minjs) {
			this.minjs = minjs;
		}

		public String getNormaljs() {
			return normaljs;
		}

		public void setNormaljs(String normaljs) {
			this.normaljs = normaljs;
		}
		
		
		
		
	}
	
	public static class Css
	{
		
		private String name;
		
		private String css;
		
		private String type;
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCss() {
			return css;
		}

		public void setCss(String css) {
			this.css = css;
		}
		
	}
}
