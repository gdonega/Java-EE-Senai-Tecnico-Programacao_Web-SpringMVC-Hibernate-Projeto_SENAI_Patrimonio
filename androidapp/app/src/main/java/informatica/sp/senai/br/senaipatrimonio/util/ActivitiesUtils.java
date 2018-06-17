package informatica.sp.senai.br.senaipatrimonio.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import informatica.sp.senai.br.senaipatrimonio.LoginActivity;
import informatica.sp.senai.br.senaipatrimonio.R;

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


    public static void setStandardAppMenu(AppCompatActivity activity){
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logout_icon);
        toolbar.setNavigationOnClickListener(
                v -> ActivitiesUtils.logout(activity)
        );
    }
}
