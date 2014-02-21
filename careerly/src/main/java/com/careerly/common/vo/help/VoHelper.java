package com.careerly.common.vo.help;

import org.apache.log4j.Logger;

public class VoHelper {
			
    public static String VO_TYPE_DTO = "DtoDataApplication";
    
    public static String voPkg = "com.test.vo";
	
	private static final Logger logger = Logger.getLogger(VoHelper.class);//日志对象
 	/**
 	 * @taskNo:反射vo对象
 	 * @Description： 反射vo对象
 	 * @param obj
 	 * @return Class
 	 * @throws: Exception
 	 * @author：huangsy
 	 * @date  : Jun 25, 2013
 	 */
 	public static Class getVoClass(Object obj)
    {
 		 String className  = obj.getClass().getName();//获取相对路径
	 		try
	        {
	            return Class.forName(className);
	        }
	        catch(ClassNotFoundException e)
	        {
	            e.printStackTrace();
	        }
	        return null;
	 }
 	
 	
 	
 	public static Class getVoClass(String componentName) {

        String className = voPkg + "." + VoHelper.getVoClassName(componentName);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("Class.forName(" + className + ") 出现异常。");
            return null;
        }
    }
 	
 	
 	 /**
     * 根据投保单要素大类的名字得到对应的VO类名
     *
     * @param componentName
     *            如"Base"
     * @return 类名 如 com.isoftstone.pcis.policy.vo.AppBaseVO
     */
    public static String getVoClassName(String componentName) {
        return  componentName + "VO";
    }
    
    
    /**
     * 根据投保单要素大类的名字生成空的VO对象
     *
     * @param componentName
     * @return 要素大类VO
     */
    public static Object createEntryVo(String componentName) {
        Class c = VoHelper.getVoClass(componentName);
        try {
            if (c != null) {
                return c.newInstance();
            }
            return null;
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
 
}
