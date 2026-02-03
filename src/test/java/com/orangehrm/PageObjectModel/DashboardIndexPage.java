package com.orangehrm.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardIndexPage {

	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[1]")
	private WebElement admin;

	@FindBy(xpath = "//h6[text()='Admin']")
	private WebElement Verifytext;

	public DashboardIndexPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickOnAdminSection() {
		admin.click();
	}

	public String VerifyDashboardIndexPage() {
		return Verifytext.getText();
	}

}
