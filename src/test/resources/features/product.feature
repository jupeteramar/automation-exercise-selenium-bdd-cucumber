Feature: Product View Functionality
  As a user
  I want to view the products
  So that I can add products to cart


  Scenario: Guest user can view the information about the product
    Given the user directed to the products page
    When the user clicks the view product button of a product
    Then the user can see the details of the product

  @Cart
  Scenario: Guest user can add a product to cart from products page successfully
    Given the user directed to the products page
    When the user adds a product to cart
    Then a modal pops up confirming adding product to cart
