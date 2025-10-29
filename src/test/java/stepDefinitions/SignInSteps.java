package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.SignInPage;
import utils.ScreenshotUtils;

public class SignInSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private SignInPage pgSignIn = new SignInPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://automationexercise.com/login");
    }

    @When("the user enters a valid email and password")
    public void the_user_enters_a_valid_email_and_password() {
        pgSignIn.typeSignInCredentials("qa@tester.com", "12345");
    }

    @When("the user enters an invalid email or password")
    public void the_user_enters_an_invalid_email_and_password() {
        pgSignIn.typeSignInCredentials("qa@tester.com", "abcde");
    }

    @When("the user enters neither email or password")
    public void the_user_enters_neither_email_or_password() {
        pgSignIn.typeSignInCredentials("", "");
    }

    @When("the user enters the email only")
    public void the_user_enters_the_email_only() {
        pgSignIn.typeEmail("qa@tester.com");
    }

    @When("the user enters the password only")
    public void the_user_enters_the_password_only() {
        pgSignIn.typePassword("12345");
    }

    @And("clicks the login button")
    public void clicks_the_login_button() {
        pgSignIn.clickLogInButton();
    }

    @Then("the user should be directed to the Products page")
    public void the_user_should_be_directed_to_the_Products_page() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/", "Engk!");
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        Assert.assertTrue(pgSignIn.isErrorMessageVisible(), "There is no error");
    }

    @Then("the user is asked to input the email")
    public void the_user_is_asked_to_input_the_email() {
        Assert.assertTrue(pgSignIn.tooltipEmail());
    }

    @Then("the user is asked to input the password")
    public void the_user_is_asked_to_input_the_password() {
        Assert.assertTrue(pgSignIn.tooltipPassword());
    }
}
