package com.speedtest.net.speedtest;

import org.testng.annotations.Test;

import com.speedtest.net.constants.ExcelFileConstants;
import com.speedtest.net.helpers.ExcelUtility;
import com.speedtest.net.pageObjectModels.TestSuiteBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Calendar;
import java.util.Date;
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

		// Write Date to Excel File
		try {
			ExcelUtility.setCellData(Calendar.getInstance().getTime(),
					totalTests + ExcelFileConstants.STARTING_CELL, ExcelFileConstants.DATE_INDEX);
			log.info("Wrote today's date.");
		} catch (Exception e1) {
			log.info("Couldnt write today's date.");
		}

		// Write Ping Speed Result to Excel File
		try {
			ExcelUtility.setCellData(Double.parseDouble(pingSpeedResult.getText()),
					totalTests + ExcelFileConstants.STARTING_CELL, ExcelFileConstants.PING_INDEX);
			log.info("Wrote ping speed.");
		} catch (Exception e1) {
			log.info("Couldnt write ping speed.");
		}

		// Write Download Speed Result to Excel File
		try {
			ExcelUtility.setCellData(Double.parseDouble(downloadSpeedResult.getText()),
					totalTests + ExcelFileConstants.STARTING_CELL, ExcelFileConstants.DOWNLOAD_INDEX);
			log.info("Wrote download speed.");
		} catch (Exception e1) {
			log.info("Couldnt write download speed.");
		}

		// Write Upload Speed Result to Excel File
		try {
			ExcelUtility.setCellData(Double.parseDouble(uploadSpeedResult.getText()),
					totalTests + ExcelFileConstants.STARTING_CELL, ExcelFileConstants.UPLOAD_INDEX);
			log.info("Wrote upload speed.");
		} catch (Exception e1) {
			log.info("Couldnt write upload speed.");
		}

		// Update Total and write it to Excel File
		try {
			ExcelUtility.setCellData(totalTests + 1, ExcelFileConstants.TOTAL_ROW_INDEX,
					ExcelFileConstants.TOTAL_COLUMN_INDEX);
			log.info("Wrote new total.");
		} catch (Exception e1) {
			log.info("Couldnt write new total.");
		}
	}
}
