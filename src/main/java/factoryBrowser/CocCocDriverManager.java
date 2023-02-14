package factoryBrowser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CocCocDriverManager implements IBrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().driverVersion("98.0.4758.48").setup();
		
		ChromeOptions options = new ChromeOptions();
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WINDOWS);
		options.merge(capability);
		
		options.setBinary("C:\\Users\\Dell\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
		
		return new ChromeDriver(options);
	}
	
}
