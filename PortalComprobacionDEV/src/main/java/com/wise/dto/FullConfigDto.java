package com.wise.dto;

import java.util.ArrayList;
import java.util.List;

public class FullConfigDto {
	
	private String lifrn;
	private String name1;
	private String deptcode;
	private String deptdesc;
	private String subdeptcode;
	private String subdeptdesc;
	private String subdeptkostl;
	private List<ConfigDto> configData;
	private List<ExpDocConfDto> expenseDocConf;
	private List<PaymethdDto> paymethd;
	public FullConfigDto() {				
		this.configData = new ArrayList<ConfigDto>();
		this.expenseDocConf = new ArrayList<ExpDocConfDto>();
		this.paymethd=new ArrayList<PaymethdDto>();
	}
	public String getLifrn() {
		return lifrn;
	}
	public void setLifrn(String lifrn) {
		this.lifrn = lifrn;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getDeptdesc() {
		return deptdesc;
	}
	public void setDeptdesc(String deptdesc) {
		this.deptdesc = deptdesc;
	}
	public String getSubdeptcode() {
		return subdeptcode;
	}
	public void setSubdeptcode(String subdeptcode) {
		this.subdeptcode = subdeptcode;
	}
	public String getSubdeptdesc() {
		return subdeptdesc;
	}
	public void setSubdeptdesc(String subdeptdesc) {
		this.subdeptdesc = subdeptdesc;
	}
	public String getSubdeptkostl() {
		return subdeptkostl;
	}
	public void setSubdeptkostl(String subdeptkostl) {
		this.subdeptkostl = subdeptkostl;
	}
	
	public List<PaymethdDto> getPaymethd() {
		return paymethd;
	}
	public void setPaymethd(List<PaymethdDto> paymethd) {
		this.paymethd = paymethd;
	}
	@Override
	public String toString() {
		return "FullConfigDto [lifrn=" + lifrn + ", name1=" + name1
				+ ", deptcode=" + deptcode + ", deptdesc=" + deptdesc
				+ ", subdeptcode=" + subdeptcode + ", subdeptdesc="
				+ subdeptdesc + ", subdeptkostl=" + subdeptkostl
				+ ", configData=" + configData + "]";
	}
	public List<ConfigDto> getConfigData() {
		return configData;
	}
	public void setConfigData(List<ConfigDto> configData) {
		this.configData = configData;
	}
	public List<ExpDocConfDto> getExpenseDocConf() {
		return expenseDocConf;
	}
	public void setExpenseDocConf(List<ExpDocConfDto> expenseDocConf) {
		this.expenseDocConf = expenseDocConf;
	}

}
