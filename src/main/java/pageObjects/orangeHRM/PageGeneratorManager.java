package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static DashboardPO getDashboardPage(WebDriver driver) {
		return new DashboardPO(driver);
	}
	
	public static PIM_EmployeeListPO getEmployeeListPage(WebDriver driver) {
		return new PIM_EmployeeListPO(driver);
	}
	
	public static PIM_AddEmployeePO getAddEmployeePage(WebDriver driver) {
		return new PIM_AddEmployeePO(driver);
	}
	
	public static MyInfoPO getMyInfoPage(WebDriver driver) {
		return new MyInfoPO(driver);
	}
	
	public static Admin_UserManagementPO getUserManagementPage(WebDriver driver) {
		return new Admin_UserManagementPO(driver);
	}
	
	public static Admin_JobPO getJobPage(WebDriver driver) {
		return new Admin_JobPO(driver);
	}
	
	public static Admin_OrganizationPO getOrganizationPage(WebDriver driver) {
		return new Admin_OrganizationPO(driver);
	}
	
	public static Admin_QualificationPO getQualificationPage(WebDriver driver) {
		return new Admin_QualificationPO(driver);
	}
}
