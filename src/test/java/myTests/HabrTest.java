package myTests;

import myTests.pages.ArticlePage;
import myTests.pages.HabrPage;
import myTests.pages.SearchResultsPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HabrTest extends JUnitTestBase {
    private HabrPage habrpage;
    private SearchResultsPage searchresultspage;
    private ArticlePage articlepage;

    @FindBy(how = How.CLASS_NAME, using = "post__title_link")
    public List<WebElement> allArticles;

    @FindBy(how = How.ID, using = "next_page")
    public WebElement nextPageButton;

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
        articlepage.checkSomeTags("java", "cucumber", "bdd", "automation testing", "automation qa");
    }
}
