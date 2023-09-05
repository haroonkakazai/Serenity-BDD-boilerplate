package ui.com.Emumba.pages.home;

import net.serenitybdd.screenplay.targets.Target;

/**
 * In this class we write all the Target/Locators related to this page
 */
@SuppressWarnings("unused")
public class HomeUI {
    public static final Target ALWAYS_TRUE = Target.the("Always True").locatedBy("//*");

    public static final Target CONTACT_US_BUTTONS = Target.the("Contact-us buttons").locatedBy("(//nav//button)[2]");

    public static final Target USER_NAME_FIELD = Target.the("user-name").locatedBy("//input[@name=\"username\"]");

    public static final Target EMAIL_FIELD = Target.the("email").locatedBy("//input[@name=\"email\"]");

    public static final Target MESSAGE_FIELD = Target.the("message").locatedBy("(//textarea)[1]");


}
