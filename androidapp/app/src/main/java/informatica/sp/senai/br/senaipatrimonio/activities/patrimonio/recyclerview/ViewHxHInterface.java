package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.view.View;

public interface ViewHxHInterface <Obj>{

    void manipulateCardView(View view);
    void setOnHolder(final Obj obj);
}
