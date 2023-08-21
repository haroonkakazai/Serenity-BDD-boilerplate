package ui.com.Emumba.pages.home;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class Questions {
    // Here we can write the Questions related to the page
    public static Question<Boolean> isFieldDisabled(Target target){
        return Question.about("Is field is disabled in the modal").answeredBy(
                actor -> {
                    return target.resolveFor(actor).isDisabled();
                }
        );
    }

}
