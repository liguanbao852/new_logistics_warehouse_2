package com.lwh.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *  涨跌宝首页
 * @author lgb
 *
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
	
	
	//首页
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("进入首页");

		// 获得系统默认展示的品种
//		ProductInfo defaultProduct = productService.getDefaultProduct();
		
		String productId = "";
//		int defaulfNum = 0;
//		for(int i=0; i<productList.size(); i++)
//		{
//			if(productList.get(i).getIsDefault() == 1)
//			{
//				defaultProduct = productList.get(i);
//				defaulfNum = i;
//				break;
//			}
//		}
//		
//		
//		// 根据当前品种获取向左向右滑动品种
//		ProductInfo nextProduct = new ProductInfo();
//		ProductInfo lastProduct = new ProductInfo();
//		if(productList.size() >= 3)
//		{
//			if(defaulfNum == 0)
//			{
//				nextProduct = productList.get(1);
//				lastProduct = productList.get(2);
//			}
//			else if(defaulfNum == productList.size()-1)
//			{
//				nextProduct = productList.get(0);
//				lastProduct = productList.get(productList.size()-2);
//			}
//			else
//			{
//				nextProduct = productList.get(defaulfNum + 1);
//				lastProduct = productList.get(defaulfNum - 1);
//			}		
//		}
//		
//		// 获得用户该品种保证金百分比
//		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
//		if(userInfo != null && userInfo.getUserId() != null)
//		{
//			String userId = userInfo.getUserId();
//			
//			// 首先查询数据库，如果没有再调用接口查询
//			double depositScale = 0.10;				// 调用保证金百分比查询接口查询用户对应期货品种的保证金百分比
//			request.setAttribute("depositScale", depositScale);
//		}
//		
//		// 获得该品种当天行情数据
//		List<Quotation> quotationList = productService.getQuotationList(defaultProduct.getInstrumentId(), "");
//		
//		// 获取对应品种的下注金额
//		List<ProductProfitLoss> productProfitLossList = productService.getProductProfitLossList(defaultProduct.getProductId(), defaultProduct.getVolumeMultiple());	
//		request.setAttribute("productProfitLossList", productProfitLossList);
//		
//		// 设置是否可以下单，Y-可以 N-不可以（1、是否是极端行情， 2、是否后台强制设定）
//		String isCanOrder = "Y";
//		request.setAttribute("isCanOrder", isCanOrder);
//		
//		request.setAttribute("nowProduct", defaultProduct);
//		request.setAttribute("lastProduct", lastProduct);
//		request.setAttribute("nextProduct", nextProduct);

		
		return "redirect:/user/index.htm";
	
		//return "index";
	}

	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
	
		logger.info("进入欢迎页");
		return "/zdb/welcome";
	}
}
