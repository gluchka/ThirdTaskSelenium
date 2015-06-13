package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public  class BasePage 
{

protected WebDriver driver;	
	
	public BasePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public WebElement findElementByXpath(String xpath){
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement findElementByCss(String css){
		return driver.findElement(By.cssSelector(css));
	}

	public WebElement findElementById(String id){
		return driver.findElement(By.id(id));
	}
	
	public WebElement findElementByLink(String link){
		return driver.findElement(By.linkText(link));
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public WebDriver driverTo(){
		return driver;
	}
	
	public BasePage open(String url){
		driver.get(url);
		return this;
	}

	public List<WebElement> findElements(By by) {
		throw new UnsupportedOperationException();
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}
}
