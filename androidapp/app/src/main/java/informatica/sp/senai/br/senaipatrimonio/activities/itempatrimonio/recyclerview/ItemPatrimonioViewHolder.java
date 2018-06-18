package informatica.sp.senai.br.senaipatrimonio.activities.itempatrimonio.recyclerview;

import android.content.Intent;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import gdonega.io.recyclerviewhelper.viewholder.BaseViewHolder;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.recyclerview.MovimentacoesActivity;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.util.ItemPatrimonioActivitiesUtils;

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
        tvId.setText("Item: "+String.valueOf(itemPatrimonio.getId()));
        tvAmbienteAtual.setText("Ambiente: "+itemPatrimonio.getAmbienteAtual().getNome());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(itemView.getContext(), MovimentacoesActivity.class);
        ItemPatrimonioActivitiesUtils.saveItemPatrimonio(itemPatrimonio);
        itemView.getContext().startActivity(intent);
    }
}
