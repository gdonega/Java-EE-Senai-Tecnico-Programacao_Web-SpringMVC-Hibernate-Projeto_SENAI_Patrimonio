package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import gdonega.io.recyclerviewhelper.viewholder.BaseViewHolder;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.activities.Main;
import informatica.sp.senai.br.senaipatrimonio.activities.itempatrimonio.recyclerview.ItemPatrimonioActivity;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class PatrimonioViewHolder extends BaseViewHolder<Patrimonio> implements View.OnClickListener{
    private TextView tvNome;
    private TextView tvCategoria;
    private Patrimonio patrimonio;

    public PatrimonioViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void manipulateCardView(View view) {
        this.tvNome = view.findViewById(R.id.tvNome);
        this.tvCategoria = view.findViewById(R.id.tvCategoria);
        view.setOnClickListener(this);
    }

    @Override
    public void setOnHolder(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        tvNome.setText("Nome: "+patrimonio.getNome());
        tvCategoria.setText("Categoria: "+patrimonio.getCategoria().getNome());
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(itemView.getContext(), ItemPatrimonioActivity.class);
        intent.putExtra("idPatrimonio", patrimonio.getId());
        itemView.getContext().startActivity(intent);
    }
}
