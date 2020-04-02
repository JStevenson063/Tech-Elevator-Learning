package com.techelevator.npgeek.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.PopularPark;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.SurveyDAO;

@Component
public class JDBCSurveyDAO implements SurveyDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveSurvey(Survey survey) {
		String sqlInsertSurvey = "INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) "
				+ "VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertSurvey, survey.getParkCode(), survey.getEmail(), survey.getState(),
				survey.getActivityLevel());

	}

	@Override
	public List<Survey> getAllSurveys() {
		List<Survey> surveyList = new ArrayList<>();
		String sqlGetAllSurveys = "SELECT surveyid, parkcode, emailaddress, state, activitylevel "
				+ "FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllSurveys);
		while (results.next()) {
			Survey survey = new Survey();
			survey.setParkCode(results.getString("parkcode"));
			survey.setEmail(results.getString("emailaddress"));
			survey.setState(results.getString("activitylevel"));
			surveyList.add(survey);
		}
		return surveyList;
	}

	@Override
	public List<PopularPark> getParksBySurveyCount() {
		List<PopularPark> popularParkList = new ArrayList<>();
		String sqlGetParksBySurveyCount = "SELECT park.parkname, park.parkcode, COUNT(survey_result.parkcode) "
				+ "FROM survey_result " + "JOIN park ON park.parkcode = survey_result.parkcode "
				+ "GROUP BY park.parkname, park.parkcode " + "ORDER BY COUNT DESC, park.parkname";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParksBySurveyCount);
		while (results.next()) {
			PopularPark popularPark = new PopularPark();
			popularPark.setParkName(results.getString("parkname"));
			popularPark.setParkCode(results.getString("parkcode"));
			popularPark.setSurveyCount(results.getInt("COUNT"));
			popularParkList.add(popularPark);
		}
		return popularParkList;
	}

}
