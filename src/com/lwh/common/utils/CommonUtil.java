package com.lwh.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 常用工具
 * @author xuming
 *
 */
public class CommonUtil {
	
	public static final String STRING_EMPTY = "";
	
	/**
	 * 个人中心左侧菜单高亮
	 * mark:
	 */
	public static void setLTab(HttpServletRequest request,int mark)
	{
		request.setAttribute("ltabId", mark);
		setTTab(request, 2);
	}
	
	/**
	 * 顶部菜单高亮
	 * mark: 1首页 2个人中心 3积分商城 4帮助中心
	 */
	public static void setTTab(HttpServletRequest request,int mark)
	{
		request.setAttribute("ttabId", mark);
	}
	
	
}
