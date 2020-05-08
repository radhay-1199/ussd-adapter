package com.gl.ussd.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

	@Value("${shortCode}")
	private String shortCode;
	@Value("${subUrlAgr}")
	private String subUrlAgr;
	@Value("${subUrlMM}")
	private String subUrlMM;
	@Value("${kannel-url}")
	private String kannelUrl;
	
	public String getKannelUrl() {
		return kannelUrl;
	}

	public String getSubUrlAgr() {
		return subUrlAgr;
	}

	public String getSubUrlMM() {
		return subUrlMM;
	}

	public String getShortCode() {
		return shortCode;
	}
	
}
