package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.ParkDAO;
import com.techelevator.npgeek.PopularPark;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyDAO;
import com.techelevator.npgeek.TemperaturePreference;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.WeatherDAO;

@Controller
@SessionAttributes("tempChoice")
public class HomeController {

	@Autowired
	private ParkDAO parkDao;

	@Autowired
	private WeatherDAO weatherDao;

	@Autowired
	private SurveyDAO surveyDao;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayHomePage(ModelMap map) {
		List<Park> parks = parkDao.getAllParks();
		map.addAttribute("parks", parks);
		return "home";
	}

	@RequestMapping(path = "/park/details", method = RequestMethod.GET)
	public String displayParkDetail(HttpServletRequest request, ModelMap map) {
		if (!map.containsAttribute("tempChoice")) {
			TemperaturePreference tempPref = new TemperaturePreference();
			tempPref.setTempChoice("Fahrenheit");
			map.addAttribute("tempChoice", tempPref);
		}

		String parkCode = request.getParameter("parkCode");
		Park park = parkDao.getParkByParkCode(parkCode);
		List<Weather> weatherList = weatherDao.getWeatherByParkCode(request.getParameter("parkCode"));
		request.setAttribute("park", park);
		request.setAttribute("weather", weatherList);

		return "details";
	}

	@RequestMapping(path = "/park/details", method = RequestMethod.POST)
	public String chooseTemperatureDisplay(ModelMap map, @RequestParam String tempSelect,
			@RequestParam String parkCode) {
		TemperaturePreference tempPref = new TemperaturePreference();
		tempPref.setTempChoice(tempSelect);
		map.addAttribute("tempChoice", tempPref);
		map.addAttribute("parkCode", parkCode);

		return "redirect:/park/details";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String displaySurveyPage(ModelMap map) {
		List<Park> parks = parkDao.getAllParks();
		map.addAttribute("parks", parks);
		if (!map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		return "survey";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, ModelMap map) {

		if (result.hasErrors()) {
			List<Park> parks = parkDao.getAllParks();
			map.addAttribute("parks", parks);
			return "survey";
		} else {
			surveyDao.saveSurvey(survey);
		}

		return "redirect:/survey/confirmation";
	}

	@RequestMapping(path = "/survey/confirmation", method = RequestMethod.GET)
	public String displaySurveyConfirmation(ModelMap map, HttpServletRequest request) {
		List<PopularPark> popularParksList = surveyDao.getParksBySurveyCount();
		map.addAttribute("popularParks", popularParksList);
		return "confirmation";
	}

}
