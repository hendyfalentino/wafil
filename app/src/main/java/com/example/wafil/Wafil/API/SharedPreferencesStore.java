package com.example.wafil.Wafil.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesStore {

    /** Menyimpan data user yang sedang melakukan login
     *  Cara penggunaan : SharedPreferencesStore.setAuthUserId("STRING_ID_DARI_USER", getBaseContext())**/
    public static void setAuthUserId(String user_auth, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_auth", user_auth);
        editor.apply();
    }

    /** Mengambil data id dari user yang sedang login
     *  Cara penggunaan : String uid = SharedPreferencesStore.getAuthUserId(getBaseContext()) **/
    public static String getAuthUserId(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("user_auth", null);
    }

    /** Validasi jika user sedang login
     *  Cara penggunaan : Boolean sedangLogin = SharedPreferencesStore.userLoggedIn(getBaseContext()) **/
    public static Boolean userLoggedIn(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String uid = preferences.getString("user_auth", null);
        return uid != null;
    }
}
