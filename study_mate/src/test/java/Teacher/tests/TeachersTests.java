package Teacher.tests;

import Utils.StudyMateConfig;
import Teacher.TeachersWebElements;
import Utils.Util;
import Utilities.Driver;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.HashMap;


@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class TeachersTests {
    public static WebDriver driver;
    public static StudyMateConfig config;
    public static TeachersWebElements teacherPath;

    @BeforeClass
    public static void initialize() throws InterruptedException {
        driver = Driver.getDriver();
        config = new StudyMateConfig();
        config.setUpConfig();
    }

    @AfterClass
    public static void finishTesting() throws InterruptedException{
        Util.printLog("success", "*** All test cases finished ***");
        Thread.sleep(5000);
//        driver.quit();
    }

    @Before
    public void runBeforeEveryTest() throws InterruptedException {
        Thread.sleep(1000);
        teacherPath = new TeachersWebElements(this.driver);
    }

    @Test
    public void step11_clickAddTeacher() throws InterruptedException {
        Util.printLog("success", "Check if new teacher form is displayed when " +
                "\"Add teacher\" is clicked");

        this.config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        teacherPath.addTeachButton.click();
        boolean teacherFormIsDisplayed = false;
        try{
            teacherFormIsDisplayed = teacherPath.modal.isDisplayed();
            Util.printLog("success", "New teacher form is displayed");
        }catch (Exception e){
            Util.printLog("error", "New teacher form is not displayed");
        }
        Assert.assertTrue("Teacher form is not displayed", teacherFormIsDisplayed);
    }

    // Click on every input teacher field in the form, after that check every input field if div and label contains
    // error css class, that mean user clicked but didn't enter any value
    @Test
    public void step12_checkSkipInput() throws InterruptedException {
        Util.printLog("success", "Check if red indicator css class is applied when " +
                "user click in then out of input without providing any value");
        teacherPath.inputFirstName.click();
        teacherPath.inputLastName.click();
        teacherPath.inputPhoneNumber.click();
        teacherPath.inputEmailAddress.click();
        teacherPath.inputSpecialization.click();
        teacherPath.inputDivCourses.click();

        Thread.sleep(1000);
        // close multiselect dropdown
        Actions action = new Actions(driver);
        action.moveToElement(teacherPath.inputFirstName).click().perform();

        String divErrorClass = "css-1v4ccyo";
        String labelErrorClass = "Mui-error";
        boolean containsDivErrorClass;
        boolean containsLabelErrorClass;

        containsDivErrorClass = Util.checkCssClass(teacherPath.inputDivFirstName.findElement(By.tagName("div")),
                divErrorClass, "first name input");
        containsLabelErrorClass = Util.checkCssClass(teacherPath.inputDivFirstName.findElement(By.tagName("label")),
                labelErrorClass, "first name label");
        Assert.assertTrue("First name div input doesn't contain css error class", containsDivErrorClass);
        Assert.assertTrue("First name label doesn't contain css error class", containsLabelErrorClass);

        containsDivErrorClass = Util.checkCssClass(teacherPath.inputDivLastName.findElement(By.tagName("div")),
                divErrorClass, "last name input");
        containsLabelErrorClass = Util.checkCssClass(teacherPath.inputDivLastName.findElement(By.tagName("label")),
                labelErrorClass, "last name label");
        Assert.assertTrue("Last name div input doesn't contain css error class", containsDivErrorClass);
        Assert.assertTrue("Last name label doesn't contain css error class", containsLabelErrorClass);

        containsDivErrorClass = Util.checkCssClass(teacherPath.inputDivPhoneNumber.findElement(By.tagName("div")),
                divErrorClass, "phone number input");
        containsLabelErrorClass = Util.checkCssClass(teacherPath.inputDivPhoneNumber.findElement(By.tagName("label")),
                labelErrorClass, "phone number label");
        Assert.assertTrue("phone number div input doesn't contain css error class", containsDivErrorClass);
        Assert.assertTrue("phone number label doesn't contain css error class", containsLabelErrorClass);

        containsDivErrorClass = Util.checkCssClass(teacherPath.inputDivEmail.findElement(By.tagName("div")),
                divErrorClass, "email address input");
        containsLabelErrorClass = Util.checkCssClass(teacherPath.inputDivEmail.findElement(By.tagName("label")),
                labelErrorClass, "email address label");
        Assert.assertTrue("email address div input doesn't contain css error class", containsDivErrorClass);
        Assert.assertTrue("email address label doesn't contain css error class", containsLabelErrorClass);

        containsDivErrorClass = Util.checkCssClass(teacherPath.inputDivSpecialization.findElement(By.tagName("div")),
                divErrorClass, "specialization input");
        containsLabelErrorClass = Util.checkCssClass(teacherPath.inputDivSpecialization.findElement(By.tagName("label")),
                labelErrorClass, "specialization label");
        Assert.assertTrue("specialization div input doesn't contain css error class", containsDivErrorClass);
        Assert.assertTrue("specialization label doesn't contain css error class", containsLabelErrorClass);

        // click on cancel form
        Thread.sleep(2000);
        try { Util.cancelButton(teacherPath.cancelButton);
        } catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    @Test
    public void step13_enterCorrectDataInForm() throws InterruptedException {
        Util.printLog("success", "Enter a teacher with correct value in form");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        Util.addNewTeacher(driver, teacherPath, config.teacherToAdd, true);
        boolean teacherAdded = Util.compareAlertMessage(driver,
                "successSuccess", "Instructor successfully saved");
        Thread.sleep(1000);
        Assert.assertTrue("Unable to add new teacher", teacherAdded);
        Thread.sleep(2000);
    }

    @Test
    public void step14_enterCorrectTeacherTwice() throws InterruptedException {
        Util.printLog("warning", "Try to enter same teacher 2 times (should not allow)");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        config.generateNewTeacher(); // generate new teacher
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        // add teacher first time
        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        Thread.sleep(2000);
        // add teacher second time
        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        boolean teacherNotAdded = Util.compareAlertMessage(driver,
                "errorSuccess", "User with the same email already exists");
        Assert.assertTrue("Unable to add new teacher second time test", teacherNotAdded);
        // cancel form
        Thread.sleep(3000);
        try { Util.cancelButton(teacherPath.cancelButton);
        } catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    @Test
    public void step15_cancelAddTeacherTest() throws InterruptedException {
        Util.printLog("success", "Click on cancel teacher form to close the form");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        boolean teacherFormIsDisplayed = true;
        Thread.sleep(1000);
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        // add teacher first time
        Util.addNewTeacher(driver, teacherPath, teacherToAdd, false);
        Thread.sleep(2000);
        try { Util.cancelButton(teacherPath.cancelButton);
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        try{
            teacherFormIsDisplayed = teacherPath.modal.isDisplayed();
            Util.printLog("error", "New teacher form is displayed");
        }catch (Exception e){
            teacherFormIsDisplayed = false;
            Util.printLog("success", "New teacher form is not displayed");
        }
        Assert.assertFalse("Unable to add new teacher second time test", teacherFormIsDisplayed);
    }

    @Test
    public void step16_checkDisableSubmitButtonTest() throws InterruptedException {
        Util.printLog("success", "Test submit teacher form enable/disable before fill up the form" +
                " after fill up the form and clear out one of the fieldsl");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        teacherPath.addTeachButton.click();
        boolean isSubmitEnabled = teacherPath.submitTeacherForm.isEnabled();
        Assert.assertTrue("Submit button is not disabled", !isSubmitEnabled);

        try { Util.cancelButton(teacherPath.cancelButton);
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        // add teacher first time
        Util.addNewTeacher(driver, teacherPath, teacherToAdd, false);
        Thread.sleep(1000);
        isSubmitEnabled = teacherPath.submitTeacherForm.isEnabled();
        Assert.assertTrue("Submit button is not active", isSubmitEnabled);

        teacherPath.inputFirstName.clear();
        isSubmitEnabled = teacherPath.submitTeacherForm.isEnabled();
        Assert.assertTrue("Submit button is not disabled", !isSubmitEnabled);
        Thread.sleep(1000);
        try { Util.cancelButton(teacherPath.cancelButton);
        } catch (InterruptedException e) { throw new RuntimeException(e); }
    }

    @Test
    public void step17_enterLargeTeacherInput() throws InterruptedException {
        Util.printLog("warning", "Test large teacher input field for first name, last name phone number" +
                " email address and specialization");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        teacherToAdd.put("email", Util.getRandomString(230) + ".urmat@gmail.com");
        teacherToAdd.put("fName", Util.getRandomString(240));
        teacherToAdd.put("lName", Util.getRandomString(240));
        teacherToAdd.put("phone", Util.generateRandomPhone(240));
        teacherToAdd.put("specialization", Util.getRandomString(240));
        teacherToAdd.put("studyFormat", "Marketing;TESTING TAB");

        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        boolean teacherAdded = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully saved");
        Assert.assertTrue("Teacher with bad long input data was entered with success", !teacherAdded);
    }

    @Test
    public void step18_enterEmptyAndNegativePhoneTeacher() throws InterruptedException {
        Util.printLog("warning", "Input empty first name, last name, specialization and" +
                " negative number on phone number field");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        teacherToAdd.put("email", Util.getRandomString(10) + ".urmat@gmail.com");
        teacherToAdd.put("fName", "       ");
        teacherToAdd.put("lName", "       ");
        teacherToAdd.put("phone", "-2135874125");
        teacherToAdd.put("specialization", "       ");
        teacherToAdd.put("studyFormat", "TESTING TAB");

        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        boolean teacherAdded = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully saved");
        Assert.assertTrue("Teacher with empty space on \"First name\", \"Last name\", \"Specialization\" " +
                " and negative \"Phone number\" was entered with success", !teacherAdded);
    }

    @Test
    public void step19_enterNumberAndNegativePhoneTeacher() throws InterruptedException {
        Util.printLog("warning", "Try number and special characters for first name, last name and " +
                "specialization");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        teacherToAdd.put("email", Util.getRandomString(10) + ".urmat@gmail.com");
        teacherToAdd.put("fName", Util.generateRandomPhone(10) + "%%%");
        teacherToAdd.put("lName", Util.generateRandomPhone(10) + "$#@@*&");
        teacherToAdd.put("phone", "-2135874125");
        teacherToAdd.put("specialization", Util.generateRandomPhone(3) + "#$@()");
        teacherToAdd.put("studyFormat", "TESTING TAB");

        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        boolean teacherAdded = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully saved");
        Assert.assertTrue("Teacher with special characters and numbers entered in \"First name\", " +
                "\"Last name\" and \"Specialization\" with success", !teacherAdded);
    }

    @Test
    public void step20_enterShortTeacherValues() throws InterruptedException {
        Util.printLog("warning", "Try to enter only a single character for first name, last name, " +
                "specialization and short email address");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        String shortEmail = Util.getRandomString(1) + "@" +
                Util.getRandomString(1) + "." +
                Util.getRandomString(1);
        teacherToAdd.put("email", shortEmail);
        teacherToAdd.put("fName", Util.getRandomString(1));
        teacherToAdd.put("lName", Util.getRandomString(1));
        teacherToAdd.put("phone", "-1");
        teacherToAdd.put("specialization", Util.getRandomString(1));
        teacherToAdd.put("studyFormat", "TESTING TAB");

        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        boolean teacherAdded = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully saved");
        Assert.assertTrue("Teacher with short values entered on \"First name\", " +
                "\"Last name\", \"Phone number\" and \"Specialization\" with success", !teacherAdded);
    }

    @Test
    public void step21_editTeacher() throws InterruptedException {
        Util.printLog("success", "Edit last teacher in the table");
        String totalTeachers = Util.getNumberOfTeachers(teacherPath);
        teacherPath.paginationCount.clear();
        teacherPath.paginationCount.sendKeys(totalTeachers);
        teacherPath.paginationCount.sendKeys(Keys.RETURN);
        teacherPath.tableBodyTr.findElement(
                By.xpath("//tr[" + Integer.parseInt(totalTeachers) + "]//td[6]//button")).click();
        teacherPath.editTeacherAction.click();
        teacherPath.inputFirstName.clear();
        teacherPath.inputFirstName.sendKeys(Util.getRandomString(5));
        teacherPath.submitTeacherForm.click();
        Thread.sleep(1000);
        boolean teacherEdited = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully updated");
        Assert.assertTrue("Teacher was unable to edit \"First name\"", teacherEdited);
    }

    @Test
    public void step22_deleteTeacher() throws InterruptedException{
        Util.printLog("success", "Create a teacher then find it as last teacher in the table and delete it");
        // first create teacher
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        config.generateNewTeacher(); // generate new teacher
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        // add teacher first time
        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        Thread.sleep(2000);

        // now delete created teacher
        String totalTeachers = Util.getNumberOfTeachers(teacherPath);
        teacherPath.paginationCount.clear();
        teacherPath.paginationCount.sendKeys(totalTeachers);
        teacherPath.paginationCount.sendKeys(Keys.RETURN);
        teacherPath.tableBodyTr.findElement(
                By.xpath("//tr[" + Integer.parseInt(totalTeachers) + "]//td[6]//button")).click();
        teacherPath.deleteTeacherAction.click();
        Thread.sleep(1000);
        teacherPath.deleteTeacherConfirm.click();
        Thread.sleep(1000);
        // find success delete message and assert it
        boolean teacherEdited = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully deleted");
        Assert.assertTrue("Teacher was unable to delete", teacherEdited);
    }

    @Test
    public void step23_navigatePagination() throws InterruptedException{
        Util.printLog("success", "click on arrow to go to next set of teachers");
        config.generateNewTeacher(); // generate new teacher
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        // add teacher first time
        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        Thread.sleep(2000);

        teacherPath.paginationCount.clear();
        teacherPath.paginationCount.sendKeys("2");
        teacherPath.paginationCount.sendKeys(Keys.RETURN);

        // navigate to next page
        teacherPath.navigateNextButton.click();
        Thread.sleep(1000);
        String activeBtn = "//li[*]//button[contains(@aria-current, 'true')]";
        boolean activeBtnsEmpty;
        activeBtnsEmpty = teacherPath.pageNavigationNav.findElements(
                By.xpath(activeBtn.replace('*', '3'))).isEmpty();
        boolean nextPageActive = !activeBtnsEmpty;
        if(nextPageActive){
            Util.printLog("success", "Second page is active");
        }else{
            Util.printLog("error", "Second page is not active");
        }
        Assert.assertTrue("Second page is not active", nextPageActive);

        // navigate previous page
        teacherPath.navigatePreviousPage.click();
        activeBtnsEmpty = teacherPath.pageNavigationNav.findElements(
                By.xpath(activeBtn.replace('*', '2'))).isEmpty();
        boolean firstPageActive = !activeBtnsEmpty;
        if(firstPageActive){
            Util.printLog("success", "First page is active");
        }else{
            Util.printLog("error", "First page is not active");
        }
        Assert.assertTrue("First page is not active", firstPageActive);
    }

    @Test
    public void step24_removeTeacherWithLargeContent() throws InterruptedException{
        Util.printLog("warning", "Test delete teacher with large first name, last name phone number" +
                " email address and specialization");
        config.wait.until(d -> teacherPath.addTeachButton.isDisplayed());
        Thread.sleep(1000);
        // add teacher first
        HashMap<String, String> teacherToAdd = config.teacherToAdd;
        teacherToAdd.put("email", Util.getRandomString(230) + ".urmat@gmail.com");
        teacherToAdd.put("fName", Util.getRandomString(240));
        teacherToAdd.put("lName", Util.getRandomString(240));
        teacherToAdd.put("phone", Util.generateRandomPhone(240));
        teacherToAdd.put("specialization", Util.getRandomString(240));
        teacherToAdd.put("studyFormat", "Marketing;TESTING TAB");

        Util.addNewTeacher(driver, teacherPath, teacherToAdd, true);
        // try to remove the teacher
        String totalTeachers = Util.getNumberOfTeachers(teacherPath);
        teacherPath.paginationCount.clear();
        teacherPath.paginationCount.sendKeys(totalTeachers);
        teacherPath.paginationCount.sendKeys(Keys.RETURN);
        teacherPath.tableBodyTr.findElement(
                By.xpath("//tr[" + Integer.parseInt(totalTeachers) + "]//td[6]//button")).click();
        teacherPath.deleteTeacherAction.click();
        Thread.sleep(1000);
        teacherPath.deleteTeacherConfirm.click();
        Thread.sleep(1000);
        boolean teacherRemoved = Util.compareAlertMessage(driver,
                "successSuccess",
                "Instructor successfully deleted");
        if(!teacherRemoved){
            teacherPath.cancelButton.click();
        }
        Assert.assertTrue("Unable to remove teacher with large content", teacherRemoved);
    }


}
