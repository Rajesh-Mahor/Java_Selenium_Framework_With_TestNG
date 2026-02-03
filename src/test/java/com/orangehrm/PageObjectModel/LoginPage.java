package com.orangehrm.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement username;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;
	@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	private WebElement elementForVerification;

	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String userName) {
		if (userName != null && !userName.isEmpty()) {
			username.sendKeys(userName);
	    }
	
	}

	 
	public void enterUserPassward(String userPssward) {
		if (userPssward != null && !userPssward.isEmpty()) {
			password.sendKeys(userPssward);
	    }
	
	}

	public void clickLoginButton() {
		loginButton.click();
	}
	
	public String verifyElement() {
		return elementForVerification.getText();
	}

}
