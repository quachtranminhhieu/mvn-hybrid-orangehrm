package pageUIs.orangeHRM;

public class MyInfoPageUI {

	public static final String EMPLOYEE_AVATAR = "//img[@class='employee-image']";
	public static final String DYNAMIC_TAB_NAME_AT_SIDE_BAR = "//a[text()='%s']";
	public static final String SMOKER_CHECKBOX = "//label[text()='Smoker']/parent::div/following-sibling::div//span[contains(@class,'oxd-checkbox-input')]";
	public static final String AMOUNT_TEXTBOX_IN_DEPOSIT_DETAILS_FORM = "//div[contains(@class,'directdeposit')]/following-sibling::div/div//div[string()='Amount']//input";
	public static final String DYNAMIC_PARENT_DROPDOWN_BY_LABEL_IN_FORM_NAME = "//h6[text()='%s']/following-sibling::div[1]//div[string()='%s']/following-sibling::div//div[@class='oxd-select-text-input']";
	public static final String DYNAMIC_CHILD_ITEM_IN_DROPDOWN_BY_LABEL_IN_FORM_NAME = "//h6[text()='%s']/following-sibling::div[1]//div[string()='%s']/following-sibling::div//div[@class='oxd-select-text oxd-select-text--focus']/following-sibling::div/div[@role='option']";
	public static final String DYNAMIC_TEXTBOX_BY_LABEL_IN_FORM_NAME = "//h6[text()='%s']/following-sibling::div[1]//div[string()='%s']//input";
}
