package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public long PAGE_LOAD_TIMEOUT = 50;
    public long IMPLICIT_WAIT = 40;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--window-size=1366,768");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        Launch_URL("https://www.ebay.com/");
        Verify_Browser_Launched();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        driver.quit();
    }

    public void Verify_Browser_Launched(){
        String result = driver.toString();
        Assert.assertFalse(result.isEmpty(), "Browser not launched");
    }

    public void Launch_URL(String URL){
        driver.get(URL);
    }

}
