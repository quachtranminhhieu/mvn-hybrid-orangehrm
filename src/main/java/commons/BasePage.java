package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.PageGeneratorManager;
import pageUIs.orangeHRM.PIM_AddEmployeePageUI;
import pageUIs.orangeHRM.BasePageUI;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void sleepInMiliSecond(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getDynamicLocator(String dynamicXpath, String... dynamicValues) {
		return String.format(dynamicXpath, (Object[]) dynamicValues);
	}
	
	public WebDriverWait setTimeoutExplicit (WebDriver driver) {
		return new WebDriverWait(driver, longTimeout);
	}
	
	public void setTimeoutImplicit(WebDriver driver, long longTimeoutImplicit) {
		driver.manage().timeouts().implicitlyWait(longTimeoutImplicit, TimeUnit.SECONDS);
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	public List<WebElement> waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
		
	}
	
	public List<WebElement> waitForAllElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String xpathLocator) {
		setTimeoutImplicit(driver, shortTimeout);
		boolean status = setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		setTimeoutImplicit(driver, longTimeout);
		return status;
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		setTimeoutImplicit(driver, shortTimeout);
		boolean status = setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
		setTimeoutImplicit(driver, longTimeout);
		return status;
	}
	
	public boolean waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		setTimeoutImplicit(driver, shortTimeout);
		boolean status = setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
		setTimeoutImplicit(driver, longTimeout);
		return status;
	}
	
	public boolean waitForAllElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		setTimeoutImplicit(driver, shortTimeout);
		boolean status = setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator, dynamicValues)));
		setTimeoutImplicit(driver, longTimeout);
		return status;
	}
	
	public WebElement waitForElementClickable(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
	
	public WebElement waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	public void openPageURL(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(2);
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver){
		return driver.manage().getCookies();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancleAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(driver.switchTo().window(id).getTitle().equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeAllTabExceptParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return driver.findElement(getByXpath(getDynamicLocator(xpathLocator, dynamicValues)));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator){
		return driver.findElements(getByXpath(xpathLocator));
	}
	

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator, String... dynamicValues){
		return driver.findElements(getByXpath(getDynamicLocator(xpathLocator, dynamicValues)));
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator) {
		waitForElementClickable(driver, xpathLocator).click();
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		waitForElementClickable(driver, xpathLocator, dynamicValues).click();
	}
	
	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		clearTextboxByAction(driver, xpathLocator);
		getWebElement(driver, xpathLocator).sendKeys(textValue);
	}
	
	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		clearTextboxByAction(driver, xpathLocator, dynamicValues);
		getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).getText();
	}
	
	public String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).getText();
	}
	
	public void clearTextboxByAction(WebDriver driver, String xpathLocator) {
		int valueLength = getElementAttribute(driver, xpathLocator, "value").length();
		
		while(valueLength > 0) {
			pressKeyToElement(driver, xpathLocator, Keys.BACK_SPACE);
			valueLength--;
		}
	}
	
	public void clearTextboxByAction(WebDriver driver, String xpathLocator, String... dynamicValues) {
		int valueLength = getElementAttribute(driver, getDynamicLocator(xpathLocator, dynamicValues), "value").length();
		
		while(valueLength > 0) {
			pressKeyToElement(driver, getDynamicLocator(xpathLocator, dynamicValues), Keys.BACK_SPACE);
			valueLength--;
		}
	}
	
	public void clearTextbox(WebDriver driver, String xpathLocator) {
		waitForElementVisible(driver, xpathLocator).clear();
	}
	
	public void clearTextbox(WebDriver driver, String xpathLocator, String... dynamicValues) {
		waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).clear();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(waitForElementVisible(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... dynamicValues) {
		Select select = new Select(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(waitForElementVisible(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Select select = new Select(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		return new Select(waitForElementVisible(driver, xpathLocator)).isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem, String... dynamicValues) {
		WebDriverWait explicitWait = setTimeoutExplicit(driver);
		
		getWebElement(driver, parentLocator, dynamicValues).click();
//		clickToElement(driver, parentLocator, dynamicValues);
		sleepInSecond(1);
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(getDynamicLocator(childItemLocator, dynamicValues))));
		for (WebElement item: allItems) {
			if (item.getText().equals(expectedItem)) {
//				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//				jsExecutor.executeScript("arguments[0].scrollIntoView(false);", item);
				sleepInMiliSecond(500);
				item.click();
				break;
			}
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		WebDriverWait explicitWait = setTimeoutExplicit(driver);
		
		waitForElementClickable(driver, parentLocator).click();
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		for (WebElement item: allItems) {
			if (item.getText().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return waitForElementVisible(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return waitForElementVisible(driver, xpathLocator).getCssValue(propertyName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName, String... dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).size();
	}
	
	public void checkToCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementClickable(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = waitForElementClickable(driver, getDynamicLocator(xpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementClickable(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckboxRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = waitForElementClickable(driver, getDynamicLocator(xpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		try {
			return getWebElement(driver, xpathLocator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		try {
			return getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		setTimeoutImplicit(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		setTimeoutImplicit(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		setTimeoutImplicit(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues));
		setTimeoutImplicit(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).isEnabled();
	}
	
	public boolean isElementEnable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(waitForElementVisible(driver, xpathLocator));
	}
	
	public void switchToFrameIframe(WebDriver driver, String xpathLocator, String... dynamicValues) {
		driver.switchTo().frame(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(waitForElementVisible(driver, xpathLocator)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues))).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.doubleClick(waitForElementVisible(driver, xpathLocator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.doubleClick(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, xpathLocator), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)), key).perform();
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName += GlobalConstants.getGlobalConstant().getUploadFilePath() + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void highlightElement(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
	}
	
	public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String textValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + textValue + "')", waitForElementVisible(driver, xpathLocator));
	}
	
	public void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + textValue + "')", waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		jsExecutor.executeScript("arguments[0].value='" + textValue + "';", waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
	}
	
	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues)));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public Boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = setTimeoutExplicit(driver);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}
	
	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public DashboardPO loginToSystem(WebDriver driver, String adminUsername, String adminPassword) {
		sendKeyToElement(driver, BasePageUI.USERNAME_TEXTBOX, adminUsername);
		sendKeyToElement(driver, BasePageUI.PASSWORD_TEXTBOX, adminPassword);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDashboardPage(driver);
	}
	
	public LoginPO logoutToSystem(WebDriver driver) {
		clickToElement(driver, BasePageUI.USER_DROPDOWN_TAB);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	public void waitForLoadingIconDisappear(WebDriver driver) {
		waitForElementInvisible(driver, BasePageUI.LOADING_ICON);
	}
	
	public void openMenuPage(WebDriver driver, String menuPageName) {
		clickToElementByJS(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		waitForLoadingIconDisappear(driver);
	}
	
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPage) {
		clickToElementByJS(driver, BasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		clickToElementByJS(driver, BasePageUI.DYNAMIC_SUB_MENU_PAGE, subMenuPage);
		
		waitForLoadingIconDisappear(driver);
	}
	
	public void openChildSubMenuPage(WebDriver driver, String subMenuPage, String childSubMenuPage) {
		clickToElementByJS(driver, BasePageUI.DYNAMIC_DROPDOWN_SUB_MENU_PAGE, subMenuPage);
		
		clickToElementByJS(driver, BasePageUI.DYNAMIC_SUB_MENU_PAGE, childSubMenuPage);
		
		waitForLoadingIconDisappear(driver);
	}

	public void sendKeyToTextboxByLabel(WebDriver driver, String textValue, String textboxLabel) {
		sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, textValue, textboxLabel);
	}
	
	public String checkEmployeeIDAlreadyExists(WebDriver driver, String employeeID) {
		setTimeoutImplicit(driver, 1);
		if (isElementDisplayed(driver, PIM_AddEmployeePageUI.EMPLOYEE_ID_ALREADY_EXISTS_MESSAGE)) {
			employeeID = String.valueOf(Integer.valueOf(employeeID) + 1);
			int stringLength = employeeID.length();
			if (stringLength <= 3) {
				for (int i = 0; i < 4 - stringLength; i++) {
					employeeID = "0" + employeeID;
				}
			}
			sendKeyToTextboxByLabel(driver, employeeID, "Employee Id");
			checkEmployeeIDAlreadyExists(driver, employeeID);
	}
		setTimeoutImplicit(driver, longTimeout);
		return getTextboxValueByLabel(driver, "Employee Id");
	}
	
	public void sendKeyToTextboxAndClickResultByLabel(WebDriver driver, String textValue, String textboxLabel) {
		String[] value = textValue.split(" ");
		sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, textValue, textboxLabel);
		try {
			clickToElement(driver, BasePageUI.DYNAMIC_OPTION_IN_TEXTBOX, value[0], value[1]);
		} catch (NoSuchElementException e) {
			sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, textValue, textboxLabel);
			clickToElement(driver, BasePageUI.DYNAMIC_OPTION_IN_TEXTBOX, value[0], value[1]);
		}
	}
	
	public void sendKeyToFirstNameTextbox(WebDriver driver, String empFirstName) {
		sendKeyToElement(driver, BasePageUI.FIRST_NAME_TEXTBOX, empFirstName);
	}

	public void sendKeyToLastNameTextbox(WebDriver driver, String empLastName) {
		sendKeyToElement(driver, BasePageUI.LAST_NAME_TEXTBOX, empLastName);
	}
	
	public void sendKeyToTextareaByLabel(WebDriver driver, String textValue, String textareaLabel) {
		sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_LABEL, textValue, textareaLabel);
	}
	
	public String getFirstNameTextboxValue(WebDriver driver) {
		return getElementAttribute(driver, BasePageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxValue(WebDriver driver) {
		return getElementAttribute(driver, BasePageUI.LAST_NAME_TEXTBOX, "value");
	}
	
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		sleepInSecond(1);
		clickToElementByJS(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		if (buttonText.equals("Add")) {
			sleepInMiliSecond(250);
		}
	}
	
	public void clickToButtonByTextInForm(WebDriver driver, String formName, String buttonText) {
		sleepInSecond(1);
		clickToElementByJS(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT_IN_FORM, formName, buttonText);
		if (buttonText.equals("Add")) {
			sleepInSecond(1);
		}
	}
	
	public String getTextareaValueByLabel(WebDriver driver, String textareaLabel) {
		return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_LABEL, "value", textareaLabel);
	}
	
	public String getTextboxValueByLabel(WebDriver driver, String textboxLabel) {
		return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, "value", textboxLabel);
	}
	
	public String getTextValueByLabel(WebDriver driver, String textLabel) {
		return getElementText(driver, BasePageUI.DYNAMIC_TEXT_BY_LABEL, textLabel);
	}
	
	public void clickToSwitchOnByLabel(WebDriver driver, String switchLabel) {
		if (getElementCssValue(driver, BasePageUI.DYNAMIC_SWITCH, "background-color", switchLabel).equals("rgb(232, 234, 239)")) {
			clickToElementByJS(driver, BasePageUI.DYNAMIC_SWITCH, switchLabel);
		}
	}
	
	public void clickToSwitchOffByLabel(WebDriver driver, String switchLabel) {
		if (!getElementCssValue(driver, BasePageUI.DYNAMIC_SWITCH, "background-color", switchLabel).equals("rgb(232, 234, 239)")) {
			clickToElementByJS(driver, BasePageUI.DYNAMIC_SWITCH, switchLabel);
		}
	}
	
	public void checkToRadioButtonByLabel(WebDriver driver, String radioButtonLabel) {
		clickToElementByJS(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabel);
	}
	
	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String radioButtonLabel) {
//		return getElementCssValue(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, "box-shadow", radioButtonLabel).contains("rgb(255, 123, 29)");
		return getElementCssValue(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, "box-shadow", radioButtonLabel).contains("rgb");
	}
	
	public String getRowIndexInDataTableNameAtColumnValue(WebDriver driver, String dataTableName, String columnValue) {
		if(dataTableName.isEmpty()) {
			int cellIndex = getElementSize(driver, BasePageUI.DYNAMIC_CELL_VALUE_IN_DATA_TABLE_WITHOUT_NAME, columnValue) + 1;
			return String.valueOf(cellIndex);
		} else {
			int cellIndex = getElementSize(driver, BasePageUI.DYNAMIC_CELL_VALUE_IN_DATA_TABLE_NAME, dataTableName, columnValue) + 1;
			return String.valueOf(cellIndex);
		}
	}
	
	public String getValueInDataTableNameAtColumnNameAndRowIndex(WebDriver driver, String dataTableName, String headerName, String rowIndex) {
		if (dataTableName.isEmpty()) {
			int columnIndex = getElementSize(driver, BasePageUI.DYNAMIC_HEADER_NAME_IN_DATA_TABLE_WITHOUT_NAME, headerName) + 1;
			return getElementText(driver, BasePageUI.DYNAMIC_CELL_IN_DATA_TABLE_WITHOUT_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex)).trim();
		} else {
			int columnIndex = getElementSize(driver, BasePageUI.DYNAMIC_HEADER_NAME_IN_DATA_TABLE_NAME, dataTableName, headerName) + 1;
			return getElementText(driver, BasePageUI.DYNAMIC_CELL_IN_DATA_TABLE_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX, dataTableName, rowIndex, String.valueOf(columnIndex)).trim();
		}
	}
	
	public void clickToButtonNameInDataTableNameAtColumnNameAndRowIndex(WebDriver driver, String dataTableName, String headerName, String rowIndex, String buttonIconName) {
		if (dataTableName.isEmpty()) {
			int columnIndex = getElementSize(driver, BasePageUI.DYNAMIC_HEADER_NAME_IN_DATA_TABLE_WITHOUT_NAME, dataTableName, headerName) + 1;
			clickToElementByJS(driver, BasePageUI.DYNAMIC_BUTTON_IN_DATA_TABLE_WITHOUT_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex), buttonIconName);
			
			if (buttonIconName.equals("trash")) {
				clickToElementByJS(driver, BasePageUI.ACCEPT_DELETE_IN_DATA_TABLE);
			}
		} else {
			int columnIndex = getElementSize(driver, BasePageUI.DYNAMIC_HEADER_NAME_IN_DATA_TABLE_NAME, dataTableName, headerName) + 1;
			clickToElementByJS(driver, BasePageUI.DYNAMIC_BUTTON_IN_DATA_TABLE_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX, dataTableName, rowIndex, String.valueOf(columnIndex), buttonIconName);
			
			if (buttonIconName.equals("trash")) {
				clickToElementByJS(driver, BasePageUI.ACCEPT_DELETE_IN_DATA_TABLE);
			}
		}
	}
	
	public String getMessageTitle(WebDriver driver) {
		return getElementText(driver, BasePageUI.DYNAMIC_MESSAGE_TITLE);
	}
	
	public boolean isTextboxEnabledByLabel(WebDriver driver, String textboxLabel) {
		return isElementEnable(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, textboxLabel);
	}
	
	public boolean isTextboxDisplayedByLabel(WebDriver driver, String textboxLabel) {
		List<WebElement> elements = getListWebElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, textboxLabel);
		
		if (elements.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isTextboxUndisplayedByLabel(WebDriver driver, String textboxLabel) {
		setTimeoutImplicit(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_LABEL, textboxLabel);
		setTimeoutImplicit(driver, longTimeout);
		
		if (elements.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void selectItemInCustomDropdownByLabel(WebDriver driver, String itemValue, String dropdownLabel) {
		selectItemInCustomDropdown(driver, BasePageUI.DYNAMIC_PARENT_DROPDOWN_BY_LABEL, BasePageUI.DYNAMIC_CHILD_ITEM_IN_DROPDOWN_BY_LABEL, itemValue, dropdownLabel);
	}
	
	public String getSelectedItemInCustomDropDownByLabel(WebDriver driver, String dropdownLabel) {
		return getElementText(driver, BasePageUI.DYNAMIC_PARENT_DROPDOWN_BY_LABEL, dropdownLabel);
	}
	
	protected long longTimeout = GlobalConstants.getGlobalConstant().getLongTimeout();
	protected long shortTimeout = GlobalConstants.getGlobalConstant().getShortTimeout();
}
