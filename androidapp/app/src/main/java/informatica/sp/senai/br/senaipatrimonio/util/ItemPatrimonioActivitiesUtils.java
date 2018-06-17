package informatica.sp.senai.br.senaipatrimonio.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

import informatica.sp.senai.br.senaipatrimonio.activities.Main;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class ItemPatrimonioActivitiesUtils {

    private static String ITEM_PREFERENCES = "item_preferences";
    private static String ITEM_AMBIENTE_ID = "item_ambiente";
    private static String ITEM_ID = "item_id";

    public static void saveItemPatrimonio(ItemPatrimonio itemPatrimonio) {
        saveItemPatrimonio(Main.getContext(), itemPatrimonio);
    }

    public static void saveItemPatrimonio(Context context, ItemPatrimonio itemPatrimonio) {
        SharedPreferences preferences = context.getSharedPreferences(ITEM_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(ITEM_AMBIENTE_ID, itemPatrimonio.getAmbienteAtual().getId());
        editor.putLong(ITEM_ID, itemPatrimonio.getId());
        editor.apply();
    }

    public static Long getAmbienteId() {
        return getAmbienteId(Main.getContext());
    }
    public static Long getAmbienteId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ITEM_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getLong(ITEM_AMBIENTE_ID, -10);
    }

    public static Long getItemId() {
        return getItemId(Main.getContext());
    }
    public static Long getItemId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ITEM_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getLong(ITEM_ID, -10);
    }

}
