package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class RVLayoutBuilder {

    public static LinearLayoutManager createLinearLayout(Context context){
        return new LinearLayoutManager(context);
    }

    public static LinearLayoutManager createLinearLayout(Context context,int orientation, boolean reverseLayout){
        return new LinearLayoutManager(context,orientation,reverseLayout);
    }

    public static LinearLayoutManager createLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        return new LinearLayoutManager(context,attrs,defStyleAttr,defStyleRes);
    }

}
