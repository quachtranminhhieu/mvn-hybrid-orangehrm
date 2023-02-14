package factoryBrowser;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements IBrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.ANY);
		options.merge(capability);
		
		// Add extension
//		File chromeExtentions = new File(GlobalConstants.EXTENSION_PATH + "adblock_plus_3_14_0_0.crx");
//		options.addExtensions(chromeExtentions);

		// Disable browser log in Console
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		// Set browser language = Vietnamese
//		options.addArguments("--lang=vi");
		
		// Disable notifications popup
		options.addArguments("--disable-notifications");
		
		// Disable location popup
		options.addArguments("--disable-geolocation");
		
		// Disable infobar in chrome
		options.setExperimentalOption("useAutomationExtension", "false");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		// Disable Save Password popup
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		// Auto Save/Download
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", GlobalConstants.getGlobalConstant().getDownloadFilePath());
		options.setExperimentalOption("prefs", prefs);
		
		// Run Incognito
//		options.addArguments("--incognito");

		return new ChromeDriver(options);
	}
	
}
