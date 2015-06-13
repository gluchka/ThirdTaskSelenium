package tests;



import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import components.BaseTest;
import org.apache.log4j.Logger;
import pages.ComparePage;
import pages.MainPage;
import pages.ItemPage;
import pages.ProductPage;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class AllTests extends BaseTest {

    private static final Logger log = Logger.getLogger(BaseTest.class);
    private final static String CATEGORY_NAME = "Бытовая техника";
    private final static String PRODUCT_REFREGIRTOR = "Холодильники";
    private final static String PRODUCT_BREAD_MACKER = "Хлебопечи";
    private final static String PRODUCT_MICROWAVE = "Микроволновки";
    private final static String PRODUCT_WASHING_MACHINE = "Стиральные машины";
    private final static String PRODUCT_AIRCONDITION = "Кондиционеры";

    MainPage mainPage;
    ProductPage productPage;
    ComparePage comparePage;
    ItemPage itemPage;

    @BeforeMethod
    public void preparationForTheTest() {
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        comparePage = new ComparePage(driver);
        itemPage = new ItemPage(driver);

    }

    @Test(enabled = true, priority = 1)
    public void firstTestSorting() {
        productPage = mainPage.clickOnCategoryByName(CATEGORY_NAME).clickOnProductByName(PRODUCT_REFREGIRTOR);
        productPage.clickSortByNameButton().checkThatSortingByNameIsCorrect();
        productPage.clickSortByPriceButton().checkThatSortingByPriceIsCorrect();
        productPage.goToMainPage();
    }


    @Test(enabled = true, priority = 2)
    public void secondTestCompare() {
        productPage = mainPage.clickOnCategoryByName(CATEGORY_NAME).clickOnProductByName(PRODUCT_MICROWAVE);
        comparePage = productPage.addToCompareListItemNumber(1).addToCompareListItemNumber(2).clickToComparePage();

        comparePage.checkColorInDiffCharacteristics(1, 2);

        List<String> allWaveCharacteristics = comparePage.compareAllCharacteristicsInProductNumber(1);
        comparePage.clickToFirstWave();
        List<String> waveCharacteristics = itemPage.getMicrowaveCharacteristics();
        itemPage.clickBackToCompare();

        assertTrue(allWaveCharacteristics.containsAll(waveCharacteristics));

        allWaveCharacteristics = comparePage.compareAllCharacteristicsInProductNumber(2);
        comparePage.clickToSecondWave();
        waveCharacteristics = itemPage.getMicrowaveCharacteristics();

        assertTrue(allWaveCharacteristics.containsAll(waveCharacteristics));

        itemPage.goToMainPage();
    }


    @Test(enabled = true, priority = 3)
    public void thirdTestFilter() {
        productPage = mainPage.clickOnCategoryByName(CATEGORY_NAME).clickOnProductByName(PRODUCT_WASHING_MACHINE);
        productPage.filterByMinMaxPrice();
        productPage.goToMainPage();

    }

    @Test(enabled = true, priority = 4)
    public void fourthTestFilterManufacture() {
        productPage = mainPage.clickOnCategoryByName(CATEGORY_NAME).clickOnProductByName(PRODUCT_BREAD_MACKER);
        productPage.filterByBreadmakerManufacturers(2, 4, 1, 7);
        productPage.goToMainPage();

    }


    @Test(enabled = true,priority = 5)
    public void fifthTestFilter() {
        productPage = mainPage.clickOnCategoryByName(CATEGORY_NAME).clickOnProductByName(PRODUCT_BREAD_MACKER);
        productPage.verifyWeightTuning();
        productPage.goToMainPage();

    }

    @Test(enabled = true, priority = 6)
    public void sixTestCompareInfo() {
        productPage = mainPage.clickOnCategoryByName(CATEGORY_NAME).clickOnProductByName(PRODUCT_AIRCONDITION);

        Assert.assertEquals(productPage.getNameAndPriceInCategoryList(1),itemPage.getItemNameAndPrice(),"1 assert failed");
        Assert.assertEquals(productPage.getNameAndPriceInCategoryList(2),itemPage.getItemNameAndPrice(),"2 assert failed");
        Assert.assertEquals(productPage.getNameAndPriceInCategoryList(3),itemPage.getItemNameAndPrice(),"3 assert failed");
        Assert.assertEquals(productPage.getNameAndPriceInCategoryList(4),itemPage.getItemNameAndPrice(),"4 assert failed");
        Assert.assertEquals(productPage.getNameAndPriceInCategoryList(5),itemPage.getItemNameAndPrice(),"5 assert failed");
        productPage.goToMainPage();
    }


}
