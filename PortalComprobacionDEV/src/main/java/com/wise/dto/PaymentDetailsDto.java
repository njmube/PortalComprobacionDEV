package com.wise.dto;

import java.util.List;

public class PaymentDetailsDto {
	
	HeaderDataDto headerDataDto;
	List<ItemDataDto> itemDataDtoList;
	List<String> headerTextList;
	public HeaderDataDto getHeaderDataDto() {
		return headerDataDto;
	}
	public void setHeaderDataDto(HeaderDataDto headerDataDto) {
		this.headerDataDto = headerDataDto;
	}
	public List<ItemDataDto> getItemDataDtoList() {
		return itemDataDtoList;
	}
	public void setItemDataDtoList(List<ItemDataDto> itemDataDtoList) {
		this.itemDataDtoList = itemDataDtoList;
	}
	public List<String> getHeaderTextList() {
		return headerTextList;
	}
	public void setHeaderTextList(List<String> headerTextList) {
		this.headerTextList = headerTextList;
	}
	@Override
	public String toString() {
		return "PaymentDetailsDto [headerDataDto=" + headerDataDto
				+ ", itemDataDtoList=" + itemDataDtoList + ", headerTextList="
				+ headerTextList + "]";
	}

}
