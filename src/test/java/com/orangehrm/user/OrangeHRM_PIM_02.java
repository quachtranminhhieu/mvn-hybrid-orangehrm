package com.orangehrm.user;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
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
import pageObjects.orangeHRM.PIM_ConfigurationPO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.PIM_EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import reportConfig.ExtentTestManagerV5;
import utilities.JsonHelper;

public class OrangeHRM_PIM_02 extends BaseTest {

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
	public void PIM_01_Configuration(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "PIM_01_Configuration");
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Open 'PIM' page");
		dashboardPage.openMenuPage(driver, "PIM");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Optional Fields' Sub Menu");
//		employeeListPage.openChildSubMenuPage(driver, "Configuration", "Optional Fields");
//		configurationPage = PageGeneratorManager.getConfigurationPage(driver);
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn off 'Show Nick Name, Smoker and Military Service in Personal Details' switch");
//		configurationPage.clickToSwitchOffByLabel(driver, "Show Nick Name, Smoker and Military Service in Personal Details");
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn off 'Show SSN field in Personal Details' switch");
//		configurationPage.clickToSwitchOffByLabel(driver, "Show SSN field in Personal Details");
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn off 'Show SIN field in Personal Details' switch");
//		configurationPage.clickToSwitchOffByLabel(driver, "Show SIN field in Personal Details");
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn off 'Show US Tax Exemptions menu' switch");
//		configurationPage.clickToSwitchOffByLabel(driver, "Show US Tax Exemptions menu");
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Save' button");
//		configurationPage.clickToButtonByText(driver, "Save");
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
//		Assert.assertEquals(configurationPage.getMessageTitle(driver), "Success");
//		configurationPage.waitForLoadingIconDisappear(driver);
//
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Employee List' Menu");
//		configurationPage.openSubMenuPage(driver, "PIM", "Employee List");
//		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Edit' button at data table");
//		employeeListPage.clickToButtonNameInDataTableNameAtColumnNameAndRowIndex(driver, "", "Actions", "1", "pencil");
//		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Nickname' textbox is not displayed");
//		Assert.assertTrue(myInfoPage.isTextboxUndisplayedByLabel(driver, "Nickname"));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Smoker' checkbox is not displayed");
//		Assert.assertTrue(myInfoPage.isSmokerCheckboxUndisplayed());
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Military Service' textbox is not displayed");
//		Assert.assertTrue(myInfoPage.isTextboxUndisplayedByLabel(driver, "Military Service"));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'SSN Number' textbox is not displayed");
//		Assert.assertTrue(myInfoPage.isTextboxUndisplayedByLabel(driver, "SSN Number"));
//		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'SIN Number' textbox is not displayed");
//		Assert.assertTrue(myInfoPage.isTextboxUndisplayedByLabel(driver, "SIN Number"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Optional Fields' Sub Menu");
		employeeListPage.openChildSubMenuPage(driver, "Configuration", "Optional Fields");
		configurationPage = PageGeneratorManager.getConfigurationPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn on 'Show Nick Name, Smoker and Military Service in Personal Details' switch");
		configurationPage.clickToSwitchOnByLabel(driver, "Show Nick Name, Smoker and Military Service in Personal Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn on 'Show SSN field in Personal Details' switch");
		configurationPage.clickToSwitchOnByLabel(driver, "Show SSN field in Personal Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn on 'Show SIN field in Personal Details' switch");
		configurationPage.clickToSwitchOnByLabel(driver, "Show SIN field in Personal Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click to turn on 'Show US Tax Exemptions menu' switch");
		configurationPage.clickToSwitchOnByLabel(driver, "Show US Tax Exemptions menu");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Save' button");
		configurationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(configurationPage.getMessageTitle(driver), "Success");
		configurationPage.waitForLoadingIconDisappear(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Employee List' Menu");
		configurationPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Click To 'Edit' button at data table");
		employeeListPage.clickToButtonNameInDataTableNameAtColumnNameAndRowIndex(driver, "", "Actions", "1", "pencil");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Nickname' textbox is displayed");
		Assert.assertTrue(myInfoPage.isTextboxDisplayedByLabel(driver, "Nickname"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Smoker' checkbox is displayed");
		Assert.assertTrue(myInfoPage.isSmokerCheckboxDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'Military Service' textbox is displayed");
		Assert.assertTrue(myInfoPage.isTextboxDisplayedByLabel(driver, "Military Service"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'SSN Number' textbox is displayed");
		Assert.assertTrue(myInfoPage.isTextboxDisplayedByLabel(driver, "SSN Number"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "PIM Configuration 01 - Step " + ++stepNumber + ": Verify 'SIN Number' textbox is displayed");
		Assert.assertTrue(myInfoPage.isTextboxDisplayedByLabel(driver, "SIN Number"));
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
	private PIM_ConfigurationPO configurationPage;
	private MyInfoPO myInfoPage;
}
