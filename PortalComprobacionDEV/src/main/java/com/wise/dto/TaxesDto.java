package com.wise.dto;

public class TaxesDto {
	
	private String kalsm;
	private String mwskz;
	private String active;
	public String getKalsm() {
		return kalsm;
	}
	public void setKalsm(String kalsm) {
		this.kalsm = kalsm;
	}
	public String getMwskz() {
		return mwskz;
	}
	public void setMwskz(String mwskz) {
		this.mwskz = mwskz;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "TaxesDto [kalsm=" + kalsm + ", mwskz=" + mwskz + ", active="
				+ active + "]";
	}

}
