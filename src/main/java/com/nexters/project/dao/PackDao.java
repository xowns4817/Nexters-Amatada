package com.nexters.project.dao;

import java.util.ArrayList;

import com.nexters.project.dto.PackDto;

public interface PackDao {

	public PackDto listDao(int pId);
	public ArrayList<PackDto> listAllDao(int cId, String sort);
	public void writeDao(PackDto dto);
	public void checkDao(int pId, String pCheck);
	public void updateDao(PackDto packdto);
	public void deleteDao(String pId);
}
