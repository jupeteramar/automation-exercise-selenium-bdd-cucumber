package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.*;
import io.cucumber.messages.types.Product;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductViewPage;
import pages.ProductsPage;
import pages.SignInPage;


public class ProductSteps {

    private WebDriver driver = DriverFactory.getDriver();

    private SignInPage pgSignIn = new SignInPage();
    private ProductsPage pgProducts = new ProductsPage();
    private ProductViewPage pgProductView = new ProductViewPage();
    private CartPage pgCart = new CartPage();

    private String productID = "1";
    private String productCardName = "";
    private String productCardPrice = "";

    @Given ("the user directed to the products page")
    public void the_user_is_on_the_products_page(){
        pgProducts.goToProducts();
        productCardName = pgProducts.getProductName(productID);
        productCardPrice = pgProducts.getProductPrice(productID);
    }

    @When("the user clicks the view product button of a product")
    public void the_user_clicks_the_view_product_button_of_a_product(){
        pgProducts.clickViewProductButton(productID);
    }

    @Then("the user can see the details of the product")
    public void the_user_can_see_the_details_of_the_product(){
        String productViewName = pgProductView.getProductName();
        String productViewPrice = pgProductView.getProductPrice();

        Assert.assertEquals(productCardName, productViewName, "Product Name from Card does not match with View: Card (" + productCardName + ") View(" + productViewName + ")");
        Assert.assertEquals(productCardPrice, productViewPrice, "Product Price from Card does not match with View: Card (" + productCardPrice + ") View(" + productViewPrice + ")");
    }

    @When("the user adds a product to cart")
    public void the_user_adds_a_product_to_cart() {
        pgProducts.clickAddProductToCart(productID);
    }

    @Then("a modal pops up confirming adding product to cart")
    public void a_modal_pops_up_confirming_adding_product_to_cart(){
        Assert.assertTrue(pgProducts.isCartModalDisplayed(), "Cart modal should be displayed.");
    }



}
