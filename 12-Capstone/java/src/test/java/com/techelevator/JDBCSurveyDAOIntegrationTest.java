package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.PopularPark;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.JDBC.JDBCParkDAO;
import com.techelevator.npgeek.JDBC.JDBCSurveyDAO;
import com.techelevator.npgeek.JDBC.JDBCWeatherDAO;

public class JDBCSurveyDAOIntegrationTest {

	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
	private static SingleConnectionDataSource dataSource;
	private JDBCSurveyDAO dao;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	/*
	 * Before any tests are run, this method initializes the datasource for testing.
	 */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
	}

	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	@Before
	public void setup() {
		dao = new JDBCSurveyDAO(dataSource);
		String sqlTruncateWholeDataBase = "TRUNCATE park, survey_result, weather";
		jdbcTemplate.update(sqlTruncateWholeDataBase);

		String sqlInsertDummySurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
				+ "VALUES ('ENP', 'boogaloo@chocolate.gov', 'Montana', 'I dont move')";
		jdbcTemplate.update(sqlInsertDummySurvey);
	}

	@Test
	public void getAllSurveysTest() {
		List<Survey> surveyTest = dao.getAllSurveys();
		assertNotNull(surveyTest);
		assertEquals("boogaloo@chocolate.gov", surveyTest.get(0).getEmail());
	}

	@Test
	public void saveSurveyTest() {
		Survey survey = new Survey();
		survey.setState("Idaho");
		survey.setParkCode("ENP");
		survey.setEmail("boogaloo@fiesta.org");
		survey.setActivityLevel("none");
		dao.saveSurvey(survey);
		List<Survey> surveyTest = dao.getAllSurveys();
		assertEquals("boogaloo@fiesta.org", surveyTest.get(1).getEmail());
		assertEquals(2, surveyTest.size());
	}

	@Test
	public void getParksBySurveyCountTest() {
		List<PopularPark> popularParksTest = dao.getParksBySurveyCount();
		assertNotNull(popularParksTest);
		assertEquals(1, popularParksTest.size());
	}

}