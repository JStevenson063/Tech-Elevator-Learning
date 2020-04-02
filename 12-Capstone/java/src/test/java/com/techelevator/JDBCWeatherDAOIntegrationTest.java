package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;
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
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.JDBC.JDBCParkDAO;
import com.techelevator.npgeek.JDBC.JDBCWeatherDAO;

public class JDBCWeatherDAOIntegrationTest {

	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
	private static SingleConnectionDataSource dataSource;
	private JDBCWeatherDAO dao;
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
		dao = new JDBCWeatherDAO(dataSource);
	}

	@Test
	public void getWeatherByParkCodeTest() {
		List<Weather> testWeather = dao.getWeatherByParkCode("GTNP");
		assertNotNull(testWeather);
		assertEquals(5, testWeather.size());
		assertEquals("cloudy", testWeather.get(0).getForecast());
		assertNotNull(testWeather.get(0).getFiveDayForecastValue());
		assertEquals(LocalDate.now(), testWeather.get(0).getFiveDayForecastValue());
	}

}
