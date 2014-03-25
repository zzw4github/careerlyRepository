package com.careerly.common.jsch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJsch {

	private  JschHandler jschHandler = null;
	@Before
	public void setUp()
	{
		 jschHandler = new JschHandler();
	}
	
	/**
	 * @author careerly
	 * @date 2014-2-26
	 * @time 上午11:08:18
	 * @Description: ssh登录并执行命令
	 * @throws 
	 */
	@Test
	public void testSshExecute()
	{
		  String ss =   jschHandler.sshExecute("10.12.13.14","root","1","ifconfig");
	       System.out.println(ss);
	       Assert.assertNotNull(ss);
	}
}
