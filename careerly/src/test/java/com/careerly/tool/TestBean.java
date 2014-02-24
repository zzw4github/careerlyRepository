package com.careerly.tool;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import org.junit.Assert;
import org.junit.Test;

import com.careerly.tool.bean.User;

public class TestBean {

	
	public TestBean()
	{
		
	}
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午6:03:28
	 * @Description: 通过class构造一个对象
	 * @throws 
	 */
	@Test
	public void testInstantiate()
	{
		try {
			Object obj = BeanUtils.instantiate(User.class);
			Assert.assertNotNull(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午6:14:33
	 * @Description:通过构造器创建对象
	 * @throws 
	 */
	@Test
	public void testInstantiateClass()
	{
		try {
			User obj = BeanUtils.instantiateClass(User.class.getDeclaredConstructor(String.class,String.class),"careerly","careerly");
			Assert.assertNotNull(obj.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-21
	 * @time 下午6:14:52
	 * @Description: 通过参数获取方法
	 * @throws 
	 */
	@Test
	public void testFindMethod()
	{
		try {
			Method obj = BeanUtils.findMethod(User.class,"getPwd");
			Assert.assertNotNull(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:46:57
	 * @Description: map对象转换为bean对象
	 * @throws 
	 */
	@Test
	public void testMap2Bean()
	{
		try {
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("name", "careerly");
			map.put("pwd", "careerlypwd");
			User user = (User)BeanUtils.map2Bean(map, User.class);
			Assert.assertNotNull(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @author careerly
	 * @date 2014-2-24
	 * @time 下午12:53:56
	 * @Description: bean对象转化为map对象
	 * @throws 
	 */
	@Test
	public void testBean2Map()
	{
		try {
			User user = new User();
			user.setName("name");
			user.setPwd("pwd");
			Map<Object,Object> map = new HashMap<Object,Object>();
			BeanUtils.bean2Map(user,map);
			Assert.assertEquals("name", map.get("name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
