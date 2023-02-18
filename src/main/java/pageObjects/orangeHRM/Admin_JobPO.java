package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangeHRM.Admin_JobUI;

public class Admin_JobPO extends BasePage{
	private WebDriver driver;
	
	public Admin_JobPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSaveButtonAtAddCurrencyForm() {
		clickToElement(driver, Admin_JobUI.SAVE_BUTTON_AT_ADD_CURRENCY_FORM);
	}

	public String convertAMPMTo24Hours(String hourInAMPM) {
		if (hourInAMPM.contains("PM")) {
			String[] hourInPM = hourInAMPM.split(":");
			return Integer.valueOf(hourInPM[0]) + 12 + ":00";
		} else {
			return hourInAMPM.replace(" AM", "");
		}
	}
}
