package factoryServer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class SauceLabFactory {
	
	public SauceLabFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platformName", osName);
		capability.setCapability("browserName", browserName);
		capability.setCapability("browserVersion", "latest");
		
		Map<String, Object> sauceOptions = new HashMap<>();
		if (osName.contains("Windows")) {
			sauceOptions.put("screenResolution", "1920x1080");
		} else {
			sauceOptions.put("screenResolution", "1920x1440");
		}
		sauceOptions.put("build", "USER-001");
		sauceOptions.put("name", "Run on " + browserName + " | " + osName);
		
		capability.setCapability("sauce:options", sauceOptions);
		
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstant().getSauceLabURL()),capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
	private WebDriver driver;
	private String browserName;
	private String osName;
}
