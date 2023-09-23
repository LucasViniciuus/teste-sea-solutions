package com.test.sea.lucas.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany
    @JoinColumn(name = "trabalhador_id")
    private List<Trabalhador> trabalhador;
    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

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

    public List<Trabalhador> getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(List<Trabalhador> trabalhador) {
        this.trabalhador = trabalhador;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
