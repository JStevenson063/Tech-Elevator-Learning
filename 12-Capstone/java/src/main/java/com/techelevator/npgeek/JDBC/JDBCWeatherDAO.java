package com.techelevator.npgeek.JDBC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.WeatherDAO;

@Component
public class JDBCWeatherDAO implements WeatherDAO {
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		List<Weather> weatherList = new ArrayList<>();
		String sqlGetWeatherByParkCode = "SELECT parkcode, fivedayforecastvalue, low, high, forecast " + "FROM weather "
				+ "WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherByParkCode, parkCode);
		while (results.next()) {
			Weather weather = mapRowToWeather(results);

			weatherList.add(weather);
		}

		return weatherList;
	}

	private Weather mapRowToWeather(SqlRowSet results) {
		Weather weather = new Weather();
		weather.setParkCode(results.getString("parkcode"));
		weather.setFiveDayForecastValue(LocalDate.now());
		weather.setTemperatureLow(results.getDouble("low"));
		weather.setTemperatureHigh(results.getDouble("high"));
		weather.setForecast(results.getString("forecast"));
		return weather;
	}

}
