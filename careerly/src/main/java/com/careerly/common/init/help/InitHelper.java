package com.careerly.common.init.help;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.careerly.common.Constants;
import com.careerly.common.GlobalConfig;
import com.careerly.common.tag.ref.vo.StaticResourceBean;
import com.careerly.common.vo.condition.PageCondition;
import com.careerly.utils.ResourceFunctionUtils;

public class InitHelper {

	private static  Logger log = LoggerFactory.getLogger(InitHelper.class);
	
	private static final String STATIC_RESOURCE = "com/resource/xml/static_resource.xml";
	/**
	 * @author careerly
	 * @Description: 设置分页常用搜索条件
	 * @returnType void
	 * @throws
	 */ 
	public static void setPageCondition(ServletContext servletContext)
	{
		log.info("set PageCondition begin execution");
		servletContext.setAttribute(Constants.PAGE_CONDITION,new PageCondition());
		log.info("set PageCondition to end");
	}
	
	
	/**
	 * @author careerly
	 * @Description: 设置系统路径
	 * @returnType void
	 * @throws
	 */ 
	public static void setApplicationPath(ServletContext servletContext)
	{
	
		log.info("set contextPath begin execution");
		ResourceFunctionUtils.setContext(servletContext.getContextPath());
		log.info("set contextPath to end");
		
		log.info("set realRoot begin execution");
		ResourceFunctionUtils.setRealRoot(servletContext.getRealPath(""));
		log.info("set realRoot to end");
	}
	
	
	/**
	 * @author careerly
	 * @Description: 设置静态资源文件在全局
	 * @returnType void
	 * @throws
	 */ 
	public static void setStaticResource(ServletContext servletContext)
 {
		log.info("set setStaticResource begin execution");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(StaticResourceBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			URL url = Thread.currentThread().getContextClassLoader().getResource(STATIC_RESOURCE);
			StaticResourceBean resourceBean = (StaticResourceBean) unmarshaller
					.unmarshal(new File(url.getFile()));
			ResourceFunctionUtils.setResourceBean(resourceBean);

			/**设置js加载环境**/
			ResourceFunctionUtils.setComp("true".equals(GlobalConfig
					.getProperties().getString("tag.isNormal")) ? false : true);

		} catch (JAXBException e) {
			log.error("Error invoke on setStaticResource", e);
		}
		log.info("set staticResource to end");
	}
	
}
