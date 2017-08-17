package com.speedtest.net.speedtest;

import org.testng.annotations.Test;

import pageObjectModels.TestSuiteBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class ConnectionSpeedTest extends TestSuiteBase {
	private static final Logger log = LogManager.getLogger(ConnectionSpeedTest.class.getName());
	
	@Test
	public void connectionSpeedTest() {
		// Click on the Start Button to start the test
		home.clickStartButton();
		
		// Get the Ping Speed Result
		WebElement pingSpeedResult = home.getPingSpeed();
		try {
			Assert.assertNotEquals(null, pingSpeedResult);
			log.info("Ping: " + pingSpeedResult.getText());
		} catch (Exception e) {
			log.info("Couldnt get ping speed.");
		}
		
		// Get the Download Speed Result
		WebElement downloadSpeedResult = home.getDownloadSpeed();
		try {
			Assert.assertNotEquals(null, downloadSpeedResult);
			log.info("Download: " + downloadSpeedResult.getText());
		} catch (Exception e) {
			log.info("Couldnt get download speed.");
		}
		
		// Get the Upload Speed Result
		WebElement uploadSpeedResult = home.getUploadSpeed();
		try {
			Assert.assertNotEquals(null, uploadSpeedResult);
			log.info("Upload: " + uploadSpeedResult.getText());
		} catch (Exception e) {
			log.info("Couldnt get upload speed.");
		}
	}
}
