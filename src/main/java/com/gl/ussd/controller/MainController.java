package com.gl.ussd.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.ussd.bean.Constants;
import com.gl.ussd.bean.StateInfo;
import com.gl.ussd.bean.VmsUser;
import com.gl.ussd.dao.StateInfoDao;
import com.gl.ussd.dao.VmsUserRepository;
import com.gl.ussd.service.SmsUtil;
import com.gl.ussd.service.UssdSessionService;







@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UssdSessionService ussdSessionService;
	@Autowired
	StateInfoDao stateInfoDao;
	@Autowired
	Constants constants;
	@Autowired
	StateInfo stateInfo;
	@Autowired
	 VmsUserRepository vmsUserRepo;
	@Autowired
	SmsUtil smsUtil;
	
	@GetMapping("/request")
	public ResponseEntity<?> ussdRequest(@RequestParam String dlg_id,@RequestParam String short_code,@RequestParam String req_text,@RequestParam String msisdn,
			@RequestParam String session_id,@RequestParam String concat_req_string,@RequestParam String date_time,@RequestParam String circle_id,
			@RequestParam String dcs,@RequestParam String curr_menu ) throws Exception{
		logger.info("dlg_id:"+dlg_id+", short_code:"+short_code+", req_text:"+req_text+", msisdn:"+msisdn+", session_id:"+session_id+", "
				+ "concat_req_string:"+concat_req_string+", date_time:"+date_time+", circle_id:"+circle_id+", dcs:"+dcs+", curr_menu:"+curr_menu);
		
		boolean alreadyInTable = ussdSessionService.checkDetails(msisdn);
		if(!alreadyInTable && constants.getShortCode().equals(req_text)) {
			logger.info("First request");
			ussdSessionService.insertDetails(msisdn,dlg_id,short_code,session_id,date_time);
			stateInfo.setServiceId(req_text);
			stateInfo.setSeqNumber(1);
			stateInfo.setCurrentId("Start");
			stateInfo.setNextStateId("MainMenu");
			String param=stateInfoDao.getEventMessage(stateInfo.getServiceId(), stateInfo.getSeqNumber());
			//check null before send content
		}
		else if((alreadyInTable && constants.getShortCode().equals(req_text)) || (!alreadyInTable && !constants.getShortCode().equals(req_text))){
			logger.info("already active session");
		}
		else if(alreadyInTable && !constants.getShortCode().equals(req_text)) {
			VmsUser user = vmsUserRepo.getUserDetails(msisdn);
			if (user != null) {
				//send message
			}
			if("1".equals(req_text)) {
				String subUrl = constants.getSubUrlAgr();
				subUrl = subUrl.replace("MSISDN", msisdn);
				subUrl = subUrl.replace("PACK", "P2");
				subUrl = subUrl.replace("CHANNEL", "USSD");
				subUrl = subUrl.replace("LANG", "1");

				logger.info(subUrl);
				String resp = CallURL(subUrl);
				logger.info("Response=" + resp);
				String param=stateInfoDao.getEventMessage(short_code, 2);
			}
			else if("2".equals(req_text)) {
				//how to know?
				//use of resp??
				String subUrl = constants.getSubUrlAgr();
				subUrl = subUrl.replace("MSISDN", msisdn);
				subUrl = subUrl.replace("PACK", "P3");
				subUrl = subUrl.replace("CHANNEL", "USSD");
				subUrl = subUrl.replace("LANG", "1");

				logger.info(subUrl);
				String resp = CallURL(subUrl);
				logger.info("Response=" + resp);
				String param=stateInfoDao.getEventMessage(short_code, 2);
			}
			else {
				String param=stateInfoDao.getEventMessage(short_code, 3);
				smsUtil.sendSMS(msisdn, param,"",0);
				//send sms??
				stateInfo.setServiceId(req_text);
				stateInfo.setSeqNumber(1);
				stateInfo.setCurrentId("Start");
				stateInfo.setNextStateId("MainMenu");
				String paramNew=stateInfoDao.getEventMessage(stateInfo.getServiceId(), stateInfo.getSeqNumber());
				// check null before send content
			}
		}
		
		return new ResponseEntity("OK",HttpStatus.OK);
	}
	
	 public String CallURL(final String urlString) {
	        String inputResponse = "";
	        try {
	            final URL url = new URL(urlString);
	            final URLConnection urlConn = url.openConnection();
	            urlConn.setConnectTimeout(30000);
	            urlConn.setReadTimeout(30000);
	            final BufferedReader inputReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	            String inputLine;
	            while ((inputLine = inputReader.readLine()) != null) {
	                inputResponse += inputLine;
	            }
	            inputReader.close();
	        }
	        catch (Exception exp) {
	            inputResponse = exp.getMessage();
	        }
	        return inputResponse;
	    }
	    
}
