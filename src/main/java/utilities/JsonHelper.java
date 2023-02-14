package utilities;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class JsonHelper {
	public static JsonHelper getEmployee() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.getGlobalConstant().getProjectPath() + "\\src\\test\\java\\com\\orangeHRM\\data\\EmployeeData.json"), JsonHelper.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@JsonProperty("projectName")
	private String projectName;
	
	@JsonProperty("Role")
	Role role;
	
	public class Role {
		@JsonProperty("adminusername")
		private String adminUsername;

		@JsonProperty("adminpassword")
		private String adminPassword;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public String getAdminUsername() {
		return role.adminUsername;
	}

	public String getAdminPassword() {
		return role.adminPassword;
	}
	
}
