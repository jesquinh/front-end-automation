package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Marca extends GenericPage {

    @FindBy(css = "button.box__btn[action-name='agreeAll']")
    WebElement acceptCookies;

    @FindBy(css = "a.tools-corporative-link[title='Login']")
    WebElement loginButton;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(className = "mdc-button__label")
    WebElement nextButton;

    @FindBy(id = "mat-mdc-error-0")
    private WebElement errorMessage;

    public void acceptCookies() {
        if (isVisible(acceptCookies)) {
            acceptCookies.click();
        }
    }

    public void fillLogin() {
        loginButton.click();
        emailField.sendKeys("Forcing error...");
        nextButton.click();
    }

    public void compareVisibleMessage() {
        compareTexts(errorMessage, "Forcing error");
    }

    public boolean isVisible() {
        return errorMessage.isDisplayed();
    }
}
