package com.nexters.project.dto;

public class WeatherDto {

	int weather_id;
	float temperature_avg;
	String weather_status;
	int city_id;
	int month;
	
	public WeatherDto(int weather_id, float temperature_avg, String weather_status, int city_id, int month) {
		super();
		this.weather_id = weather_id;
		this.temperature_avg = temperature_avg;
		this.weather_status = weather_status;
		this.city_id = city_id;
		this.month = month;
	}
	
	public WeatherDto( ) { }
	
	public int getWeather_id() {
		return weather_id;
	}
	public void setWeather_id(int weather_id) {
		this.weather_id = weather_id;
	}
	public float getTemperature_avg() {
		return temperature_avg;
	}
	public void setTemperature_avg(float temperature_avg) {
		this.temperature_avg = temperature_avg;
	}
	public String getWeather_status() {
		return weather_status;
	}
	public void setWeather_status(String weather_status) {
		this.weather_status = weather_status;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	
	
}
