package factoryBrowser;

import java.io.File;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements IBrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();
		
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.ANY);
		options.merge(capability);
		
		// Add extension
//		FirefoxProfile profile = new FirefoxProfile();
//		File firefoxExtension = new File(GlobalConstants.getGlobalConstant().getExtensionFilePath() + "adblock_plus-3.15.2.xpi");
//		profile.addExtension(firefoxExtension);
//		options.setProfile(profile);

		// Disable browser log in Console
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstant().getBrowserLogPath() + "Firefox.log");

		// Set browser language = Vietnamese
//		options.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");

		// Auto Save/Download
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", GlobalConstants.getGlobalConstant().getDownloadFilePath());
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk",
				"multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,"
				+ "text/csv,image/png ,image/jpeg, application/pdf, text/html, text/plain, application/excel, application/vnd.ms-excel, "
				+ "application/x-excel, application/x-msexcel, application/octet-stream");
		options.addPreference("pdfjs.disabled", true);

		// Run Incognito
//		options.addArguments("-private");
	
		return new FirefoxDriver(options);
	}
	
}
