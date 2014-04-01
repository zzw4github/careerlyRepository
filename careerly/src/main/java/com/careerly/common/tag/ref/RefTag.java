package com.careerly.common.tag.ref;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.careerly.common.tag.ref.vo.StaticResourceBean.Css;
import com.careerly.common.tag.ref.vo.StaticResourceBean.Script;
import com.careerly.utils.ResourceFunctionUtils;

/**
 * @Title: RefTag.java
 * @Package com.careerly.common.tag.ref
 * @Description: 标签库
 * @author careerly
 */
public class RefTag extends BaseTag {

	private static final long serialVersionUID = -4422863760862252790L;

	public static final String CSS_TYPE = "css";

	public static final String JS_TYPE = "js";

	public static final String IMG_TYPE = "img";

	public static final String CSS_TMP = "<link rel=\"stylesheet\" href=\"";

	public static final String JS_TMP = "<script src=\"";

	public static final String IMG_TMP = "<img src=\"";

	public static final String TYPE = "type";
	
	public static final String NAME = "name";
	

	/**
	 * 类型 (js css image)
	 */
	protected String configType;

	protected String configRoot;

	public int doStartTag() throws JspException {

		try {
			// 进行处理
			String type = configType;
			if (type == null) {
				type = getAttribute(TYPE);
			}
			if (type == null) {
				type = JS_TYPE;
			}

			String name = getAttribute(NAME);
			String content="";
			if (name == null) {//如果名称为null,通过类型进行数据获取
				content = processWithType(type);
			} 
			this.pageContext
					.getOut()
					.write(content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return SKIP_BODY;
	}
	
	/**
	 * @author careerly
	 * @Description: 根据类型进行获取
	 * @returnType String
	 * @throws
	 */ 
	private String processWithType(String type) {
			String content = "";
			Object obj = attributeMap.get(TYPE);//获取map类型
			if(obj instanceof java.lang.String)
			{
				String pageTagType = (String)obj;
				content = getTypeContent(pageTagType, type);
			}
			return content;
	}
	
	
	private String getTypeContent(String pageTagType,String type) {

		StringBuffer content = new StringBuffer("");
		if (JS_TYPE.equals(type)) {// js类型
			List<Script> scriptList = ResourceFunctionUtils.getResourceBean()
					.getScriptList();
			if (scriptList != null) {
				int size = scriptList.size();
				Script script = null;
				for (int i = 0; i < size; i++) {
					script = scriptList.get(i);
					if (script.getType() != null
							&& script.getType().equals(pageTagType)) {
						String js = "";
						if (ResourceFunctionUtils.isComp()) {
							js = (!StringUtils.isBlank(script.getMinjs())) ? script
									.getMinjs() : script.getNormaljs();
						} else {
							js = (!StringUtils.isBlank(script.getNormaljs())) ? script
									.getNormaljs() : script.getMinjs();
						}
						/** 组装content **/
						if (!StringUtils.isBlank(js)) {
							content.append(JS_TMP);
							content.append(ResourceFunctionUtils.getJsRoot()+"/");
							content.append(js);
							content.append("\"></script>\n");
						}
					}
				}
			}
		} else if (CSS_TYPE.equals(type)) {// css类型

			List<Css> cssList = ResourceFunctionUtils.getResourceBean()
					.getCssList();
			if (cssList != null) {
				int size = cssList.size();
				for (int i = 0; i < size; i++) {
					if (cssList.get(i).getType() != null
							&& cssList.get(i).getType().equals(pageTagType)) {
						if (!StringUtils.isBlank(cssList.get(i).getCss())) {
							content.append(CSS_TMP);
							content.append(ResourceFunctionUtils.getCtx()+"/");
							content.append(cssList.get(i).getCss());
							content.append("\"/>\n");
						}
					}
				}
			}
		}
		return content.toString();
	}

}
