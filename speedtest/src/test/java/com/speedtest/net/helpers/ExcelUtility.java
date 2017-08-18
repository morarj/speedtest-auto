package com.speedtest.net.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.speedtest.net.constants.ExcelFileConstants;
import com.speedtest.net.speedtest.ConnectionSpeedTest;

public class ExcelUtility {
	/*
	 * URL to get the binary - http://poi.apache.org/download.html
	 * Binary Name - poi-bin-3.11-beta2-20140822.zip
	 * Extract the binary
	 * Add all the jars from the location you extracted
	 * Also add all the jars from lib, do not add the jar file of log4j
	 * Also add all the jars from ooxml-lib
	 */
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static final Logger log = LogManager.getLogger(ExcelUtility.class.getName());

	/*
	 * Set the File path, open Excel file
	 * @params - Excel Path and Sheet Name
	 */
	
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the excel data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	/*
	 * Read the test data from the Excel cell
	 * @params - Row num and Col num
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	/*
	 * Read the numeric data from the Excel cell
	 * @params - Row num and Col num
	 */
	public static int getCellNumericData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int CellData = (int) Cell.getNumericCellValue();
			
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	public static String getDateCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		    Date dateValue = Cell.getDateCellValue();
		    String dateStringFormat = df.format(dateValue);
		    
			return dateStringFormat;
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	/*
	 * Write in the Excel cell
	 * @params - Row num and Col num
	 */
	@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		FileOutputStream fileOut = null;
		
		try {
			Row = ExcelWSheet.getRow(RowNum);
			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}
			
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Open the file to write the results
			fileOut = new FileOutputStream(ExcelFileConstants.FILE_PATH + ExcelFileConstants.FILE_NAME);

			ExcelWBook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		} finally {
			fileOut.flush();
			fileOut.close();
		}
	}
	
	@SuppressWarnings("static-access")
	public static void setCellData(double Result, int RowNum, int ColNum) throws Exception {
		FileOutputStream fileOut = null;
		
		try {
			Row = ExcelWSheet.getRow(RowNum);
			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}
			
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Open the file to write the results
			fileOut = new FileOutputStream(ExcelFileConstants.FILE_PATH + ExcelFileConstants.FILE_NAME);

			ExcelWBook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		} finally {
			fileOut.flush();
			fileOut.close();
		}
	}
	
	@SuppressWarnings("static-access")
	public static void setCellData(Date Result, int RowNum, int ColNum) throws Exception {
		FileOutputStream fileOut = null;
		CreationHelper createHelper = ExcelWBook.getCreationHelper();
		
		try {
			Row = ExcelWSheet.getRow(RowNum);
			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}
			
			CellStyle cellStyle = ExcelWBook.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));
			
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			Cell.setCellStyle(cellStyle);

			// Open the file to write the results
			fileOut = new FileOutputStream(ExcelFileConstants.FILE_PATH + ExcelFileConstants.FILE_NAME);

			ExcelWBook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		} finally {
			fileOut.flush();
			fileOut.close();
		}
	}
}