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
import com.techelevator.npgeek.JDBC.JDBCParkDAO;

public class JDBCParkDAOIntegrationTest {

	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO dao;
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
		String sqlTruncateWholeDataBase = "TRUNCATE park, survey_result, weather";
		jdbcTemplate.update(sqlTruncateWholeDataBase);

		String sqlInsertDummyPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies)"
				+ "VALUES ('JAVA', 'Glopper Park', 'Washington', '10', '30', '15.0', '1', 'Desert', '1942', '46', 'Glop or get glopped', 'Dr.Gloppington', 'It is so dry', '500', '5942')";

		jdbcTemplate.update(sqlInsertDummyPark);
		dao = new JDBCParkDAO(dataSource);
	}

	@Test
	public void getAllParksTest() {
		List<Park> testParks = dao.getAllParks();
		assertNotNull(testParks);
		assertEquals(1, testParks.size());
		assertEquals("JAVA", testParks.get(0).getParkCode());
	}

	@Test
	public void getParkByParkCodeTest() {
		Park testPark = dao.getParkByParkCode("JAVA");
		assertNotNull(testPark);
		assertEquals("Glopper Park", testPark.getParkName());
		assertEquals("Washington", testPark.getState());
	}

}
