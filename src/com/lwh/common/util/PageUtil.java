package com.lwh.common.util;
import javax.servlet.http.HttpServletRequest;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;
public class PageUtil {
	private static int DEFAULT_PAGE_SIZE=10;
	public static int getPageIndex(HttpServletRequest request) {
    	Context context = new HttpServletRequestContext(request);   
		   LimitFactory limitFactory = new TableLimitFactory(context);   
		   TableLimit limit = new TableLimit(limitFactory);  
		   int page= limit.getPage();
		   int pageIndex=(page-1)*(DEFAULT_PAGE_SIZE);
		   return pageIndex;
	}
	public static int getPageIndex(HttpServletRequest request,int pageSize) {
		   Context context = new HttpServletRequestContext(request);   
		   LimitFactory limitFactory = new TableLimitFactory(context);   
		   TableLimit limit = new TableLimit(limitFactory);  
		   int page= limit.getPage();
		   int pageIndex=(page-1)*(pageSize);
		   return pageIndex;
	}
	public static int getDEFAULT_PAGE_SIZE() {
		return DEFAULT_PAGE_SIZE;
	}
	public static void setDEFAULT_PAGE_SIZE(int dEFAULT_PAGE_SIZE) {
		DEFAULT_PAGE_SIZE = dEFAULT_PAGE_SIZE;
	}
	
}