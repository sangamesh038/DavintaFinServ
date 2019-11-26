package com.selenium.utilities;

import org.openqa.selenium.NoSuchElementException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.reports.ExtentListeners;
import com.common.utilities.ExcelReader;

public abstract class BasePage<T> {

	protected WebDriver driver;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testData.xlsx");

	private long LOAD_TIMEOUT = 30;
	private int AJAX_ELEMENT_TIMEOUT = 10;

	public BasePage() {
		this.driver = DriverManager.getDriver();
	}

	public T openPage(Class<T> clazz) {
		T page = null;
		try {
			driver = DriverManager.getDriver();
			AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = PageFactory.initElements(driver, clazz);
			PageFactory.initElements(ajaxElemFactory, page);
			@SuppressWarnings("rawtypes")
			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
			waitForPageToLoad(pageLoadCondition);
		} catch (NoSuchElementException e) {
			/*
			 * String error_screenshot = System.getProperty("user.dir") +
			 * "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
			 * this.takeScreenShot(error_screenshot);
			 */ throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
		}
		return page;
	}

	@SuppressWarnings("unchecked")
	private void waitForPageToLoad(@SuppressWarnings("rawtypes") ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		wait.until(pageLoadCondition);
	}

	@SuppressWarnings("rawtypes")
	protected abstract ExpectedCondition getPageLoadCondition();

	public void click(WebElement element, String elementName) {

		element.click();
		ExtentListeners.testReport.get().info("Clicking on : " + elementName);

	}

	public void type(WebElement element, String value, String elementName) {

		element.sendKeys(value);
		ExtentListeners.testReport.get().info("Typing in : " + elementName + " entered the value as : " + value);

	}
	
	public void isDisplayed(WebElement element, String elementName) {
		element.isDisplayed();
		ExtentListeners.testReport.get().info("Displayed element is  "+ elementName);
	}

}
