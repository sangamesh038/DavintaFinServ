package com.selenium.utilities;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.common.reports.ExtentListeners;
import com.common.utilities.TestData;
import com.selenium.utilities.DriverFactory;
import com.selenium.utilities.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private WebDriver driver;
	public Logger log = Logger.getLogger(BaseTest.class);
	public boolean grid = false;
	public static TestData testData = new TestData();

	@BeforeSuite
	public void setUpFramework() {

		DriverFactory.setGridPath("http://localhost:4444/wd/hub");

	}

	public void logInfo(String message) {

		ExtentListeners.testReport.get().info(message);

	}

	/*
	 * public void destroyFramework() {
	 * 
	 * }
	 */

	public void openBrowser(String browser) {

		DriverFactory.setRemote(grid);

		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			}

			try {
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
				log.info("Starting the session on Grid !!!");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		} else {

			if (browser.equals("chrome")) {
				System.out.println("Launching : " + browser);
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("Chrome browser launched !!!");
			} else if (browser.equals("firefox")) {
				System.out.println("Launching : " + browser);
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox browser launched !!!");

			}

		}

		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}
}
