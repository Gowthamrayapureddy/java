package mobile_testing_app;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Assignment {

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("appPackage", "com.example.myapplication");
        caps.setCapability("appActivity", "MainActivity");

        AndroidDriver<MobileElement> driver =
                new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        Thread.sleep(3000);

        // (1) Check login page
        assert driver.findElement(By.id("login")).isDisplayed();

        // (2) Enter username
        driver.findElement(By.id("username")).sendKeys("company");

        // (3) Focus password
        driver.findElement(By.id("password")).click();

        // (4) Enter password
        driver.findElement(By.id("password")).sendKeys("company");

        // (5) Focus username
        driver.findElement(By.id("username")).click();

        // (6) Clear username
        driver.findElement(By.id("username")).clear();

        // (7) Click login (should fail)
        driver.findElement(By.id("login")).click();

        // (8) Re-enter username
        driver.findElement(By.id("username")).sendKeys("company");

        // (9) Login success
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        // (10) Go to payment page
        driver.findElement(By.id("makePayment")).click();

        // (11) Enter phone
        driver.findElement(By.id("phone")).sendKeys("612-555-0112");

        // (12) Enter name
        driver.findElement(By.id("name")).sendKeys("Alice");

        // (13) Drag slider to $35
        MobileElement slider = driver.findElement(By.id("amount"));
        int startX = slider.getLocation().getX();
        int endX = startX + 200;
        int y = slider.getLocation().getY();

        new Actions(driver)
                .dragAndDropBy(slider, 200, 0)
                .perform();

        // (14) Send payment (fail)
        driver.findElement(By.id("sendPayment")).click();

        // (15) Enter country
        driver.findElement(By.id("country")).sendKeys("United States");

        // (16) Cancel
        driver.findElement(By.id("cancel")).click();

        // (17) Go back to payment
        driver.findElement(By.id("makePayment")).click();

        // (18) Enter phone
        driver.findElement(By.id("phone")).sendKeys("612-555-0355");

        // (19) Enter name
        driver.findElement(By.id("name")).sendKeys("Bob");

        // (20) Slider to $55
        slider = driver.findElement(By.id("amount"));
        new Actions(driver)
                .dragAndDropBy(slider, 300, 0)
                .perform();

        // (21) Open country list
        driver.findElement(By.id("countryButton")).click();

        // (22) Select France (scroll or index)
        driver.findElement(By.xpath("//android.widget.TextView[@text='France']")).click();

        // (23) Send payment → alert
        driver.findElement(By.id("sendPayment")).click();
        Thread.sleep(2000);

        // (24) Dismiss alert
        driver.switchTo().alert().dismiss();

        // (25) Send again
        driver.findElement(By.id("sendPayment")).click();

        // (26) Accept alert
        driver.switchTo().alert().accept();

        Thread.sleep(2000);

        // (27) Logout
        driver.findElement(By.id("logout")).click();

        System.out.println("All tests executed successfully!");

        driver.quit();
    }
}
