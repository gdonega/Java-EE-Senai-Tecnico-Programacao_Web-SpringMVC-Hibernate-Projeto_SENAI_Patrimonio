package informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.recyclerview;

import android.view.View;
import android.widget.TextView;

import gdonega.io.recyclerviewhelper.viewholder.BaseViewHolder;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class MovimentacaoViewHolder extends BaseViewHolder<Movimentacao> {
    private TextView tvId;
    private TextView tvExecutou;
    private TextView tvData;
    private TextView tvAmbienteOriginal;
    private TextView tvAmbienteNovo;

    public MovimentacaoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void manipulateCardView(View view) {
        this.tvId = view.findViewById(R.id.tvId);
        this.tvExecutou = view.findViewById(R.id.tvExecutou);
        this.tvData = view.findViewById(R.id.tvData);
        this.tvAmbienteOriginal = view.findViewById(R.id.tvAmbienteOriginal);
        this.tvAmbienteNovo = view.findViewById(R.id.tvAmbienteNovo);

    }

    @Override
    public void setOnHolder(Movimentacao movimentacao) {
        this.tvId.setText("ID: "+String.valueOf(movimentacao.getId()));
        this.tvExecutou.setText("Executado por: "+movimentacao.getExecutou().getNome());
        this.tvData.setText("Ocorreu em: "+movimentacao.getDataDaMovimentacaoString());
        this.tvAmbienteOriginal.setText("De: "+movimentacao.getAmbienteOriginal().getNome());
        this.tvAmbienteNovo.setText("Para: "+movimentacao.getAmbienteNovo().getNome());
    }
}
