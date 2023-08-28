package dataReader;

import com.google.gson.JsonObject;
import jsonutils.JsonFile;
import ui.com.Emumba.pages.home.modals.FillContactUsFormParameters;

import static jsonutils.model.IndexedPojoDeserializer.deserializeJsonTo;

/**
 * This class is simply read the Json File
 */
public class ContactUsReader {
    private final static String CONTACT_US_DATA_PATH = "src/test/resources/web/testdata/contact_us.json";

    private static FillContactUsFormParameters Parameters = null;

    public static FillContactUsFormParameters parameters(){
        if (Parameters==null){
            JsonObject jsonData = JsonFile.fromPath(CONTACT_US_DATA_PATH).retrieve().getAsJsonObject();
            Parameters = deserializeJsonTo(jsonData, FillContactUsFormParameters.class);
        }
        return Parameters;
    }
}
