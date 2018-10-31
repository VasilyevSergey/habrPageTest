package myTests;

import myTests.pages.ArticlePage;
import myTests.pages.HabrPage;
import myTests.pages.SearchResultsPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HabrTest extends JUnitTestBase {
    private HabrPage habrpage;
    private SearchResultsPage searchresultspage;
    private ArticlePage articlepage;

    @Before
    public void initPageObjects() {
        searchresultspage = PageFactory.initElements(driver, SearchResultsPage.class);
        habrpage = PageFactory.initElements(driver, HabrPage.class);
        articlepage = PageFactory.initElements(driver, ArticlePage.class);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testHabrPage() {
        driver.get(baseUrl);

        habrpage.searchFor("selenium java + cucumber");

        searchresultspage.getArticle("Руководство: Cucumber + Java");

        List<String> expectedTagsList = new ArrayList<>();
        expectedTagsList.add("java");
        expectedTagsList.add("cucumber");
        expectedTagsList.add("bdd");
        expectedTagsList.add("automation testing");
        expectedTagsList.add("automation qa");

        Assert.assertTrue(articlepage.getAllTags().containsAll(expectedTagsList));
    }
}
