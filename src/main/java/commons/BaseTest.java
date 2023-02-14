package commons;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import factoryServer.BrowserStackFactory;
import factoryServer.CrossBrowserFactory;
import factoryServer.ServerList;
import factoryServer.GridFactory;
import factoryServer.LambdaFactory;
import factoryServer.LocalFactory;
import factoryServer.SauceLabFactory;
import factoryServer.EnvironmentList;

public class BaseTest {

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllFilesInFolderName("allure-results");
		deleteAllFilesInFolderName("reportNGImages");
	}

	private void deleteAllFilesInFolderName(String folderName) {
		try {
			String pathFolderAllure = GlobalConstants.getGlobalConstant().getProjectPath() + "/" + folderName;
			File file = new File(pathFolderAllure);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public WebDriver getDriverInstance() {
		return driver.get();
	}
	
	protected WebDriver getBrowserDriver(String environmentName, String serverName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
		ServerList server = ServerList.valueOf(serverName.toUpperCase());
		switch (server) {
		case LOCAL:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		case GRID:
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
			break;
		case BROWSERSTACK:
			driver.set(new BrowserStackFactory(browserName, osName, osVersion).createDriver());
			break;
		case SAUCELAB:
			driver.set(new SauceLabFactory(browserName, osName).createDriver());
			break;
		case CROSSBROWSER:
			driver.set(new CrossBrowserFactory(browserName, osName).createDriver());
			break;
		case LAMBDA:
			driver.set(new LambdaFactory(browserName, osName).createDriver());
			break;
		default:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		}
		
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstant().getLongTimeout(), TimeUnit.SECONDS);
		driver.get().get(getEnvironmentURL(environmentName));
		return driver.get();
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("----------" + logging.getLevel().toString() + "----------\n" + logging.getMessage());
			}
		}
	}

	protected void closeBrowserAndDriver(String serverName) {
		ServerList server = ServerList.valueOf(serverName.toUpperCase());
		
		if (server.equals(ServerList.LOCAL) || server.equals(ServerList.GRID)) {
			String cmd = "";
			try {
				String osName = System.getProperty("os.name").toLowerCase();
				log.info("OS Name = " + osName);

				String driverInstanceName = driver.get().toString().toLowerCase();
				log.info("Driver Instance Name = " + driverInstanceName);

				if (driverInstanceName.contains("chrome")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
					} else {
						cmd = "pkill chromedriver";
					}
				} else if (driverInstanceName.contains("internetexplorer")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				} else if (driverInstanceName.contains("firefox")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
					} else {
						cmd = "pkill geckodriver";
					}
				} else if (driverInstanceName.contains("edge")) {
					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
					} else {
						cmd = "pkill msedgedriver";
					}
				}

				if (driver.get() != null) {
					driver.get().manage().deleteAllCookies();
					driver.get().quit();
					
					driver.remove();
				}
			} catch (Exception e) {
				log.info(e.getMessage());
			} finally {
				try {
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			driver.get().manage().deleteAllCookies();
			driver.get().quit();
			
			driver.remove();
		}
		
	}
	
	private String getEnvironmentURL(String environmentName) {
		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
		switch (environment) {
		case DEV:
			return "https://opensource-demo.orangehrmlive.com/";
		case TESTING:
			return "https://opensource-demo.orangehrmlive.com/";
		case STAGING:
			return "https://opensource-demo.orangehrmlive.com/";
		case PRODUCTION:
			return "https://opensource-demo.orangehrmlive.com/";
		default:
			return "https://opensource-demo.orangehrmlive.com/";
		}
	}

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	protected final Log log;
}
