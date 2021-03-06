package br.senai.sp.informatica.senaipatrimonio.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_ocorreu", nullable = false, unique = false)
    @NotNull
    private Date dataDaMovimentacao;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_item", nullable = false)
    private ItemPatrimonio itemMovido;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario executou;

    @ManyToOne
    @JoinColumn(name = "id_ambiente_origem", nullable = false)
    private Ambiente ambienteOriginal;

    @ManyToOne
    @JoinColumn(name = "id_ambiente_destino", nullable = false)
    private Ambiente ambienteNovo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDataDaMovimentacao() {
        return dataDaMovimentacao;
    }

    public void setDataDaMovimentacao(Date dataDaMovimentacao) {
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
        return "Movimentacao [id=" + id + ", dataDaMovimentacao=" + dataDaMovimentacao + ", itemMovido=" + itemMovido
                + ", executou=" + executou + ", ambienteOriginal=" + ambienteOriginal + ", ambienteNovo=" + ambienteNovo
                + "]";
    }


}
