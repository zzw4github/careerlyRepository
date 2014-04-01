package com.careerly.common;

import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**   
* @Title: GlobalConfig.java 
* @Package com.careerly.common 
* @Description: 配置文件获取
* @author careerly
*/ 
public class GlobalConfig {
   private static final String _CONFIG_FILE = "com/resource/resources.properties";

   private PropertiesConfiguration properties;

   private static GlobalConfig INSTANCE = new GlobalConfig();

   private GlobalConfig() {
      URL url = Thread.currentThread().getContextClassLoader().getResource(_CONFIG_FILE);
         try {
			properties = new PropertiesConfiguration(url);
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}

   }

   public static PropertiesConfiguration getProperties() {
      return INSTANCE.properties;
   }

   public static String getProperty(String name){
	   return (String)INSTANCE.properties.getProperty(name);
   }

   public static String getProperty(String name,String defaultValue){
	   String val =  (String)INSTANCE.properties.getProperty(name);
	   return val==null?defaultValue:val;
   }
}
