package pageObjectModels;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestSuiteBase {
	protected WebDriver driver;
	protected HomeFactory home;
	protected String baseUrl;

	@Parameters({ "platform", "browser", "version", "url", "mode" })
	@BeforeClass(alwaysRun = true)
	public void setup(String platform, String browser, String version, String url, String mode)
			throws MalformedURLException {
		baseUrl = url;
		
		driver = getDriverInstance(platform, browser, version, url, mode);
		home = PageFactory.initElements(driver, HomeFactory.class);
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
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\HugoMorArj\\OneDrive\\Cursos\\Udemy\\Selenium-Webdriver-with-Java\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\HugoMorArj\\OneDrive\\Cursos\\Udemy\\Selenium-Webdriver-with-Java\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		
		return driver;
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.close();
		driver.quit();
	}
}