package myTests;

import java.io.File;
import java.net.URL;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for all the JUnit-based test classes
 */
public class JUnitTestBase {

  protected static URL gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;
  protected static String myWebDriver;
  protected static File pathToMyDriver;

  protected WebDriver driver;

  @ClassRule
  public static ExternalResource webDriverProperties = new ExternalResource() {
    @Override
    protected void before() throws Throwable {
      SuiteConfiguration config = new SuiteConfiguration();
      baseUrl = config.getProperty("site.url");
      if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
        gridHubUrl = new URL(config.getProperty("grid.url"));
      }
      capabilities = config.getCapabilities();
    };
  };

  @Rule
  public ExternalResource webDriver = new ExternalResource() {
    @Override
    protected void before() throws Throwable {
      myWebDriver = capabilities.getCapability("myWebdriver").toString();
      pathToMyDriver = new File("src\\test\\resources\\drivers\\" + capabilities.getCapability("pathToMyDriver"));
      System.setProperty(myWebDriver, pathToMyDriver.getCanonicalPath());

      driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    };
  };
}
