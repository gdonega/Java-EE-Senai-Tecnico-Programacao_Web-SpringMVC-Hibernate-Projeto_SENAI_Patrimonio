package informatica.sp.senai.br.senaipatrimonio.logic.model;


import org.adataq.jserializer.SerializationInput;
import org.adataq.jserializer.SerializationOutput;

public class Usuario {

    private Long id;
    @SerializationInput(pattern = {"bomDia"})
    @SerializationOutput(pattern = {"456789"})
    private String nome;
    private TipoUsuario tipo;
    private String email;
    private String senha;


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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
