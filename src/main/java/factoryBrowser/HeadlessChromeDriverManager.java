package factoryBrowser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessChromeDriverManager implements IBrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.ANY);
		
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		options.addArguments("window-size=1920x1080");
		
		return new ChromeDriver(options);
	}
	
}
