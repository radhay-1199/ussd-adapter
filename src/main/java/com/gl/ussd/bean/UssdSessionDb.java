package com.gl.ussd.bean;


public class UssdSessionDb {

	public String msisdn;
	public String dlgId;
	public String shortCode;
	public String sessionId;
	public String date;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getDlgId() {
		return dlgId;
	}
	public void setDlgId(String dlgId) {
		this.dlgId = dlgId;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public UssdSessionDb(String msisdn,String dlgId,String shortCode,String sessionId,String date){
		this.msisdn=msisdn;
		this.dlgId=dlgId;
		this.shortCode=shortCode;
		this.sessionId=sessionId;
		this.date=date;
	}
	
	@Override
	public String toString() {
		return "UssdSessionDb [msisdn=" + msisdn + ", dlgId=" + dlgId + ", shortCode=" + shortCode + ", sessionId="
				+ sessionId + ", date=" + date + "]";
	}
	
}
