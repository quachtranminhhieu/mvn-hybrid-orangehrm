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
import com.orangehrm.data.MyInfoData;
import com.orangehrm.data.MyInfoData.ContactDetails;
import com.orangehrm.data.MyInfoData.Department;
import com.orangehrm.data.MyInfoData.EmergencyContact;
import com.orangehrm.data.MyInfoData.Immigration;
import com.orangehrm.data.MyInfoData.Job;
import com.orangehrm.data.MyInfoData.Memberships;
import com.orangehrm.data.MyInfoData.PersonalDetails;
import com.orangehrm.data.MyInfoData.Qualifications;
import com.orangehrm.data.MyInfoData.ReportTo;
import com.orangehrm.data.MyInfoData.Salary;
import com.orangehrm.data.MyInfoData.Tax;

import commons.BaseTest;
import pageObjects.orangeHRM.AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import reportConfig.ExtentTestManagerV5;
import utilities.JsonHelper;

public class OrangeHRM_MyInfo extends BaseTest {

	@Parameters({ "environmentName", "serverName", "browserName", "ipAddress", "portNumber", "osName", "osVersion"})
	@BeforeClass
	public void beforeClass(@Optional("dev") String environmentName, @Optional("local") String serverName, @Optional("chrome") String browserName, 
		@Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		
		driver = getBrowserDriver(environmentName, serverName, browserName, ipAddress, portNumber, osName, osVersion);

		fileName = "cat.jpg";
		
		empPersonalDetails = MyInfoData.getPersonalDetails();
		empContactDetails = MyInfoData.getContactDetails();
		empEmergencyContact = MyInfoData.getEmergencyContact();
		empDepartment = MyInfoData.getDepartment();
		empImmigration = MyInfoData.getImmigration();
		empJob = MyInfoData.getJob();
		empSalary = MyInfoData.getSalary();
		empTax = MyInfoData.getTax();
		empReportTo = MyInfoData.getReportTo();
		empQualifications = MyInfoData.getQualifications();
		empMemberships = MyInfoData.getMemberships();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.closeAllTabExceptParent(driver, driver.getWindowHandle());

	}

	@BeforeMethod
	public void beforeMethod() {
		stepNumber = 0;
	}
	
	@Test
	public void Employee_01_Add_New_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_01_Add_New_Employee");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step " + ++stepNumber + ": Check status Create New User");
		Assert.assertTrue(OrangeHRM_CreateNewUser.resultOfCreateUser);
	}
	
	@Test(dependsOnMethods = "Employee_01_Add_New_Employee")
	public void Employee_02_Upload_Avatar(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_02_Upload_Avatar");	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Login with Employee Role");
		dashboardPage = loginPage.loginToSystem(driver, OrangeHRM_CreateNewUser.employeeUserName, OrangeHRM_CreateNewUser.employeePassword);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Click to 'My Info' Menu");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Click to change photo avatar");
		myInfoPage.clickToChangePhotoImage();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Upload new avatar");
		myInfoPage.uploadMultipleFiles(driver, fileName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Click to 'Save' button");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step " + ++stepNumber + ": Verify new Avatar is displayed");
		myInfoPage.waitForLoadingIconDisappear(driver);
		Assert.assertTrue(myInfoPage.isNewAvatarDisplayed());
	}
	
	@Test(dependsOnMethods = "Employee_01_Add_New_Employee")
	public void Employee_03_Personal_Details(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_03_Personal_Details");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Open 'Personal Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify fields at 'Personal Details' form are disabled");
		Assert.assertFalse(myInfoPage.isTextboxEnabledByLabel(driver, "Employee Id"));
