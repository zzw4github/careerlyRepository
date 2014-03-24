package com.careerly.tool;


import java.util.Date;

import org.junit.Test;

public class TestDate {
	

	
	
	@Test
	public void testDateToStr()
	{
		String strDate = DateUtils.dateToStr(new Date(), "yyyy-MM-dd");
		System.out.println(strDate);
	}
	
	
	@Test
	public void testStrToDate()
	{
		Date date = DateUtils.strToDate("2012/02/12", "yyyy/MM/dd");
		System.out.println(date);
	}
	
	
	
}
