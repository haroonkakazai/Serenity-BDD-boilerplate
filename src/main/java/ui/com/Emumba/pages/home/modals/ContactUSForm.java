package ui.com.Emumba.pages.home.modals;

import dataReader.Data;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static ui.com.Emumba.pages.home.HomeUI.*;

public class ContactUSForm {
    public static Performable open() {
        return Task.where("{0} opens the Contact-Us form", actor -> {
            actor.attemptsTo(
                    WaitUntil.the(CONTACT_US_BUTTONS, isClickable()).forNoMoreThan(Duration.ofSeconds(5)),
                    Click.on(CONTACT_US_BUTTONS)
            );
        });
    }

    public static Performable fill() {
        return Task.where("{0} fills the Contact-Us form", actor -> {
            actor.attemptsTo(
                    Enter.keyValues(Data.generateName()).into(USER_NAME_FIELD),
                    Enter.keyValues(Data.generateEmail()).into(EMAIL_FIELD),
                    Enter.keyValues(Data.getText(20)).into(MESSAGE_FIELD)
            );
        });
    }
}
