package com.gl.ussd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ussd.bean.UssdSessionDb;
import com.gl.ussd.dao.UssdSessionDao;

@Service
public class UssdSessionService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UssdSessionDao ussdSessionDao;

	public int insertDetails(String msisdn, String dlg_id, String short_code, String session_id, String date_time) {
		logger.info("populating constructor UssdSessionDb");
		UssdSessionDb ussdSessionDb = new UssdSessionDb(msisdn,dlg_id,short_code,session_id,date_time);
		return ussdSessionDao.insertDb(ussdSessionDb);
	}

	public boolean checkDetails(String msisdn) {
		// TODO Auto-generated method stub
		logger.info("checking for existing entry with same msisdn");
		return ussdSessionDao.checkDb(msisdn);
	}
	
	
}
