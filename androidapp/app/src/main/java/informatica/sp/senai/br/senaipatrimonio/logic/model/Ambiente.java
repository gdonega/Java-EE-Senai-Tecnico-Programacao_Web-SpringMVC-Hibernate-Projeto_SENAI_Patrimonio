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
        return nome;
    }


    public String toNewString() {
        return "Ambiente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ambiente ambiente = (Ambiente) o;

        if (!id.equals(ambiente.id)) return false;

        return true;
    }
}
