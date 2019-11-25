package com.briozing.automation.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author KohitijDas
 */
public class JsonHelper {
    /**
     * @param type
     * @param jsonObject
     * @param jsonElement
     * @return
     */
    public static <T> String remove(Class<T> type, T jsonObject, String jsonElement) {

        String jObj = new GsonBuilder().create().toJson(jsonObject, type);
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(jObj);
        jo.remove(jsonElement);
        return jo.toString();
    }

    /**
     * @param type
     * @param jsonObject
     * @param objName
     * @param jsonElement
     * @param <T>
     * @return
     */
    public static <T> String remove(Class<T> type, T jsonObject, String objName, String jsonElement) {

        String jObj = new GsonBuilder().create().toJson(jsonObject, type);
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(jObj);
        jo.getAsJsonObject(objName).remove(jsonElement);
        return jo.toString();
    }


}
