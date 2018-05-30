package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_NAME_OBJECT = "name";
    private static final String KEY_NAME_MAIN_NAME = "mainName";
    private static final String KEY_NAME_AKA = "alsoKnownAs";

    public static Sandwich parseSandwichJson(String json) {

        try {
            Sandwich sandwich = new Sandwich();
            JSONObject sandwichObj = new JSONObject(json);

            sandwich.setImage(sandwichObj.getString(KEY_IMAGE));
            sandwich.setDescription(sandwichObj.getString(KEY_DESCRIPTION));
            sandwich.setPlaceOfOrigin(sandwichObj.getString(KEY_PLACE_OF_ORIGIN));

            // Extract Nested "name" JSON object
            JSONObject sandwichNameObj = sandwichObj.getJSONObject(KEY_NAME_OBJECT);
            sandwich.setMainName(sandwichNameObj.getString(KEY_NAME_MAIN_NAME));

            // Converting Json array to java List of String
            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientArray = sandwichObj.getJSONArray(KEY_INGREDIENTS);
            for (int i = 0; i < ingredientArray.length(); i++) {
                ingredients.add(ingredientArray.getString(i));
            }
            sandwich.setIngredients(ingredients);

            // Extract altername names from name object
            List<String> alternateNames = new ArrayList<>();
            JSONArray alternateNameArray = sandwichNameObj.getJSONArray(KEY_NAME_AKA);
            for (int i = 0; i < alternateNameArray.length(); i++) {
                alternateNames.add(alternateNameArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alternateNames);

            return sandwich;
        } catch (JSONException je) {
            je.printStackTrace();
            return null;
        }
    }
}
