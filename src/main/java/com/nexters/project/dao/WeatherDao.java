package com.nexters.project.dao;

import java.util.ArrayList;

import com.nexters.project.dto.WeatherDto;

public interface WeatherDao {

	public ArrayList<WeatherDto> listDao(int city_id, int month);
}
