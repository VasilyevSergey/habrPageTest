package myTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.ArrayList;
import java.util.List;

public class ArticlePage extends Page {
    @FindBy(how = How.XPATH, using = "//a[@rel='tag']")
    public List<WebElement> detectedTagsList;

    public List<String> getAllTags(){
        List<String> tagsList = new ArrayList<>();
        detectedTagsList.stream().forEach(webElement -> tagsList.add(webElement.getText()));
        return tagsList;
    }

    public ArticlePage(WebDriver webDriver) {
        super(webDriver);
    }
}
