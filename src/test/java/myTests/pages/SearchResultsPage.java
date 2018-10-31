package myTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class SearchResultsPage extends Page{
    @FindBy(how = How.XPATH, using = "//a[@class='post__title_link']")
    public List<WebElement> allArticles;

    @FindBy(how = How.XPATH, using = "//a[@id='next_page']")
    public WebElement nextPageButton;

    //поиск статьи по названию по всем страницам
    public void getArticle(String text) {
        byte ptext[] = text.getBytes();
        String value = new String(ptext, StandardCharsets.UTF_8);

        Optional<WebElement> article = allArticles.stream().filter(webElement -> webElement.getText().equals(value)).findFirst();
        if(article.isPresent()){
            article.get().click();
        }else{
            if(nextPageButton != null){
                nextPageButton.click();
                getArticle(text);
            }
        }
    }

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }
}
