package com.lwh.order.entity;

import java.io.Serializable;

public class MachineInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3252255246897381511L;
	
	private String machineId = "";
	
	private String machineName = "";

	private String belongProductLine = "";
	
	private String isAvailable = "";

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getBelongProductLine() {
		return belongProductLine;
	}

	public void setBelongProductLine(String belongProductLine) {
		this.belongProductLine = belongProductLine;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	
 
}