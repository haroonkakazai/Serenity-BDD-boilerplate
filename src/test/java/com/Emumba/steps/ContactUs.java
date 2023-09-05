package com.Emumba.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.com.Emumba.pages.home.modals.ContactUSForm;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static ui.com.Emumba.pages.home.HomeUI.ALWAYS_TRUE;

public class ContactUs {


    @Given("user is on Emumba home page")
    public void userIsOnHomePage(){
        theActorInTheSpotlight().attemptsTo(
                Open.url("https://www.emumba.com/")
        );

    }
    @When("user clicks on the Contact us button")
    public void userClickOnContactUsButton(){
        theActorInTheSpotlight().attemptsTo(
                ContactUSForm.open()
        );
    }
    @And("user fills the contact us form")
    public void userFillsTheForm(){
        theActorInTheSpotlight().attemptsTo(
                ContactUSForm.fill()
        );
    }
    @Then("user should see the success message")
    public void verifyTheSuccessMessage(){
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ALWAYS_TRUE, isPresent())
        );
    }
}
