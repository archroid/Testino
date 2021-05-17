package xyz.archroid.testino.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefrenceManager {

    private static PrefrenceManager instance = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    public static PrefrenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PrefrenceManager(context);
        }
        return instance;
    }

    private PrefrenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences("porsooPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void putUserType(String userType) {
        editor.putString("userType", userType).apply();
    }

    public String getUserType() {
        return sharedPreferences.getString("userType", null);
    }

    public void putUsername(int username) {
        editor.putInt("username", username).apply();
    }

    public int getUsername() {
        return sharedPreferences.getInt("username", 0);
    }

    public void putToken(String token) {
        editor.putString("token", token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

}
