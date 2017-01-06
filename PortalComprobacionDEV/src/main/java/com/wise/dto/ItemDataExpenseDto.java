package com.wise.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemDataExpenseDto {
	private String BUKRS;
	private String EXPENSELINE;
	private String EXPENSETYPE;
	private String EXPENSECLASS;
	private String EXPENSECLASSDES;
	private String EXPENQUAN;
	private String UNIT;
	private String select;
	private String PAYMET;
	private String WRBTR;
	private String WAERS;
	private String MWSKZ;
	private String KURSF;
	private String BLDAT;
	private String KOSTL;
	private String XMLICON;
	private String XMLINS;
	private String PDFICON;
	private String PDFINS;
	private String UUID;
	private String RFC;
	private String DMBTR;
	private String HWBAS;
	private String HWSTE;
	private String BUDGET_DMBTR;
	private String AVAILABLE_DMBTR;
	private String RULTYPSAT;
	private String CALCTYPESAT;
	private String PERCENT_SAT;
	private String LIMIT_SAT;
	private String DEDUAMOUNT;
	private String RULTYPBUS;
	private String CALCTYPEBUS;
	private String PERCENT_BUS;
	private String LIMIT_BUS;
	private String APPRAMOUNT;
	private String HKONT;
	private String OPERFLAG;
	private String XMLXSTRING;
	private String PDFXSTRING;
	private String pdf;
	private String xml;
	private String numfact;
	private String waers_xml;
	private String mwskz_xml;
	private List<CostDistDto> COSTDIST;
	private List<AccDataDto> ACCDATA;
	private List<CellDto> CELL;
	public ItemDataExpenseDto(){
		this.COSTDIST = new ArrayList<CostDistDto>();
		this.ACCDATA = new ArrayList<AccDataDto>();
		this.CELL = new ArrayList<CellDto>();
	}
	
	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getNumfact() {
		return numfact;
	}

	public void setNumfact(String numfact) {
		this.numfact = numfact;
	}

	public String getBUKRS() {
		return BUKRS;
	}
	public void setBUKRS(String bUKRS) {
		BUKRS = bUKRS;
	}
	public String getEXPENSELINE() {
		return EXPENSELINE;
	}
	public void setEXPENSELINE(String eXPENSELINE) {
		EXPENSELINE = eXPENSELINE;
	}
	public String getEXPENSETYPE() {
		return EXPENSETYPE;
	}
	public void setEXPENSETYPE(String eXPENSETYPE) {
		EXPENSETYPE = eXPENSETYPE;
	}
	public String getEXPENSECLASS() {
		return EXPENSECLASS;
	}
	public void setEXPENSECLASS(String eXPENSECLASS) {
		EXPENSECLASS = eXPENSECLASS;
	}
	public String getEXPENSECLASSDES() {
		return EXPENSECLASSDES;
	}
	public void setEXPENSECLASSDES(String eXPENSECLASSDES) {
		EXPENSECLASSDES = eXPENSECLASSDES;
	}
	public String getEXPENQUAN() {
		return EXPENQUAN;
	}
	public void setEXPENQUAN(String eXPENQUAN) {
		EXPENQUAN = eXPENQUAN;
	}
	public String getUNIT() {
		return UNIT;
	}
	public void setUNIT(String uNIT) {
		UNIT = uNIT;
	}
	public String getPAYMET() {
		return PAYMET;
	}
	public void setPAYMET(String pAYMET) {
		PAYMET = pAYMET;
	}
	public String getWRBTR() {
		return WRBTR;
	}
	public void setWRBTR(String wRBTR) {
		WRBTR = wRBTR;
	}
	public String getWAERS() {
		return WAERS;
	}
	public void setWAERS(String wAERS) {
		WAERS = wAERS;
	}
	public String getMWSKZ() {
		return MWSKZ;
	}
	public void setMWSKZ(String mWSKZ) {
		MWSKZ = mWSKZ;
	}
	public String getKURSF() {
		return KURSF;
	}
	public void setKURSF(String kURSF) {
		KURSF = kURSF;
	}
	public String getBLDAT() {
		return BLDAT;
	}
	public void setBLDAT(String bLDAT) {
		BLDAT = bLDAT;
	}
	public String getKOSTL() {
		return KOSTL;
	}
	public void setKOSTL(String kOSTL) {
		KOSTL = kOSTL;
	}
	public String getXMLICON() {
		return XMLICON;
	}
	public void setXMLICON(String xMLICON) {
		XMLICON = xMLICON;
	}
	public String getXMLINS() {
		return XMLINS;
	}
	public void setXMLINS(String xMLINS) {
		XMLINS = xMLINS;
	}
	public String getPDFICON() {
		return PDFICON;
	}
	public void setPDFICON(String pDFICON) {
		PDFICON = pDFICON;
	}
	public String getPDFINS() {
		return PDFINS;
	}
	public void setPDFINS(String pDFINS) {
		PDFINS = pDFINS;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public String getDMBTR() {
		return DMBTR;
	}
	public void setDMBTR(String dMBTR) {
		DMBTR = dMBTR;
	}
	public String getHWBAS() {
		return HWBAS;
	}
	public void setHWBAS(String hWBAS) {
		HWBAS = hWBAS;
	}
	public String getHWSTE() {
		return HWSTE;
	}
	public void setHWSTE(String hWSTE) {
		HWSTE = hWSTE;
	}
	public String getBUDGET_DMBTR() {
		return BUDGET_DMBTR;
	}
	public void setBUDGET_DMBTR(String bUDGET_DMBTR) {
		BUDGET_DMBTR = bUDGET_DMBTR;
	}
	public String getAVAILABLE_DMBTR() {
		return AVAILABLE_DMBTR;
	}
	public void setAVAILABLE_DMBTR(String aVAILABLE_DMBTR) {
		AVAILABLE_DMBTR = aVAILABLE_DMBTR;
	}
	public String getRULTYPSAT() {
		return RULTYPSAT;
	}
	public void setRULTYPSAT(String rULTYPSAT) {
		RULTYPSAT = rULTYPSAT;
	}
	public String getCALCTYPESAT() {
		return CALCTYPESAT;
	}
	public void setCALCTYPESAT(String cALCTYPESAT) {
		CALCTYPESAT = cALCTYPESAT;
	}
	public String getPERCENT_SAT() {
		return PERCENT_SAT;
	}
	public void setPERCENT_SAT(String pERCENT_SAT) {
		PERCENT_SAT = pERCENT_SAT;
	}
	public String getLIMIT_SAT() {
		return LIMIT_SAT;
	}
	public void setLIMIT_SAT(String lIMIT_SAT) {
		LIMIT_SAT = lIMIT_SAT;
	}
	public String getDEDUAMOUNT() {
		return DEDUAMOUNT;
	}
	public void setDEDUAMOUNT(String dEDUAMOUNT) {
		DEDUAMOUNT = dEDUAMOUNT;
	}
	public String getRULTYPBUS() {
		return RULTYPBUS;
	}
	public void setRULTYPBUS(String rULTYPBUS) {
		RULTYPBUS = rULTYPBUS;
	}
	public String getCALCTYPEBUS() {
		return CALCTYPEBUS;
	}
	public void setCALCTYPEBUS(String cALCTYPEBUS) {
		CALCTYPEBUS = cALCTYPEBUS;
	}
	public String getPERCENT_BUS() {
		return PERCENT_BUS;
	}
	public void setPERCENT_BUS(String pERCENT_BUS) {
		PERCENT_BUS = pERCENT_BUS;
	}
	public String getLIMIT_BUS() {
		return LIMIT_BUS;
	}
	public void setLIMIT_BUS(String lIMIT_BUS) {
		LIMIT_BUS = lIMIT_BUS;
	}
	public String getAPPRAMOUNT() {
		return APPRAMOUNT;
	}
	public void setAPPRAMOUNT(String aPPRAMOUNT) {
		APPRAMOUNT = aPPRAMOUNT;
	}
	public String getHKONT() {
		return HKONT;
	}
	public void setHKONT(String hKONT) {
		HKONT = hKONT;
	}
	public String getOPERFLAG() {
		return OPERFLAG;
	}
	public void setOPERFLAG(String oPERFLAG) {
		OPERFLAG = oPERFLAG;
	}
	public String getXMLXSTRING() {
		return XMLXSTRING;
	}
	public void setXMLXSTRING(String xMLXSTRING) {
		XMLXSTRING = xMLXSTRING;
	}
	public String getPDFXSTRING() {
		return PDFXSTRING;
	}
	public void setPDFXSTRING(String pDFXSTRING) {
		PDFXSTRING = pDFXSTRING;
	}
	public List<CostDistDto> getCOSTDIST() {
		return COSTDIST;
	}
	public void setCOSTDIST(List<CostDistDto> cOSTDIST) {
		COSTDIST = cOSTDIST;
	}
	public List<AccDataDto> getACCDATA() {
		return ACCDATA;
	}
	public void setACCDATA(List<AccDataDto> aCCDATA) {
		ACCDATA = aCCDATA;
	}
	public List<CellDto> getCELL() {
		return CELL;
	}
	public void setCELL(List<CellDto> cELL) {
		CELL = cELL;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getWaers_xml() {
		return waers_xml;
	}

	public void setWaers_xml(String waers_xml) {
		this.waers_xml = waers_xml;
	}

	public String getMwskz_xml() {
		return mwskz_xml;
	}

	public void setMwskz_xml(String mwskz_xml) {
		this.mwskz_xml = mwskz_xml;
	}

	

	
	
	
	
}
