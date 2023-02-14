package factoryBrowser;

import org.openqa.selenium.WebDriver;

public interface IBrowserFactory {
	public abstract WebDriver getBrowserDriver();
}
