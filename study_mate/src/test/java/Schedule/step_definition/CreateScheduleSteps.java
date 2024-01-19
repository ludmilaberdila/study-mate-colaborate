package Schedule.step_definition;

import Schedule.ScheduleTab;
import Utilities.Driver;
import Utils.Util;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateScheduleSteps {

    WebDriver driver = Driver.getDriver();
    ScheduleTab scheduleTab = new ScheduleTab(driver);

    @Before
    public void connectUI(){
        driver.get("https://codewiser.studymate.us/login");
    }

    @Given("admin logs in to studymate with {string} and {string}")
    public void admin_logs_in_to_studymate_with_and(String username, String password) {
        driver.findElement(By.id(":r0:")).sendKeys(username);
        driver.findElement(By.id(":r1:")).sendKeys(password);
        driver.findElement(By.xpath("(//button[@tabindex='0'])[2]")).click();
    }
    @When("admin go to Schedule Tab")
    public void admin_go_to_schedule_tab() {
        driver.findElement(By.xpath("//a[@href='/admin/schedule']")).click();
    }
    @Then("click on create event button")
    public void click_on_create_event_button() {
        scheduleTab.createEventButton.click();
    }

    @Then("click on schedule button and perform necessary steps")
    public void click_on_schedule_button_and_perform_necessary_steps() {
        scheduleTab.createScheduleFunction("2024","april",2,"10:00","15:00","Soccer",
                "Bartell Group","tuesday","2024","april",27);
    }
    @Then("verify presence of created schedule")
    public void verify_presence_of_created_schedule() {
        Util.printLog("Pass!","Schedule successfully created!");
    }



}
