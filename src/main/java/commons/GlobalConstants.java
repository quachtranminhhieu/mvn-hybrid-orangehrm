package commons;

import java.io.File;

import lombok.Getter;

@Getter
public class GlobalConstants {
	
	// Bill Pugh Singleton Implementation
	private GlobalConstants() {
		
	}
	
	public static GlobalConstants getGlobalConstant() {
		return SingletonHelper.GLOBAL_CONSTANT_INSTANCE;
	}
	
	private static class SingletonHelper {
		private static final GlobalConstants GLOBAL_CONSTANT_INSTANCE = new GlobalConstants();
	}
	
	// Double Check Locking Singleton
//	private static volatile GlobalConstants globalConstantInstance;
//	
//	private GlobalConstants() {
//		
//	}
//	
//	public static GlobalConstants getGlobalConstant() {
//		if (globalConstantInstance == null) {
//			synchronized (GlobalConstants.class) {
//				if (globalConstantInstance == null) {
//					globalConstantInstance = new GlobalConstants();
//				}
//			}
//		}
//		return globalConstantInstance;
//	}
	
	private final long longTimeout = 30;
	private final long shortTimeout = 3;
	
	private final String javaVersion = System.getProperty("java.version");
	private final String osName = System.getProperty("os.name");
	
	private final String projectPath = System.getProperty("user.dir");
	private final String sourceMainJavaPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
	private final String sourceMainResourcePath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
	private final String sourceTestJavaPath = projectPath + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator;
	private final String sourceTestResourcePath = projectPath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
	private final String extensionFilePath = sourceMainResourcePath + "browserExtensions" + File.separator;
	private final String uploadFilePath = sourceMainResourcePath + "uploadFiles" + File.separator;
	private final String downloadFilePath = sourceMainResourcePath + "downloadFiles" + File.separator;
	private final String browserLogPath = sourceMainResourcePath + "browserLogs" + File.separator;
	private final String reportNGScreenshotPath = projectPath + File.separator + "reportNGImages" + File.separator;
	
	private final String browserStackUsername = "minhhiu_ysaoJj";
	private final String browserStackKey = "vypfpf9dRJ6CFeb7qvF9";
	private final String browserStackURL = "https://" + browserStackUsername + ":" + browserStackKey + "@hub-cloud.browserstack.com/wd/hub";

	private final String sauceLabUsername = "oauth-nhieuautomationfc-44f15";
	private final String sauceLabKey = "a0168fd4-f49a-403a-986e-e9257ad62d67";
	private final String sauceLabURL = "https://" + sauceLabUsername + ":" + sauceLabKey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
	
	private final String crossBrowserUsername = "".replaceAll("@", "%40");
	private final String crossBrowserKey = "";
	private final String crossBrowserURL = "http://" + crossBrowserUsername + ":" + crossBrowserKey + "@hub.crossbrowsertesting.com:80/wd/hub";
	
	private final String lambdaUsername = "nhieuautomationfc";
	private final String lambdaKey = "y1uHUUKHBg8DRyrPMWM2Aa1ZuiYGESj5djOqy4w9qxIvgnqhbr";
	private final String lambdaURL = "http://" + lambdaUsername + ":" + lambdaKey + "@hub.lambdatest.com/wd/hub";
}
