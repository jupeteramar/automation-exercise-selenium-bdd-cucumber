Feature: Sign Up Functionality
  As a user
  I want to register an account
  So I can save items in cart and checkout products

  Scenario: Successful account registration
    Given the user is on the sign up page
    When the user enters a valid email and name
    And clicks the sign up button
    Then the user should be directed to the Registration Page
    When the user fills out the registration form with valid credentials and all required fields
    And clicks the submit button
    Then the user will see a successful confirmation for account creation

  Scenario: Failed account registration due to invalid email
    Given the user is on the sign up page
    When the user enters a valid name an invalid email
    And clicks the sign up button
    Then the user should see a message indicating that the email is invalid

  Scenario: Failed account registration due to blank name
    Given the user is on the sign up page
    When the user enters a valid email but blank name
    And clicks the sign up button
    Then the user should see a message indicating that name is required

  Scenario: Failed account registration due to blank email
    Given the user is on the sign up page
    When the user enters a valid name but blank email
    And clicks the sign up button
    Then the user should see a message indicating that email is required

  Scenario: Failed account registration due to incomplete form submission
    Given the user is on the sign up page
    When the user enters a valid email and name
    And clicks the sign up button
    Then the user should be directed to the Registration Page
    When the user leaves a field blank
    And clicks the submit button
    Then the user will see a message asking to fill out the required fields