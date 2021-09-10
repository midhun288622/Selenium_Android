package com.scs.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scs.driverfactory.BaseTest;
import com.scs.exceptions.DataSheetException;

import jxl.read.biff.BiffException;

/**
 * Class for handling the data read and data write for input data sheet.
 */
public class TestDataHandlerPOI {

	private static final  String extention=".xlsx";
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataHandlerPOI.class);
	private static final String testdataPath = BaseTest.externalSheetPath();
	public static Set<String> testcaseLst= new HashSet<String>(); 
	/**

    /**
	 * Function is used to get the column count .
	 * @param sheetName -This argument is for passing the sheet name in the input
	 *                  data sheet.
	 */
	private int getColumnCount(String sheetName)
			throws BiffException, IOException, DataSheetException, FileNotFoundException {

		int column = 0;
		try {

			FileInputStream input = new FileInputStream(testdataPath+sheetName+extention);
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheet(sheetName);
			column = sheet.getRow(0).getLastCellNum();

			if (column != 0) {
				return column;
			} else {
				LOGGER.error("The input data sheet is blank");
				throw new DataSheetException("The input data sheet is blank");
			}

		} catch (FileNotFoundException fe) {
			LOGGER.error("Please provide a valid sheet path:" + testdataPath + " " + "can not be found");
			throw new DataSheetException("Please provide a valid sheet path:" + testdataPath + " " 
					+ "can not be found");
		} catch (Exception e) {
			LOGGER.error("No sheet found with the class name " + sheetName);
			throw new DataSheetException("No sheet found with the class name " + sheetName);
		}
	}



	public   Map<String, Map<String, String>> setMapData(String sheetName) throws IOException, BiffException {

		List<Map<String, String>> test=new ArrayList<>();
		Map<String, Map<String, String>> excelFileMap2 = new LinkedHashMap<String, Map<String,String>>();
		try {
			FileInputStream input = new FileInputStream( System.getProperty("user.dir")+"/TestData/"+sheetName+extention);
			int coumnCount =  getColumnCount(sheetName);
			Workbook workbook = new XSSFWorkbook(input);

			Sheet sheet = workbook.getSheet(sheetName);

			int lastRow = sheet.getLastRowNum();
			int count=1;
			int tcDuplicate=1;
			String tcID;
			//Looping over entire row
			for(int i=1; i<=lastRow; i++){

				Map<String, String> dataMap = new HashMap<String, String>();

				tcID=sheet.getRow(i).getCell(0).getStringCellValue().trim();
				if(excelFileMap2.containsKey(tcID))
				{
					tcID=tcID+"_R"+tcDuplicate;
					tcDuplicate=tcDuplicate+1;
				}
				else
				{
					tcID=sheet.getRow(i).getCell(0).getStringCellValue();
				}

				for(int k=0;k<coumnCount;k++) 
				{

						String temp;
					try {
						
						temp=sheet.getRow(i).getCell(k).getStringCellValue().trim();
					} catch (NullPointerException e) {
						temp="";
					}

					dataMap.put(sheet.getRow(0).getCell(k).getStringCellValue().trim(),temp);


				}


				count=count+1;
				test.add(dataMap);
				excelFileMap2.put(tcID, dataMap);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		//Returning excelFileMap
		return excelFileMap2;

	}
	private int getTestIdCoumnNumber(String sheetName,String CoulmnName) throws BiffException, IOException {

		FileInputStream input = new FileInputStream(testdataPath+sheetName+extention);
		Workbook wb = new XSSFWorkbook(input);

		int count=0;
		Sheet sheet = wb.getSheet(sheetName);
		int column = getColumnCount(sheetName);

		for(int i=0;i<column;i++)
		{

			String tmpColmnName=sheet.getRow(0).getCell(i).getStringCellValue();
			if(tmpColmnName.equalsIgnoreCase(CoulmnName))
			{
				count=i;
				break;
			}
		}
		input.close();
		return count;
	}


	private int getTestIdRowNumber(String sheetName,String columnName,String tcID) throws BiffException, IOException {

		int colmnNumber=getTestIdCoumnNumber(sheetName, columnName);
		FileInputStream input = new FileInputStream(testdataPath+sheetName+extention);
		Workbook wb =new XSSFWorkbook(input);
		Sheet sheet = wb.getSheet(sheetName);
		int row = sheet.getLastRowNum();
		int count=0;
		for(int i=1;i<=row;i++)
		{

			String tmpColmnName=sheet.getRow(i).getCell(colmnNumber).getStringCellValue();
			if(tmpColmnName.equalsIgnoreCase(tcID))
			{
				count=i;
				break;
			}
		}
		input.close();
		return count;
	}

	public LinkedHashMap<String, String> readFromSheet(String sheetName,String columnName,String tcID)
			throws BiffException, IOException, DataSheetException {


		int tcIdRowNumber=getTestIdRowNumber(sheetName, columnName, tcID);
		int column =  getColumnCount(sheetName);
		LinkedHashMap<String, String> tempSheet = null;
		@SuppressWarnings("unused")
		int index = 0;
		// boolean headingStatus = validateHeading(sheetName);
		FileInputStream input = new FileInputStream(testdataPath+sheetName+extention);
		Workbook wb = new XSSFWorkbook(input);
		Sheet sheet = wb.getSheet(sheetName);

		// Iterates through each row in excel
		tempSheet = new LinkedHashMap<String, String>();

		for (int columns = 0; columns < column; columns++) {



			tempSheet.put(sheet.getRow(0).getCell(columns).getStringCellValue(), sheet.getRow(tcIdRowNumber).getCell(columns).getStringCellValue());

		}

		index++;


		input.close();

		return tempSheet;
	}


}
