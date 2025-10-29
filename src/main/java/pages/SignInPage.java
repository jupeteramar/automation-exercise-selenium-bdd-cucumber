package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ClickUtils;
import utils.WaitUtil;

import java.util.List;

public class SignInPage extends BasePage{

    // Locators
    private final By txtEmail = By.xpath("//div[@class='login-form']//input[@data-qa='login-email']");
    private final By txtPassword = By.xpath("//div[@class='login-form']//input[@data-qa='login-password']");
    private final By btnSubmitSignIn = By.xpath("//div[@class='login-form']//button[@data-qa='login-button']");
    private final By lblError = By.xpath("//div[@class='login-form']//p[@style='color: red;']");

    // Actions
    public void typeSignInCredentials(String email, String password){
        driver.findElement(txtEmail).sendKeys(email);
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void typeEmail(String email){
        driver.findElement(txtEmail).sendKeys(email);
    }

    public void typePassword(String password){
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void clickLogInButton(){
        ClickUtils.safeClick(driver, btnSubmitSignIn, 5);
    }

    public boolean isErrorMessageVisible(){
        WaitUtil.waitForVisibility(driver, lblError, 5);
        List<WebElement> lblErrorMessage = driver.findElements(lblError);
        return lblErrorMessage.getFirst().isDisplayed();
    }

    public boolean tooltipEmail(){
        return driver.findElement(txtEmail).getAttribute("validationMessage").equalsIgnoreCase("Please fill out this field.");
    }

    public boolean tooltipPassword(){
        return driver.findElement(txtPassword).getAttribute("validationMessage").equalsIgnoreCase("Please fill out this field.");
    }
}
