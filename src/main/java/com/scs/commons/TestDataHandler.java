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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scs.driverfactory.BaseTest;
import com.scs.exceptions.DataSheetException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



/**
 * Class for handling the data read and data write for input data sheet.
 */
public class TestDataHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataHandler.class);
    private static final String testdataPath = BaseTest.externalSheetPath();
    public static Set<String> testcaseLst= new HashSet<String>(); 
    /**
     * Function is used to validate the heading .
     * @param destFile -This argument is for passing the location of the input data
     *                 sheet -This argument is for passing the sheet name in the
     *                 input data sheet
     * @return-This method checks for the headings in the sheet and returns true if
     *              the headings are present
     */
    private boolean validateHeading(String sheetName) throws BiffException, IOException {

        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);

        Sheet sheet = wb.getSheet(sheetName);

        if ((sheet.getCell(0, 0).getContents().equalsIgnoreCase("BROWSER"))
                && (sheet.getCell(1, 0).getContents().equalsIgnoreCase("EXECUTION STATUS"))) {
            return true;
        }
        input.close();
        return false;
    }

    /**
     * Function is used to get the column count .
     * @param sheetName -This argument is for passing the sheet name in the input
     *                  data sheet.
     */
    private int getColumnCount(String sheetName)
            throws BiffException, IOException, DataSheetException, FileNotFoundException {

        int column = 0;
        try {

            FileInputStream input = new FileInputStream(testdataPath+sheetName+".xls");
            Workbook wb = Workbook.getWorkbook(input);
            Sheet sheet = wb.getSheet(sheetName);
            column = sheet.getColumns();

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

    /**
     * Function is used to get the row count from excel sheet .
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet
     * @return-This method returns the row count in the sheet
     */
    private int getRowCount(String sheetName) throws BiffException, IOException {

        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);

        Sheet sheet = wb.getSheet(sheetName);
        int row = sheet.getRows();
        input.close();
        return row;
    }
    
    
    /**
     * Function is used to get the row count from excel sheet .
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet
     * @return-This method returns the row count in the sheet
     */
    private Set<String> getTestIdRowNumberSize(String sheetName,String columnName,String tcID) throws BiffException, IOException {


    	int colmnNumber=getTestIdCoumnNumber(sheetName, columnName);
        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);
        Sheet sheet = wb.getSheet(sheetName);
        int row = sheet.getRows();
        int count=0;
        for(int i=1;i<row;i++)
        {
        	String tmpColmnName=sheet.getCell(colmnNumber,i).getContents();
        	if(tmpColmnName.equalsIgnoreCase(tcID))
        	{
        		count=i;
        		testcaseLst.add(i+"");
        	}
        }
        input.close();
        return testcaseLst;
    }
    
    
    /**
     * Function is used to get the row count from excel sheet .
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet
     * @return-This method returns the row count in the sheet
     */
    private int getTestIdRowNumber(String sheetName,String columnName,String tcID) throws BiffException, IOException {

    	int colmnNumber=getTestIdCoumnNumber(sheetName, columnName);
        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);
        Sheet sheet = wb.getSheet(sheetName);
        int row = sheet.getRows();
        int count=0;
        for(int i=1;i<=row;i++)
        {
        	String tmpColmnName=sheet.getCell(colmnNumber,i).getContents();
        	if(tmpColmnName.equalsIgnoreCase(tcID))
        	{
        		count=i;
        		break;
        	}
        }
        input.close();
        return count;
    }
   
    
    /**
     * Function is used to get the row count from excel sheet .
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet
     * @return-This method returns the row count in the sheet
     */
    private int getTestIdCoumnNumber(String sheetName,String CoulmnName) throws BiffException, IOException {

        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);

        int count=0;
        Sheet sheet = wb.getSheet(sheetName);
        int column = getColumnCount(sheetName);
        
        for(int i=0;i<column;i++)
        {
        	String tmpColmnName=sheet.getCell(i, 0).getContents();
        	if(tmpColmnName.equalsIgnoreCase(CoulmnName))
        	{
        		count=i;
        		break;
        	}
        }
        input.close();
        return count;
    }
    
    
    /**
     * Function is used to get the row count from excel sheet .
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet
     * @return-This method returns the row count in the sheet
     */
    private int getExecutionModeCoumn(String sheetName) throws BiffException, IOException {
       
        int executionMode = 0;
        boolean flag = true;
        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);
        Sheet sheet = wb.getSheet(sheetName);
        int columns = getColumnCount(sheetName);
        for (int i = 0; i < columns; i++) {
            if ("Execution Status".equalsIgnoreCase(sheet.getCell(i, 0).getContents().trim())) {
                executionMode = i;
                flag = false;
                break; 
            }
        }
        if (flag) {
            LOGGER.error("Execution mode column heading is missing");
            throw new NullPointerException(" 'Execution mode' column heading is missing");
        }
        return executionMode;
    }
    
    
    /**
     * Function is used to get the row count from excel sheet .
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet
     * @return-This method returns the row count in the sheet
     */
    private int getExecutionModeRow(String sheetName) throws BiffException, IOException {
        boolean flag = true;
        int rows = getRowCount(sheetName);
        int column = getExecutionModeCoumn(sheetName);
        int dataRow = 0;
        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);
        Sheet sheet = wb.getSheet(sheetName);
        for (int row = 1; row < rows; row++) {
         
            if ("Y".equalsIgnoreCase(sheet.getCell(column, row).getContents().trim())) {
                
                dataRow = row;
                flag = false;
                break;
            } 
        }
        if (flag) {
            LOGGER.error("Please enter either Y or N for execution status");
            throw new NullPointerException("Please enter either Y or N for execution status");
        }
        
        
        return dataRow;
    }

    /**
     * method is used to read data from excel sheet.
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet.
     * @return -Returns data object array which reads rows from the input data.
     *         sheet.
     */
    public LinkedHashMap<String, String> readFromSheet(String sheetName,String columnName,String tcID)
            throws BiffException, IOException, DataSheetException {

  
    	int tcIdRowNumber=getTestIdRowNumber(sheetName, columnName, tcID);
        int column =  getColumnCount(sheetName);
        LinkedHashMap<String, String> tempSheet = null;
        @SuppressWarnings("unused")
        int index = 0;
        System.out.println("");
       // boolean headingStatus = validateHeading(sheetName);
        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);
        Sheet sheet = wb.getSheet(sheetName);
        
            // Iterates through each row in excel
               tempSheet = new LinkedHashMap<String, String>();
              
                for (int columns = 0; columns < column; columns++) {
                    
                    tempSheet.put(sheet.getCell(columns, 0).getContents(), sheet.getCell(columns, tcIdRowNumber).getContents());
                  
                }
              
                index++;
            
        
        input.close();

        return tempSheet;
    }
    
    
    public Object[][] getDataFromDataprovider(String sheetName,String columnName,String tcID) throws BiffException, IOException{
    	
    	
    	Set<String> rowList=getTestIdRowNumberSize(sheetName, columnName, tcID);
    	Object[][] obj=new Object[rowList.size()][1];
    	
        return new Object[][] 
        	{
                { "Guru99", "India" },
                { "Krishna", "UK" },
                { "Bhupesh", "USA" }
            };

        }
    
    
    public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			HashMap<String,String> map=new HashMap<>();
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
    
    
    public LinkedHashMap<String, String> readMultipleData(String sheetName,String columnName,String tcID)
            throws BiffException, IOException, DataSheetException {

    	
    	
    	
    	
    	getTestIdRowNumberSize(sheetName, columnName, tcID);
    	int tcIdRowNumber=getTestIdRowNumber(sheetName, columnName, tcID);
        int column =  getColumnCount(sheetName);
        LinkedHashMap<String, String> tempSheet = null;
        @SuppressWarnings("unused")
        int index = 0;
        System.out.println("");
       // boolean headingStatus = validateHeading(sheetName);
        FileInputStream input = new FileInputStream(testdataPath);
        Workbook wb = Workbook.getWorkbook(input);
        Sheet sheet = wb.getSheet(sheetName);
        
            // Iterates through each row in excel
               tempSheet = new LinkedHashMap<String, String>();
              
                for (int columns = 0; columns < column; columns++) {
                    
                    tempSheet.put(sheet.getCell(columns, 0).getContents(), sheet.getCell(columns, tcIdRowNumber).getContents());
                  
                }
              
                index++;
            
        
        input.close();

        return tempSheet;
    }
    
    
    
    /**
     * method is used to read data from excel sheet.
     * @param sheetName This argument is for passing the sheet name in the input
     *                  data sheet.
     * @return -Returns data object array which reads rows from the input data.
     *         sheet.
     */
    public LinkedHashMap<String, String> readConfigSheet(String sheetName)
            throws BiffException, IOException, DataSheetException {

        //Read execution status sheet
    	//if it is Yes, 
    	 LinkedHashMap<String, String> tempSheet = null;
    	 int executionModeCoulmnNum=5;
    	 int rowCount=getRowCount(sheetName);
    	 
    	 FileInputStream input = new FileInputStream(testdataPath);
         Workbook wb = Workbook.getWorkbook(input);
         Sheet sheet = wb.getSheet(sheetName);
        
             // Iterates through each row in excel
                 tempSheet = new LinkedHashMap<String, String>();
                 for(int i=0;i<rowCount;i++) {
                     
                    String tempExeMode= sheet.getCell(i, executionModeCoulmnNum).getContents();
                    if(tempExeMode.equalsIgnoreCase("Yes"))
                    {
                    	//String testcaseId
                    }
                   
                 }
         input.close();
		return tempSheet;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public   Map<String, Map<String, String>> setMapData(String sheetName,String TCID) throws IOException, BiffException {
    	 
    	 List<Map<String, String>> test=new ArrayList<>();
    	  Map<String, Map<String, String>> excelFileMap2 = new LinkedHashMap<String, Map<String,String>>();
		try {
			FileInputStream input = new FileInputStream(testdataPath+sheetName+".xls");
			 
			  int coumnCount =  getColumnCount(sheetName);
			  Workbook workbook = Workbook.getWorkbook(input);
			
			  Sheet sheet = workbook.getSheet(sheetName);
			  
			  int lastRow = sheet.getRows();
			  int count=1;
			  int tcDuplicate=1;
				 String tcID=TCID;
			  //Looping over entire row
			  for(int i=1; i<lastRow; i++){
				 
				 Map<String, String> dataMap = new HashMap<String, String>();
			
				 tcID=sheet.getCell(0, count).getContents().trim();
				 if(excelFileMap2.containsKey(tcID))
				 {
					 tcID=tcID+"_R"+tcDuplicate;
					 tcDuplicate=tcDuplicate+1;
				 }
				 else
				 {
					 tcID=sheet.getCell(0, count).getContents();
				 }
//				 
//				 for(int k=0;k<coumnCount;k++) 
//				 {
//					 
//					 dataMap.put(sheet.getCell(k, 0).getContents().trim(), sheet.getCell(k, i).getContents().trim());
//				 }
//				 
//				 
//				 count=count+1;
//				 test.add(dataMap);
//				 excelFileMap2.put(tcID, dataMap);
			  }
			  
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 //Returning excelFileMap
		return excelFileMap2;

	}
    
    
    public static  Map<String, Map<String, String>> setMapData(String sheetName) throws IOException, BiffException {
   	 
    	
    	
   	 List<Map<String, String>> test=new ArrayList<>();
   	  Map<String, Map<String, String>> excelFileMap2 = new HashMap<String, Map<String,String>>();
		try {
			FileInputStream input = new FileInputStream(testdataPath);
			 
			 int coumnCount=3;
			  Workbook workbook = Workbook.getWorkbook(input);
			
			  Sheet sheet = workbook.getSheet(sheetName);
			  
			  int lastRow = sheet.getRows();
			  int count=1;
			  int tcDuplicate=1;
				 String tcID="0saetsdda";
			  //Looping over entire row
			  for(int i=1; i<lastRow; i++){
				 
				 Map<String, String> dataMap = new HashMap<String, String>();
			
				 tcID=sheet.getCell(0, count).getContents();
				 if(excelFileMap2.containsKey(tcID))
				 {
					 tcID=tcID+"_R"+tcDuplicate;
					 tcDuplicate=tcDuplicate+1;
				 }
				 else
				 {
					 tcID=sheet.getCell(0, count).getContents();
				 }
				 
				 for(int k=0;k<coumnCount;k++) 
				 {
					 
					 dataMap.put(sheet.getCell(k, 0).getContents(), sheet.getCell(k, i).getContents());
				 }
				 
				 
				 count=count+1;
				 test.add(dataMap);
				 excelFileMap2.put(tcID, dataMap);
			  }
			  
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 //Returning excelFileMap
		return excelFileMap2;

	}

}
