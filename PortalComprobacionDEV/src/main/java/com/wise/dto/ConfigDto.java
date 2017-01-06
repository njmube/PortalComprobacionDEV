package com.wise.dto;

import java.util.ArrayList;
import java.util.List;

public class ConfigDto {
	
	private String bukrs;	
	private String butxt;
	private List<GastoDto> docTypes;
	private List<TaxesDto> taxesDto;
	private List<CecosDto> cecosDto;
	
	public ConfigDto() {		
		this.bukrs = "";
		this.butxt = "";
		this.docTypes = new ArrayList<GastoDto>();
		this.taxesDto = new ArrayList<TaxesDto>();
		this.cecosDto = new ArrayList<CecosDto>();
	}
	public String getBukrs() {
		return bukrs;
	}
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	public String getButxt() {
		return butxt;
	}
	public void setButxt(String butxt) {
		this.butxt = butxt;
	}
	public List<GastoDto> getDocTypes() {
		return docTypes;
	}
	public void setDocTypes(List<GastoDto> docTypes) {
		this.docTypes = docTypes;
	}
	public List<TaxesDto> getTaxesDto() {
		return taxesDto;
	}
	public void setTaxesDto(List<TaxesDto> taxesDto) {
		this.taxesDto = taxesDto;
	}
	public List<CecosDto> getCecosDto() {
		return cecosDto;
	}
	public void setCecosDto(List<CecosDto> cecosDto) {
		this.cecosDto = cecosDto;
	}
	@Override
	public String toString() {
		return "ConfigDto [bukrs=" + bukrs + ", butxt=" + butxt + ", docTypes="
				+ docTypes + ", taxesDto=" + taxesDto + ", cecosDto="
				+ cecosDto + "]";
	}
	

}
