package com.lwh.order.entity;

import java.io.Serializable;

public class ProductLineInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3252255246897381511L;
	
	private String productLineId = "";
	
	private String productLineName = "";

	private String productLineStatus = "";

	public String getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(String productLineId) {
		this.productLineId = productLineId;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	public String getProductLineStatus() {
		return productLineStatus;
	}

	public void setProductLineStatus(String productLineStatus) {
		this.productLineStatus = productLineStatus;
	}
	
 
}