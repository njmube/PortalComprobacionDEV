/*package com.wise.viewResolver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wise.model.ListFacturaDto;
import com.wise.model.PendingOrdersDto;

@Component
public class PurchaseWOInvoiceView extends AbstractExcelView{
	
	private static final Logger LOGGER = Logger.getLogger(PurchaseWOInvoiceView.class);
	private Map<String, Integer> headers = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		
		{
			put("Orden de Compra",0);
			put("Posición", 1);
			put("No. Doc",2);
			put("Fecha",3);
			put("Material",4);
			put("UN",5);
			put("Descripción",6);
			put("Ctd. Aut.",7);
			put("Importe Sin IVA",8);
			put("Moneda",9);
		}
	};
	
	private static Map<String, HSSFCellStyle> styles;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(model != null){
			List<PendingOrdersDto> data = (List<PendingOrdersDto>) model.get("data");
			styles = PurchaseWOInvoiceView.createdStyles(workbook);
			String titleName = "Orden de Compra Sin Facturar";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fileName = titleName.toUpperCase() + "_" + sdf.format(new Date());
			fileName = fileName.replace("/","").replace(" ","_");
			response.setHeader("Content-Disposition", "attachment; filename="+ fileName + ".xls");
			
			HSSFSheet sheet = workbook.createSheet(titleName);
			sheet.setDefaultColumnWidth(18);		
			
			int rowcount = 0;
			
			HSSFRow infoRow = sheet.createRow(rowcount++);
			infoRow = sheet.createRow(rowcount++);
			HSSFCell infoCell = infoRow.createCell(0);
			infoCell.setCellValue("Reporte:");
			
			infoCell = infoRow.createCell(1);
			infoCell.setCellValue(titleName);
			
			infoRow = sheet.createRow(rowcount++);
			infoCell = infoRow.createCell(0);
			infoCell.setCellValue("Creado:");
			infoCell = infoRow.createCell(1);
			infoCell.setCellValue(sdf.format(new Date()));
			
			HSSFRow row = sheet.createRow(rowcount++);
			HSSFCellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
			headerStyle.setFillPattern((short)1);
			headerStyle.setWrapText(true);			
			
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.WHITE.index);
			font.setFontHeightInPoints((short)9);
			font.setBoldweight((short)700);
			
			headerStyle.setFont(font);
			for(String headerValue : headers.keySet()) {
				HSSFCell headerCell = row.createCell(headers.get(headerValue).shortValue());
				headerCell.setCellValue(headerValue);
				headerCell.setCellStyle(headerStyle);								
			}
			
			if(data != null && !data.isEmpty()) {
				for(PendingOrdersDto dataDto: data) {
					HSSFRow dataRow = sheet.createRow(rowcount++);
					addRow(dataRow,dataDto,rowcount,workbook);
				}
			}
		}		
	}
	
	private void addRow(HSSFRow dataRow, PendingOrdersDto dataDto, int rowcount, HSSFWorkbook workbook) {
		try {
		HSSFCell cell = this.createCell(dataRow, "Orden de Compra", rowcount);
		cell.setCellValue(dataDto.getEbeln());
		
		cell = this.createCell(dataRow, "Posición", rowcount);
		cell.setCellValue(dataDto.getEbelp());
		
		cell = this.createCell(dataRow, "No. Doc", rowcount);
		cell.setCellValue(dataDto.getMblnr());
		
		cell = this.createCell(dataRow, "Fecha", rowcount);
		cell.setCellValue(dataDto.getAedat());
		
		cell = this.createCell(dataRow, "Material", rowcount);
		cell.setCellValue(dataDto.getMatnr());
				
		cell = this.createCell(dataRow, "UN", rowcount);
		cell.setCellValue(dataDto.getMeins());
		
		cell = this.createCell(dataRow, "Descripción", rowcount);
		cell.setCellValue(dataDto.getTxz01());
		
		cell = this.createCell(dataRow, "Ctd. Aut.", rowcount);
		cell.setCellValue(dataDto.getMenge());
		
		cell = this.createCell(dataRow, "Importe Sin IVA", rowcount);
		cell.setCellValue(dataDto.getNetwr());
		
		cell = this.createCell(dataRow, "Moneda", rowcount);
		cell.setCellValue(dataDto.getWaers());
		
		} catch (Exception e) {
			LOGGER.error("ERROR PurchaseWOInvoiceView:",e);
		}
		
	}
	
	private HSSFCell createCell(HSSFRow hssfRow ,String header,int rowCount){
		HSSFCellStyle cellStyle = rowCount % 2 == 0 ? styles.get("evenCellStyle") : styles.get("oddCellStyle");
		HSSFCell hssfCell =  hssfRow.createCell(headers.get(header));
		hssfCell.setCellStyle(cellStyle);
		return  hssfCell;
	}
	
	private static Map<String, HSSFCellStyle> createdStyles(HSSFWorkbook wb){
        Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
        HSSFCellStyle cellStyle = wb.createCellStyle();
        
        short evenRowColorIndex = IndexedColors.GREY_25_PERCENT.getIndex();         
        cellStyle.setFillForegroundColor(evenRowColorIndex);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("evenCellStyle", cellStyle);
        
        cellStyle = wb.createCellStyle();                 
        short oddRowColorIndex = IndexedColors.WHITE.getIndex();
        cellStyle.setFillForegroundColor( oddRowColorIndex);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("oddCellStyle", cellStyle);
        
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm-dd-yyyy"));
        evenRowColorIndex = IndexedColors.GREY_25_PERCENT.getIndex();         
        cellStyle.setFillForegroundColor(evenRowColorIndex);
        cellStyle.setWrapText(true);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);            
        styles.put("evenDateCellStyle", cellStyle);
                 
        cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm-dd-yyyy"));                  
        cellStyle.setFillForegroundColor(oddRowColorIndex);
        cellStyle.setWrapText(true);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);            
        styles.put("oddDateCellStyle", cellStyle);                           
        
        return styles;
    }

}
*/