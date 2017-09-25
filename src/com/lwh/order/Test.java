package com.lwh.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.lwh.order.web.OrderController;

public class Test {

	@Autowired
	private static  HttpServletRequest request;
	
	@Autowired
	private static  HttpServletResponse response;
	
	public static void main(String[] args) {
		
		OrderController ot = new OrderController();
	
		ot.queryOrderList(request, response);

	}

}
