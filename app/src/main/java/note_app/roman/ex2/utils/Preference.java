package note_app.roman.ex2.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static final String PREFS_NAME = "preference";

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static String getLastUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER, " ");
    }

    public static void setLastUser(Context context, String user) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(USER, user).apply();
    }


    public static String getLastPassword(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PASSWORD, " ");
    }

    public static void setLastPassword(Context context, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(PASSWORD, password).apply();
    }
}
