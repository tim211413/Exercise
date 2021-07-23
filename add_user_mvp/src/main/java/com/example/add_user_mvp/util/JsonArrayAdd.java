package com.example.add_user_mvp.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonArrayAdd {
    JSONObject jsonObject = new JSONObject();
    public JSONObject addJsonArray(String name, String phone) {

        try {
            jsonObject.put("userName", name);
            jsonObject.put("userPhone", phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
