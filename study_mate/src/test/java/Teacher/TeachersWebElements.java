package Teacher;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeachersWebElements {
    public WebDriver driver;

    public TeachersWebElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Add teacher')]")
    public WebElement addTeachButton;

    @FindBy(xpath = "//div[@role='presentation']//button[contains(text(),'Cancel')]")
    public WebElement cancelButton;

    @FindBy(id = "modal")
    public WebElement modal;

    @FindBy(css="label.Mui-error")
    public WebElement inputLabelError;

    @FindBy(css="div.css-1v4ccyo.Mui-error")
    public WebElement inputDivError;

    @FindBy(css="div#modal input[name='name']")
    public WebElement inputFirstName;

    @FindBy(css="div#modal input[name=\"lastName\"]")
    public WebElement inputLastName;
    @FindBy(css="div#modal input[name=\"phoneNumber\"]")
    public WebElement inputPhoneNumber;

    @FindBy(css="div#modal input[name=\"email\"]")
    public WebElement inputEmailAddress;

    @FindBy(css="div#modal input[name=\"specialization\"]")
    public WebElement inputSpecialization;

    @FindBy(xpath = "//form[contains(@class, 'form')]//div[contains(@class, 'MuiFormControl-root')][1]")
    public WebElement inputDivFirstName;

    @FindBy(xpath = "//form[contains(@class, 'form')]//div[contains(@class, 'MuiFormControl-root')][2]")
    public WebElement inputDivLastName;
    @FindBy(xpath = "//form[contains(@class, 'form')]//div[contains(@class, 'MuiFormControl-root')][3]")
    public WebElement inputDivPhoneNumber;

    @FindBy(xpath = "//form[contains(@class, 'form')]//div[contains(@class, 'MuiFormControl-root')][4]")
    public WebElement inputDivEmail;

    @FindBy(xpath = "//form[contains(@class, 'form')]//div[contains(@class, 'MuiFormControl-root')][5]")
    public WebElement inputDivSpecialization;

    @FindBy(xpath = "//form[contains(@class, 'form')]//div[contains(@class, 'MuiFormControl-root')][6]")
    public WebElement inputDivCourses;

    @FindBy(xpath = "//ul[contains(@class, 'MuiList-root')]//li[contains(text(), 'API from Mr Jackson')]")
    public WebElement mrJacksonSelector;

    @FindBy(xpath = "//ul[contains(@class, 'MuiList-root')]")
    public WebElement courseDropUl;

    @FindBy(xpath = "//form[contains(@class, 'form')]//button[@type='submit']")
    public WebElement submitTeacherForm;

    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-successSuccess')]")
    public WebElement successAlert;
    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-errorSuccess')]")
    public WebElement errorAlert;

    @FindBy(xpath = "//div/table/tbody/tr[1]/td[6]/div")
    public WebElement editeButton;

    @FindBy(xpath = "//div[@class='css-1bslj30']//div[@class='css-syc7th']//p[@class='css-7zvtr8'][2]")
    public WebElement numberOfTeachers;

    @FindBy(xpath = "//div[@class='css-1bslj30']//div[@class='css-syc7th']//div[2]//input")
    public WebElement paginationCount;

    @FindBy(xpath = "//table//tbody")
    public WebElement tableBodyTr;

    @FindBy(xpath = "//div[contains(@class, 'MuiPaper-root') and contains(@style, 'opacity: 1')]" +
            "//ul[contains(@class, 'MuiMenu-list')]//li[contains(text(), 'Edit')]")
    public WebElement editTeacherAction;

    @FindBy(xpath = "//div[contains(@class, 'MuiPaper-root') and contains(@style, 'opacity: 1')]" +
            "//ul[contains(@class, 'MuiMenu-list')]//li[contains(text(), 'Delete')]")
    public WebElement deleteTeacherAction;

    // this button is for confirmation on delete teacher record
    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    public WebElement deleteTeacherConfirm;

    @FindBy(xpath = "//button[contains(@aria-label, 'Go to next page')]")
    public WebElement navigateNextButton;

    @FindBy(xpath = "//button[contains(@aria-label, 'Go to previous page')]")
    public WebElement navigatePreviousPage;

    @FindBy(xpath = "//nav[contains(@aria-label, 'pagination')]")
    public WebElement pageNavigationNav;

    @FindBy(xpath = "//button[contains(@aria-current, 'true')]")
    public WebElement activeNavigatePage;
    //nav[contains(@aria-label, 'pagination')]//li[3]//button[contains(@aria-current, 'true')]
}

