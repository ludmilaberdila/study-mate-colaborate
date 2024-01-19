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
        scheduleTab.createScheduleFunction("2024","february",4,"17:00","20:00","Self-Study",
                "Howe-Klein","monday","2024","april",18);
    }
    @Then("verify presence of created schedule")
    public void verify_presence_of_created_schedule() {
        Util.printLog("Pass!","Schedule successfully created!");
    }

    @When("admin click on a date")
    public void admin_clicks_on_a_date() {
        driver.findElement(By.xpath("//a[@href='/admin/schedule']")).click();
        scheduleTab.deleteEvent("2024-02-05");
    }

    @Then("click on delete button")
    public void click_on_delete_button() {
        scheduleTab.deleteButtonRed.click();
    }

    @Then("verify that event is deleted")
    public void verify_event_is_deleted() {
        Util.printLog("Pass!","Event/Schedule successfully deleted");

    }


}
