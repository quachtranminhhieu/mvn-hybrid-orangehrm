package com.orangehrm.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.data.EmployeeData;
import com.orangehrm.data.EmployeeData.EmployeeInformation;

import commons.BaseTest;
import pageObjects.orangeHRM.PIM_AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.PIM_EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import reportConfig.ExtentTestManagerV5;
import utilities.JsonHelper;

public class OrangeHRM_PIM_CreateNewUser extends BaseTest {

	@Parameters({ "environmentName", "serverName", "browserName", "ipAddress", "portNumber", "osName", "osVersion"})
	@BeforeClass
	public void beforeClass(@Optional("dev") String environmentName, @Optional("local") String serverName, @Optional("chrome") String browserName, 
		@Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		
		driver = getBrowserDriver(environmentName, serverName, browserName, ipAddress, portNumber, osName, osVersion);

		empInformation = EmployeeData.getEmployeeInformation();
		
		employeeName = empInformation.getEmpFullName();
		employeeUserName = empInformation.getEmpUserName();
		employeePassword = empInformation.getEmpPassword();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.closeAllTabExceptParent(driver, driver.getWindowHandle());

		dashboardPage = loginPage.loginToSystem(driver, JsonHelper.getEmployee().getAdminUsername(), JsonHelper.getEmployee().getAdminPassword());
	}

	@BeforeMethod
	public void beforeMethod() {
		stepNumber = 0;
	}
	
	@Test
	public void Employee_01_Add_New_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_01_Add_New_Employee");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Open 'Employee List' page");
		dashboardPage.openMenuPage(driver, "PIM");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Click To 'Add' button");
		employeeListPage.clickToButtonByText(driver, "Add");
		addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
		addEmployeePage.waitForLoadingIconDisappear(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'First Name' textbox with value is: '" + empInformation.getEmpFirstName() + "'");
		addEmployeePage.sendKeyToFirstNameTextbox(driver, empInformation.getEmpFirstName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'Last Name' textbox with value is: '" + empInformation.getEmpLastName() + "'");
		addEmployeePage.sendKeyToLastNameTextbox(driver, empInformation.getEmpLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Get value of 'Employee ID'");
		String empID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
		empInformation.setEmpID(empID);
		employeeID = empID;
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empInformation.getEmpID() + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empInformation.getEmpID(), "Employee Id");
		empInformation.setEmpID(addEmployeePage.checkEmployeeIDAlreadyExists(driver, empInformation.getEmpID()));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Click to 'Create Login Details' switch");
		addEmployeePage.clickToSwitchByLabel(driver, "Create Login Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'User Name' textbox with value is: '" + empInformation.getEmpUserName() + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empInformation.getEmpUserName(), "Username");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'Password' textbox with value is: '" + empInformation.getEmpPassword() + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empInformation.getEmpPassword(), "Password");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'Confirm Password' textbox with value is: '" + empInformation.getEmpPassword() + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empInformation.getEmpPassword(), "Confirm Password");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Click to '" + empInformation.getEmpStatusValue() +"' radio button");
		addEmployeePage.checkToRadioButtonByLabel(driver, empInformation.getEmpStatusValue());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Click to 'Save' button");
		addEmployeePage.clickToButtonByText(driver, "Save");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
 		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empInformation.getEmpFullName() + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empInformation.getEmpFullName(), "Employee Name");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empInformation.getEmpID() + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, empInformation.getEmpID(), "Employee Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Verify Employee Information is displayed at 'Result Table'");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), empInformation.getEmpID());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empInformation.getEmpFirstName());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empInformation.getEmpLastName());
	
		resultOfCreateUser = true;
	}
	
	@Parameters("serverName")
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("local") String serverName) {
		closeBrowserAndDriver(serverName);
	}

	public static boolean resultOfCreateUser = false;
	
	public static String employeeName;
	public static String employeeID;
	public static String employeeUserName;
	public static String employeePassword;
	
	private WebDriver driver;
	private int stepNumber;
	
	private EmployeeInformation empInformation;
	
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private PIM_EmployeeListPO employeeListPage;
	private PIM_AddEmployeePO addEmployeePage;
	private MyInfoPO myInfoPage;
}
