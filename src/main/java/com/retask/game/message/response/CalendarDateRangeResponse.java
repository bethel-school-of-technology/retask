package com.retask.game.message.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CalendarDateRangeResponse {
    private String beginDate;
    private String endDate;
    private Date dBeginDate;
    private Date dEndDate;
    private boolean loaded;
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@JsonIgnore
	public Date getdBeginDate() {
		return dBeginDate;
	}
	@JsonIgnore
	public void setdBeginDate(Date dBeginDate) {
		this.dBeginDate = dBeginDate;
	}
	@JsonIgnore
	public Date getdEndDate() {
		return dEndDate;
	}
	@JsonIgnore
	public void setdEndDate(Date dEndDate) {
		this.dEndDate = dEndDate;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
    
	
    
}