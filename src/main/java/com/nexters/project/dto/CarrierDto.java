package com.nexters.project.dto;

import java.util.ArrayList;

public class CarrierDto {

	int cId; // 캐리어 아이디 (기본키)
	String cName;
	int cCountry;
	String startDate;

	public CarrierDto(int cId, String cName, int cCountry, String startDate) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cCountry = cCountry;
		this.startDate = startDate;
	}
	
	public CarrierDto( ) { }
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getcCountry() {
		return cCountry;
	}
	public void setcCountry(int cCountry) {
		this.cCountry = cCountry;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
