package com.wise.dto;

public class ListRequestDto {
	
	private String bukrs;
	private String status;
	private String startDate;
	private String endDate;
	private String request;
	private String expenseid;
	public String getBukrs() {
		return bukrs;
	}
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getExpenseid() {
		return expenseid;
	}
	public void setExpenseid(String expenseid) {
		this.expenseid = expenseid;
	}
	
}
