package com.example.add_user_mvvm.model.util;

/*
public class FileRead {
    User user;
    //讀資料
    public void readFile(String fileName) {

        try (FileInputStream fin = openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
            byte[] buffer = new byte[1024];
            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    JSONArray jsonArrayInRead = new JSONArray(new String(buffer, 0, flag));
                    for (int i = 0; i < jsonArrayInRead.length(); i++) {
                        JSONObject jsonObjectInRead = jsonArrayInRead.getJSONObject(i);
                        String userNameInJson = jsonObjectInRead.getString("userName");
                        String userPhoneInJson = jsonObjectInRead.getString("userPhone");

                        Log.d("TAG", "userNameInJson: " + userNameInJson);
                        Log.d("TAG", "userPhoneInJson: " + userPhoneInJson);

                        user = new User(userNameInJson, userPhoneInJson);

                        addJsonArray(user.getUserName(), user.getUserPhone());
                        //count = jsonArrayInRead.length();
                    }
                    //Log.d("TAG", "jsonArrayInRead.length(): " + count);
                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
}*/
