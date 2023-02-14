package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class EmployeeListPO extends BasePage{
	private WebDriver driver;
	
	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}
}
