package com.lwh.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwh.order.dao.OrderInfoDao;
import com.lwh.order.entity.OrderProductInfo;
import com.lwh.order.entity.ProductLineInfo;
import com.lwh.order.service.OrderInfoService;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private OrderInfoDao orderInfoDaoImpl;

	@Override
	public List<HashMap> queryOrderInfoList() {

		List<HashMap> orderInfoList = orderInfoDaoImpl.queryOrderInfoList();
		
		if(orderInfoList != null & orderInfoList.size() > 0){
			
			String orderNo = "";
			for(int i=0; i<orderInfoList.size(); i++){

				orderNo = (String) orderInfoList.get(i).get("order_no");
				
				// 获取订单对应产品
				List<OrderProductInfo> orderProductInfoList = orderInfoDaoImpl.queryOrderProductInfoList(orderNo);				
				orderInfoList.get(i).put("orderProductInfoList", orderProductInfoList);
				
			}	
		}

		return orderInfoList;
	}

	@Override
	public List<HashMap> queryMaterialInfoList(String orderId) {	
		
		return orderInfoDaoImpl.queryMaterialInfoList(orderId);
	}

	
	
	@Override
	public List<ProductLineInfo> queryProductLineList(String orderId) {
		
		return orderInfoDaoImpl.queryProductLineList(orderId);
	}

	@Override
	public List<HashMap> queryMachineList(String orderId) {

		return orderInfoDaoImpl.queryMachineList(orderId);
	}

	@Override
	public List<HashMap> queryMachineFunctionList(String orderId) {
	
		return orderInfoDaoImpl.queryMachineFunctionList(orderId);
		
	}

	@Override
	public List<HashMap> queryAccessoryList(String orderId) {
		
		return orderInfoDaoImpl.queryAccessoryList(orderId);
	}

	@Override
	public List<HashMap> queryMaterialInfoListAll(String orderId) {

		return orderInfoDaoImpl.queryMaterialInfoList(orderId);
	}

	@Override
	public List<HashMap> queryWaitingTasklist(String orderId) {
		
		return orderInfoDaoImpl.queryWaitingTasklist(orderId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> queryNewOrderList() {
		
		// 获取原始订单
		List<HashMap> orderInfoList = orderInfoDaoImpl.queryOrderInfoList();
		
		List<HashMap> newOrderInfoList = new ArrayList<HashMap>();
		if(orderInfoList != null & orderInfoList.size() > 0){
			String orderNo = "";			
			
			for(int i=0; i<orderInfoList.size(); i++){
				orderNo = (String) orderInfoList.get(i).get("order_no");		
				// 获取订单对应产品
				List<OrderProductInfo> orderProductInfoList = orderInfoDaoImpl.queryOrderProductInfoList(orderNo);				
				orderInfoList.get(i).put("orderProductInfoList", orderProductInfoList);
	
				if(orderProductInfoList == null || orderProductInfoList.size() == 0){
					continue;
				}
				
				// 获取订单产品所需功能是否正常
				boolean productFunctionOk=true;
				for(int j=0; j<orderProductInfoList.size(); j++){				
					if(!checkProductFunctionOk(orderProductInfoList.get(j).getProductId())){
						
						productFunctionOk=false;
						break;
					}					
				}				
				if(productFunctionOk){
					List<HashMap> orderPrdFactorList  = orderInfoDaoImpl.queryOrderPrdFactorList(orderNo);
					BigDecimal ord_pa = new BigDecimal(0);
					for(int m=0; m<orderPrdFactorList.size(); m++){
						ord_pa = ord_pa.add(new BigDecimal(String.valueOf(orderPrdFactorList.get(m).get("prd_pa"))));
					}		
					orderInfoList.get(i).put("ord_pa", ord_pa);
					
					newOrderInfoList.add(orderInfoList.get(i));
	
				}				
			}	
		}
		
		// 可生产订单排序
		if(newOrderInfoList.size() > 0){
			for(int n=0; n<newOrderInfoList.size(); n++){			
				Collections.sort(newOrderInfoList, new Comparator<HashMap<String, Object>>() {
		            public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {

		                BigDecimal ord_pa1 = new BigDecimal(String.valueOf(o1.get("ord_pa")));
		                BigDecimal ord_pa2 = new BigDecimal(String.valueOf(o2.get("ord_pa")));   
		                return ord_pa2.compareTo(ord_pa1);
		            }
		        });
			}
		
		}
	
		
		
		
		
		
		return newOrderInfoList;
	}

	private boolean checkProductFunctionOk(String productId) {
		
		List<HashMap> machineFunctionList = orderInfoDaoImpl.queryMachineFunctionListForPd(productId);
		for(int i=0; i<machineFunctionList.size(); i++){
			String is_available = (String) machineFunctionList.get(i).get("is_available");
			if(!"1".equals(is_available)){				
				return false;
			}
		}

		return true;
	}

	@Override
	public void updateOnlineOrder(String nowOnlineOrderId) {
		
		orderInfoDaoImpl.updateOnlineOrder(nowOnlineOrderId); 
	}

	@Override
	public String queryNewProductMeasures(String nowOnlineOrderId, String nowOnlineOrderName) {

		String newProductMeasures = "当前生产订单变为：" + nowOnlineOrderName 
				+", " ;

		List<HashMap> nullPrdList = orderInfoDaoImpl.queryNullPrdList(nowOnlineOrderId);
		if(nullPrdList.size() > 0){
			String nullPrdListStr = "";
			for(int i=0; i<nullPrdList.size(); i++){
				nullPrdListStr = nullPrdListStr + "、" + String.valueOf(nullPrdList.get(i).get("product_name"));			
			}			
			newProductMeasures = newProductMeasures + nowOnlineOrderName + "不含有产品：" + nullPrdListStr.substring(1) ;
		}
		
		List<HashMap> prdAccessoryList = orderInfoDaoImpl.queryPrdAccessoryList(nowOnlineOrderId);
		
		String zsPrdListStr="";
		String zsNotPrdListStr="";
		String ghPrdListStr="";
		String ghNotPrdListStr="";
		
		for(int j=0; j < prdAccessoryList.size(); j++){	
			if(String.valueOf(prdAccessoryList.get(j).get("accessory_name")).indexOf("ZS") != -1){
				if(String.valueOf(prdAccessoryList.get(j).get("is_prd")).equals("1")){
					zsPrdListStr = zsPrdListStr+"," +String.valueOf(prdAccessoryList.get(j).get("accessory_name"));
				}
				else{
					zsNotPrdListStr = zsNotPrdListStr+"," +String.valueOf(prdAccessoryList.get(j).get("accessory_name"));
				}			
			}
			else if(String.valueOf(prdAccessoryList.get(j).get("accessory_name")).indexOf("GH") != -1){
				if(String.valueOf(prdAccessoryList.get(j).get("is_prd")).equals("1")){
					ghPrdListStr = ghPrdListStr+"," +String.valueOf(prdAccessoryList.get(j).get("accessory_name"));
				}
				else{
					ghNotPrdListStr = ghNotPrdListStr+"," +String.valueOf(prdAccessoryList.get(j).get("accessory_name"));
				}
			}
		}
		
		if(zsPrdListStr.length() > 0){
			newProductMeasures = newProductMeasures + "," + "ZS产线生产变成生产：" + zsPrdListStr.substring(1);
		}
		
		if(zsNotPrdListStr.length() > 0){
			newProductMeasures = newProductMeasures + "," + "不生产：" + zsNotPrdListStr.substring(1);
		}
		
		if(ghPrdListStr.length() > 0){
			newProductMeasures = newProductMeasures +  "," + "GH产线生产变成生产：" + ghPrdListStr.substring(1);
		}
		
		if(ghNotPrdListStr.length() > 0){
			newProductMeasures = newProductMeasures + "," + "不生产：" + ghNotPrdListStr.substring(1);
		}
		
		return newProductMeasures;
	}

	@Override
	public String queryNewProductMaterial(String nowOnlineOrderId,
			String nowOnlineOrderName) {
		String newProductMaterial = "对于装配产线的影响是：";
		List<HashMap> prdBuyAccessoryList = orderInfoDaoImpl.queryPrdBuyAccessoryList(nowOnlineOrderId);
		
		for(int i=0; i<prdBuyAccessoryList.size(); i++){
			int buy_num = Integer.valueOf(String.valueOf(prdBuyAccessoryList.get(i).get("buy_num")));
			int tatal_num = Integer.valueOf(String.valueOf(prdBuyAccessoryList.get(i).get("tatal_num")));
			String accessory_name = (String) prdBuyAccessoryList.get(i).get("accessory_name");
			
			if(buy_num > 0){
				newProductMaterial =newProductMaterial + "可以适当减少" + accessory_name +"采购， 减少到" + tatal_num +"根 ，" ;
			} 
			if(buy_num < 0){
				
				int f_buy_num = buy_num*(-1);
				newProductMaterial =newProductMaterial + "需要采购更多的" + accessory_name +"，额外增加" + f_buy_num +"根 ，" ;
			}		
		}


		newProductMaterial = newProductMaterial.substring(0, newProductMaterial.length()-1) + "。";

		
		
		
		return newProductMaterial;
	}

	@Override
	public String queryNewCompanyMeasures(String nowOnlineOrderId,
			String nowOnlineOrderName) {
		String newCompanyMeasures = "";
		List<HashMap> prdBuyAccessoryList = orderInfoDaoImpl.queryPrdBuyAccessoryList(nowOnlineOrderId);
		
		for(int i=0; i<prdBuyAccessoryList.size(); i++){
			int buy_num = Integer.valueOf(String.valueOf(prdBuyAccessoryList.get(i).get("buy_num")));
			String accessory_name = String.valueOf( prdBuyAccessoryList.get(i).get("accessory_name"));
			String company_name =  String.valueOf( prdBuyAccessoryList.get(i).get("company_name"));
			
			if(buy_num < 0){
				newCompanyMeasures =newCompanyMeasures + "采购更多的" + accessory_name +"，通知供应商" + company_name  + "加大 " +accessory_name +"的生产，";
			} 
			if(buy_num > 0){
				newCompanyMeasures =newCompanyMeasures + "减少 " + accessory_name +"的采购，通知" + company_name +"公司适当的减少" +accessory_name +"的生产，";
			}		
		}

		newCompanyMeasures = newCompanyMeasures.substring(0, newCompanyMeasures.length()-1) + "。";

		return newCompanyMeasures;
	}



	
}
