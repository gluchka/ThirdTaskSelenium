package components;

import helpers.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public WebDriver driver;


    @BeforeTest
    public void beforeTest() {
        driver = DriverManager.chromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.GET_URL);

    }


	@AfterTest
	public void endTest() {
		if (driver != null)
			driver.quit();
	}

}
