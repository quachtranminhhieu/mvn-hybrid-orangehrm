package com.orangehrm.data;

import lombok.Getter;
import lombok.Setter;
import utilities.DataUtil;

public class EmployeeData {
	
	public static EmployeeInformation getEmployeeInformation() {
		return EMPLOYEE_INSTANCE.new EmployeeInformation();
	}
	
	public static PersonalDetails getPersonalDetails() {
		return EMPLOYEE_INSTANCE.new PersonalDetails();
	}
	
	public static ContactDetails getContactDetails() {
		return EMPLOYEE_INSTANCE.new ContactDetails();
	}
	
	public static EmergencyContact getEmergencyContact() {
		return EMPLOYEE_INSTANCE.new EmergencyContact();
	}
	
	public static Department getDepartment() {
		return EMPLOYEE_INSTANCE.new Department();
	}
	
	public static Immigration getImmigration() {
		return EMPLOYEE_INSTANCE.new Immigration();
	}
	
	public static Job getJob() {
		return EMPLOYEE_INSTANCE.new Job();
	}
	
	public static Salary getSalary() {
		return EMPLOYEE_INSTANCE.new Salary();
	}
	
	public static Tax getTax() {
		return EMPLOYEE_INSTANCE.new Tax();
	}
	
	public static ReportTo getReportTo() {
		return EMPLOYEE_INSTANCE.new ReportTo();
	}
	
	public static Qualifications getQualifications() {
		return EMPLOYEE_INSTANCE.new Qualifications();
	}
	
	public static Memberships getMemberships() {
		return EMPLOYEE_INSTANCE.new Memberships();
	}
	
	@Getter
	@Setter
	public class EmployeeInformation {
		private String empID = "";
		private String empFirstName = FAKER.getFirstName();
		private String empLastName = FAKER.getLastName();
		private String empFullName = empFirstName + " " + empLastName;
		private String empEmail = FAKER.getEmailAddress();
		private String empUserName = FAKER.getUserName();
		private String empPassword = FAKER.getStrongPassword();
		private String empStatusValue = "Enabled";
	}
	
	@Getter
	@Setter
	public class PersonalDetails {
		private String editEmpFirstName = FAKER.getFirstName().replace("'", "");
		private String editEmpLastName = FAKER.getLastName().replace("'", "");
		private String editEmpFullName = editEmpFirstName + " " + editEmpLastName;
		private String editEmpGender = FAKER.getGender();
		private String editEmpMaritalStatus = "Married";
		private String editEmpNationality = "Vietnamese";
		private String editMilitaryService = "Unknown";
	}
	
	@Getter
	@Setter
	public class ContactDetails {
		private String empFirstAddress = FAKER.getAddress().replace("'", "");
		private String empSecondAddress = FAKER.getAddress().replace("'", "");
		private String empCity = FAKER.getCity(); 
		private String empState = FAKER.getState().replace("'", ""); 
		private String empZipCode = FAKER.getPostalCode();
		private String empCountry = "Viet Nam";
		private String empMobile = FAKER.getRandomNumber(100000000,999999999); 
		private String empWorkEmail = FAKER.getEmailAddress();
	}
	
	@Getter
	@Setter
	public class EmergencyContact {
		private String empEmergName = FAKER.getFullName().replace("'", ""); 
		private String empEmergRelp = "Brother"; 
		private String empEmergHomePhone = FAKER.getRandomNumber(100000000,999999999);  
		private String empEmergMobile = FAKER.getRandomNumber(100000000,999999999);  
		private String empEmergWorkPhone = FAKER.getRandomNumber(100000000,999999999); 
	}
	
	@Getter
	@Setter
	public class Department {
		private String empDepName = FAKER.getFullName().replace("'", "");
		private String empDepRelp = "Child"; 
		private String empDepDOB = FAKER.getBirthDate();
	}
	
	@Getter
	@Setter
	public class Immigration {
		private String empIMGDocument = "Visa"; 
		private String empIMGNumber = FAKER.getRandomNumber(100000, 999999);
		private String empIMGIssuedDate = FAKER.getPastDate(); 
		private String empIMGExpiryDate = FAKER.getFutureDate(); 
		private String empIMGStatus = "Enabled"; 
		private String empIMGIssuedBy = "Viet Nam"; 
		private String empIMGReviewDate = FAKER.getFutureDate();
	}
	
