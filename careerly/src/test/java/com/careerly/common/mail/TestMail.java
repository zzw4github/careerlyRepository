package com.careerly.common.mail;


import org.junit.Before;
import org.junit.Test;

import com.careerly.common.mail.MailMessage;
import com.careerly.common.mail.MailSender;
import com.careerly.common.mail.vo.MailServerInfo;

public class TestMail {

	private MailSender sender = null;
	
	/**需要修改部分数据***/
	private String host = "smtp.163.com";//邮件服务器地址
	private String userName = "huangshengya6740@163.com";//用户名
	private String userPassword = "servermima"; //登录邮件服务器密码
	
	@Before
	public void setUp()
	{
		MailServerInfo server = new MailServerInfo();
		server.setHost(host);
		server.setUserName(userName);
		server.setPassWord(userPassword);
		sender = new MailSender(server);
	}
	
	/**
	 * @author huangshengya@rockontrol.com
	 * @date 2014-2-21
	 * @time 上午11:23:59
	 * @Description:群发文本文件测试
	 * @throws 
	 */
	@Test
	public void testSendMoreTextMail()
	{
		try {
			MailMessage msg = new MailMessage();
			msg.setTo("huangshengya6740@163.com;rockontrol_iaas@163.com");
			msg.setSubject("群发text邮件测试");
			msg.setContent("测试群发!!!");
			sender.sendMoreTextMail(msg,userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author huangshengya@rockontrol.com
	 * @date 2014-2-21
	 * @time 上午11:30:26
	 * @Description: 单发text邮件测试
	 * @throws 
	 */
	@Test
	public void testSendTextMail()
	{
		try {
			MailMessage msg = new MailMessage();
			msg.setTo("huangshengya6740@163.com");
			msg.setSubject("单发txt邮件测试");
			msg.setContent("测试单发!!!");
			sender.sendTextMail(msg,userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author huangshengya@rockontrol.com
	 * @date 2014-2-21
	 * @time 上午11:32:18 //单发html测试
	 * @Description: TODO void
	 * @throws 
	 */
	@Test
	public void testSendHtmlMail()
	{
		try {
			MailMessage msg = new MailMessage();
			msg.setTo("huangshengya6740@163.com");
			msg.setSubject("单发html邮件测试");
			msg.setContent("测试单发<br/>测试单发!!!");
			sender.sendHtmlMail(msg,userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @author huangshengya@rockontrol.com
	 * @date 2014-2-21
	 * @time 上午11:32:38
	 * @Description: 群发html测试
	 * @throws 
	 */
	@Test
	public void testSendMoreHtmlMail()
	{
		try {
			MailMessage msg = new MailMessage();
			msg.setTo("huangshengya6740@163.com");
			msg.setSubject("单发html邮件测试");
			msg.setContent("测试群发发<br/>测试群发!!!");
			sender.sendMoreHtmlMail(msg,userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
