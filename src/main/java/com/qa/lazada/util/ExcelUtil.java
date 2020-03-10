package com.qa.lazada.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static Workbook book;
	public static Sheet sheet;
	
	
	//******************************* LOCATING ANY PROJECT FILE *******************************//
	
	// Project always runs at "ProjectName i.e. Dec2019POMSeries" level which is represented as ".". Thus to locate any file inside the Project directory, we can use the
	//	PATH of the file mentioned inside PROPERTIES[ALT + ENTER] and navigate from ./src/........
	// This will make the file generic and avoid any dependency on local machines and its directories
	
	public static String TESTDATA_SHEET_PATH = "./src/main/java/com/qa/hubspot/testdata/HubSpotTestData.xlsx";
	
	
	
	/**
	 * This method will get/ receive WorkSheet Name and give the data from that Worksheet
	 * @param sheetName
	 * @throws InvalidFormatException 
	 */
	public static Object[][] getTestData(String workSheetName) throws InvalidFormatException {
			
		try {
			// First, we need to create a FileInputSteam Object with our Excel File path to load the Excel file in memory.
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			
			//2. After that, we'll store the file from memory inside a newly created WorkBook, using WorkBookFactory's create method.
			book = WorkbookFactory.create(fis);	
			
			//3. After that, we'll have to locate our Worksheet inside the WorkBook using getSheet method
			sheet = book.getSheet(workSheetName);	// 32:57
			
			// For Data from worksheet, we'll using 2D Object Array
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];	// This is hardcoded. so we should not use
												// Object[To no. of Rows][To no. of Columns]
												//	sheet.getLastRowNum() will give the last row no.
												//	sheet.getRow(0).getLastCellNum() --> This will go to 1st Row and then get the Last Cell No. (column) which has data.
												//	Columns count will be always same in a set of data. Only row count increases
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {					//	--> This gives the No. of rows.
				
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {	//	--> This gives the No. of columns.
					data[i][k] = sheet.getRow(i+1).getCell(k).toString();		// row(i+1) will point to 2nd row since 1st row only contains headers
																				// This will transfer all the data from Excel file to 2D Array
				}
			}
			
			return data;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;	//	If one return is inside try block, then we need to create another return with default value of the datatype
		
	}
	
	
	
	
	
}
