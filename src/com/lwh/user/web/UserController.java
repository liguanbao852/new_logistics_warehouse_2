package com.lwh.user.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
