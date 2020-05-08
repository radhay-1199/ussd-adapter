package com.gl.ussd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.gl.ussd.bean.UssdSessionDb;

@Component
public class UssdSessionDao {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertDb(UssdSessionDb ussdSessionDb) {
		try {
			logger.info("inserting into ussd_session_db table");
			String query="insert into ussd_Session_db(msisdn,dlg_id,short_code,session_id,date) values(?,?,?,?,?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(query, new String[] {"id"});
							ps.setString(1, ussdSessionDb.getMsisdn());
							ps.setString(2, ussdSessionDb.getDlgId());
							ps.setString(3, ussdSessionDb.getShortCode());
							ps.setString(4, ussdSessionDb.getSessionId());
							ps.setString(5, ussdSessionDb.getDate());
							return ps;
						}
					},
					keyHolder);
			
			return Integer.parseInt(""+keyHolder.getKey());
		} catch (Exception e) {
			logger.info("Exception in UssdSessionDb while inserting into ussd_session_db table");
			e.printStackTrace();
		}
		
		return 0;
	}

	public boolean checkDb(String msisdn) {
		boolean result = false;
		try {
			logger.info("checking table table ussd_session_db");
			String query="select count(*) from ussd_session_db where msisdn= ?";
			logger.info("query: "+query);
			int count = jdbcTemplate.queryForObject(query, new Object[] { msisdn}, Integer.class);
			if (count > 0) {
			      result = true;
			    }
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception during checking for duplicate entry in ussd_session_db table");
			e.printStackTrace();
		}
		return result;
	}

}
