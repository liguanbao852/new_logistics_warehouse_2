package com.lwh.user.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jctp.CThostFtdcQryInstrumentMarginRateField;
import net.jctp.CThostFtdcQryInvestorField;
import net.jctp.CThostFtdcQryTradingAccountField;
import net.jctp.CThostFtdcReqUserLoginField;
import net.jctp.CThostFtdcUserLogoutField;
import net.jtrader.JTraderException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.lwh.common.web.BaseController;
import com.lwh.user.entity.UserInfo;
import com.lwh.user.service.UserInfoService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserInfoService userInfoServiceImpl;
	
	@RequestMapping("/index")
	public String toPage(HttpServletRequest request, HttpServletResponse response) {
		
		String lastUrl = request.getRequestURI();
		logger.info("lastUrl: " + lastUrl);
		
		String userId =  UUID.randomUUID().toString();
		String userName = "TEST"; 
		String password = "123456789"; 
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfo.setUserName(userName);
		userInfo.setPassword(password);
		
		int inRes = userInfoServiceImpl.insertUserInfo(userInfo);
		
		request.getSession().setAttribute("userInfo", userInfo);
		
		return "index";
	}
	
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("signName");
		String password = request.getParameter("signPwd");

		
		
				
	}

	
	
	
	
}
