package informatica.sp.senai.br.senaipatrimonio.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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


    public static void saveTokenWithoutBearer(Activity activity, String tokenWithoutBearer) {
        String fullToken = "Bearer " + tokenWithoutBearer;
        saveToken(activity, fullToken);
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
        editor.putLong(expiresAtKeyName,expiresAt.getTime());
        editor.apply();
    }

    public static String getToken() {
        SharedPreferences preferences = Main.getContext().getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getString(tokenKeyName, null);
    }

    public static String getToken(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getString(tokenKeyName, null);
    }

    public static Long getTokenExpireDate(){
        SharedPreferences preferences = Main.getContext().getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getLong(expiresAtKeyName, new Date().getTime()-99999999L);
    }

    public static Long getTokenExpireDate(Activity activity){
        SharedPreferences preferences = activity.getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        return preferences.getLong(expiresAtKeyName, new Date().getTime()-99999999L);
    }

    public static Boolean isTokenValid(){
        if(getTokenExpireDate() > new Date().getTime())
            return true;

        return false;
    }
}
