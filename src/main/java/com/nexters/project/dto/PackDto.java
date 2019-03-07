package com.nexters.project.dto;

public class PackDto {

	int pId;
	int pcId;
	String pName;
	String pColor;
	String pCheck;
	
	public PackDto(int pId, int pcId, String pName, String pColor, String pCheck) {
		super();
		this.pId = pId;
		this.pcId = pcId;
		this.pName = pName;
		this.pColor = pColor;
		this.pCheck = pCheck;
	}
	
	public PackDto( ){ }

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getPcId() {
		return pcId;
	}

	public void setPcId(int pcId) {
		this.pcId = pcId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpColor() {
		return pColor;
	}

	public void setpColor(String pColor) {
		this.pColor = pColor;
	}

	public String getpCheck() {
		return pCheck;
	}

	public void setpCheck(String pCheck) {
		this.pCheck = pCheck;
	}
}
