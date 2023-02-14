package factoryBrowser;

public class BrowserNotSupportedException extends IllegalStateException{
	private static final long serialVersionUID = 3540548682203339240L;
	
	public BrowserNotSupportedException(String browserName) {
		super(String.format("Browser not supported: %s", browserName));
	}

}
