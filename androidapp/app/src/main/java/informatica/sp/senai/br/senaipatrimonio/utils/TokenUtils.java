package informatica.sp.senai.br.senaipatrimonio.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class TokenUtils {

    private static String tokenSharedName = "token";
    private static String tokenKeyName = "token";

    public static void saveToken(Activity activity, String token){
        SharedPreferences preferences = activity.getSharedPreferences(tokenSharedName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(tokenKeyName, token);
        editor.apply();
    }
    public static void saveTokenWithoutBearer(Activity activity, String tokenWithoutBearer){
        tokenWithoutBearer = "Bearer " + tokenWithoutBearer;
        saveToken(activity, tokenWithoutBearer);
    }

    public static String getToken(Activity activity){
        SharedPreferences preferences = activity.getSharedPreferences(tokenKeyName, MODE_PRIVATE);
        return preferences.getString(tokenSharedName, null);
    }
}
