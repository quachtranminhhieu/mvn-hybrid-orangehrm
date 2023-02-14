package factoryBrowser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessFirefoxDriverManager implements IBrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.ANY);
		
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		options.addArguments("window-size=1920x1080");
		options.merge(capability);
		
		return new FirefoxDriver(options);
	}
	
}
