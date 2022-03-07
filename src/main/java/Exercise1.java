import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Exercise1 {
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

    @BeforeTest
    @Parameters({"platform", "browser"})
    public void beforeClass(String platform, String browser) throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        RemoteWebDriver driver = null;
        //Platform
        if (browser.equalsIgnoreCase("Windows")){
            caps.setPlatform(Platform.WINDOWS);
        }
        if (browser.equalsIgnoreCase("MAC")){
            caps.setPlatform(Platform.MAC);
        }
        //Browser
        if (browser.equals("Chrome")){
            caps.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), caps);
        }
        if (browser.equals("Firefox")){
            caps.setBrowserName(BrowserType.FIREFOX);
            driver = new RemoteWebDriver(new URL("http://localhost:5577/wd/hub"), caps);
        }
        if(browser.equals("HeadlessMode")){
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
            driver = new PhantomJSDriver(caps);
        }
        setWebDriver(driver);
        driver.manage().window().maximize();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private WebDriver getDriver() {
        return dr.get();
    }

    private void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }

    @AfterClass
    public void afterClass() {
        getDriver().quit();
        dr.set(null);
    }
}
