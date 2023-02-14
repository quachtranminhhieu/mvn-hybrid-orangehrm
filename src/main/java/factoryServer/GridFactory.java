package factoryServer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;
import factoryBrowser.BrowserList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {

	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}
	
	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

		DesiredCapabilities capability = null;

		switch (browser) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions f_options = new FirefoxOptions();
			
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			
			f_options.addPreference("browser.download.folderList", 2);
			f_options.addPreference("browser.download.dir", GlobalConstants.getGlobalConstant().getDownloadFilePath());
			f_options.addPreference("browser.download.useDownloadDir", true);
			f_options.addPreference("browser.helperApps.neverAsk.saveToDisk",
					"multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,"
							+ "text/csv,image/png ,image/jpeg, application/pdf, text/html, text/plain, application/excel, application/vnd.ms-excel, "
							+ "application/x-excel, application/x-msexcel, application/octet-stream");
			f_options.addPreference("pdfjs.disabled", true);

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstant().getBrowserLogPath() + "Firefox.log");
			
			f_options.merge(capability);

			break;
		case H_FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions h_f_options = new FirefoxOptions();
			h_f_options.addArguments("--headless");
			h_f_options.addArguments("window-size=1920x1080");

			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			h_f_options.merge(capability);

			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();

			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);

			ChromeOptions c_options = new ChromeOptions();
			c_options.setExperimentalOption("useAutomationExtension", "false");
			c_options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			c_options.addArguments("--disable-notifications");

			c_options.addArguments("--disable-geolocation");

			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			c_options.setExperimentalOption("prefs", prefs);
			
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", GlobalConstants.getGlobalConstant().getDownloadFilePath());
			c_options.setExperimentalOption("prefs", prefs);

			c_options.merge(capability);

			break;
			
		case H_CHROME:
			WebDriverManager.chromedriver().setup();
			
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);
			
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1920x1080");
			
			options.merge(capability);
			
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("98.0.4758.48").setup();

			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);

			ChromeOptions cc_options = new ChromeOptions();
			cc_options.setBinary("C:\\Users\\Dell\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");

			cc_options.merge(capability);

			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();

			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			capability.setPlatform(Platform.ANY);
			capability.setJavascriptEnabled(true);

			break;
		case SAFARI:
			capability = DesiredCapabilities.safari();
			capability.setBrowserName("safari");
			capability.setPlatform(Platform.MAC);
			capability.setJavascriptEnabled(true);
			
			break;
		case IE:
			WebDriverManager.iedriver().arch32().setup();
			
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setPlatform(Platform.WINDOWS);
			capability.setJavascriptEnabled(true);

			break;
		default:
			throw new RuntimeException("Browser name invalid.");
		}
		
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;
}
