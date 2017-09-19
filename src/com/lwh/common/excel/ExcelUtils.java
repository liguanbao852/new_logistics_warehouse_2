package com.lwh.common.excel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;

public class ExcelUtils {

	public static void main(String[] args) throws Exception {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name1", "第一个——" + i);
			map.put("name2", "第二个——" + i);
			map.put("name3", "第三个——" + i);
			list.add(map);
		}
		String[] titles = new String[]{"账号", "真实姓名",  "提现卡号"};
		String[] filedNames = new String[]{"name1", "name2", "name3"};
		
		Workbook workbook = ExcelUtils.exportExcel("提现申请成功表", list, titles , filedNames);
		
		String fileName = "aaa.xls";
		File file = new File("d:\\test");
		
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream outputStream = new FileOutputStream(file + File.separator + fileName);
		workbook.write(outputStream);
		outputStream.close();

	}
	
	/**
	 * 获取目标Excel中第一个sheet的最大行数
	 * @param file
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static int getExcelRows(File file) throws InvalidFormatException, IOException
	{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		Workbook wb = WorkbookFactory.create(in);
		Sheet st = wb.getSheetAt(0);
		int result = st.getLastRowNum();
		IOUtils.closeQuietly(in);
		return result;
	}

	/**
	 * 
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * 
	 * @param startRow
	 *            从第几行开始读Excel
	 * 
	 * @return 读出的Excel中数据的内容
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException 
	 * 
	 */

	public static String[][] readExcel(File file, int startRow) throws FileNotFoundException, IOException, InvalidFormatException {

		List<String[]> result = new ArrayList<String[]>();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		Workbook wb = WorkbookFactory.create(in);
		Cell cell = null;
		int rowSize = 0;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
	
			Sheet st = wb.getSheetAt(sheetIndex);
			rowSize = st.getLastRowNum();
			for (int rowIndex = startRow; rowIndex <= rowSize; rowIndex++) {
				Row row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int cellSize = row.getLastCellNum();
				String[] values = new String[cellSize];
				Arrays.fill(values, "");
				for (int columnIndex = 0; columnIndex < cellSize; columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case Cell.CELL_TYPE_BLANK:
							break;
						case Cell.CELL_TYPE_ERROR:
							value = "";
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							value = "";
						}
					}
					if (StringUtils.isBlank(value)) {
						value = "";
					}
					else {
						value = value.trim();
					}
					values[columnIndex] = value;
				}
				result.add(values);
			}
			
		}
		
		IOUtils.closeQuietly(in);
		String[][] returnArray = new String[result.size()][rowSize+1];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}


	public static Workbook exportExcel(String sheetName,
			List<Map<String, Object>> list, String[] titles, String[] fieldNames){

		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			Sheet sheet = wb.createSheet(sheetName);
			int k = 0;
			// 对每个表生成一个新的sheet,并以表名命名
			sheet.setDefaultColumnWidth((short) 28);
			CellStyle style = wb.createCellStyle();
			// 设置这些样式
			style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setAlignment(CellStyle.ALIGN_CENTER);
			// 生成一个字体
			Font font = wb.createFont();
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// 把字体应用到当前的样式
			style.setFont(font);
			// 生成并设置另一个样式
			CellStyle style2 = wb.createCellStyle();
			style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style2.setBorderBottom(CellStyle.BORDER_THIN);
			style2.setBorderLeft(CellStyle.BORDER_THIN);
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 生成另一个字体
			Font font2 = wb.createFont();
			font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
			// 把字体应用到当前的样式
			style2.setFont(font2);
			// 设置表头的说明

			Row topRow = sheet.createRow(0);
			for (int i = 0; i < titles.length; i++) {
				setCellGBKValue(topRow.createCell((short) i), titles[i]);
			}
			k = 1;
			if (null != list && list.size() != 0) {
				for (Map<String, Object> map : list) {
					Row row = sheet.createRow(k);
					for (int i = 0; i < fieldNames.length; i++) {
						setCellGBKValue(row.createCell((short) i), map.get(fieldNames[i]) + "");
					}
					k++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	private static void setCellGBKValue(Cell cell, String value) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		// 设置CELL的编码信息
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(value);
	}
 

	
	

}
