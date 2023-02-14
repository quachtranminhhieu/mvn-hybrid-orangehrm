package pageUIs.orangeHRM;

public class BasePageUI {
	public static final String UPLOAD_FILE = "//input[@type='file']";
	public static final String USERNAME_TEXTBOX = "//input[@name='username']";
	public static final String PASSWORD_TEXTBOX = "//input[@name='password']";
	public static final String FIRST_NAME_TEXTBOX = "//input[@name='firstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@name='lastName']";
	public static final String LOGIN_BUTTON = "//button[@type='submit']";
	public static final String DYNAMIC_TEXTBOX_BY_LABEL = "//label[text()='%s']/parent::div/following-sibling::div//input";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(string(),'%s')]";
	public static final String DYNAMIC_BUTTON_BY_TEXT_IN_FORM = "//h6[text()='%s']/following-sibling::button[contains(string(),'%s')]";
	public static final String DYNAMIC_SWITCH= "//*[text()='%s']/parent::div//span";
	public static final String DYNAMIC_MENU_PAGE = "//a[contains(string(),'%s')]";
	public static final String DYNAMIC_DROPDOWN_SUB_MENU_PAGE = "//span[contains(text(),'%s')]";
	public static final String DYNAMIC_SUB_MENU_PAGE = "//a[text()='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "//label[string()='%s']/span";
	
	public static final String DYNAMIC_TABLE = "//div[@role='table']";
	public static final String DYNAMIC_HEADER_NAME_IN_DATA_TABLE_WITHOUT_NAME = "//div[@role='table']/div[@class='oxd-table-header']//div[text()='%s']/preceding-sibling::div";
	public static final String DYNAMIC_CELL_IN_DATA_TABLE_WITHOUT_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX = "//div[@role='table']/div[@role='rowgroup']/div[%s]//div[@role='cell'][%s]";
	public static final String DYNAMIC_BUTTON_IN_DATA_TABLE_WITHOUT_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX = "//div[@role='table']/div[@role='rowgroup']/div[%s]//div[@role='cell'][%s]//button/i[contains(@class,'%s')]";
	public static final String ACCEPT_DELETE_IN_DATA_TABLE = "//button[contains(string(),'Yes, Delete')]";
	
	public static final String DYNAMIC_HEADER_NAME_IN_DATA_TABLE_NAME = "//h6[text()='%s']/parent::div/parent::div/following-sibling::div/div[@role='table']/div[@class='oxd-table-header']//div[text()='%s']/preceding-sibling::div";
	public static final String DYNAMIC_CELL_IN_DATA_TABLE_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX = "//h6[text()='%s']/parent::div/parent::div/following-sibling::div/div[@role='table']/div[@role='rowgroup']/div[%s]//div[@role='cell'][%s]";
	public static final String DYNAMIC_BUTTON_IN_DATA_TABLE_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX = "//h6[text()='%s']/parent::div/parent::div/following-sibling::div/div[@role='table']/div[@role='rowgroup']/div[%s]//div[@role='cell'][%s]//button/i[contains(@class,'%s')]";
	
	public static final String DYNAMIC_MESSAGE_TITLE = "//div[@aria-live]//p[contains(@class,'title')]";
	public static final String USER_DROPDOWN_TAB = "//span[@class='oxd-userdropdown-tab']";
	public static final String LOGOUT_LINK = "//a[text()='Logout']";
	public static final String LOADING_ICON = "//div[@class='oxd-loading-spinner-container']";
	public static final String DYNAMIC_PARENT_DROPDOWN_BY_LABEL = "//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
	public static final String DYNAMIC_CHILD_ITEM_IN_DROPDOWN_BY_LABEL = "//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text oxd-select-text--focus']/following-sibling::div/div[@role='option']";
	public static final String DYNAMIC_OPTION_IN_TEXTBOX = "//div[@role='option' and contains(string(),'%s') and contains(string(),'%s')]";
}
