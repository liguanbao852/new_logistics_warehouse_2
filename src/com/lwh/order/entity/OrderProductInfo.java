package com.lwh.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderProductInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5627660999593890692L;

	private String orderNo = "";
	
	private String productId = "";

	private String productName = "";
	
	private int productNum = 0;

    private int productCnt = 0;
    
    private BigDecimal productPrice;

    private String isFull = "0";

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getProductCnt() {
		return productCnt;
	}

	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getIsFull() {
		return isFull;
	}

	public void setIsFull(String isFull) {
		this.isFull = isFull;
	}
	
	
}