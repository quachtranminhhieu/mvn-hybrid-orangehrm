package com.orangehrm.data;

import lombok.Getter;
import lombok.Setter;
import utilities.DataUtil;

public class EmployeeData {
	
	public static EmployeeInformation getEmployeeInformation() {
		return EMPLOYEE_INSTANCE.new EmployeeInformation();
	}
	
	@Getter
	@Setter
	public class EmployeeInformation {
		private String empID = "";
		private String empFirstName = FAKER.getFirstName().replace("'", "");
		private String empLastName = FAKER.getLastName().replace("'", "");
		private String empFullName = empFirstName + " " + empLastName;
		private String empEmail = FAKER.getEmailAddress();
		private String empUserName = FAKER.getUserName();
		private String empPassword = FAKER.getStrongPassword();
		private String empStatusValue = "Enabled";
	}
	
	public static final DataUtil FAKER = DataUtil.getData();
	public static final EmployeeData EMPLOYEE_INSTANCE = new EmployeeData();
}
