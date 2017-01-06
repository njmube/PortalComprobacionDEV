package com.wise.dto;

import java.util.List;

public class ExpenseDataDto {
	
	HeaderExpenseDto headerExpenseDto;
	List<ItemDataExpenseDto> itemDataExpenseDtoList;
	List<String> headerTextList;
	public HeaderExpenseDto getHeaderExpenseDto() {
		return headerExpenseDto;
	}
	public void setHeaderExpenseDto(HeaderExpenseDto headerExpenseDto) {
		this.headerExpenseDto = headerExpenseDto;
	}
	public List<ItemDataExpenseDto> getItemDataExpenseDtoList() {
		return itemDataExpenseDtoList;
	}
	public void setItemDataExpenseDtoList(List<ItemDataExpenseDto> itemDataExpenseDtoList) {
		this.itemDataExpenseDtoList = itemDataExpenseDtoList;
	}
	public List<String> getHeaderTextList() {
		return headerTextList;
	}
	public void setHeaderTextList(List<String> headerTextList) {
		this.headerTextList = headerTextList;
	}
	@Override
	public String toString() {
		return "ExpenseDataDto [headerExpenseDto=" + headerExpenseDto
				+ ", itemDataExpenseDtoList=" + itemDataExpenseDtoList + ", headerTextList="
				+ headerTextList + "]";
	}
	
}
