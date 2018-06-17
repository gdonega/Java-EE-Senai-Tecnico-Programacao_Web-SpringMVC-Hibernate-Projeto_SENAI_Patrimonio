package informatica.sp.senai.br.senaipatrimonio.logic.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class Movimentacao {

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private Long id;
    private Long dataDaMovimentacao;
    private ItemPatrimonio itemMovido;
    private Usuario executou;
    private Ambiente ambienteOriginal;
    private Ambiente ambienteNovo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataDaMovimentacao() {
        return dataDaMovimentacao;
    }
    public String getDataDaMovimentacaoString() {
        return dateFormat.format(dataDaMovimentacao);
    }

    public void setDataDaMovimentacao(Long dataDaMovimentacao) {
        this.dataDaMovimentacao = dataDaMovimentacao;
    }

    public ItemPatrimonio getItemMovido() {
        return itemMovido;
    }

    public void setItemMovido(ItemPatrimonio itemMovido) {
        this.itemMovido = itemMovido;
    }

    public Usuario getExecutou() {
        return executou;
    }

    public void setExecutou(Usuario executou) {
        this.executou = executou;
    }

    public Ambiente getAmbienteOriginal() {
        return ambienteOriginal;
    }

    public void setAmbienteOriginal(Ambiente ambienteOriginal) {
        this.ambienteOriginal = ambienteOriginal;
    }

    public Ambiente getAmbienteNovo() {
        return ambienteNovo;
    }

    public void setAmbienteNovo(Ambiente ambienteNovo) {
        this.ambienteNovo = ambienteNovo;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", dataDaMovimentacao=" + dataDaMovimentacao +
                ", itemMovido=" + itemMovido +
                ", executou=" + executou +
                ", ambienteOriginal=" + ambienteOriginal +
                ", ambienteNovo=" + ambienteNovo +
                '}';
    }
}
