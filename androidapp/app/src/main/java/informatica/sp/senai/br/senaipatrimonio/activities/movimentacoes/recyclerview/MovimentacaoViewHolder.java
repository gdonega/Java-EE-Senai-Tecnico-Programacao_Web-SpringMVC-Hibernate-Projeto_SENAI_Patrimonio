package informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.recyclerview;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import gdonega.io.recyclerviewhelper.viewholder.BaseViewHolder;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.activities.Main;
import informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.MovEasterEggActivity;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class MovimentacaoViewHolder extends BaseViewHolder<Movimentacao> implements View.OnClickListener{
    private Byte count;

    private TextView tvExecutou;
    private TextView tvData;
    private TextView tvAmbienteOriginal;
    private TextView tvAmbienteNovo;

    public MovimentacaoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void manipulateCardView(View view) {
        count = 0;

        this.tvExecutou = view.findViewById(R.id.tvExecutou);
        this.tvData = view.findViewById(R.id.tvData);
        this.tvAmbienteOriginal = view.findViewById(R.id.tvAmbienteOriginal);
        this.tvAmbienteNovo = view.findViewById(R.id.tvAmbienteNovo);
        view.setOnClickListener(this);
    }

    @Override
    public void setOnHolder(Movimentacao movimentacao) {
        this.tvExecutou.setText("Executado por: "+movimentacao.getExecutou().getNome());
        this.tvData.setText("Ocorreu em: "+movimentacao.getDataDaMovimentacaoString());
        this.tvAmbienteOriginal.setText("De: "+movimentacao.getAmbienteOriginal().getNome());
        this.tvAmbienteNovo.setText("Para: "+movimentacao.getAmbienteNovo().getNome());
    }

    @Override
    public void onClick(View v) {
        count++;

        if(count == 10){
            count = 0;
            Intent intent = new Intent(itemView.getContext(), MovEasterEggActivity.class);
            itemView.getContext().startActivity(intent);
        }
    }
}
