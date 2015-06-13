package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverManager
{
	
	 public static WebDriver chromeDriver()
	 {
		 System.setProperty("webdriver.chrome.driver", "DriversExeLib/chromedriver.exe");
		 return new ChromeDriver();
     }	 
	 public static WebDriver operaDriver()
	 {
		 System.setProperty("webdriver.opera.driver", "DriversExeLib/operadriver32.exe");
		 return new OperaDriver();
     }	
	 public static WebDriver ieDriver()
	 {
		 System.setProperty("webdriver.ie.driver", "DriversExeLib/IEDriverServer32.exe");
		 return new InternetExplorerDriver();
     }
	 public static WebDriver firefoxDriver()
	 {
		 return new FirefoxDriver();
     }
	 
}
