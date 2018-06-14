package informatica.sp.senai.br.senaipatrimonio.utils;

import android.app.Activity;

public class ActivitiesUtils {
    public static void closeActivity(Activity activity){
        if(!TokenUtils.isTokenValid()){
            activity.finish();
        }
    }
}
