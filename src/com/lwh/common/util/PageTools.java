package com.lwh.common.util;

import org.apache.commons.lang.StringUtils;

public class PageTools {

	/**
	 * 每页默认10条记录
	 */
	private static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 默认第一页
	 */
	private static final int DEFAULT_CURRENT_PAGE = 1;

	/**
	 * 获取一页记录数
	 * 
	 * @param pageSize
	 * @return
	 */
	public static final int getPageSize(String pageSize) {
		if (StringUtils.isBlank(pageSize)) {
			return DEFAULT_PAGE_SIZE;
		}
		return Integer.parseInt(pageSize);
	}

	/**
	 * 获取当前页
	 * 
	 * @param currentPage
	 * @return
	 */
	public static final int getCurrentPage(String currentPage) {
		if (StringUtils.isBlank(currentPage)) {
			return DEFAULT_CURRENT_PAGE;
		}
		return Integer.parseInt(currentPage);
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 * 
	 */
	public static final int getTotalPages(int totalRows, String pageSize) {
		int totalPages = 0;
		if ((totalRows % getPageSize(pageSize)) == 0) {
			totalPages = totalRows / getPageSize(pageSize);
		} else {
			totalPages = totalRows / getPageSize(pageSize) + 1;
		}
		return totalPages;
	}

	/**
	 * 当前页在数据库中的起始行数
	 * 
	 * @return
	 */
	public static final int startRow(String currentPage, String pageSize) {
		return getCurrentPage(currentPage) * getPageSize(pageSize)-1;
	}

}
