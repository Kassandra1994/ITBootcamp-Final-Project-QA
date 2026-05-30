package Test;

import Base.BaseTest;
import Page.HomePage;
import Page.PracticeFormPage;
import Page.RegistrationPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestRegistration extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/");

        homePage = new HomePage();
        practiceFormPage = new PracticeFormPage();
        registrationPage = new RegistrationPage();


        homePage.clickOnCard("Forms");
        practiceFormPage.clickOnSidebarButton("Practice Form");


    }
//Uspešna registracija sa svim popunjenim poljima
    @Test(priority = 1)
    public void verifySuccessfulFormSubmission(){
        registrationPage.inputFirstName("Kasandra");
        registrationPage.inputLastName("Galic");
        registrationPage.inputEmail("kasandra@gmail.com");
        registrationPage.clickFemaleGender();
        registrationPage.inputMobileNumber("0645454544");
        registrationPage.inputDateOfBirth("May", "1989", "26");
        registrationPage.inputSubject("Math");
        registrationPage.uploadPicture(
                "D:\\ITBootCamp\\Zavrsni projekat\\slika.png"
        );
        registrationPage.clickHobby();
        registrationPage.inputAddress("Jugoviceva 25");
        registrationPage.inputState("NCR");
        registrationPage.inputCity("Delhi");
        registrationPage.clickSubmit();

        Assert.assertEquals(
                registrationPage.getSuccessMessage(),
                "Thanks for submitting the form"
        );
    }

    //Uspešna registracija samo sa obaveznim poljima (Ime, Prezime, Pol, Telefon, Datum rođenja)
    @Test(priority = 2)
    public void verifySuccessfulSubmissionWithOnlyMandatoryFields() {

        registrationPage.inputFirstName("Kasandra");
        registrationPage.inputLastName("Galic");
        registrationPage.clickFemaleGender();
        registrationPage.inputMobileNumber("0645454544");
        registrationPage.inputDateOfBirth("May", "1989", "26");

        registrationPage.clickSubmit();

        Assert.assertEquals(
                registrationPage.getSuccessMessage(),
                "Thanks for submitting the form"
        );
    }

    //Validacija: Slanje prazne forme ne sme biti uspešno
    @Test(priority = 3)
    public void verifyFormCannotBeSubmittedWithEmptyRequiredFields() {

        registrationPage.clickSubmit();

        Assert.assertFalse(
                registrationPage.isDisplayed(
                        registrationPage.successfulSubmissionMessage
                )
        );
    }
//Validacija: Nevalidan format broja telefona (manje od 10 cifara)
    @Test(priority = 4)
    public void verifyFormCannotBeSubmittedWithInvalidPhoneNumber() {

        registrationPage.inputFirstName("Kasandra");
        registrationPage.inputLastName("Galic");
        registrationPage.clickFemaleGender();
        registrationPage.inputMobileNumber("123");
        registrationPage.clickSubmit();

        Assert.assertFalse(
                registrationPage.isDisplayed(
                        registrationPage.successfulSubmissionMessage
                )
        );
    }

//Validacija: Nevalidan format email adrese
    @Test(priority = 5)
    public void verifyFormCannotBeSubmittedWithInvalidEmail() {

        registrationPage.inputFirstName("Kasandra");
        registrationPage.inputLastName("Galic");
        registrationPage.clickFemaleGender();
        registrationPage.inputMobileNumber("0645454544");

        registrationPage.inputEmail("kasandra.com");

        registrationPage.clickSubmit();

        Assert.assertFalse(
                registrationPage.isDisplayed(
                        registrationPage.successfulSubmissionMessage
                )
        );
    }

    // Validacija (Bag): Forma ne bi smela da prihvati datum rođenja u budućnosti
    @Test(priority = 6)
    public void verifyFormCannotBeSubmittedWithFutureDateOfBirth() {
        registrationPage.inputFirstName("Kasandra");
        registrationPage.inputLastName("Galic");
        registrationPage.clickFemaleGender();
        registrationPage.inputMobileNumber("0645454544");

        // Biramo godinu u budućnosti (npr. 2030)
        registrationPage.inputDateOfBirth("May", "2030", "26");
        registrationPage.clickSubmit();

        Assert.assertFalse(
                registrationPage.isDisplayed(
                        registrationPage.successfulSubmissionMessage
                ),
                "BAG: Forma je uspešno poslata sa datumom rođenja u budućnosti!"
        );
    }

    // Validacija (Bag): Dokazujemo da klik na Close dugme NE ZATVARA pop-up prozor
    //(prozor ostaje vidljiv)
    @Test(priority = 7)
    public void verifyCloseButtonDoesNotCloseTheSuccessModalDueToBug() {
        registrationPage.inputFirstName("Kasandra");
        registrationPage.inputLastName("Galic");
        registrationPage.clickFemaleGender();
        registrationPage.inputMobileNumber("0645454544");
        registrationPage.inputDateOfBirth("May", "1989", "26");
        registrationPage.clickSubmit();

        // Pokušavamo da kliknemo na Close dugme
        registrationPage.clickCloseButton();

        // Umesto dugmeta koje ne radi, šaljemo ESC taster
        //registrationPage.closePlaceholderWithEscape();

        // Proveravamo da li je pop-up i dalje prisutan
        // (očekujemo TRUE jer je dugme zabagovano)
        Assert.assertTrue(
                registrationPage.isDisplayed(
                        registrationPage.successfulSubmissionMessage
                ),
                "BAG: Pop-up je nestao, što znači da je bag popravljen!"
        );
    }
}
