package com.techelevator.npgeek;

import java.util.List;

public interface WeatherDAO {

	public List<Weather> getWeatherByParkCode(String parkCode);

}
