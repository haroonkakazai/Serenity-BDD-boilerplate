package com.Emumba.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

// This NavigateTo class is used to navigate to the respected website's home page. In this class we are using performable
// In the Performable we are using the built-in methods like Open, browseOn
// you can find more details about performable at https://serenity-bdd.github.io/docs/screenplay/screenplay_fundamentals
@SuppressWarnings("unused")
public class NavigateTo {
    public static Performable homePage(){
        return Task.where("{0} opens xyz home page",
                Open.browserOn().the(EmumbaHomePage.class));
    }
}
