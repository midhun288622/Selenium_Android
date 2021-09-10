package com.scs.execution;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import com.scs.commons.TestDataHandlerPOI;
import com.scs.driverfactory.BaseTest;
import com.scs.pages.DevicesPage;
import com.scs.pages.GroupPage;
import com.scs.pages.HomePage;
import com.scs.pages.Login_Page;
import com.scs.pages.ScenesPage;
import com.scs.pages.SpacesPage;

/**
 * This is a PPS specific BaseTest class.
 */
public class BaseAppTest extends BaseTest {


	public String sTestCaseName;
	protected Login_Page login;
	protected HomePage homepage;
	protected GroupPage groupPage;
	protected ScenesPage scenesPage;
	protected DevicesPage devicesPage;
	protected SpacesPage spacesPage;
	public BaseAppTest() {
		super();
	}
	
	String className=this.getClass().getSimpleName();
	/**
	 * Used to pre-create instances of pages our tests need.
	 * 
	 * @param driver     web driver we are using
	 * @param extentTest extent test to log too
	 */
	public void setupPages(WebDriver driver) {
		
		login = new Login_Page(getDriver());
		homepage=new HomePage(getDriver());
		devicesPage=new DevicesPage(driver);
		groupPage=new GroupPage(driver);
		scenesPage=new ScenesPage(driver);
		spacesPage=new SpacesPage(driver);
	}

	@DataProvider(name = "TestData")
	public Object[][] InsertTradeDataProvider(Method method) throws Exception {
		Map<String, Map<String, String>> excelFileMap;
		TestDataHandlerPOI data=new TestDataHandlerPOI();
		//TestDataHandler data=new TestDataHandler();
		sTestCaseName = method.getName().trim();
		Object[][] testObjArray;
		String testTemp = null;
		int length, iter = 0;
		List<Map<String, String>> listTc=new ArrayList<>();
		excelFileMap=data.setMapData(this.getClass().getSimpleName());
		Set<String> setKey=excelFileMap.keySet();
		for(String temp:setKey)
		{
			if(temp.trim().contains("_"))
			{
				testTemp=temp.split("_")[0];
			}
			else
			{
				testTemp=temp;
			}
			if(testTemp.equalsIgnoreCase(sTestCaseName))
			{
				listTc.add(excelFileMap.get(temp));		
			}
		}
		length=listTc.size();
		testObjArray = new Map[length][1];
		for (Map<String, String> object : listTc) {
			testObjArray[iter++][0] = object;
		}
		return (testObjArray);
	}
}

