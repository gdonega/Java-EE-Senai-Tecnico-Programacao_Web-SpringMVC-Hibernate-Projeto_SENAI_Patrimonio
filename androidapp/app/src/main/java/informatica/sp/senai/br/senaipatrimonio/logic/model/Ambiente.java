package informatica.sp.senai.br.senaipatrimonio.logic.model;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class Ambiente {
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ambiente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
