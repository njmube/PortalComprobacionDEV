package com.wise.dto;

public class GastoDto {
	
	private String expenseClass;
	private String hkont;
	private String expenseClassDes;
	private String rultypsat;
	private String rultypbus;
	private String unit;
	public String getExpenseClass() {
		return expenseClass;
	}
	public void setExpenseClass(String expenseClass) {
		this.expenseClass = expenseClass;
	}
	public String getHkont() {
		return hkont;
	}
	public void setHkont(String hkont) {
		this.hkont = hkont;
	}
	public String getExpenseClassDes() {
		return expenseClassDes;
	}
	public void setExpenseClassDes(String expenseClassDes) {
		this.expenseClassDes = expenseClassDes;
	}
	public String getRultypsat() {
		return rultypsat;
	}
	public void setRultypsat(String rultypsat) {
		this.rultypsat = rultypsat;
	}
	public String getRultypbus() {
		return rultypbus;
	}
	public void setRultypbus(String rultypbus) {
		this.rultypbus = rultypbus;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "GastoDto [expenseClass=" + expenseClass + ", hkont=" + hkont
				+ ", expenseClassDes=" + expenseClassDes + ", rultypsat="
				+ rultypsat + ", rultypbus=" + rultypbus + ", unit=" + unit
				+ "]";
	}

}
