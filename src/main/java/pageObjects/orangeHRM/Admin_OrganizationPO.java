package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangeHRM.Admin_OrganizationPageUI;

public class Admin_OrganizationPO extends BasePage{
	private WebDriver driver;
	
	public Admin_OrganizationPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean checkParentNoteAddedSuccessfully(String parentNodeName) {
		return isElementDisplayed(driver, Admin_OrganizationPageUI.DYNAMIC_PARENT_NOTE_NAME, parentNodeName);
	}
	
	public boolean checkChildNoteAddedSuccessfully(String parentNodeName, String childNoteName) {
		return isElementDisplayed(driver, Admin_OrganizationPageUI.DYNAMIC_CHILD_NOTE_NAME_IN_PARENT_NOTE_NAME, parentNodeName, childNoteName);
	}

	public void clickToButtonNameInTreeNodeAtParentNodeValue(String parentNodeValue, String buttonName) {
		clickToElementByJS(driver, Admin_OrganizationPageUI.DYNAMIC_BUTTON_NAME_AT_PARENT_NODE_VALUE, parentNodeValue, buttonName);
	}

	public void clickToDropdownIconAtParentNodeValue(String parentNodeValue) {
		clickToElementByJS(driver, Admin_OrganizationPageUI.DYNAMIC_DROPDOWN_ICON_AT_PARENT_NODE_VALUE, parentNodeValue);
	}

}
