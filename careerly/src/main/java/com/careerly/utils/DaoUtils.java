package com.careerly.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;



import com.careerly.common.Constants;
import com.careerly.common.vo.condition.SearchModel;

public class DaoUtils {

	/**
	 * @author careerly
	 * @Description: 公用设置访问参数
	 * @returnType String
	 * @param hql(需要执行查询的hql语句，示例：from aa where 1=1) ,
	 * List<SearchModel> （查询条件集合，从pageBean中获取）
	 * searchMap（存放查询条件）
	 * @throws
	 * 注意：此方法只能是在 hql（where）之后 、（order by） 之前使用
	 */ 
	public static void setSearchParam(StringBuffer hql,List<SearchModel> list,Map<String, Object> searchMap)
 {
		int size = list != null ? list.size() : 0;
		// 只处理50个参数一下的拼装
		if (list.size() > 50) {
			return;
		}
		SearchModel searchModel = null;
		String conditionValue = "";
		for (int i = 0; i < size; i++) {
			searchModel = list.get(i);
			conditionValue = Constants.PageConstants.PAGE_MAP.get(searchModel
					.getCondition());//条件值  例：= >
			if (!StringUtils.isBlank(conditionValue)
					&& !StringUtils.isBlank(searchModel.getName())
					&& !StringUtils.isBlank(searchModel.getValue())) {
				String name = searchModel.getName();
				String paramName = name.indexOf(".") != -1 ? name.substring(
						name.indexOf(".") + 1, name.length()) : name;
				if (Constants.PageConstants.LIKE.equals(searchModel.getCondition())
						|| Constants.PageConstants.NOTLIKE.equals(searchModel.getCondition())) {// like条件与not like条件处理
					
					hql.append(" AND " + name + " " + conditionValue + " :"+ paramName);
					searchMap.put(paramName,new String("%" + searchModel.getValue() + "%"));
					
				} else if (Constants.PageConstants.ISNULL.equals(searchModel.getCondition())
						|| Constants.PageConstants.ISNOTNULL.equals(searchModel.getCondition())) {//null not null
					
					hql.append(" AND " + name + " " + conditionValue);

				} else {
					
					hql.append(" AND " + name + " " + conditionValue + " :"+ paramName);
					searchMap.put(paramName, searchModel.getValue());
				}

			}
		}
	}
}
