package factoryBrowser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements IBrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.edgedriver().setup();
		
		DesiredCapabilities capability = DesiredCapabilities.edge();
		capability.setBrowserName("edge");
		capability.setPlatform(Platform.ANY);
		capability.setJavascriptEnabled(true);
		
		EdgeOptions options = new EdgeOptions();
		options.merge(capability);
		
		return new EdgeDriver(options);
	}
	
}
