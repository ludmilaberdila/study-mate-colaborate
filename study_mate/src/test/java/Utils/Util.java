package Utils;

import Teacher.TeachersWebElements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Random;

public class Util {

    public static boolean checkCssClass(WebElement webElement, String className, String whichInput){
        boolean divErrorClass = false;
        try{
            divErrorClass = webElement.getAttribute("class").contains(className);
            Assert.assertTrue("Div element doesn't contain css error class", divErrorClass);
            Util.printLog("success", "\"" + whichInput + "\" " +
                    " \"" + className +
                    "\" css error class is present");
        }catch (Exception e){
            Util.printLog("error", "\"" + whichInput + "\" " +
                    "\"" + className +
                    "\" css error class is missing on div or label");
        }
        return divErrorClass;
    }

    /**
     * find cancel button and click on it
     * @param teacherPath
     * @throws InterruptedException
     */
    public static void cancelButton(WebElement teacherPath) throws InterruptedException {
        try {
            teacherPath.click();
        }catch (Exception e){
            printLog("warning", "No Cancel button found to click");
        }
        Thread.sleep(2000);
    }

    /**
     * Will generate a string containing numbers
     * @param size how many numbers phone number will have
     * @return
     */
    public static String generateRandomPhone(int size){
        Random r = new Random();
        int randIndex;
        String numbers = "0123456789";
        String phone = "";
        int pSize = size;
        while (pSize > 0){
            randIndex = r.nextInt(numbers.length());
            phone += numbers.charAt(randIndex);
            pSize--;
        }
        return phone;
    }

    /**
     * Generate a random string
     * @return
     */
    public static String getRandomString(int textSize){
        Random random = new Random();
        int leftLimit = 96;
        int rightLimit = 122;
        int targetStringLength = textSize;
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    /**
     * Test if alert message is displayed correctly
     * @param driver
     * @param alertType: successSuccess, errorSuccess
     * @param message
     */
    public static boolean compareAlertMessage(WebDriver driver, String alertType, String message) {
        boolean passFail = false;
        String alertSelector = "div.MuiAlert-" + alertType;
        if (!driver.findElements(By.cssSelector(alertSelector)).isEmpty() &&
                driver.findElement(By.xpath("//div[contains(@class,'MuiAlert-" +
                                alertType + "')]//p[@class='sc-dkrFOg hbyUzQ']"))
                        .getText().equals(message)) {
            printLog("success", "Alert message does match: \"" + message + "\"");
            passFail = true;
        } else {
            printLog("error", "Alert message doesn't match \"" + message + "\"");
        }
        return passFail;
    }

    /**
     * Helps to print message in the console log
     * @param successMsg takes a success message
     * @param failMsg takes a fail message
     * @param successFail a boolean value if it should be success or fail
     * @throws InterruptedException
     */
    public static void displayMessage(String successMsg, String failMsg, boolean successFail) throws InterruptedException {
        if (successFail) {
            printLog("success", successMsg);
        } else {
            printLog("error", failMsg);
            Thread.sleep(2000);
        }
    }

    /***
     * Console print colored message
     * @param type: success, error, warning
     * @param message
     * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println/5762502#5762502
     */
    public static void printLog(String type, String message){
        type = type.toLowerCase();
        if(type.equals("success")){
            System.out.println("\033[0;32m" + message + "\033[0m");
        } else if (type.equals("error")) {
            System.out.println("\033[0;31m" + message + "\033[0m");
        } else if (type.equals("warning")) {
            System.out.println("\033[0;33m" + message + "\033[0m");
        }else { System.out.println(message); }
    }

    /**
     * Populate teacher form by providing WebDriver and a HashMap with values
     * @param driver
     * @param teacherPath
     * @param teacher
     * @throws InterruptedException
     */
    public static void addNewTeacher(WebDriver driver, TeachersWebElements teacherPath,
                                     HashMap<String, String> teacher,
                                     boolean clickSubmit) throws InterruptedException {

        teacherPath.addTeachButton.click();
        Thread.sleep(2000);
        printLog("success", "Filling out the form for new teacher...");

        teacherPath.inputFirstName.sendKeys(teacher.get("fName"));
        Thread.sleep(200);

        teacherPath.inputLastName.sendKeys(teacher.get("lName"));
        Thread.sleep(200);

        teacherPath.inputPhoneNumber.sendKeys(teacher.get("phone"));
        Thread.sleep(200);

        teacherPath.inputEmailAddress.sendKeys(teacher.get("email"));
        Thread.sleep(200);

        teacherPath.inputSpecialization.sendKeys(teacher.get("specialization"));
        Thread.sleep(200);

        teacherPath.inputDivCourses.click();
        String[] courses = teacher.get("studyFormat").split(";");
        for(String course : courses){
            teacherPath.courseDropUl.findElement(
                    By.xpath("//li[contains(text(),'" + course + "')]")).click();
        }
        Thread.sleep(500);

        Actions action = new Actions(driver);
        action.moveToElement(teacherPath.inputFirstName).click().perform();
        Thread.sleep(2000);
        if(clickSubmit){
            teacherPath.submitTeacherForm.click();
        }
    }

    public static String getNumberOfTeachers(TeachersWebElements teacherPath) throws InterruptedException {
        Thread.sleep(2000);
        String[] fromTotal_2 = teacherPath.numberOfTeachers.getText().split(" ");
        return fromTotal_2[1];
    }
}
