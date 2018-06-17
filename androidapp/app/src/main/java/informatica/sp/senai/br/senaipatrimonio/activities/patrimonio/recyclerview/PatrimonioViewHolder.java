package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.view.View;
import android.widget.TextView;

import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class PatrimonioViewHolder extends ViewHxH<Patrimonio> {
    private TextView tvNome;
    private TextView tvCategoria;

    public PatrimonioViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void manipulateCardView(View view) {
        this.tvNome = view.findViewById(R.id.tvNome);
        this.tvCategoria = view.findViewById(R.id.tvCategoria);
    }

    @Override
    public void setOnHolder(Patrimonio patrimonio) {
        tvNome.setText(patrimonio.getNome());
        tvCategoria.setText(patrimonio.getCategoria().getNome());
    }


}
