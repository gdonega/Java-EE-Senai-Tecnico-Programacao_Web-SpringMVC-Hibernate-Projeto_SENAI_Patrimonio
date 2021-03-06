package br.senai.sp.informatica.senaipatrimonio.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Patrimonio {
	
    public Patrimonio(Long id) {
		super();
		this.id = id;
	}

	public Patrimonio() {
		super();
	}

	// Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 40, nullable = false, unique = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String nome;

    @Column(name = "data_cadastro", nullable = false, unique = false)
    private Date dt_cadastro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false) // alterar informações sobre a chave estrangeira
    private Usuario cadastrante;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @NotNull
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    private Categoria categoria;

    

	// Getters && Setters
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

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public Usuario getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(Usuario cadastrante) {
        this.cadastrante = cadastrante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Patrimonio [id=" + id + ", nome=" + nome + ", dt_cadastro=" + dt_cadastro + ", cadastrante="
                + cadastrante + ", categoria=" + categoria + "]";
    }


}
