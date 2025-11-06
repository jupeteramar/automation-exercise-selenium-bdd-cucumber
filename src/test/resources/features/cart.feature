Feature: Cart Functionality
  As a user
  I want to view the products
  So that I can add products to cart

  Scenario: Guest user can view the Cart Page by pressing the button from the modal
    Given the user is on the products page
    When the user clicks the add to cart button of a product
    Then a modal pops up for successful confirmation
    And the user clicks the view cart button
    Then the user is directed to the Cart Page


  Scenario: Guest user can add a product then view the accurate total and computations
    Given the user is on the products page
    When the user clicks the add to cart button of a product
    Then a modal pops up for successful confirmation
    And the user clicks the view cart button
    Then the user is directed to the Cart Page
    And the total price is displayed with an accurate computation

  Scenario: Guest user can add multiple product then view the accurate total and computations
    Given the user is on the products page
    When the user adds multiple product to cart
    Then the user is directed to the Cart Page
    And the total price is displayed with an accurate computation for multiple products

  Scenario: Guest user cannot checkout the cart if it is empty
    Given the user is on the products page
    When the user clicks the cart navigation
    Then the user is should not see the checkout button

  Scenario: Registered user cannot checkout the cart if it is empty
    Given the user is logged in and on the products page
    When the user clicks the cart navigation
    Then the user is should not see the checkout button

  Scenario: Registered user can delete products to cart
    Given the user is logged in and on the products page
    When the user clicks the add to cart button of a product
    Then a modal pops up for successful confirmation
    And the user clicks the view cart button
    When the user clicks the delete button of a product
    Then the product will be removed from the cart


  Scenario: Guest user can delete products to cart
    Given the user is on the products page
    When the user clicks the add to cart button of a product
    Then a modal pops up for successful confirmation
    And the user clicks the view cart button
    When the user clicks the delete button of a product
    Then the product will be removed from the cart

  Scenario: Registered user see the added product in the cart even after logging out
    Given the user is logged in and on the products page
    When the user clicks the add to cart button of a product
    Then a modal pops up for successful confirmation
    And the user clicks the view cart button
    When the user logs out their account
    And the user is logged in and on the products page
    When the user clicks the view cart button
    Then the user must see the added product


