package com.orangehrm.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import pageObjects.orangeHRM.PIM_AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.PIM_EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import utilities.DataUtil;

public class Cloud_Testing extends BaseTest {

	@Parameters({ "environmentName", "serverName", "browserName", "ipAddress", "portNumber", "osName", "osVersion"})
	@BeforeClass
	public void beforeClass(@Optional("dev") String environmentName, @Optional("local") String serverName, 
							@Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, 
							@Optional("Windows") String osName, @Optional("10") String osVersion) {
		
		driver = getBrowserDriver(environmentName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		System.out.println("Environment Name Before class = " + environmentName);
		System.out.println("Browser Name Before class = " + browserName);
		System.out.println("Os Name Before class = " + osName);
		System.out.println("Os Version Before class = " + osVersion);
		faker = DataUtil.getData();
		
		adminUsername = "Admin";
		adminPassword = "admin123";
		empFirstName = faker.getFirstName().replace("'", "a");
		empLastName = faker.getLastName().replace("'", "a");
		empFullName = empFirstName + " " + empLastName;
		empUserName = faker.getUserName();
		empPassword = faker.getStrongPassword();
		empStatusValue = "Enabled";
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.closeAllTabExceptParent(driver, driver.getWindowHandle());

		dashboardPage = loginPage.loginToSystem(driver, adminUsername, adminPassword);
	}

	@BeforeMethod
	public void beforeMethod() {
		stepNumber = 0;
	}
	
	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add New 01 - Step " + ++stepNumber + ": Open 'Employee List' page");
		dashboardPage.openMenuPage(driver, "PIM");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

		log.info("Add New 01 - Step " + ++stepNumber + ": Click To 'Add' button");
		employeeListPage.clickToButtonByText(driver, "Add");
		addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
		addEmployeePage.waitForLoadingIconDisappear(driver);

		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'First Name' textbox with value is: '" + empFirstName + "'");
		addEmployeePage.sendKeyToFirstNameTextbox(driver, empFirstName);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'Last Name' textbox with value is: '" + empLastName + "'");
		addEmployeePage.sendKeyToLastNameTextbox(driver, empLastName);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Get value of 'Employee ID'");
		empID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empID + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empID, "Employee Id");
		empID = addEmployeePage.checkEmployeeIDAlreadyExists(driver, empID);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Click to 'Create Login Details' switch");
		addEmployeePage.clickToSwitchByLabel(driver, "Create Login Details");
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'User Name' textbox with value is: '" + empUserName + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empUserName, "Username");
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'Password' textbox with value is: '" + empPassword + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empPassword, "Password");
		
		System.out.println("Employee Name = " + empFullName);
		System.out.println("Employee ID = " + empID);
		
		System.out.println("Password = " + empPassword);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'Confirm Password' textbox with value is: '" + empPassword + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empPassword, "Confirm Password");
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Click to '" + empStatusValue +"' radio button");
		addEmployeePage.checkToRadioButtonByLabel(driver, empStatusValue);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Click to 'Save' button");
		addEmployeePage.clickToButtonByText(driver, "Save");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
 		log.info("Add New 01 - Step " + ++stepNumber + ": Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empFullName + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empFullName, "Employee Name");
	
		log.info("Add New 01 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empID + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, empID, "Employee Id");
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		log.info("Add New 01 - Step " + ++stepNumber + ": Verify Employee Information is displayed at 'Result Table'");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), empID);
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empFirstName);
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empLastName);
	}
	
	@Parameters("environmentName")
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("local") String environmentName) {
		closeBrowserAndDriver(environmentName);
	}

	private WebDriver driver;
	private int stepNumber;
	private DataUtil faker;
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private PIM_EmployeeListPO employeeListPage;
	private PIM_AddEmployeePO addEmployeePage;
	private MyInfoPO myInfoPage;
	private String adminUsername, adminPassword, empFirstName, empLastName, empFullName ,empID, empUserName, empPassword, empStatusValue;
}
