Feature: Sign In Functionality
  As a user
  I want to log in with valid credentials
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters a valid email and password
    And clicks the login button
    Then the user should be directed to the Products page

  Scenario: Failed login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid email or password
    And clicks the login button
    Then the user should see an error message

  Scenario: Failed login with no credentials input
    Given the user is on the login page
    When the user enters neither email or password
    And clicks the login button
    Then the user is asked to input the email

  Scenario: Failed login with no password input
    Given the user is on the login page
    When the user enters the email only
    And clicks the login button
    Then the user is asked to input the password

  Scenario: Failed login with no email input
    Given the user is on the login page
    When the user enters the password only
    And clicks the login button
    Then the user is asked to input the email