	@Getter
	@Setter
	public class Job {
		private String empJobTitle = "Software Architect"; 
		private String empJobStatus = "Full-Time Contract"; 
		private String empJobCategory = "Professionals"; 
		private String empJobJoined = FAKER.getPastDate();
		private String empJobUnit = "Quality Assurance";
		private String empJobLocation = "Texas R&D"; 
		private String empJobStartDate = FAKER.getPastDate(); 
		private String empJobEndDate = FAKER.getFutureDate();
	}
	
	@Getter
	@Setter
	public class Salary {
		private String empSalaryGrade = "Grade 2"; 
		private String empSalaryComponent = "Basic"; 
		private String empSalaryFrequency = "Monthly on first pay of month."; 
		private String empSalaryCurrency = "United States Dollar"; 
		private String empSalaryAmount = FAKER.getRandomNumber(40000,50000); 
		private String empSalaryAccNumb = FAKER.getRandomNumber(100000000,999999999); 
		private String empSalaryAccType = "Savings"; 
		private String empSalaryRoutNum = FAKER.getRandomNumber(100000000,999999999);  
		private String empSalaryAccAmount = FAKER.getRandomNumber(100000000,999999999); 
	}
	
	@Getter
	@Setter
	public class Tax {
		private String empTaxFedStatus = "Married"; 
		private String empTaxFedExemption = FAKER.getRandomNumber(10,99);  
		private String empTaxState = "Ohio"; 
		private String empTaxStateStatus = "Non Resident Alien"; 
		private String empTaxStateExemption = FAKER.getRandomNumber(10,99); 
		private String empTaxStateUnemp = "Florida"; 
		private String empTaxStateWork = "Washington";
	}
	
	@Getter
	@Setter
	public class ReportTo {
		private String empRepSuperID = "";
		private String empRepSuperFirstName = FAKER.getFirstName().replace("'", "");
		private String empRepSuperLastName = FAKER.getLastName().replace("'", "");
		private String empRepSuperFullName  = empRepSuperFirstName + " " + empRepSuperLastName;
		private String empRepSuperMethod = "Direct";
		private String empRepSuborID = "";
		private String empRepSuborFirstName = FAKER.getFirstName().replace("'", "");
		private String empRepSuborLastName = FAKER.getLastName().replace("'", "");
		private String empRepSuborFullName = empRepSuborFirstName + " " + empRepSuborLastName;
		private String empRepSuborMethod = "Indirect";
		
	}
	
	@Getter
	@Setter
	public class Qualifications {
		private String empQualExpCompany = "Automation Company"; 
		private String empQualExpJobTitle = "QC Engineer"; 
		private String empQualExpFrom = FAKER.getPastDate();
		private String empQualExpTo = FAKER.getFutureDate(); 
		private String empQualEduLevel = "Master's Degree"; 
		private String empQualEduInst = "Banking Institute"; 
		private String empQualEduMajor = "Management Information System"; 
		private String empQualEduYear = FAKER.getRandomDigit();
		private String empQualEduGPA = FAKER.getRandomDouble(1,1,9); 
		private String empQualEduStart = FAKER.getPastDate();
		private String empQualEduEnd = FAKER.getFutureDate();
		private String empQualSkill = "Java"; 
		private String empQualSkillYear = FAKER.getRandomDigit();
		private String empQualLang = "Russian"; 
		private String empQualLangFluency = "Speaking"; 
		private String empQualLangCompetency = "Good";
		private String empQualLicType = "Cisco Certified Network Professional (CCNP)"; 
		private String empQualLicNumber = FAKER.getRandomNumber(100000000,999999999); 
		private String empQualLicIssued = FAKER.getPastDate(); 
		private String empQualLicExpiry = FAKER.getFutureDate();
	}
	
	@Getter
	@Setter
	public class Memberships {
		private String empMemType = "British Computer Society (BCS)";
		private String empMemSubPaidBy = "Company";
		private String empMemSubAmount = FAKER.getRandomNumber(1, 999999999);
		private String empMemCurrency = "Vietnamese Dong"; 
		private String empMemSubCommenceDate = FAKER.getPastDate();
		private String empMemSubRenewalDate = FAKER.getFutureDate();
	}
	
	public static final DataUtil FAKER = DataUtil.getData();
	public static final EmployeeData EMPLOYEE_INSTANCE = new EmployeeData();
}
