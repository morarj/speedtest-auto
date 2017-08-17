package pageObjectModels;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeFactory {
	WebDriver driver;
	
	@FindBy(css=".start-text")
	WebElement startButton;
	
	@FindBy(css=".result-data-large.number.result-data-value.ping-speed")
	WebElement pingSpeed;
	
	@FindBy(css=".result-data-large.number.result-data-value.download-speed")
	WebElement downloadSpeed;
	
	@FindBy(css=".result-data-large.number.result-data-value.upload-speed")
	WebElement uploadSpeed;
	
	public HomeFactory(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
	}
	
	/***
	 * Click start button
	 */
	public void clickStartButton() {
		try {
			startButton.click();
		} catch(Exception e) {
			
		}
	}
	
	/***
	 * Gets ping speed
	 */
	public WebElement getPingSpeed() {
		By css = By.cssSelector(".result-data-large.number.result-data-value.ping-speed");
		Pattern pattern = Pattern.compile("[0-9]{1,5}");
		
		Boolean result = new WebDriverWait(driver, 45).until(ExpectedConditions.textMatches(css, pattern));
		
		if(result)
			return pingSpeed;
		
		return null;
	}
	
	/***
	 * Gets download speed
	 */
	public WebElement getDownloadSpeed() {
		By css = By.cssSelector(".result-data-large.number.result-data-value.download-speed");
		
		Boolean result = new WebDriverWait(driver, 45).until(ExpectedConditions.not(ExpectedConditions.attributeContains(downloadSpeed, "data-download-status-value", "NaN")));
		
		if(result)
			return downloadSpeed;
		
		return null;
	}
	
	/***
	 * Gets upload speed
	 */
	public WebElement getUploadSpeed() {
		By css = By.cssSelector(".result-data-large.number.result-data-value.upload-speed");
		
		Boolean result = new WebDriverWait(driver, 45).until(ExpectedConditions.not(ExpectedConditions.attributeContains(uploadSpeed, "data-upload-status-value", "NaN")));
		
		if(result)
			return uploadSpeed;
		
		return null;
	}
	
	/***
	 * 
	 * @param
	 */
}
