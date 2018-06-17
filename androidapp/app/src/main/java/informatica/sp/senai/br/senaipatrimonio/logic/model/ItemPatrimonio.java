package informatica.sp.senai.br.senaipatrimonio.logic.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class ItemPatrimonio {

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Long id;
    private Ambiente ambienteAtual;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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
                "dateFormat=" + dateFormat +
                ", id=" + id +
                ", ambienteAtual=" + ambienteAtual +
                '}';
    }
}
