package com.gl.ussd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.gl.ussd.bean.StateInfo;

@Component
public class StateInfoDao {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<StateInfo> loadFlowFromDB(String serviceId) {
		try {
			logger.info("Getting information from state_info table");
			String query="select serviceid , sequencenumber , currentstateid , eventid , actionid , nextstate , timeout from state_info where serviceid ='" + serviceId + "'";
			logger.info("query: "+query);
			return jdbcTemplate.query(query,new ResultSetExtractor<List<StateInfo>>(){  
			    @Override  
			     public List<StateInfo> extractData(ResultSet rs) throws SQLException,  
			            DataAccessException {  
			      List<StateInfo> stateInfoList=new ArrayList<StateInfo>();  
			        while(rs.next()){  
			        	StateInfo stateInfo=new StateInfo(); 
			        	stateInfo.setEventId(rs.getString("eventid"));
			        	stateInfo.setNextStateId(rs.getString("nextstate"));
			        	stateInfo.setTimeout(rs.getInt("timeout"));
			        	stateInfo.setActionId(rs.getString("actionid"));	
			        	stateInfoList.add(stateInfo);
			        }  
			        return stateInfoList;  
			        }  
			    });  
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in loading flow from state_info table");
			e.printStackTrace();
		}
		return null;
	}
	public String getEventMessage(String serviceId, int seqNumber) throws Exception {
	    //String eventMsg = null;
	    try {
	    	String query = "select parametervalue from action_relation where serviceid='" + serviceId + "' and sequencenumber=" + seqNumber;
	    	logger.info("query: "+query);
	    	return jdbcTemplate.query(query,new ResultSetExtractor<String>(){
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					String eventMsg = null;
					while(rs.next()) {
						 eventMsg = rs.getString("parametervalue");
					}
					logger.info("Event Message for the current action: "+eventMsg);
					return eventMsg;
				}  
			    });  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return null;
	  }
}
