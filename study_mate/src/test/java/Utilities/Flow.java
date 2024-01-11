package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class Flow {

   /*this method should use when we need to ckick
   on dynimic webElement and ignore NoSuchElementException
   if it appears
   @ param locator is the element to be clicked
   @param driver is driver to connect the UI
   @author Ludmila
   * */

    public static  void safeClick(WebDriver driver, By locator){

        try{
            WebElement element = driver.findElement(locator);
            element.click();

        }catch (NoSuchElementException  | ElementClickInterceptedException e2){
            System.out.println("element not found");
            e2.printStackTrace();
        }
    }



    /*
    this method will safely try to switch to iframe
    @param driver is the driver to connect to UI
    @ author Liudmila
    * */
    public void safeSwitchToFrame(WebDriver driver, By locator){

        try {

        }catch (NoSuchElementException e) {
            System.out.println(" no Iframe to switc to!");
            e.printStackTrace();
        }
    }
    /*
    * @param driver go to ui
    * @param nameOrWindowHandle is uniquie id of window to switc to*/
    public static void saflySwitchToWindow(WebDriver driver, String nameOrWindowHandle){

        try {
            driver.switchTo().window(nameOrWindowHandle);

        }catch (NoSuchWindowException e){
            System.out.println(" no window to switch to was found, check the window or name");
        }
    }


    /* this method check if driver is in main parent window, and then only switch to
    new window, if driver is already in new window the method will not switch
    * */
    public static void saflySwitchToWindow(WebDriver driver, String parentOrWindowHandle, String nameOrWindowHandle){

        try {
            if(driver.getCurrentUrl().equals(parentOrWindowHandle))
            driver.switchTo().window(nameOrWindowHandle);

        }catch (NoSuchWindowException e){
            System.out.println(" no window to switch to was found, check the window or name");
        }
    }

    /*
    this meth thie to cclick on webelement before to throwing noSuchElementException */

    public static void clickWithRetries(WebDriver driver, By locator , int numOfRetries){

        for (int i = 0; i<= numOfRetries; i++){
            try {
                driver.findElement(locator).click();
            }catch (NoSuchWindowException | ElementClickInterceptedException | StaleElementReferenceException e){
                System.out.println("failed to click on the webelement , retry #" + i);
                System.out.println("try again.....");
                e.printStackTrace();// we will print the red error text
            }

        }
    }
// theis method takes screenschot and stares it in a file
    public static void takeScreenshot(WebDriver driver, String pathToFile){
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

       try {

           FileUtils.copyFile(src, new File(pathToFile));
       }catch (IOException e){
           System.out.println("file to store screenshot in give file ceck the file path");
           System.out.println("the file path provided: " + pathToFile);
       }
    }

//    public static void screenShot(){
//        // go to magento and take a screen shot
//
//        WebDriver driver = Driver.getDriver();
//        driver.get("https://magento.softwaretestingboard.com/");
//        Flow.takeScreenshot(driver,target/test-classes/tests);
    }


