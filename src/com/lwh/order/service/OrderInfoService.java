package com.lwh.order.service;

import java.util.HashMap;
import java.util.List;

import com.lwh.order.entity.ProductLineInfo;




public interface OrderInfoService {

	List<HashMap> queryOrderInfoList();

	List<HashMap> queryMaterialInfoList(String orderId);

	List<ProductLineInfo> queryProductLineList(String orderId);

	List<HashMap> queryMachineList(String orderId);

	List<HashMap> queryMachineFunctionList(String orderId);

	List<HashMap> queryAccessoryList(String orderId);

	List<HashMap> queryMaterialInfoListAll(String orderId);

	List<HashMap> queryWaitingTasklist(String orderId);

	List<HashMap> queryNewOrderList();

	void updateOnlineOrder(String nowOnlineOrderId);

	String queryNewProductMeasures(String nowOnlineOrderId, String nowOnlineOrderName);

	String queryNewProductMaterial(String nowOnlineOrderId,
			String nowOnlineOrderName);

	String queryNewCompanyMeasures(String nowOnlineOrderId,
			String nowOnlineOrderName);

	

	
	
	
	


}
