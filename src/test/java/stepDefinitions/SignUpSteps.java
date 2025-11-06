package stepDefinitions;

import data.User;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.SignUpPage;

public class SignUpSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private SignUpPage pgSignUp = new SignUpPage();

    @Given("the user is on the sign up page")
    public void the_user_is_on_the_sign_up_page() {
        driver.get("https://automationexercise.com/login");
    }

    @When("the user enters a valid email and name")
    public void the_user_enters_a_valid_email_and_name() {
        // pre-Registration
        pgSignUp.typeName(User.NAME);
        pgSignUp.typeEmail(User.EMAIL);
    }

    @When("the user enters a valid email but blank name")
    public void the_user_enters_a_valid_email_but_blank_name() {
        // pre-Registration
        // pgSignUp.typeName(User.NAME);
        pgSignUp.typeEmail(User.EMAIL);
    }

    @When("the user enters a valid name but blank email")
    public void the_user_enters_a_valid_name_but_blank_email() {
        // pre-Registration
        pgSignUp.typeName(User.NAME);
        //pgSignUp.typeEmail(User.EMAIL);
    }

    @When("the user enters a valid name an invalid email")
    public void the_user_enters_a_valid_name_an_invalid_email() {
        // pre-Registration
        pgSignUp.typeName(User.NAME);
        pgSignUp.typeEmail("InvalidEmail");
    }

    @And("clicks the sign up button")
    public void clicks_the_sign_up_button() {
        pgSignUp.clickSignUpButton();
    }

    @Then("the user should be directed to the Registration Page")
    public void the_user_should_be_directed_to_the_Registration_Page() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/signup", "Invalid email or already exists");
    }

    @Then("the user should see a message indicating that the email is invalid")
    public void the_user_should_see_a_message_indicating_that_the_email_is_invalid() {
        Assert.assertTrue(pgSignUp.tooltipEmail());
    }

    @Then("the user should see a message indicating that email is required")
    public void the_user_should_see_a_message_indicating_that_email_is_required() {
        Assert.assertTrue(pgSignUp.tooltipEmailRequired());
    }

    @Then("the user should see a message indicating that name is required")
    public void the_user_should_see_a_message_indicating_that_name_is_required() {
        Assert.assertTrue(pgSignUp.tooltipNameRequired());
    }

    @When("the user fills out the registration form with valid credentials and all required fields")
    public void the_user_fills_out_the_registration_form_with_valid_credentials_and_all_required_fields() {
        pgSignUp.selectTitle(User.TITLE);
        pgSignUp.typePassword(User.PASSWORD);
        pgSignUp.selectDay(User.DAY);
        pgSignUp.selectMonth(User.MONTH);
        pgSignUp.selectYear(User.YEAR);
        pgSignUp.checkPromotions(User.NEWSLETTER, User.OFFERS);
        pgSignUp.typeFirstName(User.FIRST_NAME);
        pgSignUp.typeLastName(User.LAST_NAME);
        pgSignUp.typeCompany(User.COMPANY);
        pgSignUp.typeAddress(User.ADDRESS);
        pgSignUp.typeAddress2(User.ADDRESS2);
        pgSignUp.selectCountry(User.COUNTRY);
        pgSignUp.typeState(User.STATE);
        pgSignUp.typeCity(User.CITY);
        pgSignUp.typeZip(User.ZIP);
        pgSignUp.typeMobileNumber(User.MOBILE_NUMBER);
    }

    @When("the user leaves a field blank")
    public void the_user_leaves_a_field_blank() {
        pgSignUp.selectTitle(User.TITLE);
        //pgSignUp.typePassword(User.PASSWORD);
        pgSignUp.selectDay(User.DAY);
        pgSignUp.selectMonth(User.MONTH);
        pgSignUp.selectYear(User.YEAR);
        pgSignUp.checkPromotions(User.NEWSLETTER, User.OFFERS);
        pgSignUp.typeFirstName(User.FIRST_NAME);
        pgSignUp.typeLastName(User.LAST_NAME);
        pgSignUp.typeCompany(User.COMPANY);
        pgSignUp.typeAddress(User.ADDRESS);
        pgSignUp.typeAddress2(User.ADDRESS2);
        pgSignUp.selectCountry(User.COUNTRY);
        pgSignUp.typeState(User.STATE);
        pgSignUp.typeCity(User.CITY);
        pgSignUp.typeZip(User.ZIP);
        pgSignUp.typeMobileNumber(User.MOBILE_NUMBER);
    }

    @And("clicks the submit button")
    public void clicks_the_submit_button() {
        pgSignUp.clickSubmit();
    }

    @Then("the user will see a successful confirmation for account creation")
    public void the_user_will_be_see_a_successful_confirmation_for_account_creation() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/account_created", "Form not submitted");
        pgSignUp.clickContinueButton();
        pgSignUp.deleteAccount();
    }

    @Then("the user will see a message asking to fill out the required fields")
    public void the_user_will_see_a_message_asking_to_fill_out_the_required_fields(){
        Assert.assertTrue(pgSignUp.tooltipPassword(), "Engk!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/signup", "Form submitted");
    }
}
