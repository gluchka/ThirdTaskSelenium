package pages;

import components.BasePage;
import helpers.ClickerHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Viktoriia_Akhadova on 09-Jun-15.
 */
public class ItemPage extends BasePage {

    ItemPage itemPage;
    ComparePage comparePage;
    ProductPage productPage;

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    private final static String BACK_TO_COMPARE = "//div[@class='compare-links']/span[3]/a";
    private final static String MICROWAVE_CHARACTERISTICS ="//div[@class='column width-40']//span[2]";
    private final static String MAIN_PAGE ="//div[@id='page-breadcrumbs']/a[1]";
    private final static String ITEM_NAME = "//div[@id='page-subheader']/h1";
    private final static String PRICE = "//div[@class='summary-price']/b";


    @FindBy(xpath=BACK_TO_COMPARE)
    private WebElement backToCompareList;

    @FindBy(xpath=MICROWAVE_CHARACTERISTICS)
    private List<WebElement> listCharacteristics;

    @FindBy(xpath=MAIN_PAGE)
    private WebElement goToMainPage;

    @FindBy(xpath=ITEM_NAME)
    private WebElement itemName;

    @FindBy(xpath=PRICE)
    private WebElement itemPrice;

    public Map<String, Integer> getItemNameAndPrice(){
        Map<String, Integer> productDescription = new HashMap<String, Integer>();
        String name = itemName.getText();
        Integer price = Integer.parseInt(getSomePrice(itemPrice.getText()));
        productDescription.put(name, price);
        System.out.println(productDescription + "   productDescription2");
        driver.navigate().back();
        return productDescription;
    }

    public String getSomePrice(String price) {
        String onlyPrice = null;
        Pattern p = Pattern.compile("\\d+\\s\\d+");
        Matcher m = p.matcher(price);
        while (m.find()) {
            onlyPrice = m.group().replace(" ", "");
        }
        return onlyPrice;
    }


    public List<String> getMicrowaveCharacteristics(){

        List<String> microwaveCharacteristics = new ArrayList<String>();
        for (WebElement element : listCharacteristics) {
            microwaveCharacteristics.add(element.getText());
        }
        System.out.println("2 "+ microwaveCharacteristics);
        return microwaveCharacteristics;
    }

    public ComparePage clickBackToCompare() {
        ClickerHelper.clickOnElement(driver, backToCompareList);
        return  new ComparePage(driver);
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
