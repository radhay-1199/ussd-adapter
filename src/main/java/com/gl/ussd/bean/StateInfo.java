package com.gl.ussd.bean;

public class StateInfo {
  private String serviceId = null;
  private int seqNumber = -1;
  private String currentId = null;
  private String eventId = null;
  private String actionId = null;
  private String nextStateId = null;
  private String eventMsg = null;
  private int timeout = 0;
  public StateInfo() {
	  
  }
  public StateInfo(String serviceId, int seqNumber, String currentId, String eventId, String actionId, String nextStateId, int timeout, String eventMsg) {
    this.serviceId = serviceId;
    this.seqNumber = seqNumber;
    this.currentId = currentId;
    this.eventId = eventId;
    this.actionId = actionId;
    this.nextStateId = nextStateId;
    this.timeout = timeout;
    this.eventMsg = eventMsg;
  }
public String getServiceId() {
	return serviceId;
}
public void setServiceId(String serviceId) {
	this.serviceId = serviceId;
}
public int getSeqNumber() {
	return seqNumber;
}
public void setSeqNumber(int seqNumber) {
	this.seqNumber = seqNumber;
}
public String getCurrentId() {
	return currentId;
}
public void setCurrentId(String currentId) {
	this.currentId = currentId;
}
public String getEventId() {
	return eventId;
}
public void setEventId(String eventId) {
	this.eventId = eventId;
}
public String getActionId() {
	return actionId;
}
public void setActionId(String actionId) {
	this.actionId = actionId;
}
public String getNextStateId() {
	return nextStateId;
}
public void setNextStateId(String nextStateId) {
	this.nextStateId = nextStateId;
}
public String getEventMsg() {
	return eventMsg;
}
public void setEventMsg(String eventMsg) {
	this.eventMsg = eventMsg;
}
public int getTimeout() {
	return timeout;
}
public void setTimeout(int timeout) {
	this.timeout = timeout;
}
  
 
}
