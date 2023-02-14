package factoryServer;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class BrowserStackFactory {
	
	public BrowserStackFactory(String browserName, String osName, String osVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVersion);
		capability.setCapability("browser", browserName);
		capability.setCapability("browser_version", "latest");
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("project", "Orange HRM");
		capability.setCapability("resolution", "1920x1080");
		capability.setCapability("name", "Run on " + osName + " | " + osVersion + " | " + browserName);
		
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstant().getBrowserStackURL()),capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
	private WebDriver driver;
	private String browserName;
	private String osName;
	private String osVersion;
}
