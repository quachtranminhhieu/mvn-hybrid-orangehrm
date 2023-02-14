package factoryServer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class LambdaFactory {
	
	public LambdaFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("browserName", browserName);
		capability.setCapability("browserVersion", "latest");
		capability.setCapability("record_video", "true");
	
		if (osName.contains("Windows")) {
			capability.setCapability("screenResolution", "1920x1080");
		} else {
			capability.setCapability("screenResolution", "2560x1440");
		}
		
		Map<String, Object> lambdaOptions = new HashMap<String, Object>();
		lambdaOptions.put("visual", true);
		lambdaOptions.put("video", true);
		lambdaOptions.put("platformName", osName);
		lambdaOptions.put("build", "USER-001");
		lambdaOptions.put("project", "Orange HRM");
		lambdaOptions.put("console", "true");
		lambdaOptions.put("name", "Run on " + osName + " | " + browserName);
		capability.setCapability("LT:Options", lambdaOptions);
		
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstant().getLambdaURL()),capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
		
	}
	
	private WebDriver driver;
	private String browserName;
	private String osName;
}
