package com.wise.dto;

public class CreateRequestDto {
	private String sociedad;
	private String documento;
	private String requestDate;
	private String createdDate;
	private String createdBy;
	private String comments;
	private String waers;
	private String deptcode;
	private String subdeptcode;
	
	public String getSociedad() {
		return sociedad;
	}
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "CreateRequestDto [sociedad=" + sociedad + ", documento="
				+ documento + ", requestDate=" + requestDate + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", comments="
				+ comments + "]";
	}
	public String getWaers() {
		return waers;
	}
	public void setWaers(String waers) {
		this.waers = waers;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getSubdeptcode() {
		return subdeptcode;
	}
	public void setSubdeptcode(String subdeptcode) {
		this.subdeptcode = subdeptcode;
	}
}
