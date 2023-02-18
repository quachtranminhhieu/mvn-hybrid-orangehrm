package pageUIs.orangeHRM;

public class Admin_OrganizationPageUI {

	public static final String DYNAMIC_PARENT_NOTE_NAME = "//ul[@class='oxd-tree-node-child']/li[contains(@class,'tree-node') and contains(string(),'%s')]";
	public static final String DYNAMIC_CHILD_NOTE_NAME_IN_PARENT_NOTE_NAME = "//ul[@class='oxd-tree-node-child']/li[contains(@class,'tree-node') and contains(string(),'%s')]/ul[contains(string(),'%s')]";
	public static final String DYNAMIC_ROW_INDEX_OF_NODE = "//ul[@class='oxd-tree-node-child']/li[contains(@class,'tree-node') and contains(string(),'%s')]/preceding-sibling::li";
	public static final String DYNAMIC_BUTTON_NAME_AT_PARENT_NODE_VALUE = "//div[contains(text(),'%s')]/parent::div//button/i[contains(@class,'plus')]";
	public static final String DYNAMIC_DROPDOWN_ICON_AT_PARENT_NODE_VALUE = "//div[contains(text(),'%s')]/parent::div/parent::div/preceding-sibling::span//i[contains(@class,'down')]";

}
