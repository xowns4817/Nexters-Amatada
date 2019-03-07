package com.nexters.project.dao;

import java.util.ArrayList;

import com.nexters.project.dto.CarrierDto;

public interface CarrierDao {

	public ArrayList<CarrierDto> listDao(int cId);
	public void writeDao(CarrierDto dto);
	public void updateDao(String cName, int cCountry, String startDate, int cId);
	public void deleteDao(String cId);
}
