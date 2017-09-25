package com.lwh.order.entity;

import java.io.Serializable;

public class MachineFunctionInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4652807032774123523L;

	private String machineFunctionId = "";
	
	private String machineFunctionName = "";

	private String machineId = "";
	
	private String machineName = "";
	
	private double elapsedTime = 0.0;
	
	private double machineCost = 0.0;
	
	private double humanCost = 0.0;
	
	private String isAvailable = "";

	public String getMachineFunctionId() {
		return machineFunctionId;
	}

	public void setMachineFunctionId(String machineFunctionId) {
		this.machineFunctionId = machineFunctionId;
	}

	public String getMachineFunctionName() {
		return machineFunctionName;
	}

	public void setMachineFunctionName(String machineFunctionName) {
		this.machineFunctionName = machineFunctionName;
	}

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

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public double getMachineCost() {
		return machineCost;
	}

	public void setMachineCost(double machineCost) {
		this.machineCost = machineCost;
	}

	public double getHumanCost() {
		return humanCost;
	}

	public void setHumanCost(double humanCost) {
		this.humanCost = humanCost;
	}
 
}