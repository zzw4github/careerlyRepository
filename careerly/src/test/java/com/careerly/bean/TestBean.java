package com.careerly.bean;

import org.junit.Test;

import com.careerly.tool.BeanUtils;

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
	public void TestInstantiate()
	{
		try {
			Object obj = BeanUtils.instantiate(TestBean.class);
			System.out.println(obj.toString()+"------------");
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
	public void TestInstantiateClass()
	{
		try {
			Object obj = BeanUtils.instantiateClass(TestBean.class);
			System.out.println(obj.toString()+"------------");
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
	public void TestFindMethod()
	{
		try {
			Object obj = BeanUtils.findMethod(TestBean.class,"TestFindMethod");
			System.out.println(obj.toString()+"------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
