package pages;

import components.BasePage;
import helpers.ClickerHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPage extends BasePage {

    ProductPage productPage;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private final static String SORTING_BY_PRICE_BUTTON = "//div[@class='order']/a[1]";
    private final static String SORTING_BY_NAME_BUTTON = "//div[@class='order']/a[2]";

    private final static String PRODUCTS_SORTED_BY_NAME = "//div[@class='name']/a";
    private final static String PRODUCTS_SORTED_BY_PRICE = "//div[@class='item']//div[@class='price']/strong";

    private final static String PRODUCT_IN_LIST = "//div[@class='item'][%d]/div[@class='name']/a";
    private final static String PRODUCT_PRICE_IN_LIST = "//div[@class='item'][%d]//div[@class='price']/strong";

    private final static String ADD_TO_COMPARE = "//div[@class='compare-links']/span[1]";
    private final static String COMPARE_BUTTON = "//a[contains(@class,'show_compare')]";

    private final static String FILTER_BY_MIN_PRICE = "//div[contains(@class,'panel corner criteria')]//div[3]/div[2]/a[6]";
    private final static String FILTER_BY_MAX_PRICE = "//div[contains(@class,'panel corner criteria')]//div[4]/div[2]/a[1]";
    private final static String MANUFACTURER = "//div[5]/div[@class ='is_empty_items']/a[%d]";
    private final static String WEIGHT_TUNING = "//a[contains(text(),'Регулировка веса')]";
    private final static String PRODUCT_DESCRIPTION = "//div[@class='item']//div[@class='description']";

    private final static String MAIN_PAGE = "//div[@id='page-breadcrumbs']/a[1]";

    @FindBy(xpath = SORTING_BY_PRICE_BUTTON)
    private WebElement sortingByPriceButton;

    @FindBy(xpath = SORTING_BY_NAME_BUTTON)
    private WebElement sortingByNameButton;

    @FindBy(xpath = PRODUCTS_SORTED_BY_NAME)
    private List<WebElement> productsSortedByName;

    @FindBy(xpath = PRODUCTS_SORTED_BY_PRICE)
    private List<WebElement> productsSortedByPrice;

    @FindBy(xpath = ADD_TO_COMPARE)
    private List<WebElement> addItemToCompare;

    @FindBy(xpath = COMPARE_BUTTON)
    private WebElement clickOnCompareButton;

    @FindBy(xpath = FILTER_BY_MIN_PRICE)
    private WebElement filterByMinPriceButton;

    @FindBy(xpath = FILTER_BY_MAX_PRICE)
    private WebElement filterByMaxPriceButton;

    @FindBy(xpath = MANUFACTURER)
    private List<WebElement> manufucturerToFilter;

    @FindBy(xpath = WEIGHT_TUNING)
    private WebElement weightTuning;

    @FindBy(xpath = PRODUCT_DESCRIPTION)
    private List<WebElement> productsDescription;

    @FindBy(xpath = MAIN_PAGE)
    private WebElement goToMainPage;


    public MainPage goToMainPage() {
        ClickerHelper.clickOnElement(driver, goToMainPage);
        return new MainPage(driver);
    }

    //  FIRST TEST

    public ProductPage clickSortByPriceButton() {
        ClickerHelper.clickOnElement(driver, sortingByPriceButton);
        return this;
    }

    public ProductPage clickSortByNameButton() {
        ClickerHelper.clickOnElement(driver, sortingByNameButton);
        return this;
    }

    public void checkThatSortingByNameIsCorrect() {
        List<WebElement> sortByName = productsSortedByName;
        List<String> listToCheck = new ArrayList<String>();
        List<String> listFromSite = new ArrayList<String>();
        for (WebElement element : sortByName) {
            listFromSite.add(element.getText());
            listToCheck.add(element.getText());
        }
        Collections.sort(listToCheck);
        for (int i = 0; i < listToCheck.size(); i++) {
            Assert.assertTrue(listToCheck.get(i).equals(listFromSite.get(i)));
        }
    }

    public void checkThatSortingByPriceIsCorrect() {
        List<WebElement> sortByPrice = productsSortedByPrice;
        List<String> listPricesToCheck = new ArrayList<String>();
        List<String> listPricesFromSite = new ArrayList<String>();
        for (WebElement element : sortByPrice) {
            listPricesFromSite.add(getSomePrice(element.getText()));
            listPricesToCheck.add(getSomePrice(element.getText()));
        }
        Collections.sort(listPricesToCheck);
        for (int i = 0; i < listPricesToCheck.size(); i++) {
            Assert.assertTrue(listPricesToCheck.get(i).equals(listPricesFromSite.get(i)));
        }

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

    // SECOND TEST

    public ProductPage addToCompareListItemNumber(int itemIndex) {
        ClickerHelper.clickOnElement(driver, addItemToCompare.get(itemIndex - 1));
        return this;
    }

    public ComparePage clickToComparePage() {
        ClickerHelper.clickOnElement(driver, clickOnCompareButton);
        return new ComparePage(driver);
    }

    // THIRD TEST

    public void filterByMinMaxPrice() {
        ClickerHelper.clickOnElement(driver, filterByMinPriceButton);
        ClickerHelper.clickOnElement(driver, filterByMaxPriceButton);
        Integer minFilter = Integer.parseInt(filterByMinPriceButton.getText());
        Integer maxFilter = Integer.parseInt(filterByMaxPriceButton.getText());
        List<WebElement> filtredByMinMaxPrice = productsSortedByPrice;
        List<String> listPricesFromSite = new ArrayList<String>();

        for (WebElement element : filtredByMinMaxPrice) {
            listPricesFromSite.add(getSomePrice(element.getText()));
            Integer priceInList = Integer.parseInt(getSomePrice(element.getText()));
            Assert.assertTrue(priceInList > minFilter && priceInList < maxFilter, "Test 'filterByMinMaxPrice' failed! Prices don't lie in the specified range!");
        }
    }

    // FOURTH TEST

    public ProductPage filterByBreadmakerManufacturers(int... lilterLink) {

        Set<String> listManufacturers = new LinkedHashSet<String>();
        for (int everyLinkToFilter : lilterLink) {
            String firstFilter = String.format(MANUFACTURER, everyLinkToFilter);
            WebElement firstLink = driver.findElement(By.xpath(firstFilter));
            listManufacturers.add(firstLink.getText());
            ClickerHelper.clickOnElement(driver, firstLink);
        }


        List<WebElement> sortByName = productsSortedByName;
        Set<String> filteredListManufacturers = new LinkedHashSet<String>();
        for (WebElement element : sortByName) {
            filteredListManufacturers.add(element.getText().split("\\s+")[0]);
        }

        Assert.assertEquals(listManufacturers, filteredListManufacturers, " present different manufacturers");
        return this;
    }


    //FIFTH TEST
    public ProductPage verifyWeightTuning() {

        ClickerHelper.clickOnElement(driver, weightTuning);

        List<WebElement> description = productsDescription;
        Set<String> productsDescriptionInFilter = new LinkedHashSet<String>();
        for (WebElement element : description) {
            Assert.assertTrue(element.getText().contains("Регулировка веса"));
            productsDescriptionInFilter.add(element.getText());
        }
        return this;
    }

    //SIX TEST

    public Map<String, Integer> getNameAndPriceInCategoryList(int... links) {
        Map<String, Integer> listProductDescription = new HashMap<String, Integer>();
        WebElement prodLink = null;
        for (int everyLinkToFilter : links) {
            String nameProd = String.format(PRODUCT_IN_LIST, everyLinkToFilter);
            String priceProd = String.format(PRODUCT_PRICE_IN_LIST, everyLinkToFilter);

            prodLink = driver.findElement(By.xpath(nameProd));
            WebElement priceLink = driver.findElement(By.xpath(priceProd));

            listProductDescription.put(prodLink.getText()+" в Харькове", Integer.parseInt(getSomePrice(priceLink.getText())));
        }
        ClickerHelper.clickOnElement(driver, prodLink);
        System.out.println(listProductDescription+"         listProductDescription");
        return listProductDescription;
    }

}
