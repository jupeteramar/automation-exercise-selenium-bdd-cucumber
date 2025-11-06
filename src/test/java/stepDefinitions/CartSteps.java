package stepDefinitions;

import data.User;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import io.cucumber.messages.types.Product;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductsPage;
import pages.SignInPage;

public class CartSteps {
    private WebDriver driver = DriverFactory.getDriver();

    private SignInPage pgSignIn = new SignInPage();
    private ProductsPage pgProducts = new ProductsPage();
    private CartPage pgCart = new CartPage();


    String product1 = "24";

    String[] products = {
            "8",
            "24",
            "21"
    };

    @Given("the user is on the products page")
    public void the_user_is_on_the_products_page() {
        driver.get("https://automationexercise.com/products");
    }

    @Given("the user is logged in and on the products page")
    public void the_user_is_logged_in_and_on_the_products_page() throws InterruptedException {
        driver.get("https://automationexercise.com/login");
        signInUser();
    }

    @When("the user clicks the add to cart button of a product")
    public void the_user_clicks_the_add_to_cart_button_of_a_product() {
        pgProducts.clickAddProductToCart(product1);
    }

    @When("the user clicks the cart navigation")
    public void the_user_clicks_the_cart_navigation() {
        pgProducts.goToCart();
    }

    @Then("a modal pops up for successful confirmation")
    public void a_modal_pops_up_for_successful_confirmation() {
        Assert.assertTrue(pgProducts.isCartModalDisplayed());
    }

    @Then("the user is should not see the checkout button")
    public void the_user_is_should_not_see_the_checkout_button() {
        Assert.assertTrue(pgCart.isCheckOutNotDisplayed());
    }

    @And("the user clicks the view cart button")
    public void the_user_clicks_the_view_cart_button() {
        pgProducts.clickViewCartFromModal();
    }

    @Then("the user is directed to the Cart Page")
    public void the_user_is_directed_to_the_Cart_Page() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
    }

    @Then("the product will be removed from the cart")
    public void the_product_will_be_removed_from_the_cart() {
        Assert.assertTrue(pgCart.isProductInCart(product1), "Item is still in cart.");
    }

    @Then("the user must see the added product")
    public void the_user_must_see_the_added_product() {
        Assert.assertTrue(pgCart.isProductInCart(product1), "Item is still in cart.");
    }

    @And("the total price is displayed with an accurate computation")
    public void the_total_price_is_displayed_with_an_accurate_computation() {
        int price = pgCart.getBasePriceOfProductInCart(product1);
        int quantity = pgCart.getQuantityOfProductInCart(product1);
        int computedTotalPrice = price * quantity;

        Assert.assertEquals(computedTotalPrice, pgCart.getTotalPriceOfProductInCart(product1), "Engk");
    }

    @And("the user logs back in")
    public void the_user_logs_back_in() throws InterruptedException {
        pgSignIn.goToSignupLogin();
        signInUser();
    }

    @And("the total price is displayed with an accurate computation for multiple products")
    public void the_total_price_is_displayed_with_an_accurate_computation_for_multiple_products() {
        int totalComputed = 0;
        int totalDisplayed = 0;

        String[] prods = products;

        for (String productId : prods) {
            int price = pgCart.getBasePriceOfProductInCart(productId);
            int quantity = pgCart.getQuantityOfProductInCart(productId);
            int expected = price * quantity;

            int actual = pgCart.getTotalPriceOfProductInCart(productId);

            Assert.assertEquals(actual, expected,
                    String.format("❌ Mismatch for %s | Expected: %d | Actual: %d", productId, expected, actual));

            totalComputed += expected;
            totalDisplayed += actual;
        }

        Assert.assertEquals(totalDisplayed, totalComputed, "❌ Cart total mismatch!");
    }

    @When("the user adds multiple product to cart")
    public void the_user_adds_multiple_product_to_cart() {
        String[] productIds = new String[products.length];

        for (int i = 0; i < products.length; i++) {
            //productIds[i] = pgProducts.getProductIdByName(products[i]);

            pgProducts.clickAddProductToCart(products[i]);
            pgProducts.closeAddedToCartSuccessModal();
        }

        pgProducts.goToCart();
    }

    @When("the user clicks the delete button of a product")
    public void the_user_clicks_the_delete_button_of_a_product() {
        pgCart.deleteProductFromCart(product1);
    }

    @When("the user logs out their account")
    public void the_user_logs_out_their_account() {
        pgCart.logOut();
    }

    public void signInUser() throws InterruptedException {
        pgSignIn.typeSignInCredentials("qa@tester.com", "12345");
        pgSignIn.clickLogInButton();
        Thread.sleep(200);
    }
}
