package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DashboardPO extends BasePage{
	private WebDriver driver;
	
	public DashboardPO(WebDriver driver) {
		this.driver = driver;
	}
}
