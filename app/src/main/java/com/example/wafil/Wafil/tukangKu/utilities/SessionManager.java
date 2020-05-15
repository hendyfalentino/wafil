package com.example.wafil.Wafil.tukangKu.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0 ;
    private static final String PREF_NAME = "pref";
    private static final String LOGIN = "IS_LOGIN";
    public static final String user_name = "user_name";
    public static final String user_first_name = "user_first_name";
    public static final String user_id = "user_id";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String user_first_name ,String user_name, int user_id){
        editor.putBoolean("LOGIN", true);
        editor.putString("user_first_name", user_first_name);
        editor.putString("user_name", user_name);
        editor.putInt("user_id", user_id);
        editor.apply();
    }

    public boolean isLogIn(){
        return sharedPreferences.getBoolean("LOGIN", false);
    }

    public boolean checkLogIn(){
        return isLogIn();
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(user_first_name, sharedPreferences.getString(user_first_name, null));
        user.put(user_name, sharedPreferences.getString(user_name, null));
        user.put(user_id, sharedPreferences.getString(user_id, null));
        return user;
    }

    public void LogOut(){
        editor.clear();
        editor.commit();
    }
}
