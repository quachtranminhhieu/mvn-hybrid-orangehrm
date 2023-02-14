package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangeHRM.MyInfoPageUI;

public class MyInfoPO extends BasePage{
	private WebDriver driver;
	
	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		clickToElement(driver, MyInfoPageUI.EMPLOYEE_AVATAR);
	}

	public boolean isNewAvatarDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.EMPLOYEE_AVATAR);
		int imageWidth = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.EMPLOYEE_AVATAR, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.EMPLOYEE_AVATAR, "height"));
		return (imageHeight != 104) || (imageWidth != 104);
	}

	public void openTabAtSideBarByName(String tabName) {
		clickToElementByJS(driver, MyInfoPageUI.DYNAMIC_TAB_NAME_AT_SIDE_BAR, tabName);
		waitForLoadingIconDisappear(driver);
	}

	public void checkToSmokerCheckbox() {
		checkToCheckboxRadio(driver, MyInfoPageUI.SMOKER_CHECKBOX);
	}

	public void sendKeyToAmountTextboxInDepositDetailsForm(String empSalaryAccAmount) {
		sendKeyToElement(driver, MyInfoPageUI.AMOUNT_TEXTBOX_IN_DEPOSIT_DETAILS_FORM, empSalaryAccAmount);
	}

	public void selectItemInDropdownLabelInFormName(String itemValue, String formName, String dropdownLabel) {
		selectItemInCustomDropdown(driver, MyInfoPageUI.DYNAMIC_PARENT_DROPDOWN_BY_LABEL_IN_FORM_NAME, MyInfoPageUI.DYNAMIC_CHILD_ITEM_IN_DROPDOWN_BY_LABEL_IN_FORM_NAME, itemValue, formName, dropdownLabel);
	}

	public void sendkeyToTextboxLabelInFormName(String textValue, String formName, String textboxLabel) {
		sendKeyToElement(driver, MyInfoPageUI.DYNAMIC_TEXTBOX_BY_LABEL_IN_FORM_NAME, textValue, formName, textboxLabel);
		
	}

	public String getSelectedItemInCustomDropDownLabelInFormName(String formName, String dropdownLabel) {
		return getElementText(driver, MyInfoPageUI.DYNAMIC_PARENT_DROPDOWN_BY_LABEL_IN_FORM_NAME, formName, dropdownLabel);
	}

	public String getTextboxValueLabelInFormName(String formName, String textboxLabel) {
		return getElementAttribute(driver, MyInfoPageUI.DYNAMIC_TEXTBOX_BY_LABEL_IN_FORM_NAME, "value", formName, textboxLabel);
	}
}
