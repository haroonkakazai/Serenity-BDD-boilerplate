package ui.com.Emumba.pages.home;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
@SuppressWarnings("unused")
public class HomePage {
    // In this class we can write the high/low level functions related to this page
    // like If you want to fill/open/update any modal or form we can write whose functions here
   public static Performable open(){
       return Task.where("{0} opens the Modal", Actor::attemptsTo);
   }
   public static Performable fill(){
       return Task.where("{0} fills the Modal", Actor::attemptsTo);
   }
   // These are just example methods, could be different for you


}
