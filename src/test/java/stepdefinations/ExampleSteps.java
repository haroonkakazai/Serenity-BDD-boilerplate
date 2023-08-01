package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ExampleSteps {

    @Given("user is on login Page")
    public void userIsOnLoginPage(){
        theActorInTheSpotlight().attemptsTo(
                //rest of code
        );

    }
    @When("user enter the credentials username and password")
    public void enterTheCredentials(){
        theActorInTheSpotlight().attemptsTo(

        );
    }
    @Then("user should be logged in as Admin")
    public void verifyTheUserIsLoggedInAsAdmin(){
        theActorInTheSpotlight().attemptsTo(

        );
    }
}
