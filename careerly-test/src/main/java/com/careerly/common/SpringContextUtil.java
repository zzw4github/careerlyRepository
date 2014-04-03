package com.careerly.common;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 获取spring容器，以访问容器中定义的其他bean
 * 
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	
	Logger log = LoggerFactory.getLogger(SpringContextUtil.class);
   private static ApplicationContext applicationContext;

   public static ApplicationContext getApplicationContext() {
      return applicationContext;
   }

   /**
    * 获取对象
    * 
    * @return Object 一个以所给名字注册的bean的实例 (service注解方式，自动生成以首字母小写的类名为bean name)
    */
   public static Object getBean(String name) throws BeansException {
      return applicationContext.getBean(name);
   }

   /**
    * 实现ApplicationContextAware接口的回调方法，设置上下文环境
    */
   @Override
   public void setApplicationContext(ApplicationContext applicationContext)
         throws BeansException {
      SpringContextUtil.applicationContext = applicationContext;
   }

   /**
    * 容器加载完后，启动系统任务
    */
   @PostConstruct
   private void init() {
	   log.info("ceshishishsisssssss -------------------------------------------------");
	   System.out.println("init start ---------------");
   }

}
