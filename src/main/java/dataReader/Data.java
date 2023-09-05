package dataReader;

import com.google.gson.JsonObject;
import jsonutils.JsonFile;
import net.datafaker.Faker;
import ui.com.Emumba.pages.home.modals.JsonData.FillContactUsFormParameters;

import static jsonutils.model.IndexedPojoDeserializer.deserializeJsonTo;

/**
 * This class is simply read the Json File
 */
public class Data {


    private final static String CONTACT_US_DATA_PATH = "src/test/resources/web/testdata/contact_us.json";

    private static FillContactUsFormParameters Parameters = null;

    public static FillContactUsFormParameters parameters(){
        if (Parameters==null){
            JsonObject jsonData = JsonFile.fromPath(CONTACT_US_DATA_PATH).retrieve().getAsJsonObject();
            Parameters = deserializeJsonTo(jsonData, FillContactUsFormParameters.class);
        }
        return Parameters;
    }

    public static String generateName(){
        Faker fake = new Faker();
        return fake.text().text(3,10,true,false,false);
    }
    public static String generateEmail(){
        Faker fake = new Faker();
        String mail = "@automation.emumba";
        return fake.name().firstName().toLowerCase() + mail;
    }

    public static String getText(Integer length){
        return new Faker().text().text(length,length);
    }


}
