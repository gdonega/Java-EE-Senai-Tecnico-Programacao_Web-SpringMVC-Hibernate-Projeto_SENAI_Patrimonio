package informatica.sp.senai.br.senaipatrimonio.logic.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class ItemPatrimonio implements Serializable{


    private Long id;
    private Ambiente ambienteAtual;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }

    @Override
    public String toString() {
        return "ItemPatrimonio{" +
                "id=" + id +
                ", ambienteAtual=" + ambienteAtual +
                '}';
    }
}
