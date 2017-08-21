package com.speedtest.net.pageObjectModels;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.speedtest.net.constants.BrowserDriverConstants;
import com.speedtest.net.constants.ExcelFileConstants;
import com.speedtest.net.helpers.ExcelUtility;
import com.speedtest.net.speedtest.ConnectionSpeedTest;

public class TestSuiteBase {
	protected WebDriver driver;
	protected HomeFactory home;
	protected int totalTests;
	
	private static final Logger log = LogManager.getLogger(TestSuiteBase.class.getName());

	@Parameters({ "platform", "browser", "version", "url", "mode" })
	@BeforeClass(alwaysRun = true)
	public void setup(String platform, String browser, String version, String url, String mode)
			throws NumberFormatException, Exception {
		driver = getDriverInstance(platform, browser, version, url, mode);
		home = PageFactory.initElements(driver, HomeFactory.class);

		// Maximize the browser's window
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Open the Application
		driver.get(url);
		
		// Get Excel File
		try {
			ExcelUtility.setExcelFile(ExcelFileConstants.FILE_PATH + ExcelFileConstants.FILE_NAME, ExcelFileConstants.SHEET_NAME);
			log.info("Opened Excel File");
		} catch (Exception e) {
			log.info("Couldnt get the Excel File.");
		}
		
		// Get total tests from file
		try {
			totalTests = ExcelUtility.getCellNumericData(ExcelFileConstants.TOTAL_ROW_INDEX, ExcelFileConstants.TOTAL_COLUMN_INDEX);
			log.info("Got total tests.");
		} catch (Exception e) {
			log.info("Couldnt get total tests.");
		}
	}

	public static WebDriver getDriverInstance(String platform, String browser, String version, String url, String mode)
			throws MalformedURLException {
		String nodeURL = "http://ip address:5555/wd/hub";
		WebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();

		if (mode.equalsIgnoreCase("grid")) {
			// Platforms
			if (platform.equalsIgnoreCase("Windows")) {
				caps.setPlatform(Platform.WINDOWS);
			}
			if (platform.equalsIgnoreCase("MAC")) {
				caps.setPlatform(Platform.MAC);
			}

			// Browsers
			if (browser.equalsIgnoreCase("chrome")) {
				caps = DesiredCapabilities.chrome();
			}
			if (browser.equalsIgnoreCase("firefox")) {
				caps = DesiredCapabilities.firefox();
			}

			// Version
			caps.setVersion(version);
			driver = new RemoteWebDriver(new URL(nodeURL), caps);
		} else if (mode.equalsIgnoreCase("local")) {
			// Browsers
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", BrowserDriverConstants.GECKO_DRIVER);
				driver = new FirefoxDriver();
			}
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", BrowserDriverConstants.CHROME_DRIVER);
				driver = new ChromeDriver();
			}
		}

		return driver;
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
}