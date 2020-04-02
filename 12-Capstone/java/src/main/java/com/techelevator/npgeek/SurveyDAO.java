package com.techelevator.npgeek;

import java.util.List;

public interface SurveyDAO {

	public void saveSurvey(Survey survey);

	public List<Survey> getAllSurveys();

	public List<PopularPark> getParksBySurveyCount();
}
