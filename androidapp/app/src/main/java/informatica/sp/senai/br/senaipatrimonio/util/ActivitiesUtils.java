package informatica.sp.senai.br.senaipatrimonio.util;

import android.app.Activity;
import android.content.Intent;

import informatica.sp.senai.br.senaipatrimonio.LoginActivity;

public class ActivitiesUtils {
    public static void closeActivityIfInvalidToken(Activity activity){
        if(!TokenUtils.isTokenValid()){
            closeActivity(activity);
        }
    }

    public static void logout(Activity activity){
        TokenUtils.logoutToken();
        closeActivity(activity);
    }

    public static void closeActivity(Activity activity){
        Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

}
