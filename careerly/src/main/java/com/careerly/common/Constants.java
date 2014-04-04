package com.careerly.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String DEFAULT_ENCODING = "UTF-8";//设置系统编码
	
	public static final String PAGE_CONDITION = "pageCondition";//搜索条件key (servletCotext容器中进行初始化)
	
	public static final class PageConstants
	{
		public static final String EQUAL = "equal";
		public static final String NOTEQUAL = "not_equal";
		public static final String GREATERTHAN = "greater_than";
		public static final String LESSTHAN = "less_than";
		public static final String GREATEREQUAL = "greater_equal";
		public static final String LESSEQUAL = "less_equal";
		public static final String ISNULL = "is_null";
		public static final String ISNOTNULL = "is_not_null";
		public static final String LIKE = "like";
		public static final String NOTLIKE = "not_like";
		

		public static final Map<String,String> PAGE_MAP = new HashMap<String,String>()
	 {
			{
				put(EQUAL, " = ");
				put(NOTEQUAL, " <> ");
				put(GREATERTHAN, " > ");
				put(LESSTHAN, " < ");
				put(GREATEREQUAL, " >= ");
				put(LESSEQUAL, " <= ");
				put(ISNULL, " IS NULL ");
				put(ISNOTNULL, " IS NOT NULL ");
				put(LIKE, " LIKE ");
				put(NOTLIKE, " NOT LIKE ");
			}
		};
	
	};
	
	
}
