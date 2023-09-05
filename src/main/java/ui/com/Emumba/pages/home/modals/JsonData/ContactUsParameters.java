package ui.com.Emumba.pages.home.modals.JsonData;

import com.google.gson.annotations.SerializedName;
import jsonutils.model.IndexedPojo;

public class ContactUsParameters extends IndexedPojo {

    public static final String USER_NAME = "Name";
    @SerializedName(USER_NAME)
    public String Name = "";

    public static final String USER_EMAIL = "Email";
    @SerializedName(USER_EMAIL)
    public String Email = "";

    public static final String USER_MESSAGE = "Message";
    @SerializedName(USER_MESSAGE)
    public String Message = "";

}
