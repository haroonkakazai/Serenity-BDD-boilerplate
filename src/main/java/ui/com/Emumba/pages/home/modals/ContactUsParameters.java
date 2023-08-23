package ui.com.Emumba.pages.home.modals;

import com.google.gson.annotations.SerializedName;
import jsonutils.model.IndexedPojo;

public class ContactUsParameters extends IndexedPojo {

    public static final String USER_NAME = "Full Name";
    @SerializedName(USER_NAME)
    public String nameOfUSer = "";

    public static final String USER_EMAIL = "Email";
    @SerializedName(USER_EMAIL)
    public String emailOFUSer = "";

    public static final String USER_MESSAGE = "Email";
    @SerializedName(USER_MESSAGE)
    public String messageFromUSer = "";
}
