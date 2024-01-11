package Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;
    private static String browser = Config.getValue("browser");

    public static WebDriver getDriver(){
        // if we don't have currently running browser
        // then create a new browser session
        if(driver == null){
            if(browser.equalsIgnoreCase("chrome")){
                driver = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
            }
            else{
                // open my default browser
                driver = new FirefoxDriver();
            }
            int pageLoadTimeOut = Integer.parseInt(Config.getValue("pageLoadTimeOut"));
            int implicitlyWait = Integer.parseInt(Config.getValue("implicitWait"));

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeOut));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
            driver.manage().window().maximize();
            // when you need to open new browser
            return driver;
        }
        // if you have opened browser, it will just return it
        return driver;
    }

    public static void quitBrowser(){
        if(driver!= null){
            driver.quit();
            driver = null;
        }
    }
}