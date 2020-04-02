package com.techelevator.npgeek.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.ParkDAO;

@Component
public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parkList = new ArrayList<>();
		String sqlGetAllParks = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies " + "FROM park "
				+ "ORDER BY parkname";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park park = mapRowToPark(results);
			parkList.add(park);
		}
		return parkList;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park park = null;
		String sqlGetParkByParkCode = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies " + "FROM park "
				+ "WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByParkCode, parkCode);
		while (results.next()) {
			park = mapRowToPark(results);
		}
		return park;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcreage(results.getLong("acreage"));
		park.setElevationInFeet(results.getLong("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setNumberOfCampSites(results.getLong("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getLong("yearfounded"));
		park.setInspirationalQuote(results.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		park.setParkDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getLong("entryfee"));
		park.setNumberOfAnimalSpecies(results.getLong("numberofanimalspecies"));
		park.setAnnualVisitorCount(results.getLong("annualvisitorcount"));
		return park;
	}

}
