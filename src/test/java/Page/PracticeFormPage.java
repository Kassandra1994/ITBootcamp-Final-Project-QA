package Page;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PracticeFormPage extends BaseTest {

    public PracticeFormPage() {

        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "header-wrapper")
    public List<WebElement> getCards;

    @FindBy(className = "router-link")
    public List<WebElement> getSidebarOptions;

    //---------------------------

    // Metoda koja prolazi kroz listu svih kartica
    // i klikće na onu čije ime prosledimo
    public void clickOnCard(String cardName) {
        for (int i = 0; i < getCards.size(); i++) {
            scrollToElement(getCards.get(i));
            if(getCards.get(i).getText().equals(cardName)) {
                getCards.get(i).click();
                break;
            }
        }
    }
    // Metoda koja prolazi kroz bočni meni
    // i klikće na opciju čije ime prosledimo
    public void clickOnSidebarButton(String buttonName) {
        for (int i = 0; i < getSidebarOptions.size(); i++) {
            if (getSidebarOptions.get(i).getText().equals(buttonName)) {
                getSidebarOptions.get(i).click();
                break;
            }
        }
    }


}
