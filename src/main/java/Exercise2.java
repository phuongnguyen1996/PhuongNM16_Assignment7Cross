import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise2 {
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);

        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);

        driver.manage().window().maximize();
    }

    @Test
    public void testGooglePageTitleInChrome() {
        driver.navigate().to("https://www.google.com.vn/");
        String strPageTitle = driver.getTitle();
        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
    }

    public void testSearchGoogle() {
        System.out.println("Opening Google..");
        driver.navigate().to("https://www.google.com.vn/");
        driver.findElement(By.name("q")).sendKeys("Selenium Easy Grid Tutorials");
        driver.findElement(By.name("q")).submit();
    }

    @AfterSuite
    public void afterTest() {
        driver.close();
    }
}
