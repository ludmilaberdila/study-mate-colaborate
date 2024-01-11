package Utils;

import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class StudyMateConfig {
    public static WebDriver driver;
    public static Wait<WebDriver> wait;
    public String mainUrl = "https://codewiser.studymate.us/";

    public HashMap<String, String> teacherToAdd;

    public StudyMateConfig(){
        Util.printLog("success", "configuring webdriver");
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Util.printLog("success", "set browser dimensions 1400x1000");
        Dimension browserSize = new Dimension(1400, 1000);
        driver.manage().window().setSize(browserSize);
    }

    /**
     * Dedicated for user login
     * @throws InterruptedException
     */
    public void logInUser() throws InterruptedException {
        // access studymate page
        driver.get( mainUrl + "login" );
        // wait until page is loaded
        wait.until(d -> driver.findElement(By.xpath("//input[@id=':r0:']")).isDisplayed());
        Util.printLog("success", "page loaded successfully");

         Util.printLog("success", "fill up login form...");
        driver.findElement(By.xpath("//input[@id=':r0:']"))
                .sendKeys("admin@codewise.com");
        Thread.sleep(300);
        driver.findElement(By.xpath("//input[@id=':r1:']"))
                .sendKeys("codewise123");
        Thread.sleep(300);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(d -> driver.findElement(By.xpath("//li[contains(text(),'Student')]"))
                .isDisplayed());
        Thread.sleep(2000);
        Util.printLog("success", "User logged in");
    }

    public void navigateToPage(String page){

        driver.get(mainUrl + page);
        Util.printLog("success", "User navigated to \"" + page + "\"");
    }

    public void generateNewTeacher(){
        teacherToAdd = new HashMap<String, String>() {{
            put("email", Util.getRandomString(10) + ".urmat@gmail.com");
            put("fName", "Albina");
            put("lName", "Urmat");
            put("phone", "7739853257");
            put("specialization", "SoftSkills");
            put("studyFormat", "Education;TESTING TAB;Master of Business");
        }};
    }
    public void setUpConfig() throws InterruptedException {
        generateNewTeacher();
        logInUser();
        // navigate to teachers
        navigateToPage("admin/teachers");
    }
}
