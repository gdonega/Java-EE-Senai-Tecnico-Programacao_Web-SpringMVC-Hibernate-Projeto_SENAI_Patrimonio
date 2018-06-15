package informatica.sp.senai.br.senaipatrimonio.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

import informatica.sp.senai.br.senaipatrimonio.activities.Main;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class TokenUtils {

    private static String tokenSharedName = "shared_token";
    private static String tokenKeyName = "token";
    private static String expiresAtKeyName = "expires_at";


    public static void saveTokenWithoutBearer(Context context, String tokenWithoutBearer) {
        String fullToken = "Bearer " + tokenWithoutBearer;
        saveToken(context, fullToken);
    }

    public static void saveTokenWithoutBearer(String tokenWithoutBearer) {
        String fullToken = "Bearer " + tokenWithoutBearer;
        saveToken(Main.getContext(), fullToken);
    }

    public static void saveToken(Context context, String token) {
        saveTokenMeth(context.getSharedPreferences(tokenSharedName, MODE_PRIVATE), token);
    }

    private static void saveTokenMeth(SharedPreferences preferences, String token) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 8);

        Date expiresAt = calendar.getTime();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(tokenKeyName, token);
        editor.putLong(expiresAtKeyName, expiresAt.getTime());
        editor.apply();
    }

    public static String getToken() {
        SharedPreferences preferences = Main.getContext().getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getString(tokenKeyName, null);
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getString(tokenKeyName, null);
    }

    public static Long getTokenExpireDate() {
        SharedPreferences preferences = Main.getContext().getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getLong(expiresAtKeyName, new Date().getTime() - 99999999L);
    }

    public static Long getTokenExpireDate(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getLong(expiresAtKeyName, new Date().getTime() - 99999999L);
    }

    public static Boolean isTokenValid() {
        if (getTokenExpireDate() > new Date().getTime())
            return true;

        return false;
    }


    public static void logoutToken(Context context) {
        logoutTokenMeth(context);
    }

    public static void logoutToken() {
        logoutTokenMeth(Main.getContext());
    }

    private static void logoutTokenMeth(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        sharedPreferences.edit()
                .clear()
                .apply();
    }
}
