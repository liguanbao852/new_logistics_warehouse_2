package com.lwh.order.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3252255246897381511L;
	
	private String orderNo = "";
	
	private String orderName = "";

	private String custId = "";
	
	private String custName = "";
	
	private Date orderBeginDate;

	private Date orderEndDate;
	
    private String isFull = "0";
	
    private List<OrderProductInfo> OrderProductList = new ArrayList<OrderProductInfo>();

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getIsFull() {
		return isFull;
	}

	public void setIsFull(String isFull) {
		this.isFull = isFull;
	}

	public List<OrderProductInfo> getOrderProductList() {
		return OrderProductList;
	}

	public void setOrderProductList(List<OrderProductInfo> orderProductList) {
		OrderProductList = orderProductList;
	}

	public Date getOrderBeginDate() {
		return orderBeginDate;
	}

	public void setOrderBeginDate(Date orderBeginDate) {
		this.orderBeginDate = orderBeginDate;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	
	
 
}