package jsonutils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFile {
    String filePath;
    public JsonFile(String filePath){
        this.filePath = filePath;
    }
    public static JsonFile fromPath(String filePath){
        return new JsonFile(filePath);
    }
    public JsonElement retrieve(){
        Gson gson = new Gson();
        JsonElement jsonElement = new JsonElement() {
            @Override
            public JsonElement deepCopy() {
                return null;
            }
        };
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            jsonElement = gson.fromJson(reader, JsonElement.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        return jsonElement;
    }



}
