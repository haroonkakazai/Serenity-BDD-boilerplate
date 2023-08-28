package ui.com.Emumba.pages.home;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Screenplay is an actor-centric pattern, and just as actors interact with the system by performing tasks and interactions, they can query the state of the system by asking questions.
 */
@SuppressWarnings("unused")
public class Questions {
    public static Question<Boolean> isFieldDisabled(Target target){
        return Question.about("Is field is disabled in the modal").answeredBy(
                actor -> {
                    return target.resolveFor(actor).isDisabled();
                }
        );
    }

}
