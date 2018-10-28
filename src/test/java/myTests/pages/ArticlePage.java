package myTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class ArticlePage extends Page {
    @FindBy(how = How.CLASS_NAME, using = "post__tags-list")
    public WebElement detectedTags;

    //находим строку с тегами detectedTags, парсим её, проверяем наличие в строке detectedTags каждого из тегов expectedTags
    public void checkSomeTags(String tag, String... tags) {
        ArrayList<String> expectedTags = new ArrayList();
        expectedTags.add(new String(tag.getBytes(), StandardCharsets.UTF_8));

        for(String expectedTag : tags){
            expectedTags.add(new String(expectedTag.getBytes(), StandardCharsets.UTF_8));
        }

        //парсим строку найденных тегов detectedTags
        Pattern pattern = Pattern.compile("\n");
        String[] words = pattern.split(detectedTags.getText());
        List<String> listOfDetectedTags = new ArrayList<>();
        Arrays.asList(words).forEach(word -> listOfDetectedTags.add(word));

        for (String expectedTag : expectedTags){
            boolean didWeFoundTag = false;

            for(String currentDetectedTag : listOfDetectedTags) {
                if(currentDetectedTag.equals(expectedTag)){
                    assertEquals(expectedTag, currentDetectedTag);
                    didWeFoundTag = true;
                    break;
                }
            }
            if(didWeFoundTag == false){
                assertEquals(expectedTag, detectedTags.getText());
            }
        }
    }

    public ArticlePage(WebDriver webDriver) {
        super(webDriver);
    }
}
