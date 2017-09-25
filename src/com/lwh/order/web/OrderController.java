package com.lwh.order.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwh.common.web.BaseController;
import com.lwh.order.entity.ProductLineInfo;
import com.lwh.order.service.OrderInfoService;


@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderInfoService orderInfoServiceImpl;
	
	@RequestMapping("/queryOrderList")
	public void queryOrderList(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("获取原始订单列表");
			
		JSONObject jsonObject = new JSONObject();
		
		List<HashMap> orderInfoList = orderInfoServiceImpl.queryOrderInfoList();
	
		logger.info("查询结果orderInfoList:" + orderInfoList);
		jsonObject.put("orderInfoList", orderInfoList);
	
		print(response, jsonObject.toString());
		
	}
	
	// 排产优化后的订单列表
	@RequestMapping("/queryNewOrderList")
	public void queryNewOrderList(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("排产优化后的订单列表");
			
		JSONObject jsonObject = new JSONObject();
		
		List<HashMap> newOrderInfoList = orderInfoServiceImpl.queryNewOrderList();
	
		logger.info("查询结果newOrderInfoList:" + newOrderInfoList);
		jsonObject.put("newOrderInfoList", newOrderInfoList);
	
		
		String nowOnlineOrderId = (String)newOrderInfoList.get(0).get("order_no");
		String nowOnlineOrderName = (String)newOrderInfoList.get(0).get("order_name");
		// 更新最新产线
		orderInfoServiceImpl.updateOnlineOrder(nowOnlineOrderId);
		// 会话存储当前产线ID
		request.getSession().setAttribute("nowOnlineOrderId", nowOnlineOrderId);
		request.getSession().setAttribute("nowOnlineOrderName", nowOnlineOrderName);	
		
		print(response, jsonObject.toString());
		
	}
	
	@RequestMapping("/queryMaterialList")
	public void queryMaterialList(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("获取物料列表");
			
		JSONObject jsonObject = new JSONObject();
		
		String orderId = null;
		List<HashMap> materialList = orderInfoServiceImpl.queryMaterialInfoListAll(orderId);
	
		logger.info("查询结果materialList:" + materialList);
		jsonObject.put("materialList", materialList);
	
		print(response, jsonObject.toString());
		
	}
	

	@RequestMapping("/queryMachineFunctionList")
	public void queryMachineFunctionList(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("获取产线，机器，功能列表");
			
		JSONObject jsonObject = new JSONObject();
		String available_type = null;
		
		List<ProductLineInfo> productLineList = orderInfoServiceImpl.queryProductLineList(available_type);
		logger.info("查询结果productLineList:" + productLineList);
		jsonObject.put("productLineList", productLineList);
		
		List<HashMap> machineList = orderInfoServiceImpl.queryMachineList(available_type);
		logger.info("查询结果machineList:" + machineList);
		jsonObject.put("machineList", machineList);
		
		
		List<HashMap> machineFunctionList = orderInfoServiceImpl.queryMachineFunctionList(available_type);
		logger.info("查询结果machineFunctionList:" + machineFunctionList);
		jsonObject.put("machineFunctionList", machineFunctionList);
		
		print(response, jsonObject.toString());
		
	}
	
	// 本地生产优化措施
	@RequestMapping("/queryNewProductMeasures")
	public void queryNewProductMeasures(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("本地生产优化措施");
		
		//获取当前生产订单
		String nowOnlineOrderId = (String)request.getSession().getAttribute("nowOnlineOrderId");
		String nowOnlineOrderName = (String)request.getSession().getAttribute("nowOnlineOrderName");
		
		String newProductMeasures = orderInfoServiceImpl.queryNewProductMeasures(nowOnlineOrderId, nowOnlineOrderName);

		JSONObject jsonObject = new JSONObject();
		
		logger.info("查询结果newProductMeasures:" + newProductMeasures);
		jsonObject.put("newProductMeasures", newProductMeasures);
	
		print(response, jsonObject.toString());
		
	}
	
	// 采购优化措施
	@RequestMapping(value="/queryNewProductMaterial")
	public void queryNewProductMaterial(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("采购优化措施");
		
		//获取当前生产订单
		String nowOnlineOrderId = (String)request.getSession().getAttribute("nowOnlineOrderId");
		String nowOnlineOrderName = (String)request.getSession().getAttribute("nowOnlineOrderName");
		
		String newProductMaterial = orderInfoServiceImpl.queryNewProductMaterial(nowOnlineOrderId, nowOnlineOrderName);

		JSONObject jsonObject = new JSONObject();
		
		logger.info("查询结果newProductMaterial:" + newProductMaterial);
		jsonObject.put("newProductMaterial", newProductMaterial);
	
		print(response, jsonObject.toString());
		
	}
	
	// 合作公司生产优化措施
	@RequestMapping(value="/queryNewCompanyMeasures")
	public void queryNewCompanyMeasures(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("合作公司生产优化措施");
		
		//获取当前生产订单
		String nowOnlineOrderId = (String)request.getSession().getAttribute("nowOnlineOrderId");
		String nowOnlineOrderName = (String)request.getSession().getAttribute("nowOnlineOrderName");
		
		String newCompanyMeasures = orderInfoServiceImpl.queryNewCompanyMeasures(nowOnlineOrderId, nowOnlineOrderName);

		JSONObject jsonObject = new JSONObject();
		
		logger.info("查询结果newCompanyMeasures:" + newCompanyMeasures);
		jsonObject.put("newCompanyMeasures", newCompanyMeasures);
	
		print(response, jsonObject.toString());
		
	}
	
}
