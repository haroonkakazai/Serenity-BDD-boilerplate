package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NavigateToEmumbaHomePage {

    @Given("user is on Emumba home page")
    public void userIsOnHomePage(){
        theActorInTheSpotlight().attemptsTo(

        );

    }
    @When("user clicks on the Contact us button")
    public void userClickOnContactUsButton(){
        theActorInTheSpotlight().attemptsTo(

        );
    }
    @And("user fills the contact us form")
    public void userFillsTheForm(){
        theActorInTheSpotlight().attemptsTo(

        );
    }
    @Then("user should see the success message")
    public void verifyTheSuccessMessage(){
        theActorInTheSpotlight().attemptsTo(

        );
    }
}
