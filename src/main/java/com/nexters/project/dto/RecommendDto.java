package com.nexters.project.dto;

public class RecommendDto {

	int rId;
	int rCategory; //옵션 id
	String rName; // 추천 물품 이름
	
	public RecommendDto(int rId, int rCategory, String rName) {
		super();
		this.rId = rId;
		this.rCategory = rCategory;
		this.rName = rName;
	}
	
	public RecommendDto( ) { }
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getRcategoryId() {
		return rCategory;
	}
	public void setRcategoryId(int roId) {
		this.rCategory = roId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
}
