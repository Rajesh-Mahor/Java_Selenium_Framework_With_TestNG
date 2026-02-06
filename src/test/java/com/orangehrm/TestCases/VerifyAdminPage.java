package com.orangehrm.TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.PageObjectModel.DashboardIndexPage;
import com.orangehrm.PageObjectModel.LoginPage;
import com.orangehrm.Utilities.ExcelUtility;

public class VerifyAdminPage extends BaseClass {
	
	Logger logger = LogManager.getLogger(VerifyAdminPage.class);
	
	@Test(dataProvider = "UserVerifyOnAdminPage")
	public void userAdminPage(String username,String passward, String expected) throws IOException, InterruptedException {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterUserPassward(passward);
		loginPage.clickLoginButton();
		
		DashboardIndexPage DashboardIndexPage = new DashboardIndexPage(driver);
		DashboardIndexPage.clickOnAdminSection();
		logger.info("Successfully Click On Admin Section");
		
		String actualText = DashboardIndexPage.VerifyDashboardIndexPage().toString();
		if(actualText.equals(expected)) {
			logger.info("VerifyAdminPage - Pass");
			Assert.assertTrue(true);
		}else {
			logger.info("VerifyAdminPage - Fail");
			ScreenShotCaptured(driver, "VerifyAdminPage");
			Assert.assertTrue(false);
		}
				
	}
	
	@DataProvider(name = "UserVerifyOnAdminPage")
	public String[][] userdataFromExcel() throws IOException {
		
		String fileName = "D:\\eclipse-workspace\\Java Selenium Project\\OrangeHRM\\Configurations\\OrangeHRM_TestData.xlsx";
		String shhetName = "Admin";
		
		int ttlRow = ExcelUtility.getRowCount(fileName, shhetName);
		int ttlCell = ExcelUtility.getCellCount(fileName, shhetName);
		
		String data [][] = new String[ttlRow-1][ttlCell];
		
		for(int i=1;i<ttlRow;i++) {
			for(int j=0;j<ttlCell;j++) {
				data [i-1][j] = ExcelUtility.getCellValue(fileName, shhetName, i, j);
				System.out.println(data [i-1][j]);
			}
		}
		return data;
	}
	
	
}
