package Schedule.tests;

import Schedule.ScheduleTab;
import Utilities.Driver;
import Utils.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScheduleTabTest {

    static WebDriver driver  = Driver.getDriver();
    static ScheduleTab scheduleTab = new ScheduleTab(driver);

    @BeforeTest
    public static void connectToUI(){
        driver.get("https://codewiser.studymate.us/admin/teachers?size=6&page=1");

        driver.findElement(By.id(":r0:")).sendKeys("admin@codewise.com");
        driver.findElement(By.id(":r1:")).sendKeys("codewise123");
        driver.findElement(By.xpath("(//button[@tabindex='0'])[2]")).click();
        driver.findElement(By.xpath("//a[@href='/admin/schedule']")).click();
    }

    @Test
    public void createEvent() throws InterruptedException {
        scheduleTab.createEventFunction("2024", "march",20,"01:10","02:20","Work");
        Util.printLog("success","Event successfully created!");
    }
}
