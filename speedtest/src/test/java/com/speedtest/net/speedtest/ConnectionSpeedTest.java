package com.speedtest.net.speedtest;

import org.testng.annotations.Test;

import pageObjectModels.TestSuiteBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class ConnectionSpeedTest extends TestSuiteBase {
	@Test
	public void connectionSpeedTest() {
		home.clickStartButton();
		WebElement result = home.getPingSpeed();
		if(result != null) {
			System.out.println(result.getText());
		}
		result = home.getDownloadSpeed();
		if(result != null) {
			System.out.println(result.getText());
		}
		result = home.getUploadSpeed();
		if(result != null) {
			System.out.println(result.getText());
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		// Maximize the browser's window
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Open the Application
		driver.get(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}
}
