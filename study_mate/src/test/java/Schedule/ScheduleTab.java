package Schedule;

import Utilities.JsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ScheduleTab {

    WebDriver driver;

    public ScheduleTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root " +
            "MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium css-slyssw']")
    public WebElement calendarIcon;
    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary " +
            "MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root " +
            "MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium css-79mk38']")
    public WebElement createEventButton;
    @FindBy(id = ":r6:")
    public WebElement date;

    @FindBy(xpath = "//input[@id='startTime']")
    public WebElement fromTime;

    @FindBy(xpath = "//input[@id='endTime']")
    public WebElement tillTime;

    @FindBy(id = "title")
    public WebElement name;

    @FindBy(id = "mui-component-select-groupIds")
    public WebElement chooseGroupButton;

    @FindBy(xpath = "//li[@data-value='459']")
    public WebElement group;

    @FindBy(xpath = "//div[@class='sc-gJqSRm fJqgnq']")
    public WebElement orange;

    @FindBy(xpath = "//div[@class='sc-gJqSRm jbRidv']")
    public WebElement red;

    @FindBy(xpath = "//div[@class='sc-gJqSRm iA-deBs']")
    public WebElement yellow;

    @FindBy(xpath = "//div[@class='sc-gJqSRm frSawh']")
    public WebElement green;

    @FindBy(xpath = "//div[@class='sc-gJqSRm kvfMbB']")
    public WebElement blue;

    @FindBy(xpath = "//div[@class='sc-gJqSRm dSJjef']")
    public WebElement violet;

    @FindBy(xpath = "(//div[contains(text(),'Schedule')])[3]")
    public WebElement createSchedule;

    @FindBy(id = ":r7:")
    public WebElement scheduleName;

    @FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root " +
            "MuiIconButton-edgeEnd MuiIconButton-sizeMedium css-slyssw'])[2]")
    public WebElement scheduleCalendarIcon;

    public void createEventFunction(String yearValue,String monthValue,int dayValue, String startTime,String endTime, String nameValue) throws InterruptedException {
        createEventButton.click();
        calendarIcon.click();

        WebElement yearButton = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall " +
                "MuiPickersCalendarHeader-switchViewButton css-1wjkg3']"));
        JsUtils.click(yearButton, driver);

        WebElement yearValueButton = driver.findElement(By.xpath("//button[contains(text(), '" + yearValue + "')]"));
        JsUtils.click(yearValueButton, driver);


        selectMonth(monthValue);

        WebElement dayValueButton = driver.findElement(By.xpath("//button[contains(text(),'" + (dayValue-1) + "')]"));
        JsUtils.click(dayValueButton, driver);


        fromTime.click();
        fromTime.sendKeys(startTime);
        tillTime.click();
        tillTime.sendKeys(endTime);

        name.sendKeys(nameValue);

        chooseGroupButton.click();
        JsUtils.click(group, driver);

        Actions actions = new Actions(driver);
        actions.moveByOffset(1, 1).click().build().perform();


        WebElement publishButton = driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained " +
                "MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root " +
                "MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium css-79mk38'])[2]"));
        publishButton.click();
    }


    public void createScheduleFunction(String yearValue, String monthValue, int dayValue, String startTime, String
            endTime, String nameValue, String groupName, String weekDay, String yearValue2,
                                       String monthValue2, int dayValue2){

        createSchedule.click();

        calendarIcon.click();
        WebElement yearButton = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall " +
                "MuiPickersCalendarHeader-switchViewButton css-1wjkg3']"));
        JsUtils.click(yearButton, driver);

        WebElement yearValueButton = driver.findElement(By.xpath("//button[contains(text(), '" + yearValue + "')]"));
        JsUtils.click(yearValueButton, driver);

        selectMonth(monthValue);

        WebElement dayValueButton = driver.findElement(By.xpath("//button[contains(text(),'" + (dayValue-1) + "')]"));
        JsUtils.click(dayValueButton, driver);

        fromTime.click();
        fromTime.sendKeys(startTime);
        tillTime.click();
        tillTime.sendKeys(endTime);

        scheduleName.sendKeys(nameValue);

        chooseGroupButton.click();
        chooseGroupField(groupName);

        Actions actions = new Actions(driver);
        actions.moveByOffset(1, 1).click().build().perform();

        clickDay(weekDay);

        scheduleCalendarIcon.click();

        WebElement yearButton2 = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root " +
                "MuiIconButton-root MuiIconButton-sizeSmall MuiPickersCalendarHeader-switchViewButton css-1wjkg3']"));
        JsUtils.click(yearButton2, driver);


        WebElement yearValueButton2 = driver.findElement(By.xpath("//button[contains(text(), '" + yearValue2 + "')]"));
        JsUtils.click(yearValueButton2, driver);

        selectMonth(monthValue2);

        WebElement dayValueButton2 = driver.findElement(By.xpath("//button[contains(text(),'" + (dayValue2-1) + "')]"));
        JsUtils.click(dayValueButton2, driver);



        WebElement publishButton = driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained " +
                "MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root " +
                "MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium css-79mk38'])[2]"));
        publishButton.click();


    }

    public void chooseGroupField(String name){
        WebElement choseGroupText = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        JsUtils.click(choseGroupText,driver);
    }

    public String getDays(String daysName){

        switch (daysName.toLowerCase()){
            case "monday":
                return "Mon";
            case "tuesday":
                return "Tues";
            case "wednesday":
                return "Wen";
            case "thursday":
                return "Thurs";
            case "friday":
                return "Fri";
            case "saturday":
                return "sat";
            case "sunday":
                return "sun";
            default:
                throw new IllegalArgumentException("Invalid day's name: " + daysName);
        }
    }

    public void clickDay(String dayName){
        String shortDay = getDays(dayName);
        WebElement weekDay = driver.findElement(By.xpath("//button[contains(text(),'" + shortDay + "')]"));
        weekDay.click();
    }

    public void setColor(String colorName){
        switch (colorName.toLowerCase()){
            case "orange":
                orange.click();
            case "red":
                red.click();
            case "yellow":
                yellow.click();
            case "green":
                green.click();
            case "blue":
                blue.click();
            case "violet":
                violet.click();
            default:
                throw new IllegalArgumentException("Invalid color name: " + colorName);
        }
    }





    public void selectMonth(String targetMonth){

        int clicks = getMonthNumber(targetMonth);

        WebElement nextArrow = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart " +
                "MuiIconButton-sizeMedium MuiPickersArrowSwitcher-button css-1fklenr']"));
        WebElement previousArrow = driver.findElement( By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root " +
                "MuiIconButton-edgeEnd MuiIconButton-sizeMedium MuiPickersArrowSwitcher-button css-11wxb']"));

        if(clicks > 0){
            for (int i = 1; i <= clicks-1; i++) {
                nextArrow.click();
            }
        }
        else {
            for (int i = 0; i >clicks ; i--) {
                previousArrow.click();
            }
        }
    }



    public int getMonthNumber(String monthName) {
        // Convert month name to a number (e.g., January -> 1, February -> 2, etc.)
        switch (monthName.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }
}
