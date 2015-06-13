package pages;

import components.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static helpers.ClickerHelper.clickOnElement;

public class MainPage extends BasePage {

	MainPage mainPage;
	ProductPage categoryPage;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private final static String CATEGORY_LIST = "//div[@class = 'home-page-cloud']/h1/a";
	private final static String PRODUCT_LIST=" //div[contains(@class,'ct')]//a";

	
	@FindBy(xpath = CATEGORY_LIST)
	private List<WebElement> categoryList;

	@FindBy(xpath = PRODUCT_LIST)
	private List<WebElement> productList;

	
	public MainPage clickOnCategoryByName( String categoryName) {
		List<WebElement> listOfCategories = categoryList;
		for (WebElement webElement : listOfCategories) {
			if (webElement.getText().equals(categoryName)) {
				clickOnElement(driver, webElement);
				break;
			}
		}
		return this;
	}

	public ProductPage clickOnProductByName( String productName) {
		List<WebElement> listOfCategories = productList;
		for (WebElement webElement : listOfCategories) {
			if (webElement.getText().equals(productName)) {
				clickOnElement(driver, webElement);
				break;
			}
		}
		return new ProductPage(driver);
	}
}

