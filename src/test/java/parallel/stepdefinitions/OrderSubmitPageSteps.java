package parallel.stepdefinitions;

import com.pages.OrderSubmitPage;
import com.qa.factory.DriverFactory;
import com.qa.util.LoggerHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static com.qa.util.ElementActions.waitForSecs;

public class OrderSubmitPageSteps {

    private static final Logger Log = LoggerHelper.getLogger();
    private OrderSubmitPage orderConfirmPage =new OrderSubmitPage(DriverFactory.getDriver());

    @Then("Verify order confirmation message is displayed")
    public void verify_order_confirmation_message_is_displayed() {
        Assert.assertTrue(orderConfirmPage.isOrderConfirmMessageIsDisplayed(),
                "Order confirm message is NOT displayed");
        Log.info("Order confirm message is displayed");
    }

    @Then("Verify order reservation number is displayed")
    public void verify_order_reservation_number_is_displayed() {
        Assert.assertTrue(orderConfirmPage.isOrderReserveNumberIsDisplayed(),
                "Order reservation number is NOT displayed");
        Log.info("Order reservation number is displayed");
    }

    @Then("Verify denied message is displayed")
    public void verify_denied_message_is_displayed() {
        Assert.assertTrue(orderConfirmPage.isDeniedMessageIsDisplayed(),
                "Denied message is NOT displayed");
        Log.info("Denied message is displayed");
    }

    @Then("Verify order confirm message {string} is displayed")
    public void is_order_confirm_message_is_displayed(String orderConfirmMessage) {
        waitForSecs(5);
        Assert.assertTrue(orderConfirmPage.isOrderConfirmMessageTextDisplayed(orderConfirmMessage),"Order confirm message text NOT displayed");
        Log.info("Order confirm message text is displayed");
    }

    @Then("Verify cart remaining message is displayed")
    public void verifyCartRemainingMessage() {
        Assert.assertEquals(orderConfirmPage.verifyRemainingCartMessage(), "You still have items in your cart! Checkout now or save it for later.","Cart remaining message is NOT displayed");
        Log.info("Cart remaining message is NOT displayed");
    }

    @When("the user click on cart view button")
    public void the_user_click_on_cart_view_button() {
        orderConfirmPage.clickOnCartViewButton();
        Log.info("Clicked on cart view button");
    }

}
