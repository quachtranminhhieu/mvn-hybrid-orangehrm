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

import commons.BaseTest;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.Admin_JobPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import pageObjects.orangeHRM.Admin_UserManagementPO;
import reportConfig.ExtentTestManagerV5;
import utilities.DataUtil;
import utilities.JsonHelper;

public class OrangeHRM_Admin_01 extends BaseTest{
	
	@Parameters({"environmentName", "serverName", "browserName", "ipAddress", "portNumber", "osName", "osVersion"})
	@BeforeClass
	public void beforeClass(@Optional("dev") String environmentName, @Optional("lcoal") String serverName, @Optional("chrome") String browserName, 
			@Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		
		faker = DataUtil.getData();
		
		adminUserRole = "Admin";
		adminEmployeeName = OrangeHRM_PIM_CreateNewUser.employeeName;
		adminStatus = "Enabled";
		adminUserName = faker.getUserName();
		adminPassword = faker.getStrongPassword();
		
		adminJobTitle = faker.getJobTitle();
		adminJobDescription = faker.getJobDescription();
		adminFileName = "dog.jpg";
		adminJobNote = faker.getJobNote();
		
		adminJobPayGradeName = "Grade " + faker.getRandomNumber(10, 50);
		adminJobPayGradeCurrency = "VND - Vietnamese Dong";
		adminJobPayGradeMin = faker.getRandomNumber(1000000, 5000000);
		adminJobPayGradeMax = faker.getRandomNumber(6000000, 10000000);
		
		adminJobEmpStatusName = "Full-Time " + faker.getJobTitle();
		
		adminJobCategoryName = faker.getJobCategory() + " " + faker.getRandomNumber(0, 50);
		
		adminJobWorkShiftName = "Work Shift " + faker.getRandomNumber(0, 100);
		adminJobWorkShiftFrom = faker.getRandomNumber(1, 12)  + ":00 AM";
		adminJobWorkShiftTo = faker.getRandomNumber(1, 12) + ":00 PM";
		
		driver = getBrowserDriver(environmentName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.closeAllTabExceptParent(driver, driver.getWindowHandle());
		
		dashboardPage = loginPage.loginToSystem(driver, JsonHelper.getEmployee().getAdminUsername(), JsonHelper.getEmployee().getAdminPassword());
	}
	
	@BeforeMethod
	public void beforeMethod() {
		stepNumber = 0;
	}
	
	@Test
	public void Admin_01_Add_New_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_01_Add_New_Employee");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Check status Create New User");
		Assert.assertTrue(OrangeHRM_PIM_CreateNewUser.resultOfCreateUser);
	}
	
	@Test(dependsOnMethods = "Admin_01_Add_New_Employee")
	public void Admin_02_User_Management(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_02_User_Management");
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Click to 'Users' Sub Menu");
		dashboardPage.openMenuPage(driver, "Admin");
		userManagementPage = PageGeneratorManager.getUserManagementPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Click to 'Add' button");
		userManagementPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Select item in 'User Role' dropdown with value is: '" + adminUserRole + "'");
		userManagementPage.selectItemInCustomDropdownByLabel(driver, adminUserRole, "User Role");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + adminEmployeeName + "'");
		userManagementPage.sendKeyToTextboxAndClickResultByLabel(driver, adminEmployeeName, "Employee Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Select item in 'Status' dropdown with value is: '" + adminStatus + "'");
		userManagementPage.selectItemInCustomDropdownByLabel(driver, adminStatus, "Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Enter to 'Username' textbox with value is: '" + adminUserName + "'");
		userManagementPage.sendKeyToTextboxByLabel(driver, adminUserName, "Username");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Enter to 'Password' textbox with value is: '" + adminPassword + "'");
		userManagementPage.sendKeyToTextboxByLabel(driver, adminPassword, "Password");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Enter to 'Confirm Password' textbox with value is: '" + adminPassword + "'");
		userManagementPage.sendKeyToTextboxByLabel(driver, adminPassword, "Confirm Password");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Click to 'Save' button");
		userManagementPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(userManagementPage.getMessageTitle(driver), "Success");
		userManagementPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Enter to 'Username' textbox with value is: '" + adminUserName + "'");
		userManagementPage.sendKeyToTextboxByLabel(driver, adminUserName, "Username");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Select item in 'User Role' dropdown with value is: '" + adminUserRole + "'");
		userManagementPage.selectItemInCustomDropdownByLabel(driver, adminUserRole, "User Role");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + adminEmployeeName + "'");
		userManagementPage.sendKeyToTextboxAndClickResultByLabel(driver, adminEmployeeName, "Employee Name");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Select item in 'Status' dropdown with value is: '" + adminStatus + "'");
		userManagementPage.selectItemInCustomDropdownByLabel(driver, adminStatus, "Status");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Click to 'Search' button");
		userManagementPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Verify 'Username' is correct");
		Assert.assertEquals(userManagementPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Username", "1"), adminUserName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Verify 'User Role' is correct");
		Assert.assertEquals(userManagementPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "User Role", "1"), adminUserRole);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Verify 'Employee Name' is correct");
		Assert.assertEquals(userManagementPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Employee Name", "1"), adminEmployeeName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "User Management 02 - Step " + ++stepNumber + ": Verify 'Status' is correct");
		Assert.assertEquals(userManagementPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Status", "1"), adminStatus);
	}
	
	@Test(dependsOnMethods = "Admin_02_User_Management")
	public void Admin_03_Job_Title(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_03_Job_Title");
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Click to 'Job Title' Sub Menu");
		userManagementPage.openChildSubMenuPage(driver, "Job", "Job Titles");
		jobPage = PageGeneratorManager.getJobPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Click to 'Add' button");
		jobPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Enter to 'Job Title' textbox with value is: '" + adminJobTitle + "'");
		jobPage.sendKeyToTextboxByLabel(driver, adminJobTitle, "Job Title");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Enter to 'Job Description' textarea with value is: '" + adminJobDescription + "'");
		jobPage.sendKeyToTextareaByLabel(driver, adminJobDescription, "Job Description");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Upload new file: '" + adminFileName + "'");
		jobPage.uploadMultipleFiles(driver, adminFileName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Enter to 'Note' textarea with value is: '" + adminJobNote + "'");
		jobPage.sendKeyToTextareaByLabel(driver, adminJobTitle, "Note");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Click to 'Save' button");
		jobPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(jobPage.getMessageTitle(driver), "Success");
		jobPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Verify 'Job Titles' is correct");
		String rowIndex = jobPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminJobTitle);
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Job Titles", rowIndex), adminJobTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Title 03 - Step " + ++stepNumber + ": Verify 'Job Description' is correct");
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Job Description", rowIndex), adminJobDescription);
	}
	
	@Test(dependsOnMethods = "Admin_03_Job_Title")
	public void Admin_04_Job_Pay_Grades(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_04_Job_Pay_Grades");
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Click to 'Pay Grades' Sub Menu");
		jobPage.openChildSubMenuPage(driver, "Job", "Pay Grades");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Click to 'Add' button");
		jobPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminJobPayGradeName+ "'");
		jobPage.sendKeyToTextboxByLabel(driver, adminJobPayGradeName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Click to 'Save' button");
		jobPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertTrue(jobPage.getMessageTitle(driver).equals("Success") || jobPage.getMessageTitle(driver).equals("Info"));
		jobPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Verify 'Name' textbox is correct");
		Assert.assertEquals(jobPage.getTextboxValueByLabel(driver, "Name"), adminJobPayGradeName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Click to 'Add' button at 'Currencies' form");
		jobPage.clickToButtonByTextInForm(driver, "Currencies", "Add");
		jobPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Select item in 'Currency' dropdown with value is: '" + adminJobPayGradeCurrency + "'");
		jobPage.selectItemInCustomDropdownByLabel(driver, adminJobPayGradeCurrency, "Currency");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Enter to 'Minimum Salary' textbox with value is: '" + adminJobPayGradeMin+ "'");
		jobPage.sendKeyToTextboxByLabel(driver, adminJobPayGradeMin, "Minimum Salary");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Enter to 'Maximum Salary' textbox with value is: '" + adminJobPayGradeMax+ "'");
		jobPage.sendKeyToTextboxByLabel(driver, adminJobPayGradeMax, "Maximum Salary");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Click to 'Save' button at 'Add Currency' form");
		jobPage.clickToSaveButtonAtAddCurrencyForm();
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(jobPage.getMessageTitle(driver), "Success");
		jobPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Verify 'Currency' is correct");
		String[] jobPayGradeCurrency = adminJobPayGradeCurrency.split(" - ");
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Currencies", "Currency", "1"), jobPayGradeCurrency[1]);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Verify 'Minimun Salary' is correct");
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Currencies", "Minimum Salary", "1"), adminJobPayGradeMin + ".00");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Pay Grades 04 - Step " + ++stepNumber + ": Verify 'Maximun Salary' is correct");
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Currencies", "Maximum Salary", "1"), adminJobPayGradeMax + ".00");
	}
	
	@Test(dependsOnMethods = "Admin_03_Job_Title")
	public void Admin_05_Job_Employment_Status(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_05_Job_Employment_Status");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Employment Status 05 - Step " + ++stepNumber + ": Click to 'Employment Status' Sub Menu");
		jobPage.openChildSubMenuPage(driver, "Job", "Employment Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Employment Status 05 - Step " + ++stepNumber + ": Click to 'Add' button");
		jobPage.clickToButtonByText(driver, "Add");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Employment Status 05 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminJobEmpStatusName + "'");
		jobPage.sendKeyToTextboxByLabel(driver, adminJobEmpStatusName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Employment Status 05 - Step " + ++stepNumber + ": Click to 'Save' button");
		jobPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Employment Status 05 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(jobPage.getMessageTitle(driver), "Success");
		jobPage.waitForLoadingIconDisappear(driver);
	
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Employment Status 05 - Step " + ++stepNumber + ": Verify 'Employment Status' is correct");
		String rowIndex = jobPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminJobEmpStatusName);
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Employment Status", rowIndex), adminJobEmpStatusName);
	}
	
	@Test(dependsOnMethods = "Admin_03_Job_Title")
	public void Admin_06_Job_Categories(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_06_Job_Categories");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Categories 06 - Step " + ++stepNumber + ": Click to 'Job Categories' Sub Menu");
		jobPage.openChildSubMenuPage(driver, "Job", "Job Categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Categories 06 - Step " + ++stepNumber + ": Click to 'Add' button");
		jobPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Categories 06 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminJobCategoryName + "'");
		jobPage.sendKeyToTextboxByLabel(driver, adminJobCategoryName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Categories 06 - Step " + ++stepNumber + ": Click to 'Save' button");
		jobPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Categories 06  - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(jobPage.getMessageTitle(driver), "Success");
		jobPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Categories 06  - Step " + ++stepNumber + ": Verify 'Job Category' is correct");
		String rowIndex = jobPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminJobCategoryName);
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Job Category", rowIndex), adminJobCategoryName);
	}
	
	@Test(dependsOnMethods = {"Admin_01_Add_New_Employee", "Admin_03_Job_Title"})
	public void Admin_07_Job_Work_Shift(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_07_Job_Work_Shift");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Click to 'Work Shift' Sub Menu");
		jobPage.openChildSubMenuPage(driver, "Job", "Work Shifts");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Click to 'Add' button");
		jobPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Enter to 'Assigned Employees' textbox with value is: '" + adminEmployeeName + "'" );
		jobPage.sendKeyToTextboxAndClickResultByLabel(driver, adminEmployeeName, "Assigned Employees");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Enter to 'From' textbox with value is: '" + adminJobWorkShiftFrom + ":00 AM" + "'" );
		jobPage.sendKeyToTextboxByLabel(driver, adminJobWorkShiftFrom, "From");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Enter to 'To' textbox with value is: '" + adminJobWorkShiftTo + ":00 PM" + "'" );
		jobPage.sendKeyToTextboxByLabel(driver, adminJobWorkShiftTo, "To");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Enter to 'Shift Name' textbox with value is: '" + adminJobWorkShiftName + "'" );
		jobPage.sendKeyToTextboxByLabel(driver, adminJobWorkShiftName, "Shift Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Get value of 'Duration Per Day'" );
		adminJobWorkShiftHoursPerDay = jobPage.getTextValueByLabel(driver, "Duration Per Day");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job Work Shift 07  - Step " + ++stepNumber + ": Click to 'Save' button");
		jobPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Work Shift 07  - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(jobPage.getMessageTitle(driver), "Success");
		jobPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Work Shift 07  - Step " + ++stepNumber + ": Verify 'Name' is correct");
		String rowIndex = jobPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminJobWorkShiftName);
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Name", rowIndex), adminJobWorkShiftName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Work Shift 07  - Step " + ++stepNumber + ": Verify 'From' is correct");
		adminJobWorkShiftFrom = jobPage.convertAMPMTo24Hours(adminJobWorkShiftFrom);
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "From", rowIndex), adminJobWorkShiftFrom);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Work Shift 07  - Step " + ++stepNumber + ": Verify 'To' is correct");
		adminJobWorkShiftTo = jobPage.convertAMPMTo24Hours(adminJobWorkShiftTo);
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "To", rowIndex), adminJobWorkShiftTo);
		
		ExtentTestManagerV5.getTest().log(Status.INFO,  "Job Work Shift 07  - Step " + ++stepNumber + ": Verify 'Hours Per Day' is correct");
		Assert.assertEquals(jobPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Hours Per Day", rowIndex), adminJobWorkShiftHoursPerDay);
	}
	
	
	@Parameters("serverName")
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("local") String serverName) {
		closeBrowserAndDriver(serverName);
	}
	
	WebDriver driver;
	private int stepNumber;
	DataUtil faker;
	
	LoginPO loginPage;
	DashboardPO dashboardPage;
	Admin_UserManagementPO userManagementPage;
	Admin_JobPO jobPage;
	
	String adminUserRole, adminEmployeeName, adminStatus, adminUserName, adminPassword, 
	
	adminJobTitle, adminJobDescription, adminFileName, adminJobNote,
	
	adminJobPayGradeName, adminJobPayGradeCurrency, adminJobPayGradeMin, adminJobPayGradeMax,
	
	adminJobEmpStatusName,
	
	adminJobCategoryName,
	
	adminJobWorkShiftName, adminJobWorkShiftFrom, adminJobWorkShiftTo, adminJobWorkShiftHoursPerDay;
}
