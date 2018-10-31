package myTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;

public class HabrPage extends Page {
    @FindBy(how = How.XPATH, using = "//button[@id='search-form-btn']")
    public WebElement searchBtn;

    @FindBy(how = How.XPATH, using = "//input[@id='search-form-field']")
    public WebElement searchField;

    public void searchFor(String text) {
        byte ptext[] = text.getBytes();
        String value = new String(ptext, StandardCharsets.UTF_8);

        searchBtn.click();
        searchField.sendKeys(value);
        searchField.submit();

        //ожидаем загрузки страницы
        String expectedTitle = "Результаты поиска по запросу «" + text + "» / Хабр";
        byte pString[] = expectedTitle.getBytes();
        String valueTitle = new String(pString, StandardCharsets.UTF_8);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(valueTitle));
    }

    public HabrPage(WebDriver webDriver) {
        super(webDriver);
    }
}