//		Assert.assertFalse(myInfoPage.isTextboxEnabledByLabel(driver, "Driver's License Number"));
//		Assert.assertFalse(myInfoPage.isTextboxEnabledByLabel(driver, "SSN Number"));
		Assert.assertFalse(myInfoPage.isTextboxEnabledByLabel(driver, "SIN Number"));
		Assert.assertFalse(myInfoPage.isTextboxEnabledByLabel(driver, "Date of Birth"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Enter new value to 'First Name' textbox with value is: '" + empPersonalDetails.getEditEmpFirstName() + "'");
		myInfoPage.sendKeyToFirstNameTextbox(driver, empPersonalDetails.getEditEmpFirstName());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Enter new value to 'Last Name' textbox with value is: '" + empPersonalDetails.getEditEmpLastName() + "'");
		myInfoPage.sendKeyToLastNameTextbox(driver, empPersonalDetails.getEditEmpLastName());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Check to 'Gender' radio button with value is: '" + empPersonalDetails.getEditEmpGender() + "'");
		myInfoPage.checkToRadioButtonByLabel(driver, empPersonalDetails.getEditEmpGender());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Select new value to 'Marital Status' dropdown with value is: '" + empPersonalDetails.getEditEmpMaritalStatus() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empPersonalDetails.getEditEmpMaritalStatus(), "Marital Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Select new value to 'Nationality' dropdown with value is: '" + empPersonalDetails.getEditEmpNationality() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empPersonalDetails.getEditEmpNationality(), "Nationality");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Enter new value to 'Military Service' textbox with value is: '" + empPersonalDetails.getEditMilitaryService() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empPersonalDetails.getEditMilitaryService(), "Military Service");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Check to 'Smoker' checkbox");
		myInfoPage.checkToSmokerCheckbox();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Click to 'Save' button at 'Personal Details' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'First Name' textbox is updated successfully");
		Assert.assertEquals(myInfoPage.getFirstNameTextboxValue(driver), empPersonalDetails.getEditEmpFirstName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'Last Name' textbox is updated successfully");
		Assert.assertEquals(myInfoPage.getLastNameTextboxValue(driver), empPersonalDetails.getEditEmpLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'Gender' radio button is updated successfully");
		Assert.assertTrue(myInfoPage.isRadioButtonSelectedByLabel(driver, empPersonalDetails.getEditEmpGender()));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'Marital Status' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Marital Status"), empPersonalDetails.getEditEmpMaritalStatus());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'Nationality' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Nationality"), empPersonalDetails.getEditEmpNationality());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step " + ++stepNumber + ": Verify 'Employee ID' textbox value is correct");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Employee Id"), OrangeHRM_CreateNewUser.employeeID);
	}
	
	@Test(dependsOnMethods = "Employee_01_Add_New_Employee")
	public void Employee_04_Contact_Details(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_04_Contact_Details");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Open 'Contact Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'Street 1' textbox with value is: '" + empContactDetails.getEmpFirstAddress() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpFirstAddress(), "Street 1");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'Street 2' textbox with value is: '" + empContactDetails.getEmpSecondAddress() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpSecondAddress(), "Street 2");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'City' textbox with value is: '" + empContactDetails.getEmpCity() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpCity(), "City");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'State/Province' textbox with value is: '" + empContactDetails.getEmpState() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpState(), "State/Province");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'Zip/Postal Code' textbox with value is: '" + empContactDetails.getEmpZipCode() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpZipCode(), "Zip/Postal Code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Select item in 'Country' dropdown with value is: '" + empContactDetails.getEmpCountry() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empContactDetails.getEmpCountry(), "Country");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'Mobile' textbox with value is: '" + empContactDetails.getEmpMobile() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpMobile(), "Mobile");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Enter to 'Work Email' textbox with value is: '" + empContactDetails.getEmpWorkEmail() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empContactDetails.getEmpWorkEmail(), "Work Email");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Click to 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify Successful Message is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'Street 1' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Street 1"), empContactDetails.getEmpFirstAddress());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'Street 2' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Street 2"), empContactDetails.getEmpSecondAddress());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'City' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "City"), empContactDetails.getEmpCity());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'State/Province' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "State/Province"), empContactDetails.getEmpState());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'Zip/Postal Code' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Zip/Postal Code"), empContactDetails.getEmpZipCode());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'Country' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Country"), empContactDetails.getEmpCountry());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'Mobile' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Mobile"), empContactDetails.getEmpMobile());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step " + ++stepNumber + ": Verify 'Work Email' texbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Work Email"), empContactDetails.getEmpWorkEmail());
	}
	
	@Test(dependsOnMethods = "Employee_01_Add_New_Employee")
	public void Employee_05_Emergency_Contacts(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_05_Emergency_Contacts");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Open 'Emergency Contacts' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Click to 'Add' button at 'Assigned Emergency Contacts'");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Emergency Contacts", "Add");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + empEmergencyContact.getEmpEmergName() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empEmergencyContact.getEmpEmergName(), "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Enter to 'Relationship' textbox with value is: '" + empEmergencyContact.getEmpEmergRelp() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empEmergencyContact.getEmpEmergRelp(), "Relationship");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Enter to 'Home Telephone' textbox with value is: '" + empEmergencyContact.getEmpEmergHomePhone() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empEmergencyContact.getEmpEmergHomePhone(), "Home Telephone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Enter to 'Mobile' textbox with value is: '" + empEmergencyContact.getEmpEmergMobile() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empEmergencyContact.getEmpEmergMobile(), "Mobile");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Enter to 'Work Telephone' textbox with value is: '" + empEmergencyContact.getEmpEmergWorkPhone() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empEmergencyContact.getEmpEmergWorkPhone(), "Work Telephone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Click to 'Save' button at 'Save Emergency Contact' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Verify 'Name' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Emergency Contacts", "Name", "1"), empEmergencyContact.getEmpEmergName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Verify 'Relationship' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Emergency Contacts", "Relationship", "1"), empEmergencyContact.getEmpEmergRelp());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Verify 'Home Telephone' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Emergency Contacts", "Home Telephone", "1"), empEmergencyContact.getEmpEmergHomePhone());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Verify 'Mobile' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Emergency Contacts", "Mobile", "1"), empEmergencyContact.getEmpEmergMobile());

		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step " + ++stepNumber + ": Verify 'Work Telephone' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Emergency Contacts", "Work Telephone", "1"), empEmergencyContact.getEmpEmergWorkPhone());
	}
	
	@Test(dependsOnMethods = "Employee_01_Add_New_Employee")
	public void Employee_06_Dependents(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_06_Dependents");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Open 'Dependents' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Dependents");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Click to 'Add' button at 'Assigned Dependents'");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Dependents", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is: '" + empDepartment.getEmpDepName() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empDepartment.getEmpDepName(), "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Enter to 'Date of Birth' textbox with value is: '" + empDepartment.getEmpDepDOB() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empDepartment.getEmpDepDOB(), "Date of Birth");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Select item in 'Relationship' dropdown with value is: '" + empDepartment.getEmpDepRelp() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empDepartment.getEmpDepRelp(), "Relationship");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Click to 'Save' button at 'Add Dependent' form");
		myInfoPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Verify 'Sucessful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Verify 'Name' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Dependents", "Name", "1"), empDepartment.getEmpDepName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Verify 'Relationship' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Dependents", "Relationship", "1"), empDepartment.getEmpDepRelp());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step " + ++stepNumber + ": Verify 'Date of Birth' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Dependents", "Date of Birth", "1"), empDepartment.getEmpDepDOB());
	}
	
	@Test(dependsOnMethods = "Employee_01_Add_New_Employee")
	public void Employee_07_Edit_Immigration(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_07_Edit_Immigration");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration - Step " + ++stepNumber + ": Open 'Immigration' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Immigration");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Click to 'Add' button at 'Assigned Immigration Records'");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Immigration Records", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Check to 'Document' radio button with value is: '" + empImmigration.getEmpIMGDocument() + "'");
		myInfoPage.checkToRadioButtonByLabel(driver, empImmigration.getEmpIMGDocument());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Enter to 'Number' textbox with value is: '" + empImmigration.getEmpIMGNumber() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empImmigration.getEmpIMGNumber(), "Number");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Enter to in 'Issued Date' textbox with value is: '" + empImmigration.getEmpIMGIssuedDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empImmigration.getEmpIMGIssuedDate(), "Issued Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Enter to in 'Expiry Date' textbox with value is: '" + empImmigration.getEmpIMGExpiryDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empImmigration.getEmpIMGExpiryDate(), "Expiry Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Enter to 'Eligible Status' textbox with value is: '" + empImmigration.getEmpIMGStatus() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empImmigration.getEmpIMGStatus(), "Eligible Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Select item in 'Issued By' dropdown with value is: '" + empImmigration.getEmpIMGIssuedBy() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empImmigration.getEmpIMGIssuedBy(), "Issued By");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Enter to 'Eligible Review Date' textbox with value is: '" + empImmigration.getEmpIMGReviewDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empImmigration.getEmpIMGReviewDate(), "Eligible Review Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Click to 'Save' button");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Verify 'Document' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Immigration Records", "Document", "1"), empImmigration.getEmpIMGDocument().toUpperCase());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Verify 'Number' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Immigration Records", "Number", "1"), empImmigration.getEmpIMGNumber());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Verify 'Issued By' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Immigration Records", "Issued By", "1"), empImmigration.getEmpIMGIssuedBy());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Verify 'Issued Date' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Immigration Records", "Issued Date", "1"), empImmigration.getEmpIMGIssuedDate());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Immigration 07 - Step " + ++stepNumber + ": Verify 'Expiry Date' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Immigration Records", "Expiry Date", "1"), empImmigration.getEmpIMGExpiryDate());
	}
	
	@Test(dependsOnMethods = "Employee_03_Personal_Details")
	public void Employee_08_Edit_View_Job(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_08_Edit_View_Job");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Login with Admin role");
		loginPage= myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, JsonHelper.getEmployee().getAdminUsername(), JsonHelper.getEmployee().getAdminPassword());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Open 'Employee List' page");
		dashboardPage.openMenuPage(driver, "PIM");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empPersonalDetails.getEditEmpFullName() + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empPersonalDetails.getEditEmpFullName(), "Employee Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + OrangeHRM_CreateNewUser.employeeID + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, OrangeHRM_CreateNewUser.employeeID, "Employee Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify Employee Information displayed at 'Result Table'");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), OrangeHRM_CreateNewUser.employeeID);
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empPersonalDetails.getEditEmpFirstName());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empPersonalDetails.getEditEmpLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Click to 'Edit' button in 'Result Table'");
		employeeListPage.clickToButtonNameInDataTableNameAtColumnNameAndRowIndex(driver, "", "Actions", "1", "pencil");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Open 'Job' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Job");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Enter to 'Joined Date' textbox with value is: '" + empJob.getEmpJobJoined() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empJob.getEmpJobJoined(), "Joined Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Select item in 'Job Title' dropdown with value is: '" + empJob.getEmpJobTitle() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobTitle(), "Job Title");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Select item in 'Job Category' dropdown with value is: '" + empJob.getEmpJobCategory() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobCategory(), "Job Category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Select item in 'Sub Unit' dropdown with value is: '" + empJob.getEmpJobUnit() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobUnit(), "Sub Unit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Select item in 'Location' dropdown with value is: '" + empJob.getEmpJobLocation() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobLocation(), "Location");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Select item in 'Employment Status' dropdown with value is: '" + empJob.getEmpJobStatus() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobStatus(), "Employment Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Click to 'Include Employment Contract Details' switch");
		myInfoPage.clickToSwitchByLabel(driver, "Include Employment Contract Details");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Enter to 'Contract Start Date' textbox with value is: '" + empJob.getEmpJobStartDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empJob.getEmpJobStartDate(), "Contract Start Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Enter to 'Contract End Date' textbox with value is: '" + empJob.getEmpJobEndDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empJob.getEmpJobEndDate(), "Contract End Date");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Click to 'Save' button at 'Job Details' form");
		myInfoPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Joined Date' textbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Joined Date"), empJob.getEmpJobJoined());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Job Title' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Job Title"), empJob.getEmpJobTitle());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Job Category' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Job Category"), empJob.getEmpJobCategory());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Sub Unit' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Sub Unit"), empJob.getEmpJobUnit());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Location' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Location"), empJob.getEmpJobLocation());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Employement Status' dropdown is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownByLabel(driver, "Employment Status"), empJob.getEmpJobStatus());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Contract Start Date' textbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Contract Start Date"), empJob.getEmpJobStartDate());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 08 - Step " + ++stepNumber + ": Verify 'Contract End Date' textbox is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueByLabel(driver, "Contract End Date"), empJob.getEmpJobEndDate());
	}
	
	@Test(dependsOnMethods = {"Employee_03_Personal_Details", "Employee_08_Edit_View_Job"})
	public void Employee_09_Edit_View_Salary(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_09_Edit_View_Salary");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Open 'Salary' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Salary");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Click to 'Add' button at 'Salary' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Salary Components", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Enter to 'Salary Component' textbox with value is: '" + empSalary.getEmpSalaryComponent() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empSalary.getEmpSalaryComponent(), "Salary Component");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Select item in 'Pay Grade' dropdown with value is: '" + empSalary.getEmpSalaryGrade() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empSalary.getEmpSalaryGrade(), "Pay Grade");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Select item in 'Pay Frequency' dropdown with value is: '" + empSalary.getEmpSalaryFrequency() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empSalary.getEmpSalaryFrequency(), "Pay Frequency");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Select item in 'Currency' dropdown with value is: '" + empSalary.getEmpSalaryCurrency() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empSalary.getEmpSalaryCurrency(), "Currency");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Enter to 'Amount' textbox with value is: '" + empSalary.getEmpSalaryAmount() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empSalary.getEmpSalaryAmount(), "Amount");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Click to 'Include Direct Deposit Details' switch");
		myInfoPage.clickToSwitchByLabel(driver, "Include Direct Deposit Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Enter to 'Account Number' textbox with value is: '" + empSalary.getEmpSalaryAccNumb() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empSalary.getEmpSalaryAccNumb(), "Account Number");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Select item in 'Account Type' dropdown with value is: '" + empSalary.getEmpSalaryAccType() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empSalary.getEmpSalaryAccType(), "Account Type");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Enter to 'Routing Number' textbox with value is: '" + empSalary.getEmpSalaryRoutNum() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empSalary.getEmpSalaryRoutNum(), "Routing Number");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Enter to 'Amount' textbox with value is: '" + empSalary.getEmpSalaryAccAmount() + "'");
		myInfoPage.sendKeyToAmountTextboxInDepositDetailsForm(empSalary.getEmpSalaryAccAmount());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Click to 'Save' button at 'Add Salary Component' form");
		myInfoPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Verify 'Salary Component' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Salary Components", "Salary Component", "1"), empSalary.getEmpSalaryComponent());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Verify 'Amount' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Salary Components", "Amount", "1"), empSalary.getEmpSalaryAmount());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Verify 'Currency' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Salary Components", "Currency", "1"), empSalary.getEmpSalaryCurrency());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Verify 'Pay Frequency' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Salary Components", "Pay Frequency", "1"), empSalary.getEmpSalaryFrequency());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 09 - Step " + ++stepNumber + ": Verify 'Direct Deposit Amount' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Salary Components", "Direct Deposit Amount", "1"), empSalary.getEmpSalaryAccAmount() + ".00");
	}
	
	@Test(dependsOnMethods = {"Employee_03_Personal_Details", "Employee_08_Edit_View_Job"})
	public void Employee_10_Edit_View_Tax(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_10_Edit_View_Tax");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Open 'Tax Exemptions' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Tax Exemptions");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Select item in 'Status' dropdown in 'Federal Income Tax' form with value is: '" + empTax.getEmpTaxFedStatus() + "'");
		myInfoPage.selectItemInDropdownLabelInFormName(empTax.getEmpTaxFedStatus(), "Federal Income Tax", "Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Enter to 'Exemptions' textbox in 'Federal Income Tax' form with values is: '" + empTax.getEmpTaxFedExemption() + "'");
		myInfoPage.sendkeyToTextboxLabelInFormName(empTax.getEmpTaxFedExemption(), "Federal Income Tax", "Exemptions");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Select item in 'State' dropdown in 'State Income Tax' form with value is: '" + empTax.getEmpTaxState() + "'");
		myInfoPage.selectItemInDropdownLabelInFormName(empTax.getEmpTaxState(), "State Income Tax", "State");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Select item in 'Status' dropdown in 'State Income Tax' form with value is: '" + empTax.getEmpTaxStateStatus() + "'");
		myInfoPage.selectItemInDropdownLabelInFormName(empTax.getEmpTaxStateStatus(), "State Income Tax", "Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Enter to 'Exemptions' textbox in 'State Income Tax' form with values is: '" + empTax.getEmpTaxStateExemption() + "'");
		myInfoPage.sendkeyToTextboxLabelInFormName(empTax.getEmpTaxStateExemption(), "State Income Tax", "Exemptions");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Select item in 'Unemployment State' dropdown in 'State Income Tax' form with value is: '" + empTax.getEmpTaxStateUnemp() + "'");
		myInfoPage.selectItemInDropdownLabelInFormName(empTax.getEmpTaxStateUnemp(), "State Income Tax", "Unemployment State");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Select item in 'Work State' dropdown in 'State Income Tax' form with value is: '" + empTax.getEmpTaxStateWork() + "'");
		myInfoPage.selectItemInDropdownLabelInFormName(empTax.getEmpTaxStateWork(), "State Income Tax", "Work State");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Click to 'Save' button at 'Tax Exemptions' form");
		myInfoPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'Federal Income Tax - Status' is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownLabelInFormName("Federal Income Tax", "Status"), empTax.getEmpTaxFedStatus());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'Federal Income Tax - Exemptions' is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueLabelInFormName("Federal Income Tax", "Exemptions"), empTax.getEmpTaxFedExemption());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'State Income Tax - State' is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownLabelInFormName("State Income Tax", "State"), empTax.getEmpTaxState());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'State Income Tax - Status' is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownLabelInFormName("State Income Tax", "Status"), empTax.getEmpTaxStateStatus());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'State Income Tax - Exemptions' is updated successfully");
		Assert.assertEquals(myInfoPage.getTextboxValueLabelInFormName("State Income Tax", "Exemptions"), empTax.getEmpTaxStateExemption());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'State Income Tax - Unemployment State' is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownLabelInFormName("State Income Tax", "Unemployment State"), empTax.getEmpTaxStateUnemp());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 10 - Step " + ++stepNumber + ": Verify 'State Income Tax - Work State' is updated successfully");
		Assert.assertEquals(myInfoPage.getSelectedItemInCustomDropDownLabelInFormName("State Income Tax", "Work State"), empTax.getEmpTaxStateWork());
	}
	
	@Test(dependsOnMethods = {"Employee_03_Personal_Details", "Employee_08_Edit_View_Job"})
	public void Employee_11_Report_To(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_11_Report_To");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Add New Supervisor");
		myInfoPage.openSubMenuPage(driver, "PIM", "Add Employee");
		addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'First Name' textbox with value is: '" + empReportTo.getEmpRepSuperFirstName() + "'");
		addEmployeePage.sendKeyToFirstNameTextbox(driver, empReportTo.getEmpRepSuperFirstName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Last Name' textbox with value is: '" + empReportTo.getEmpRepSuperLastName() + "'");
		addEmployeePage.sendKeyToLastNameTextbox(driver, empReportTo.getEmpRepSuperLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Get value of 'Employee ID'");
		empReportTo.setEmpRepSuperID(addEmployeePage.getTextboxValueByLabel(driver, "Employee Id"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empReportTo.getEmpRepSuperID() + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empReportTo.getEmpRepSuperID(), "Employee Id");
		empReportTo.setEmpRepSuperID(addEmployeePage.checkEmployeeIDAlreadyExists(driver, empReportTo.getEmpRepSuperID()));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Save' button");
		addEmployeePage.clickToButtonByText(driver, "Save");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empReportTo.getEmpRepSuperFullName() + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empReportTo.getEmpRepSuperFullName(), "Employee Name");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empReportTo.getEmpRepSuperID() + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, empReportTo.getEmpRepSuperID(), "Employee Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify Supervisor Information is displayed at 'Result Table'");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), empReportTo.getEmpRepSuperID());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empReportTo.getEmpRepSuperFirstName());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empReportTo.getEmpRepSuperLastName());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Add New Supervisor");
		employeeListPage.openSubMenuPage(driver, "PIM", "Add Employee");
		addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'First Name' textbox with value is: '" + empReportTo.getEmpRepSuborFirstName() + "'");
		addEmployeePage.sendKeyToFirstNameTextbox(driver, empReportTo.getEmpRepSuborFirstName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Last Name' textbox with value is: '" + empReportTo.getEmpRepSuborLastName() + "'");
		addEmployeePage.sendKeyToLastNameTextbox(driver, empReportTo.getEmpRepSuborLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Get value of 'Employee Id'");
		empReportTo.setEmpRepSuborID(addEmployeePage.getTextboxValueByLabel(driver, "Employee Id"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empReportTo.getEmpRepSuborID() + "'");
		addEmployeePage.sendKeyToTextboxByLabel(driver, empReportTo.getEmpRepSuborID(), "Employee Id");
		empReportTo.setEmpRepSuborID(addEmployeePage.checkEmployeeIDAlreadyExists(driver, empReportTo.getEmpRepSuborID()));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Save' button");
		addEmployeePage.clickToButtonByText(driver, "Save");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empReportTo.getEmpRepSuborFullName() + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empReportTo.getEmpRepSuborFullName(), "Employee Name");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + empReportTo.getEmpRepSuborID() + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, empReportTo.getEmpRepSuborID(), "Employee Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify Supervisor Information is displayed at 'Result Table'");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), empReportTo.getEmpRepSuborID());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empReportTo.getEmpRepSuborFirstName());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empReportTo.getEmpRepSuborLastName());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empPersonalDetails.getEditEmpFullName() + "'");
		employeeListPage.refreshCurrentPage(driver);
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empPersonalDetails.getEditEmpFullName(), "Employee Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + OrangeHRM_CreateNewUser.employeeID + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, OrangeHRM_CreateNewUser.employeeID, "Employee Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify Employee Information displayed at 'Result Table'");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), OrangeHRM_CreateNewUser.employeeID);
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empPersonalDetails.getEditEmpFirstName());
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empPersonalDetails.getEditEmpLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Edit' button in 'Result Table'");
		employeeListPage.clickToButtonNameInDataTableNameAtColumnNameAndRowIndex(driver, "", "Actions", "1", "pencil");
		myInfoPage = PageGeneratorManager.getMyInfoPage(driver);
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Open 'Report-to' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Report-to");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Add' button at 'Assigned Supervisors' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Supervisors", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is : '" + empReportTo.getEmpRepSuperFullName() + "'");
		myInfoPage.sendKeyToTextboxAndClickResultByLabel(driver, empReportTo.getEmpRepSuperFullName(), "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Select item in 'Reporting Method' dropdown with value is : '" + empReportTo.getEmpRepSuperMethod() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empReportTo.getEmpRepSuperMethod(), "Reporting Method");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Save' button at 'Add Supervisor' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Successfull Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Name' in 'Assigned Supervisors' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Supervisors", "Name", "1"), empReportTo.getEmpRepSuperFullName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Reporting Method' in 'Assigned Supervisors' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Supervisors", "Reporting Method", "1"), empReportTo.getEmpRepSuperMethod());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Add' button at 'Assigned Subordinates' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Subordinates", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Enter to 'Name' textbox with value is : '" + empReportTo.getEmpRepSuborFullName() + "'");
		myInfoPage.sendKeyToTextboxAndClickResultByLabel(driver, empReportTo.getEmpRepSuborFullName(), "Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Select item in 'Reporting Method' dropdown with value is : '" + empReportTo.getEmpRepSuborMethod() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empReportTo.getEmpRepSuborMethod(), "Reporting Method");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Click to 'Save' button at 'Add Subordinate' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify Successfull Message is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Name' in 'Assigned Subordinates' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Subordinates", "Name", "1"), empReportTo.getEmpRepSuborFullName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step " + ++stepNumber + ": Verify 'Reporting Method' in 'Assigned Subordinates' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Subordinates", "Reporting Method", "1"), empReportTo.getEmpRepSuborMethod());
	}
	
	@Test(dependsOnMethods = {"Employee_03_Personal_Details", "Employee_08_Edit_View_Job"})
	public void Employee_12_Qualifications(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_12_Qualifications");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Open 'Qualifications' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Qualifications");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Add' button at 'Work Experience' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Work Experience", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'From' textbox with value is: '" + empQualifications.getEmpQualExpFrom() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualExpFrom(), "From");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'To' textbox with value is: '" + empQualifications.getEmpQualExpTo() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualExpTo(), "To");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Company' textbox with value is: '" + empQualifications.getEmpQualExpCompany() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualExpCompany(), "Company");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Job Title' textbox with value is: '" + empQualifications.getEmpQualExpJobTitle() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualExpJobTitle(), "Job Title");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Save' button at 'Work Experience' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Successfull Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Company' is updated successfully in 'Work Expericence' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Work Experience", "Company", "1"), empQualifications.getEmpQualExpCompany());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Job Title' is updated successfully in 'Work Expericence' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Work Experience", "Job Title", "1"), empQualifications.getEmpQualExpJobTitle());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'From' is updated successfully in 'Work Expericence' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Work Experience", "From", "1"), empQualifications.getEmpQualExpFrom());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'To' is updated successfully in 'Work Expericence' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Work Experience", "To", "1"), empQualifications.getEmpQualExpTo());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Add' button at 'Education' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Education", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Institute' textbox with value is: '" + empQualifications.getEmpQualEduInst() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualEduInst(), "Institute");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Major/Specialization' textbox with value is: '" + empQualifications.getEmpQualEduMajor() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualEduMajor(), "Major/Specialization");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Year' textbox with value is: '" + empQualifications.getEmpQualEduYear() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualEduYear(), "Year");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Start Date' textbox with value is: '" + empQualifications.getEmpQualEduStart() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualEduStart(), "Start Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'End Date' textbox with value is: '" + empQualifications.getEmpQualEduEnd() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualEduEnd(), "End Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'GPA/Score' textbox with value is: '" + empQualifications.getEmpQualEduGPA() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualEduGPA(), "GPA/Score");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Select item in 'Level' dropdown with value is: '" + empQualifications.getEmpQualEduLevel() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empQualifications.getEmpQualEduLevel(), "Level");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Save' button at 'Education' form");
		myInfoPage.clickToButtonByText(driver, "Save");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Level' is updated successfully in 'Education' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Education", "Level", "1"), empQualifications.getEmpQualEduLevel());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Year' is updated successfully in 'Education' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Education", "Year", "1"), empQualifications.getEmpQualEduYear());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'GPA/Score' is updated successfully in 'Education' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Education", "GPA/Score", "1"), empQualifications.getEmpQualEduGPA());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Add' button at 'Skills' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Skills", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Years of Experience' textbox with value is: '" + empQualifications.getEmpQualSkillYear() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualSkillYear(), "Years of Experience");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Select item in 'Skill' dropdown with value is: '" + empQualifications.getEmpQualSkill() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empQualifications.getEmpQualSkill(), "Skill");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Save' button at 'Skills' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Skill' is updated successfully in 'Skills' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Skills", "Skill", "1"), empQualifications.getEmpQualSkill());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Years of Experience' is updated successfully in 'Skills' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Skills", "Years of Experience", "1"), empQualifications.getEmpQualSkillYear());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Add' button at 'Languages' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Languages", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Select item in 'Language' dropdown with value is: '" + empQualifications.getEmpQualLang() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empQualifications.getEmpQualLang(), "Language");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Select item in 'Fluency' dropdown with value is: '" + empQualifications.getEmpQualLangFluency() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empQualifications.getEmpQualLangFluency(), "Fluency");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Select item in 'Competency' dropdown with value is: '" + empQualifications.getEmpQualLangCompetency() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empQualifications.getEmpQualLangCompetency(), "Competency");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Save' button at 'Languages' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Language' is updated successfully in 'Languages' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Languages", "Language", "1"), empQualifications.getEmpQualLang());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Fluency' is updated successfully in 'Languages' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Languages", "Fluency", "1"), empQualifications.getEmpQualLangFluency());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Competency' is updated successfully in 'Languages' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Languages", "Competency", "1"), empQualifications.getEmpQualLangCompetency());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Add' button at 'License' form");
		myInfoPage.clickToButtonByTextInForm(driver, "License", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Select item in 'License Type' dropdown with value is: '" + empQualifications.getEmpQualLicType() + "'");
		myInfoPage.sleepInSecond(1);
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empQualifications.getEmpQualLicType(), "License Type");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Issued Date' textbox with value is: '" + empQualifications.getEmpQualLicIssued() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualLicIssued(), "Issued Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'Expiry Date' textbox with value is: '" + empQualifications.getEmpQualLicExpiry() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualLicExpiry(), "Expiry Date");
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Enter to 'License Number' textbox with value is: '" + empQualifications.getEmpQualLicNumber() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empQualifications.getEmpQualLicNumber(), "License Number");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Click to 'Save' button at 'License' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'License Type' is updated successfully in 'License' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "License", "License Type", "1"), empQualifications.getEmpQualLicType());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Issued Date' is updated successfully in 'License' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "License", "Issued Date", "1"),empQualifications. getEmpQualLicIssued());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 12 - Step " + ++stepNumber + ": Verify 'Expiry Date' is updated successfully in 'License' data table");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "License", "Expiry Date", "1"), empQualifications.getEmpQualLicExpiry());
	}
	
	@Test(dependsOnMethods = {"Employee_03_Personal_Details", "Employee_08_Edit_View_Job"})
	public void Employee_13_Memberships(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_13_Memberships");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Open 'Memberships' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Memberships");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Click to 'Add' button at 'Assigned Memberships' form");
		myInfoPage.clickToButtonByTextInForm(driver, "Assigned Memberships", "Add");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Select item in 'Membership' dropdown with value is : '" + empMemberships.getEmpMemType() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empMemberships.getEmpMemType(), "Membership");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Select item in 'Subscription Paid By' dropdown with value is : '" + empMemberships.getEmpMemSubPaidBy() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empMemberships.getEmpMemSubPaidBy(), "Subscription Paid By");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Enter to 'Subscription Amount' textbox with value is : '" + empMemberships.getEmpMemSubAmount() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empMemberships.getEmpMemSubAmount(), "Subscription Amount");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Enter to 'Subscription Commence Date' textbox with value is : '" + empMemberships.getEmpMemSubCommenceDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empMemberships.getEmpMemSubCommenceDate(), "Subscription Commence Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Enter to 'Subscription Renewal Date' textbox with value is : '" + empMemberships.getEmpMemSubRenewalDate() + "'");
		myInfoPage.sendKeyToTextboxByLabel(driver, empMemberships.getEmpMemSubRenewalDate(), "Subscription Renewal Date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Select item in 'Currency' dropdown with value is : '" + empMemberships.getEmpMemCurrency() + "'");
		myInfoPage.selectItemInCustomDropdownByLabel(driver, empMemberships.getEmpMemCurrency(), "Currency");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Click to 'Save' button at 'Add Membership' form");
		myInfoPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ": Verify 'Successful Message' is displayed");
		Assert.assertEquals(myInfoPage.getMessageTitle(driver), "Success");
		myInfoPage.waitForLoadingIconDisappear(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ":  Verify 'Membership' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Memberships", "Membership", "1"), empMemberships.getEmpMemType());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ":  Verify 'Subscription Paid By' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Memberships", "Subscription Paid By", "1"), empMemberships.getEmpMemSubPaidBy());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ":  Verify 'Subscription Amount' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Memberships", "Subscription Amount", "1"), empMemberships.getEmpMemSubAmount() + ".00");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ":  Verify 'Currency' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Memberships", "Currency", "1"), empMemberships.getEmpMemCurrency());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ":  Verify 'Subscription Commence Date' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Memberships", "Subscription Commence Date", "1"), empMemberships.getEmpMemSubCommenceDate());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Memberships 13 - Step " + ++stepNumber + ":  Verify 'Subscription Renewal Date' is updated successfully");
		Assert.assertEquals(myInfoPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "Assigned Memberships", "Subscription Renewal Date", "1"), empMemberships.getEmpMemSubRenewalDate());
	}
	
	@Test(dependsOnMethods = {"Employee_03_Personal_Details", "Employee_08_Edit_View_Job", "Employee_11_Report_To"})
	public void Employee_14_Verify_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_14_Verify_Employee");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Enter to 'Employee Name' textbox with value is: '" + empPersonalDetails.getEditEmpFullName() + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empPersonalDetails.getEditEmpFullName(), "Employee Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Enter to 'Employee Id' textbox with value is: '" + OrangeHRM_CreateNewUser.employeeID + "'");
		employeeListPage.sendKeyToTextboxByLabel(driver, OrangeHRM_CreateNewUser.employeeID, "Employee Id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Select item in 'Employement Status' dropdown with value is: '" + empJob.getEmpJobStatus() + "'");
		employeeListPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobStatus(), "Employment Status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Enter to 'Supervisor Name' textbox with value is: '" + empReportTo.getEmpRepSuperFullName() + "'");
		employeeListPage.sendKeyToTextboxAndClickResultByLabel(driver, empReportTo.getEmpRepSuperFullName(), "Supervisor Name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Select item in 'Job Title' dropdown with value is: '" + empJob.getEmpJobTitle() + "'");
		employeeListPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobTitle(), "Job Title");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Select item in 'Sub Unit' dropdown with value is: '" + empJob.getEmpJobUnit() + "'");
		employeeListPage.selectItemInCustomDropdownByLabel(driver, empJob.getEmpJobUnit(), "Sub Unit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Click to 'Search' button");
		employeeListPage.clickToButtonByText(driver, "Search");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'Id' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Id", "1"), OrangeHRM_CreateNewUser.employeeID);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'First (& Middle) Name' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "First (& Middle) Name", "1"), empPersonalDetails.getEditEmpFirstName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'Last Name' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Last Name", "1"), empPersonalDetails.getEditEmpLastName());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'Job Title' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Job Title", "1"), empJob.getEmpJobTitle());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'Employment Status' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Employment Status", "1"), empJob.getEmpJobStatus());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'Sub Unit' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Sub Unit", "1"), empJob.getEmpJobUnit());
	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 14 - Step " + ++stepNumber + ": Verify 'Supervisor' is correct");
		Assert.assertEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex(driver, "", "Supervisor", "1"), empReportTo.getEmpRepSuperFullName());
	}
	
	@Parameters("serverName")
	@AfterClass(alwaysRun = true)
	public void afterClass(@Optional("local") String serverName) {
		closeBrowserAndDriver(serverName);
	}

	private WebDriver driver;
	private int stepNumber;
	private String fileName;
	
	private PersonalDetails empPersonalDetails;
	private ContactDetails empContactDetails;
	private EmergencyContact empEmergencyContact;
	private Department empDepartment;
	private Immigration empImmigration;
	private Job empJob;
	private Salary empSalary;
	private Tax empTax;
	private ReportTo empReportTo;
	private Qualifications empQualifications;
	private Memberships empMemberships;
	
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	private AddEmployeePO addEmployeePage;
	private MyInfoPO myInfoPage;
}
