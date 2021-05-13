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

    public void putUserType(String userType){
        editor.putString("userType", userType).apply();
    }

    public String getUserType(){
        return sharedPreferences.getString("userType", null);
    }



}
