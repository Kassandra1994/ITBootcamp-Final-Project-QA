package Page;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BaseTest {
    // KONSTRUKTOR
    // Inicijalizuje elemente na stranici (Page Factory) kada se kreira objekat klase

    public RegistrationPage(){
        PageFactory.initElements(driver, this);
    }

    // LOKATORI / WEB ELEMENTI (@FindBy)

    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    @FindBy(id = "userEmail")
    public WebElement userEmailField;

    @FindBy(id = "gender-radio-2")
    public WebElement genderFemaleField;

    @FindBy(id = "userNumber")
    public WebElement mobileField;

    @FindBy(id = "dateOfBirthInput")
    public WebElement dateOfBirthField;


    @FindBy(className = "react-datepicker__month-select" )
    public WebElement monthField;

    @FindBy(className = "react-datepicker__year-select" )
    public WebElement yearField;

    @FindBy(id = "subjectsInput")
    public WebElement subjectsField;

    @FindBy(id = "hobbies-checkbox-1")
    public WebElement hobbiesField;

    @FindBy(id = "uploadPicture")
    public WebElement uploadPictureField;

    @FindBy(id = "currentAddress")
    public WebElement addressField;

    @FindBy(xpath = "//div[@id='state']//input")
    public WebElement stateField;

    @FindBy(xpath = "//div[@id='city']//input")
    public WebElement cityField;

    @FindBy(id = "submit")
    public WebElement submitButton;

    // Lokator za Close dugme na dnu uspešnog pop-up prozora
    @FindBy(id = "closeLargeModal")
    public WebElement closeButton;

    // Lokator za naslov u pop-up prozoru nakon uspešnog slanja forme
    @FindBy(id = "example-modal-sizes-title-lg")
    public WebElement successfulSubmissionMessage;


    // METODE / FUNKCIONALNOSTI STRANICE

    // Metoda za unos imena
    public void inputFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    // Metoda za unos prezimena
    public void inputLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    // Metoda za unos email adrese
    public void inputEmail(String email) {
        userEmailField.clear();
        userEmailField.sendKeys(email);
    }
    // Metoda za odabir ženskog pola
    public void clickFemaleGender() {
        genderFemaleField.click();
    }

    // Metoda za unos broja mobilnog telefona
    public void inputMobileNumber(String mobileNumber) {
        mobileField.clear();
        mobileField.sendKeys(mobileNumber);
    }
    // Metoda za unos datuma rođenja kroz kalendar (Mesec, Godina, Dan)
    public void inputDateOfBirth(String month, String year, String day) {
        dateOfBirthField.clear();
        dateOfBirthField.click();
        monthField.click();
        monthField.sendKeys(month);
        monthField.sendKeys(Keys.ENTER);
        yearField.click();
        yearField.sendKeys(year);
        yearField.sendKeys(Keys.ENTER);
        //Dinamički lokator za pronalaženje tačnog dana
        // u kalendaru na osnovu prosleđenog teksta
        WebElement selectedDay = driver.findElement(
                By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")
        );
        selectedDay.click();
    }
    // Metoda za unos predmeta
    public void inputSubject(String subject) {
        subjectsField.clear();
        subjectsField.sendKeys(subject);
        subjectsField.sendKeys(Keys.ENTER);
    }
    // Metoda za odabir hobija
    public void clickHobby() {
        hobbiesField.click();
    }

    // Metoda za slanje (upload)
    // slike sa računara prosleđivanjem putanje
    public void uploadPicture(String imagePath) {
        uploadPictureField.sendKeys(imagePath);
    }
    //Metoda za unos trenutne adrese
    public void inputAddress(String address) {
        addressField.clear();
        addressField.sendKeys(address);
    }
    // Metoda za odabir države iz padajućeg menija
    public void inputState(String state) {
        scrollToElement(stateField);
        stateField.sendKeys(Keys.DOWN);
        stateField.click();
        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);
    }
    // Metoda za odabir grada iz padajućeg menija
    // na osnovu izabrane države
    public void inputCity(String city) {
        scrollToElement(cityField);
        cityField.sendKeys(Keys.DOWN);
        cityField.click();
        cityField.sendKeys(city);
        cityField.sendKeys(Keys.ENTER);
    }
    // Metoda za skrolovanje do dugmeta
    // i kliktanje preko JavaScript-a
    public void clickSubmit(){
        scrollToElement(submitButton);
        jsClick(submitButton);
    }

    // Metoda koja pokušava da zatvori pop-up na regularan način
    public void clickCloseButton() {
        scrollToElement(closeButton);
        closeButton.click();
    }

    public void closePlaceholderWithEscape() {
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
    }

    // Metoda koja vraća tekst poruke
    // o uspešnoj registraciji za potrebe Assert-a
    public String getSuccessMessage() {
        return successfulSubmissionMessage.getText();
    }






}
