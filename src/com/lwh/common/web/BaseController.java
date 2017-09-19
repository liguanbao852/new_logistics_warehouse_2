package com.lwh.common.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.egssoft.common.client.CommonClient;
import cn.egssoft.common.service.ServiceRequest;
import cn.egssoft.common.service.ServiceResponse;

public class BaseController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	protected ServiceResponse call(String serviceID, Map<String, Object> params) {
		ServiceRequest request = new ServiceRequest();
		request.setServiceId(serviceID);
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet())
				request.setBodyValue(entry.getKey(), entry.getValue());
		}
		return CommonClient.call(request);
	}

	protected void print(HttpServletResponse response, String msg) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(msg);
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	protected Map<String, String> getEasyUiPage(HttpServletRequest request)
	{
		String start = "0";
		//定义局部变量
		Map<String,String> page = new HashMap<String,String>();
		String currentPage = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(StringUtils.isNotBlank(currentPage) && StringUtils.isNotBlank(pageSize)){
			start = String.valueOf((Integer.valueOf(currentPage)-1)*(Integer.valueOf(pageSize)));
			page.put("start", start);
			page.put("pageSize", pageSize);
		}else{
			currentPage = "1";
			pageSize = "10";
			start = String.valueOf((Integer.valueOf(currentPage)-1)*(Integer.valueOf(pageSize)));
			page.put("start", start);
			page.put("pageSize", pageSize);
		}
		return page;
	}
}
