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
import pageObjects.orangeHRM.Admin_OrganizationPO;
import pageObjects.orangeHRM.Admin_QualificationPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import pageObjects.orangeHRM.Admin_UserManagementPO;
import reportConfig.ExtentTestManagerV5;
import utilities.DataUtil;
import utilities.JsonHelper;

public class OrangeHRM_Admin_02 extends BaseTest{
	
	@Parameters({"environmentName", "serverName", "browserName", "ipAddress", "portNumber", "osName", "osVersion"})
	@BeforeClass
	public void beforeClass(@Optional("dev") String environmentName, @Optional("lcoal") String serverName, @Optional("chrome") String browserName, 
			@Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		
		faker = DataUtil.getData();
		
		adminORGName = "Apple HRM " + faker.getRandomNumber(0, 100);
		adminORGNumber = faker.getRandomNumber(100000000,999999999); 
		adminORGTax = faker.getRandomNumber(100000000,999999999); 
		adminORGPhone = faker.getRandomNumber(100000000,999999999);
		adminORGFax = faker.getRandomNumber(100000000,999999999); 
		adminORGEmail = faker.getEmailAddress();
		adminORGFirstAddress = faker.getAddress();
		adminORGSecondAddress = faker.getAddress();
		adminORGCity = faker.getCity();
		adminORGState = faker.getState();
		adminORGZipCode = faker.getPostalCode();
		adminORGCountry = "Viet Nam";
		adminORGNote = faker.getJobNote();
		
		adminORGLocationName = "Apple HRM " + faker.getCountry();
		adminORGLocationCity = faker.getCity(); 
		adminORGLocationState = faker.getState();
		adminORGLocationZipCode = faker.getPostalCode();
		adminORGLocationCountry = "Viet Nam";
		adminORGLocationPhone = faker.getRandomNumber(100000000,999999999);
		adminORGLocationFax = faker.getRandomNumber(100000000,999999999);
		adminORGLocationAddress = faker.getAddress();
		adminORGLocationNote = faker.getJobNote();
		
		adminORGUnitParentID = faker.getRandomNumber(100, 999); 
		adminORGUnitParentName = faker.getJobCategory() + " " + faker.getRandomNumber(0, 100);
		adminORGUnitParentDescription = faker.getJobDescription();
		adminORGUnitChildID = faker.getRandomNumber(100, 999); 
		adminORGUnitChildName = faker.getJobCategory() + " " + faker.getRandomNumber(0, 100);
		adminORGUnitChildDescription = faker.getRandomNumber(100, 999);
		
		adminQUALSkillName = faker.getProgrammingLanguageName() + " " + faker.getRandomNumber(0, 100);
		adminQUALSkillDescription = faker.getProgrammingLanguageCreator();
		
		adminQUALEduLevel = faker.getEducationLevel();
		
		adminQUALLicenseName = faker.getEducationLevel();
		
		adminQUALLanguageName = faker.getCountry() + " " + faker.getRandomNumber(0, 100);
		
		adminQUALMembershipName = "Membership "+ faker.getRandomNumber(0, 100);
		
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
	public void Admin_01_Organization_Information(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_01_Organization_Information");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Open 'Admin' page");
		dashboardPage.openMenuPage(driver, "Admin");
		userManagementPage = PageGeneratorManager.getUserManagementPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Click to 'General Information' Sub Menu");
		userManagementPage.openChildSubMenuPage(driver, "Organization", "General Information");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Click to 'Edit' switch");
		organizationPage = PageGeneratorManager.getOrganizationPage(driver);
		organizationPage.clickToSwitchOnByLabel(driver, "Edit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Organization Name' textbox with value is: '" + adminORGName + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGName, "Organization Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Registration Number' textbox with value is: '" + adminORGNumber + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGNumber, "Registration Number");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Tax ID' textbox with value is: '" + adminORGTax + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGTax, "Tax ID");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Phone' textbox with value is: '" + adminORGPhone + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGPhone, "Phone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Fax' textbox with value is: '" + adminORGFax + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGFax, "Fax");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Email' textbox with value is: '" + adminORGEmail + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGEmail, "Email");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Address Street 1' textbox with value is: '" + adminORGFirstAddress + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGFirstAddress, "Address Street 1");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Address Street 2' textbox with value is: '" + adminORGSecondAddress + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGSecondAddress, "Address Street 2");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'City' textbox with value is: '" + adminORGCity + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGCity, "City");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'State/Province' textbox with value is: '" + adminORGState + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGState, "State/Province");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Zip/Postal Code' textbox with value is: '" + adminORGZipCode + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGZipCode, "Zip/Postal Code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Select item in 'Country' dropdown with value is: '" + adminORGCountry + "'");
		organizationPage.selectItemInCustomDropdownByLabel(driver, adminORGCountry, "Country");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Enter to 'Notes' textarea with value is: '" + adminORGNote + "'");
		organizationPage.sendKeyToTextareaByLabel(driver, adminORGNote, "Notes");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Click to 'Save' button");
		organizationPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Sucessful Message' is displayed");
		Assert.assertEquals(organizationPage.getMessageTitle(driver), "Success");
		organizationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Organization Name' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Organization Name"), adminORGName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Registration Number' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Registration Number"), adminORGNumber);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Tax ID' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Tax ID"), adminORGTax);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Phone' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Phone"), adminORGPhone);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Fax' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Fax"), adminORGFax);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Email' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Email"), adminORGEmail);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Address Street 1' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Address Street 1"), adminORGFirstAddress);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Address Street 2' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Address Street 2"), adminORGSecondAddress);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'City' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "City"), adminORGCity);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'State/Province' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "State/Province"), adminORGState);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Zip/Postal Code' is correct");
		Assert.assertEquals(organizationPage.getTextboxValueByLabel(driver, "Zip/Postal Code"), adminORGZipCode);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Country' is correct");
		Assert.assertEquals(organizationPage.getSelectedItemInCustomDropDownByLabel(driver, "Country"), adminORGCountry);
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Information 01 - Step " + ++stepNumber + ": Verify 'Notes' is correct");
		Assert.assertEquals(organizationPage.getTextareaValueByLabel(driver, "Notes"), adminORGNote);
	}
	
	@Test(dependsOnMethods = "Admin_01_Organization_Information")
	public void Admin_02_Organization_Location(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_02_Organization_Location");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Click to 'Locations' Sub Menu");
		organizationPage.openChildSubMenuPage(driver, "Organization", "Locations");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Click to 'Add' button");
		organizationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminORGLocationName + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'City' textbox with value is: '" + adminORGLocationCity + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationCity, "City");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'State/Province' textbox with value is: '" + adminORGLocationState + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationState, "State/Province");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Zip/Postal Code' textbox with value is: '" + adminORGLocationZipCode + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationZipCode, "Zip/Postal Code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Select item in 'Country' dropdown with value is: '" + adminORGLocationCountry + "'");
		organizationPage.selectItemInCustomDropdownByLabel(driver, adminORGLocationCountry, "Country");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Phone' textbox with value is: '" + adminORGLocationPhone + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationPhone, "Phone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Fax' textbox with value is: '" + adminORGLocationFax + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationFax, "Fax");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Address' textarea with value is: '" + adminORGLocationAddress + "'");
		organizationPage.sendKeyToTextareaByLabel(driver, adminORGLocationAddress, "Address");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Notes' textarea with value is: '" + adminORGLocationNote + "'");
		organizationPage.sendKeyToTextareaByLabel(driver, adminORGLocationNote, "Notes");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Click to 'Save' button");
		organizationPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(organizationPage.getMessageTitle(driver), "Success");
		organizationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminORGLocationName + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Enter to 'City' textbox with value is: '" + adminORGLocationCity + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGLocationCity, "City");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Select item in 'Country' dropdown with value is: '" + adminORGLocationCountry + "'");
		organizationPage.selectItemInCustomDropdownByLabel(driver, adminORGLocationCountry, "Country");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Click to 'Search' button");
		organizationPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Verify 'Name' is correct");
		Assert.assertEquals(organizationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Name", "1"), adminORGLocationName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Verify 'City' is correct");
		Assert.assertEquals(organizationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "City", "1"), adminORGLocationCity);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Verify 'Country' is correct");
		Assert.assertEquals(organizationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Country", "1"), adminORGLocationCountry);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Location 02 - Step " + ++stepNumber + ": Verify 'Phone' is correct");
		Assert.assertEquals(organizationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Phone", "1"), adminORGLocationPhone);
	}
	
	@Test(dependsOnMethods = "Admin_01_Organization_Information")
	public void Admin_03_Organization_Structure(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_03_Organization_Structure");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Structure' Sub Menu");
		organizationPage.openChildSubMenuPage(driver, "Organization", "Structure");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Edit' switch");
		organizationPage.clickToSwitchOnByLabel(driver, "Edit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Add' button");
		organizationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Enter to 'Unit Id' textbox with value is: '" + adminORGUnitParentID + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGUnitParentID, "Unit Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminORGUnitParentName + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGUnitParentName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Enter to 'Description' textarea with value is: '" + adminORGUnitParentDescription + "'");
		organizationPage.sendKeyToTextareaByLabel(driver, adminORGUnitParentDescription, "Description");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Save' button");
		organizationPage.sleepInSecond(1);
		organizationPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(organizationPage.getMessageTitle(driver), "Success");
		organizationPage.waitForLoadingIconDisappear(driver);
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Verify 'Name' of parent node is correct");
		Assert.assertTrue(organizationPage.checkParentNoteAddedSuccessfully(adminORGUnitParentName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Add' button at '" + adminORGUnitParentName + "' node");
		organizationPage.clickToButtonNameInTreeNodeAtParentNodeValue(adminORGUnitParentName, "plus");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Enter to 'Unit Id' textbox with value is: '" + adminORGUnitChildID + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGUnitChildID, "Unit Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminORGUnitChildName + "'");
		organizationPage.sendKeyToTextboxByLabel(driver, adminORGUnitChildName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Enter to 'Description' textarea with value is: '" + adminORGUnitChildDescription + "'");
		organizationPage.sendKeyToTextareaByLabel(driver, adminORGUnitChildDescription, "Description");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Save' button");
		organizationPage.sleepInSecond(1);
		organizationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(organizationPage.getMessageTitle(driver), "Success");
		organizationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Click to 'Dropdrop' icon at '" + adminORGUnitParentName + "' node");
		organizationPage.clickToDropdownIconAtParentNodeValue(adminORGUnitParentName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Organization Structure 03 - Step " + ++stepNumber + ": Verify 'Name' of child node is correct");
		Assert.assertTrue(organizationPage.checkChildNoteAddedSuccessfully(adminORGUnitParentName, adminORGUnitChildName));
	}
	
	@Test(dependsOnMethods = "Admin_01_Organization_Information")
	public void Admin_04_Qualifications_Skills(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_04_Qualifications_Skills");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Click to 'Skills' Sub Menu");
		organizationPage.openChildSubMenuPage(driver, "Qualifications", "Skills");
		qualificationPage = PageGeneratorManager.getQualificationPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Click to 'Add' button");
		qualificationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminQUALSkillName + "'");
		qualificationPage.sendKeyToTextboxByLabel(driver, adminQUALSkillName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Enter to 'Description' textarea with value is: '" + adminQUALSkillDescription + "'");
		qualificationPage.sendKeyToTextareaByLabel(driver, adminQUALSkillDescription, "Description");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Click to 'Save' button");
		qualificationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(qualificationPage.getMessageTitle(driver), "Success");
		qualificationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Verify 'Name' is correct");
		String rowIndex = qualificationPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminQUALSkillName);
		Assert.assertEquals(qualificationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Name", rowIndex), adminQUALSkillName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Skills 04 - Step " + ++stepNumber + ": Verify 'Description' is correct");
		Assert.assertEquals(qualificationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Description", rowIndex), adminQUALSkillDescription);
	}
	
	@Test(dependsOnMethods = "Admin_04_Qualifications_Skills")
	public void Admin_05_Qualifications_Education(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_05_Qualifications_Education");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Education 05 - Step " + ++stepNumber + ": Click to 'Education' Sub Menu");
		qualificationPage.openChildSubMenuPage(driver, "Qualifications", "Education");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Education 05 - Step " + ++stepNumber + ": Click to 'Add' button");
		qualificationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Education 05 - Step " + ++stepNumber + ": Enter to 'Level' textbox with value is: '" + adminQUALEduLevel + "'");
		qualificationPage.sendKeyToTextboxByLabel(driver, adminQUALEduLevel, "Level");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Education 05 - Step " + ++stepNumber + ": Click to 'Save' button");
		qualificationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Education 05 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(qualificationPage.getMessageTitle(driver), "Success");
		qualificationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Education 05 - Step " + ++stepNumber + ": Verify 'Level' is correct");
		String rowIndex = qualificationPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminQUALEduLevel);
		Assert.assertEquals(qualificationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Level", rowIndex), adminQUALEduLevel);
	}
	
	@Test(dependsOnMethods = "Admin_04_Qualifications_Skills")
	public void Admin_06_Qualifications_Licenses(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_06_Qualifications_Licenses");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Licenses 06 - Step " + ++stepNumber + ": Click to 'Licenses' Sub Menu");
		qualificationPage.openChildSubMenuPage(driver, "Qualifications", "Licenses");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Licenses 06 - Step " + ++stepNumber + ": Click to 'Add' button");
		qualificationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Licenses 06 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminQUALLicenseName + "'");
		qualificationPage.sendKeyToTextboxByLabel(driver, adminQUALLicenseName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Licenses 06 - Step " + ++stepNumber + ": Click to 'Save' button");
		qualificationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Licenses 06 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(qualificationPage.getMessageTitle(driver), "Success");
		qualificationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Licenses 06 - Step " + ++stepNumber + ": Verify 'Name' is correct");
		String rowIndex = qualificationPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminQUALLicenseName);
		Assert.assertEquals(qualificationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Name", rowIndex), adminQUALLicenseName);
	}
	
	@Test(dependsOnMethods = "Admin_04_Qualifications_Skills")
	public void Admin_07_Qualifications_Languages(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_07_Qualifications_Languages");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Languages 07 - Step " + ++stepNumber + ": Click to 'Languages' Sub Menu");
		qualificationPage.openChildSubMenuPage(driver, "Qualifications", "Languages");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Languages 07 - Step " + ++stepNumber + ": Click to 'Add' button");
		qualificationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Languages 07 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminQUALLanguageName + "'");
		qualificationPage.sendKeyToTextboxByLabel(driver, adminQUALLanguageName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Languages 07 - Step " + ++stepNumber + ": Click to 'Save' button");
		qualificationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Languages 07 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(qualificationPage.getMessageTitle(driver), "Success");
		qualificationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Languages 07 - Step " + ++stepNumber + ": Verify 'Name' is correct");
		String rowIndex = qualificationPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminQUALLanguageName);
		Assert.assertEquals(qualificationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Name", rowIndex), adminQUALLanguageName);
	}
	
	@Test(dependsOnMethods = "Admin_04_Qualifications_Skills")
	public void Admin_08_Qualifications_Memberships(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Admin_08_Qualifications_Memberships");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Memberships 08 - Step " + ++stepNumber + ": Click to 'Memberships' Sub Menu");
		qualificationPage.openChildSubMenuPage(driver, "Qualifications", "Memberships");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Memberships 08 - Step " + ++stepNumber + ": Click to 'Add' button");
		qualificationPage.clickToButtonByText(driver, "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Memberships 08 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + adminQUALMembershipName + "'");
		qualificationPage.sendKeyToTextboxByLabel(driver, adminQUALMembershipName, "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Memberships 08 - Step " + ++stepNumber + ": Click to 'Save' button");
		qualificationPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Memberships 08 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(qualificationPage.getMessageTitle(driver), "Success");
		qualificationPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications Memberships 08 - Step " + ++stepNumber + ": Verify 'Name' is correct");
		String rowIndex = qualificationPage.getRowIndexInDataTableNameAtColumnValue(driver, "", adminQUALMembershipName);
		System.out.println("Row index = " + rowIndex);
		Assert.assertEquals(qualificationPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Membership", rowIndex), adminQUALMembershipName);
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
	Admin_OrganizationPO organizationPage;
	Admin_QualificationPO qualificationPage;
	
	String adminORGName, adminORGNumber, adminORGTax, adminORGPhone, adminORGFax, adminORGEmail, adminORGFirstAddress, adminORGSecondAddress,
	adminORGCity, adminORGState, adminORGZipCode, adminORGCountry, adminORGNote,
	
	adminORGLocationName, adminORGLocationCity, adminORGLocationState, adminORGLocationZipCode, adminORGLocationCountry,
	adminORGLocationPhone, adminORGLocationFax, adminORGLocationAddress, adminORGLocationNote,
	
	adminORGUnitParentID, adminORGUnitParentName, adminORGUnitParentDescription, adminORGUnitChildID, adminORGUnitChildName, adminORGUnitChildDescription,
	
	adminQUALSkillName, adminQUALSkillDescription,
	
	adminQUALEduLevel,
	
	adminQUALLicenseName,
	
	adminQUALLanguageName,
	
	adminQUALMembershipName;
	
}
