package ui.com.Emumba.pages.home.modals.JsonData;

import com.google.gson.annotations.SerializedName;
import jsonutils.model.IndexedPojo;
import ui.com.Emumba.pages.home.modals.JsonData.ContactUsParameters;

import java.util.ArrayList;
import java.util.List;

public class FillContactUsFormParameters extends IndexedPojo {
    public static final String CONTACT_US_KEY = "Contact_Us";
    @SerializedName(CONTACT_US_KEY)
    public List<ContactUsParameters> Contact_Us = new ArrayList<>();

}
