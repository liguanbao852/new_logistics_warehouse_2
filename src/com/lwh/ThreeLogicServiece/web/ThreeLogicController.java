package com.lwh.ThreeLogicServiece.web;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwh.common.web.BaseController;
import com.lwh.order.service.OrderInfoService;


@Controller
@RequestMapping("/factor")
public class ThreeLogicController extends BaseController {

	@Autowired
	private OrderInfoService orderInfoServiceImpl;
	
	// 可用的设备功能
	@RequestMapping("/availableFuction")
	public void availableFuction(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("availableFuction begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "可用的设备功能");

		String orderId = request.getParameter("orderId");
		List<HashMap> machineFunctionList = orderInfoServiceImpl.queryMachineFunctionList(orderId);
		logger.info("查询结果machineFunctionList:" + machineFunctionList);
		
		jsonObject.put("list", machineFunctionList);
		
		print(response, jsonObject.toString());
	}
	
	// 可用的设备
	@RequestMapping("/availableMachine")
	public void availableMachine(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("availableMachine begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "可用的设备");
		
		String orderId = request.getParameter("orderId");
		List<HashMap> machineList = orderInfoServiceImpl.queryMachineList(orderId);
		logger.info("查询结果machineList:" + machineList);
		jsonObject.put("list", machineList);	
		
		print(response, jsonObject.toString());
	}
	
	// 可用的生产物料
	@RequestMapping("/availableMaterial")
	public void availableMaterial(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("availableMaterial begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "可用的生产物料");
		
		String orderId = request.getParameter("orderId");
		List<HashMap> materialList = orderInfoServiceImpl.queryMaterialInfoList(orderId);	
		logger.info("查询结果materialList:" + materialList);
		jsonObject.put("list", materialList);	
		
		print(response, jsonObject.toString());
	}
	
	// 可用的生产配件
	@RequestMapping("/availableAccessory")
	public void availableAccessory(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("availableAccessory begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "可用的生产配件");
		
		String orderId = request.getParameter("orderId");
		List<HashMap> accessoryList = orderInfoServiceImpl.queryAccessoryList(orderId);	
		logger.info("查询结果accessoryList:" + accessoryList);
		jsonObject.put("list", accessoryList);	
		
		print(response, jsonObject.toString());
	}
	
	
	
	// 产线是否构成
	@RequestMapping("/isProductlineOk")
	public void isProductlineOk(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("isProductlineOk begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "产线是否构成");
		jsonObject.put("result", true);	
		
		print(response, jsonObject.toString());
	}
	
	
	// 人力是否足够
	@RequestMapping("/isHumanCostOk")
	public void isHumanCostOk(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("isHumanCostOk begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "人力是否足够");
		jsonObject.put("result", true);	
		
		print(response, jsonObject.toString());
	}
	
	
	// 物流是否支持
	@RequestMapping("/isLogisticsOk")
	public void isLogisticsOk(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("isLogisticsOk begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "物流是否支持");
		jsonObject.put("result", true);	
		
		print(response, jsonObject.toString());
	}
	
	
	// 新产线是否影响其他产线
	@RequestMapping("/isChangeOtherPl")
	public void isChangeOtherPl(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("isLogisticsOk begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "新产线是否影响其他产线");
		
		Random random = new Random();
		int rd = random.nextInt();
		
		if(rd%2 == 0){
			jsonObject.put("result", true);
		}
		else{
			jsonObject.put("result", false);
		}
				
		print(response, jsonObject.toString());
	}
	
	
	// 待生产的任务列表
	@RequestMapping("/waitingTasklist")
	public void waitingTasklist(HttpServletRequest request, HttpServletResponse response) {
		
		logger.info("waitingTasklist begin");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "待生产的任务列表");
		
		String orderId = request.getParameter("orderId");
		List<HashMap> waitingTasklist = orderInfoServiceImpl.queryWaitingTasklist(orderId);	
		logger.info("查询结果waitingTasklist:" + waitingTasklist);
		jsonObject.put("list", waitingTasklist);	
		
		print(response, jsonObject.toString());
	}
		
	
	
	
	
	
	
}
