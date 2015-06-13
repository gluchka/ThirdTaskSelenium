package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WaiterHelper {
	
	public static boolean waitElement(WebDriver driver, WebElement element,	int seconds) {
		for (int i = 0; i < seconds; i += Constants.HALF_SEC_DELAY) {
			driver.manage().timeouts()
					.implicitlyWait(Constants.HALF_SEC_DELAY, TimeUnit.MILLISECONDS);
			try {
				if (element.isDisplayed()) {
					return true;
				} else {
					delay(Constants.HALF_SEC_DELAY);
				}
			} catch (NoSuchElementException e) {
			} finally {
				driver.manage().timeouts().implicitlyWait(Constants.MIDDLE_DELAY, TimeUnit.MILLISECONDS);
			}
		}
		return false;
	}

	
	public static void delay(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException!");
		}
	}
}
