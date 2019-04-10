package com.retask.game.message.request;

import java.sql.Date;
import java.sql.Timestamp;

public class DateTimeRangeRequest {
	
	private String startdate;
	private String enddate;
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}		
}