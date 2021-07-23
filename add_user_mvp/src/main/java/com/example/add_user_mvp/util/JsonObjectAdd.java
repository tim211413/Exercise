package com.example.add_user_mvp.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectAdd {
    JSONObject jsonObject = new JSONObject();
    public JSONObject addJsonObject(String name, String phone) {
        try {
            jsonObject.put("userName", name);
            jsonObject.put("userPhone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
