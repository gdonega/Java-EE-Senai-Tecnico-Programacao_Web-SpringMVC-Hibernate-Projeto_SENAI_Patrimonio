package informatica.sp.senai.br.senaipatrimonio.activities.itempatrimonio.recyclerview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import gdonega.io.recyclerviewhelper.viewholder.BaseViewHolder;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.recyclerview.MovimentacoesActivity;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class ItemPatrimonioViewHolder extends BaseViewHolder<ItemPatrimonio> implements View.OnClickListener {

    private TextView tvId;
    private TextView tvAmbienteAtual;
    private ItemPatrimonio itemPatrimonio;

    public ItemPatrimonioViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void manipulateCardView(View view) {
        this.tvId = view.findViewById(R.id.tvID);
        this.tvAmbienteAtual = view.findViewById(R.id.tvAmbienteAtual);
        view.setOnClickListener(this);
    }

    @Override
    public void setOnHolder(ItemPatrimonio itemPatrimonio) {
        this.itemPatrimonio = itemPatrimonio;
        tvId.setText("ID: "+String.valueOf(itemPatrimonio.getId()));
        tvAmbienteAtual.setText("Ambiente: "+itemPatrimonio.getAmbienteAtual().getNome());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(itemView.getContext(), MovimentacoesActivity.class);
        intent.putExtra("idItem", itemPatrimonio.getId());
        itemView.getContext().startActivity(intent);
    }
}
