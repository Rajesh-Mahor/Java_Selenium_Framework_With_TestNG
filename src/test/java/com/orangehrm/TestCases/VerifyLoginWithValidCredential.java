package com.orangehrm.TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.orangehrm.PageObjectModel.LoginPage;
import com.orangehrm.Utilities.ExcelUtility;

public class VerifyLoginWithValidCredential extends BaseClass {
	
	
	Logger logger = LogManager.getLogger(VerifyLoginWithValidCredential.class);

	@Test(dataProvider = "UserData")
	public void userLogin(String username,String passward, String expected) throws IOException {
		LoginPage login = new LoginPage(driver);
		login.enterUserName(username);
		logger.info("user name entered Successfully");
		login.enterUserPassward(passward);
		logger.info("password entered Successfully");
		login.clickLoginButton();
		logger.info("Successfully clicked on Login Button");
		String actualText = login.verifyElement();

		if (actualText.equals(expected)) {
			logger.info("userLogin - Pass");
			Assert.assertTrue(true);
		} else {
			logger.info("userLogin - Fail");
			ScreenShotCaptured(driver, "userLogin");
			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "UserData")
	public String[][] userdataFromExcel() throws IOException {
		
		logger.info("Entrer in Data Provider Method.");
		
		String fileName = "D:\\eclipse-workspace\\Java Selenium Project\\OrangeHRM\\Configurations\\OrangeHRM_TestData.xlsx";
		String shhetName = "LoginDetails";
		
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
