package com.lwh.order.entity;

import java.io.Serializable;

public class MaterialInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3252255246897381511L;
	
	private String materialId = "";
	
	private String materialName = "";

	private String materialNum = "";
	
	private String companyid = "";
	
	private String companyName = "";
	
	private String isMaterial = "";

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getIsMaterial() {
		return isMaterial;
	}

	public void setIsMaterial(String isMaterial) {
		this.isMaterial = isMaterial;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
 
}