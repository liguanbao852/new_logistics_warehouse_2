package com.lwh.order.dao;

import java.util.HashMap;
import java.util.List;

import com.lwh.order.entity.MachineFunctionInfo;
import com.lwh.order.entity.MachineInfo;
import com.lwh.order.entity.MaterialInfo;
import com.lwh.order.entity.OrderInfo;
import com.lwh.order.entity.OrderProductInfo;
import com.lwh.order.entity.ProductLineInfo;




public interface OrderInfoDao {

	List<HashMap> queryOrderInfoList();

	List<OrderProductInfo> queryOrderProductInfoList(String orderNo);

	List<HashMap> queryMaterialInfoList(String orderId);

	List<ProductLineInfo> queryProductLineList(String available_type);

	List<HashMap> queryMachineList(String orderId);

	List<HashMap> queryMachineFunctionList(String orderId);

	List<HashMap> queryAccessoryList(String orderId);

	List<HashMap> queryWaitingTasklist(String orderId);

	List<HashMap> queryMachineFunctionListForPd(String productId);

	List<HashMap> queryMachineListForPd(String productId);

	List<HashMap> queryOrderPrdFactorList(String orderNo);

	void updateOnlineOrder(String nowOnlineOrderId);

	List<HashMap> queryNullPrdList(String nowOnlineOrderId);

	List<HashMap> queryPrdAccessoryList(String nowOnlineOrderId);

	List<HashMap> queryPrdBuyAccessoryList(String nowOnlineOrderId);




}
