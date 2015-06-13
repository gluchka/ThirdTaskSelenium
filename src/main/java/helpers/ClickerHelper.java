package helpers;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import static helpers.WaiterHelper.delay;
import static helpers.WaiterHelper.waitElement;

public class ClickerHelper {


	public static void clickOnElement(WebDriver driver, WebElement element)throws StaleElementReferenceException {
		waitElement(driver, element, Constants.SMALL_DELAY);
		try {
			element.click();
			delay(Constants.HALF_SEC_DELAY + Constants.SEC_DELAY);
		} catch (WebDriverException e) {
			System.out.println("WebDriverException " + e);
		}
	}
}
