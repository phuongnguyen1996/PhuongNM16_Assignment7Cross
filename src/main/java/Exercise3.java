import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Exercise3 {
    @Test
    public void test1() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php?controller=contact");

//        Subject Heading, Email address, Order reference, Attach File, Message and send button
        WebElement subject = driver.findElement(By.xpath("//select[@id='id_contact']"));
        System.out.println(subject.getLocation().getX());
//        Select “Customer service” in Subject Heading.
        Select select1 = new Select(subject);
        select1.selectByIndex(1);

        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        System.out.println(email.isDisplayed());
//        Input email address
        email.sendKeys("nmaiphuong2015@gmail.com");

        WebElement orderReference = driver.findElement(By.xpath("//input[@id='id_order']"));
        System.out.println(orderReference.isDisplayed());
//        Select the Order reference
        orderReference.sendKeys("Product");

        WebElement attachFile = driver.findElement(By.xpath("//input[@id='fileUpload']"));
        System.out.println(attachFile.getLocation().getX());

        WebElement message = driver.findElement(By.xpath("//textarea[@id='message']"));
        System.out.println(message.isDisplayed());
//        Input the message
        message.sendKeys("Hello friends!");

        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='submitMessage']"));
        System.out.println(sendBtn.isDisplayed());
//        Click Send button and verify message successfully: Your message has been successfully sent to our team.
        sendBtn.click();

        WebElement result = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
        Assert.assertEquals(result.getText(), "Your message has been successfully sent to our team.");

        driver.close();
    }
}
