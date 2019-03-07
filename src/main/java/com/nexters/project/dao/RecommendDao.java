package com.nexters.project.dao;

import java.util.ArrayList;

import com.nexters.project.dto.RecommendDto;

public interface RecommendDao {

	public ArrayList<RecommendDto> listDao(int catagoryId);
}
