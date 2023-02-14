package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.GlobalConstants;

public class ExtentManagerV5 {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.getGlobalConstant().getProjectPath() + "/extentReportV5/ExtentReportV5.html");
		reporter.config().setReportName("Orange HRM HTML Report");
		reporter.config().setDocumentTitle("Orange HRM HTML Report");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "SK Telecom");
		extentReports.setSystemInfo("Project", "Orange HRM");
		extentReports.setSystemInfo("Team", "T1");
		extentReports.setSystemInfo("JDK version", GlobalConstants.getGlobalConstant().getJavaVersion());
		return extentReports;
	}
}