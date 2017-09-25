package com.lwh.order.entity;

import java.io.Serializable;

public class AccessoryInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3252255246897381511L;
	
	private String accessoryId = "";
	
	private String accessoryName = "";

	private String accessoryNum = "";
	
	private String isBuy = "";

	public String getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryId(String accessoryId) {
		this.accessoryId = accessoryId;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getAccessoryNum() {
		return accessoryNum;
	}

	public void setAccessoryNum(String accessoryNum) {
		this.accessoryNum = accessoryNum;
	}

	public String getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}

}