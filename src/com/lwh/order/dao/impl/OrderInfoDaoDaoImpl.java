package com.lwh.order.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.lwh.order.dao.OrderInfoDao;
import com.lwh.order.entity.OrderProductInfo;
import com.lwh.order.entity.ProductLineInfo;


@Component
public class OrderInfoDaoDaoImpl implements OrderInfoDao {
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryOrderInfoList() {
		
		return sqlMapClientTemplate.queryForList("queryOrderInfoList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProductInfo> queryOrderProductInfoList(String orderNo) {

		return sqlMapClientTemplate.queryForList("queryOrderProductInfoList", orderNo);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryMaterialInfoList(String orderId) {
		if(orderId == null){
			return sqlMapClientTemplate.queryForList("queryMaterialInfoList");
		}
		return sqlMapClientTemplate.queryForList("queryMaterialInfoList_forOrder");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductLineInfo> queryProductLineList(String available_type) {

		if(available_type == null){
			return sqlMapClientTemplate.queryForList("queryProductLineList");
		}
		
		return sqlMapClientTemplate.queryForList("queryProductLineList_available", available_type);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryMachineList(String orderId) {
		if(orderId == null){
			return sqlMapClientTemplate.queryForList("queryMachineList");
		}
		return sqlMapClientTemplate.queryForList("queryMachineList_available", orderId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryMachineFunctionList(String orderId) {
		if(orderId == null){
			return sqlMapClientTemplate.queryForList("queryMachineFunctionList");
		}
		return sqlMapClientTemplate.queryForList("queryMachineFunctionList_forOrder", orderId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryAccessoryList(String orderId) {
		if(orderId == null){
			return sqlMapClientTemplate.queryForList("queryAccessoryList");
		}
		return sqlMapClientTemplate.queryForList("queryAccessoryList_forOrder", orderId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryWaitingTasklist(String orderId) {
		
		return sqlMapClientTemplate.queryForList("queryWaitingTasklist", orderId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryMachineFunctionListForPd(String productId) {
		
		return sqlMapClientTemplate.queryForList("queryMachineFunctionListForPd", productId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryMachineListForPd(String productId) {
		
		return sqlMapClientTemplate.queryForList("queryMachineListForPd", productId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryOrderPrdFactorList(String orderId) {
		
		return sqlMapClientTemplate.queryForList("queryOrderPrdFactorList", orderId);
	}

	@Override
	public void updateOnlineOrder(String nowOnlineOrderId) {
	
		sqlMapClientTemplate.update("updateOnlineOrderOff");
		
		sqlMapClientTemplate.update("updateOnlineOrder", nowOnlineOrderId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryNullPrdList(String nowOnlineOrderId) {
		
		return sqlMapClientTemplate.queryForList("queryNullPrdList", nowOnlineOrderId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> queryPrdAccessoryList(String nowOnlineOrderId) {

		return sqlMapClientTemplate.queryForList("queryPrdAccessoryList", nowOnlineOrderId);
	}

	@Override
	public List<HashMap> queryPrdBuyAccessoryList(String nowOnlineOrderId) {
		
		return sqlMapClientTemplate.queryForList("queryPrdBuyAccessoryList", nowOnlineOrderId);
	}

	
}
