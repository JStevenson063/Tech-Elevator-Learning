package com.techelevator.npgeek;

import java.time.LocalDate;

public class Weather {

	private String parkCode;
	private LocalDate fiveDayForecastValue;
	private double temperatureLow;
	private double temperatureHigh;
	private String forecast;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public LocalDate getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}

	public void setFiveDayForecastValue(LocalDate fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

	public double getTemperatureLow() {
		return temperatureLow;
	}

	public void setTemperatureLow(double temperatureLow) {
		this.temperatureLow = temperatureLow;
	}

	public double getTemperatureHigh() {
		return temperatureHigh;
	}

	public void setTemperatureHigh(double temperatureHigh) {
		this.temperatureHigh = temperatureHigh;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getRecommendationFahrenheit() {
		String recommendation = "";
		if (temperatureHigh > 75) {
			recommendation = " Pack an extra gallon of water! It's going to be hot!";
		}
		if (temperatureHigh - temperatureLow > 20) {
			recommendation += " Wear breathable layers! The temperature will vary today.";
		}
		if (temperatureLow < 20) {
			recommendation += " Freezing temperatures! Exercise caution while visiting.";
		}
		if (forecast.equalsIgnoreCase("snow")) {
			recommendation += " Pack your snowshoes! Snow is coming.";
		}
		if (forecast.equalsIgnoreCase("rain")) {
			recommendation += " Pack your ponchos and rainboots! Rain is coming.";
		}
		if (forecast.equalsIgnoreCase("thunderstorms")) {
			recommendation += " Danger! Seek shelter and avoid hiking on exposed ridges, thunderstorms in the area.";
		}
		if ((forecast.equalsIgnoreCase("sunny"))) {
			recommendation += " Pack your sunscreen! It's a beautiful day!";
		}

		return recommendation;
	}

	public String getRecommendationCelcius() {
		String recommendation = "";
		if (temperatureHigh > 23.8889) {
			recommendation = " Pack an extra gallon of water! It's going to be hot!";
		}
		if (temperatureHigh - temperatureLow > -6.66667) {
			recommendation += " Wear breathable layers! The temperature will vary today.";
		}
		if (temperatureLow < -6.66667) {
			recommendation += " Freezing temperatures! Exercise caution while visiting.";
		}
		if (forecast.equalsIgnoreCase("snow")) {
			recommendation += " Pack your snowshoes! Snow is coming.";
		}
		if (forecast.equalsIgnoreCase("rain")) {
			recommendation += " Pack your ponchos and rainboots! Rain is coming.";
		}
		if (forecast.equalsIgnoreCase("thunderstorms")) {
			recommendation += " Danger! Seek Shelter and avoid hiking on exposed ridges, thunderstorms in the area.";
		}
		if ((forecast.equalsIgnoreCase("sunny"))) {
			recommendation += " Pack your sunscreen! It's a beautiful day!";
		}

		return recommendation;
	}

	public String getCelciusHigh() {
		temperatureHigh = (temperatureHigh - 32) / 1.8;
		String highTemperature = String.format("%.2f", temperatureHigh);
		return highTemperature;
	}

	public String getCelciusLow() {
		temperatureLow = (temperatureLow - 32) / 1.8;
		String lowTemperature = String.format("%.2f", temperatureLow);
		return lowTemperature;
	}
}
