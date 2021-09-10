//package com.scs.execution;
//import org.testng.annotations.Test;
//import org.testng.annotations.Test;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.testng.TestNG;
//import org.testng.xml.XmlClass;
//import org.testng.xml.XmlInclude;
//import org.testng.xml.XmlSuite;
//import org.testng.xml.XmlTest;
//
//import com.codoid.products.exception.FilloException;
//import com.codoid.products.fillo.Recordset;
//import com.scs.commons.FunctionLibrary;
//import com.scs.commons.XLSReader;
//
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//
//public class DynamicTestNG {
//
//	static Map<String, String> map=new HashMap<>();
//	static Map<String, String> testMap=new HashMap<>();
//	static Map<String, XmlSuite> suteFiles=new HashMap<>();
//	static Map<String, XmlTest> testFiles=new HashMap<>();
//	static List<XmlSuite> suites = new ArrayList<XmlSuite>();
//	static ArrayList<String> classes=new ArrayList<String>(); 
//	String testdataPath = FunctionLibrary.readPathFromPropertyFile("Dynamic_Sheet_Path");
//	XLSReader suite = new XLSReader(testdataPath);
//	List<XmlClass> classesLst=null;
//	
//	public static void getAllClasses(String pckgname) {
//		try{ 
//			File directory=null; 
//			try { 
//				directory=new File(Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile()); 
//			} catch(NullPointerException x) { 
//				throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
//			} 
//			if(directory.exists()) { 
//				// Get the list of the files contained in the package 
//				String[] files=directory.list(); 
//				for(int i=0; i<files.length; i++) { 
//					
//					System.out.println(pckgname);
//					// we are only interested in .class files 
//					if(files[i].endsWith(".class")) { 
//						// removes the .class extension 
//						classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6))+""); 
//					} 
//				} 
//			} else { 
//				System.out.println("Directory does not exist");
//				throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
//			} 
//			@SuppressWarnings("rawtypes")
//			Class[] classesA=new Class[classes.size()]; 
//			classes.toArray(classesA); 
//
//			
//		} catch (Exception e) {
//			System.out.println();
//		}
//	}
//	
//	
//	
////	public void getAllDirectory()
////	{
////
////		try {
////			String path="src/test/java";
////			File[] directories = new File(path).listFiles(File::isDirectory);
////			List<File> list=Arrays.asList(directories);
////			while(list.size()>0)
////			{
////				for(File file1:list)
////				{
////					path=file1+"";
////					directories = new File(path).listFiles(File::isDirectory);
////					list=Arrays.asList(directories);
////					if(!(list.size()>0))
////					{
////						path=path.replaceAll("/", ".");
////						path=path.replaceAll("src.test.java.", "");
////						getAllClasses(path);
////					}
////				}
////			}
////		}catch (Exception e) {
////			System.out.println(e.getMessage()+" ");
////		}
////	
////	}
//	
////	public String getDirectoryName(String className)
////	{
////		String dName="";
////		for(String list:classes)
////		{
////			System.out.println(list);
////			if(list.contains(className))
////			{
////				list=list.replaceAll("class", "").trim();
////				dName=list;
////			}
////		}
////		return dName;
////	}
//
//	
//	/*public Map<String, String> getSuiteLoc() throws FilloException
//	{
//	
//		Map<String, String> folderLocMap=new HashMap<>();
//		Recordset suiteSet= suite.getTests("select * from ParallelExecution");
//		while (suiteSet.next()) {
//
//			String suiteName = suiteSet.getField("SuiteName");
//			String folderLoc = suiteSet.getField("FolderLoc");
//			folderLocMap.put(suiteName, folderLoc);
//		}
//		
//		return folderLocMap;
//	}
//	*/
//	
//
///*	public Map<String, String> getThreadCount() throws FilloException
//	{
//		Map<String, String> threadMap=new HashMap<>();
//		
//		Recordset suiteSet= suite.getTests("select * from ParallelExecution");
//		while (suiteSet.next()) {
//
//			String suiteName = suiteSet.getField("SuiteName");
//			String threadCount = suiteSet.getField("ThreadCount");
//			threadMap.put(suiteName, threadCount);
//			
//		}
//		
//		return threadMap;
//	}*/
//	
////	@Test
////	public void executeTestNg() throws FilloException, BiffException, IOException
////	{
////		
////	try{
////		 // boolean headingStatus = validateHeading(sheetName);
////        FileInputStream input = new FileInputStream(testdataPath);
////        Workbook wb = Workbook.getWorkbook(input);
////        
////        
////        for(String sheetName:   wb.getSheetNames())
////        {
////		 //String regressionSheetName= FunctionLibrary.readPropertyValue("exeSheet");
////
////		Recordset recordset= suite.getTests("select * from "+sheetName+" where ExecutionStatus='Yes'");
////	    ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
////		XmlSuite sampleSuite = null;
////		getAllDirectory();
////		XmlTest test = null;
////		XmlClass testClass=null;
////		while (recordset.next()) {
////
////			String testName = recordset.getField("Test case ID");
////			String browser = recordset.getField("Browser");
////			String iterationName=recordset.getField("Iteration");
////			if(!map.containsKey(sheetName))
////			{
////				sampleSuite = new XmlSuite();
////				sampleSuite.setName(sheetName);
////				sampleSuite.setParallel(FunctionLibrary.readPropertyValue("IsParallel"));
////				sampleSuite.setThreadCount(Integer.parseInt(FunctionLibrary.readPropertyValue("ThreadCount")));
////				Map<String, String> params = new HashMap<>();
////				sampleSuite.setParameters(params);
////				suteFiles.put(sheetName, sampleSuite);
////				suites.add(sampleSuite);
////				
////				if(!testFiles.containsKey(sheetName))
////				{
////					test = new XmlTest(suteFiles.get(sheetName));
////					test.setName(sheetName);
////					test.addParameter("browser", browser);
////					test.addParameter("Iteration Name", iterationName);
////				
////					testFiles.put(sheetName, test);
////					
////					classesLst = new ArrayList<XmlClass>();
////					testClass=new XmlClass();
////					testClass.setName(getDirectoryName(sheetName));
////				}
////					
////
////			}
////
////			methodsToRun.add(new XmlInclude(testName));
////			testClass.setIncludedMethods(methodsToRun);
////			classesLst.add(testClass);
////			testFiles.get(sheetName).setXmlClasses(classesLst);
////			 
////			testMap.put(sheetName, sheetName);
////			map.put(sheetName, sheetName);
////		 }
////        }
////		TestNG tng = new TestNG();
////		tng.setXmlSuites(suites);
////		tng.run();
////        
////	}catch (Exception e) {
////		// TODO: handle exception
////		System.out.println(" ");
////	}
////		
////	}
//}
