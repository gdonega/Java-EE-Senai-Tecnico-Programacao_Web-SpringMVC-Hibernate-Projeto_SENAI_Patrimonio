package br.senai.sp.informatica.senaipatrimonio.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Categoria {

    public Categoria(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Categoria(Long id) {
        super();
        this.id = id;
    }

    public Categoria() {
        super();
    }

    //Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 30, nullable = false, unique = true)
    @NotNull
    @Size(min = 1, max = 30)
    private String nome;


    //Getters && Setters
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


}
