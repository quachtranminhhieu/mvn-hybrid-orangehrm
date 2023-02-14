package factoryBrowser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import commons.GlobalConstants;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SafariDriverManager implements IBrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		if (!IS_OS_MAC) {
			throw new BrowserNotSupportedException("Safari is not supported on " + GlobalConstants.getGlobalConstant().getOsName());
		}
		WebDriverManager.safaridriver().setup();
		
		DesiredCapabilities capability = DesiredCapabilities.safari();
		capability.setBrowserName("safari");
		capability.setPlatform(Platform.MAC);
		capability.setJavascriptEnabled(true);
		
		SafariOptions options = new SafariOptions();
		options.merge(capability);
		options.setCapability("safari.cleanSession", true);
		
		return new SafariDriver(options);
	}
	
}
