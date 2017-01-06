package com.wise.dto;

public class CostDistDto {
	String EXPENSELINE;
	String DISTLIN;
	String KOSTL;
	String COST_PERCENT;
	String APPDMBTR;
	String OPERFLAG;
	String name;
	String co_area;
	String descript;
	String act_state;
	 
	public CostDistDto() {
		this.EXPENSELINE="";
		this.DISTLIN="";
		this.KOSTL="";
		this.COST_PERCENT="";
		this.APPDMBTR="";
		this.OPERFLAG="";
		this.name="";
		this.co_area="";
		this.descript="";
		this.act_state="";
	}
	public String getEXPENSELINE() {
		return EXPENSELINE;
	}
	public void setEXPENSELINE(String eXPENSELINE) {
		EXPENSELINE = eXPENSELINE;
	}
	public String getDISTLIN() {
		return DISTLIN;
	}
	public void setDISTLIN(String dISTLIN) {
		DISTLIN = dISTLIN;
	}
	public String getKOSTL() {
		return KOSTL;
	}
	public void setKOSTL(String kOSTL) {
		KOSTL = kOSTL;
	}
	public String getCOST_PERCENT() {
		return COST_PERCENT;
	}
	public void setCOST_PERCENT(String cOST_PERCENT) {
		COST_PERCENT = cOST_PERCENT;
	}
	public String getAPPDMBTR() {
		return APPDMBTR;
	}
	public void setAPPDMBTR(String aPPDMBTR) {
		APPDMBTR = aPPDMBTR;
	}
	public String getOPERFLAG() {
		return OPERFLAG;
	}
	public void setOPERFLAG(String oPERFLAG) {
		OPERFLAG = oPERFLAG;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCo_area() {
		return co_area;
	}
	public void setCo_area(String co_area) {
		this.co_area = co_area;
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
	
	

}
