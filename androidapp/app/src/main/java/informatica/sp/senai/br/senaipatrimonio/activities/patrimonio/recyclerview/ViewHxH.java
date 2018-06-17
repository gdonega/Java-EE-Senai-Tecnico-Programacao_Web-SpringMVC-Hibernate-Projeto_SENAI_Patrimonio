package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ViewHxH<Object> extends RecyclerView.ViewHolder implements ViewHxHInterface<Object> {

    private View cardView;


    public ViewHxH(View itemView) {
        super(itemView);
        this.cardView = itemView;
        manipulateCardView(itemView);
    }

    public View getCardView() {
        return cardView;
    }
}
