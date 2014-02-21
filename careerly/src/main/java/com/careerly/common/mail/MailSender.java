package com.careerly.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.careerly.common.mail.vo.MailServerInfo;

/**
 * 简单邮件（不带附件的邮件）发送器 http://www.bt285.cn BT下载
 */
public class MailSender {
	private static final Log log = LogFactory.getLog(MailSender.class);
	private String auth = "true";
	private MailServerInfo mailServer;

	public MailSender(MailServerInfo mailServer) {
		this.mailServer = mailServer;
	}

	/**
	 * @throws Exception 
	 * @author huangshengya@rockontrol.com
	 * @date 2013-10-21
	 * @time 上午9:59:33
	 * @Description:单发txt的邮件
	 * @param mailMsg
	 * @return boolean
	 * @throws
	 */
	public boolean sendTextMail(MailMessage mailMsg,String fromAddress) throws Exception {

		return this.sendMail(mailMsg, "txt", false, fromAddress);
	}

	/**
	 * @throws Exception 
	 * @author huangshengya@rockontrol.com
	 * @date 2013-10-21
	 * @time 上午9:59:33
	 * @Description:群发txt的邮件
	 * @param mailMsg
	 * @return boolean
	 * @throws
	 */
	public boolean sendMoreTextMail(MailMessage mailMsg,String fromAddress) throws Exception {
		return this.sendMail(mailMsg, "txt", true, fromAddress);
	}

	/**
	 * @throws Exception 
	 * @author huangshengya@rockontrol.com
	 * @date 2013-10-21
	 * @time 上午9:59:33
	 * @Description:单个发送html的文件
	 * @param mailMsg
	 * @return boolean
	 * @throws
	 */
	public boolean sendHtmlMail(MailMessage mailMsg,String fromAddress) throws Exception {
		return this.sendMail(mailMsg, "html", false,fromAddress);
	}

	/**
	 * @throws Exception 
	 * @author huangshengya@rockontrol.com
	 * @date 2013-10-21
	 * @time 上午9:59:33
	 * @Description:群发html格式的文件
	 * @param mailMsg
	 * @return boolean
	 * @throws
	 */
	public boolean sendMoreHtmlMail(MailMessage mailMsg,String fromAddress) throws Exception {
		return this.sendMail(mailMsg, "html", true,fromAddress);
	}

	public boolean sendMail(MailMessage mailMsg, String textType, boolean isMore,String fromAddress) throws Exception{
		// 判断是否需要身份认证
		MailAuthenticator authenticator = null;
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServer.getHost());
		p.put("mail.smtp.port", "25");
		p.put("mail.smtp.auth", this.auth);
		// 如果需要身份认证，则创建一个密码验证器
		authenticator = new MailAuthenticator(this.mailServer.getUserName(),
				this.mailServer.getPassWord());
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(p, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(fromAddress);
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			// Message.RecipientType.TO属性表示接收者的类型为TO
			if (isMore == true) {
				String[] eamil = mailMsg.getTo().split(";");
				InternetAddress[] address = new InternetAddress[eamil.length];
				for (int i = 0; i < address.length; i++) {
					address[i] = new InternetAddress(eamil[i]);
				}
				mailMessage
				.setRecipients(Message.RecipientType.TO, address);
			} else {
				Address to = new InternetAddress(mailMsg.getTo());
				mailMessage.setRecipient(Message.RecipientType.TO, to);
			}
			// 设置邮件消息的主题
			mailMessage.setSubject(mailMsg.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			if ("html".equals(textType))// 如果发送的是html
			{
				// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
				Multipart mainPart = new MimeMultipart();
				// 创建一个包含HTML内容的MimeBodyPart
				BodyPart html = new MimeBodyPart();
				// 设置HTML内容
				html.setContent(mailMsg.getContent(),
						mailMsg.getContentType());
				mainPart.addBodyPart(html);
				// 将MiniMultipart对象设置为邮件内容
				mailMessage.setContent(mainPart);
			} else // 发送的是文本
			{
				// 设置邮件消息的主要内容
				String mailContent = mailMsg.getContent();
				mailMessage.setText(mailContent);
			}
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			throw new Exception(mailMsg.getTo()+"发送失败！");
		}
	}

	public static void main(String[] args) {
		MailServerInfo server = new MailServerInfo();
		server.setHost("smtp.163.com");
		server.setUserName("zhangsan@163.com");
		server.setPassWord("mima");

		MailSender sender = new MailSender(server);

		MailMessage msg = new MailMessage();
		msg.setTo("lisi@163.com");
		msg.setSubject("邮件测试");
		msg.setContent("ceeshi  ~~~~~~~~~~~~~~<br/>");
		try {
			sender.sendMoreTextMail(msg,"zhangsan@163.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}