package com.wise.dto;

public class CecosDto {
	private String co_area;
	private String kostl;
	private String name;
	private String descript;
	private String act_state;
	public String getCo_area() {
		return co_area;
	}
	public void setCo_area(String co_area) {
		this.co_area = co_area;
	}
	public String getKostl() {
		return kostl;
	}
	public void setKostl(String kostl) {
		this.kostl = kostl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getAct_state() {
		return act_state;
	}
	public void setAct_state(String act_state) {
		this.act_state = act_state;
	}
	@Override
	public String toString() {
		return "CecosDto [co_area=" + co_area + ", costcenter=" + kostl
				+ ", name=" + name + ", descript=" + descript + ", act_state="
				+ act_state + "]";
	}
	

}
