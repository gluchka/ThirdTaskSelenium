package pages;

import components.BasePage;
import helpers.ClickerHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ComparePage extends BasePage {

	ComparePage comparePage;
	ItemPage itemPage;

	public ComparePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private final static String DIFFERENT_CHARACTERISTICS = "//table[@class='compare']//tr[@class='different']//td[%d]";
	private final static String DIFFERENCE = "background-color";
	private final static String CLICK_TO_FIRST_WAVE = "//table[@class='compare']//tr[2]/th[1]/a";
	private final static String CLICK_TO_SECOND_WAVE = "//table[@class='compare']//tr[2]/th[2]/a";
	private final static String ALL_CHARACTERISTICS = "//table[@class='compare']//tr/td[%d]";
	private final static String MAIN_PAGE ="//div[@id='page-breadcrumbs']/a[1]";

	@FindBy(xpath = ALL_CHARACTERISTICS)
	private List<WebElement> waveCharacteristicsInCompare;

	@FindBy(xpath=CLICK_TO_FIRST_WAVE)
	private WebElement clickToFirstWave;

	@FindBy(xpath=CLICK_TO_SECOND_WAVE)
	private WebElement clickToSecondWave;

	@FindBy(xpath=MAIN_PAGE)
	private WebElement goToMainPage;


	public List<String> compareAllCharacteristicsInProductNumber(int number){
		List<WebElement> firstProductList = listOfCharacteristics(ALL_CHARACTERISTICS,number);
		List<String> listCharacterFromCompare = new ArrayList<String>();
		for (WebElement element : firstProductList) {
			listCharacterFromCompare.add(element.getText());
		}
		System.out.println("1  "+listCharacterFromCompare);
		return listCharacterFromCompare;
	}

	public List<String>  getCharacteristics()
	{
		List<WebElement> sortByName = waveCharacteristicsInCompare;
		List<String> listFromSite = new ArrayList<String>();
		for (WebElement element : sortByName)
		{
			listFromSite.add(element.getText());
		}
		return listFromSite;
	}


	public ItemPage clickToFirstWave() {
		ClickerHelper.clickOnElement(driver, clickToFirstWave);
		return  new ItemPage(driver);
	}
	public ItemPage clickToSecondWave() {
		ClickerHelper.clickOnElement(driver, clickToSecondWave);
		return  new ItemPage(driver);
	}

	public void checkColorInDiffCharacteristics(int firstItem, int secondItem) {
		
		List<WebElement> firstProductList = listOfCharacteristics(DIFFERENT_CHARACTERISTICS,firstItem);
		List<WebElement> secondProductList = listOfCharacteristics(DIFFERENT_CHARACTERISTICS,secondItem);

		int diff = 0;
		for (int i = 0; i < secondProductList.size(); i++) {
			if (secondProductList.get(i).getCssValue(DIFFERENCE).equals(firstProductList.get(i).getCssValue(DIFFERENCE))) {
				if (!secondProductList.get(i).getText().equals(firstProductList.get(i).getText()))		
					diff++;
			}
		}
		Assert.assertEquals(firstProductList.size(), diff, "Test 'checkColorInDiffCharacteristics' failed! ");
	}

	public List<WebElement> listOfCharacteristics(String xPath,int item) {
		String characteristicsList = String.format(xPath,item + 1);
		List<WebElement> characterList = driver.findElements(By.xpath(characteristicsList));
		return characterList;
	}

	public MainPage goToMainPage(){
		ClickerHelper.clickOnElement(driver, goToMainPage);
		return new MainPage(driver);
	}

}
