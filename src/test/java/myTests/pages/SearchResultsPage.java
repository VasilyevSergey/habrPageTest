package myTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SearchResultsPage extends Page{
    @FindBy(how = How.CLASS_NAME, using = "post__title_link")
    public List<WebElement> allArticles;

    @FindBy(how = How.ID, using = "next_page")
    public WebElement nextPageButton;

    //поиск статьи по названию по всем страницам
    public void getArticle(String text) {
        byte ptext[] = text.getBytes();
        String value = new String(ptext, StandardCharsets.UTF_8);

        boolean didWeFoundArticle = false;
        for (WebElement article : allArticles) {
            if(article.getText().equals(value)){
                didWeFoundArticle = true;
                article.click();
                break;
            }
        }
        if(didWeFoundArticle == false){
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
