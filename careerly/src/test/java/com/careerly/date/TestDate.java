package com.careerly.date;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.careerly.common.mail.MailMessage;
import com.careerly.common.mail.MailSender;
import com.careerly.common.mail.vo.MailServerInfo;
import com.careerly.tool.DateUtil;

public class TestDate {
	
	private static DateUtil dateUtil = null;
	
	@Before
	public void setUp()
	{
		dateUtil = DateUtil.getInstance();
	}
	
	
	@Test
	public void testDateToStr()
	{
		String strDate = dateUtil.dateToStr(new Date(), "yyyy-MM-dd");
		System.out.println(strDate);
	}
	
	
	@Test
	public void testStrToDate()
	{
		Date date = dateUtil.strToDate("2012/02/12", "yyyy/MM/dd");
		System.out.println(date);
	}
	
	
}
