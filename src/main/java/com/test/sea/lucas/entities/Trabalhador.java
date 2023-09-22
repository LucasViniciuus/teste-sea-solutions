package com.test.sea.lucas.entities;

import javax.persistence.*;

@Entity
public class Trabalhador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nome;
    @Column(unique = true)
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "trabalhador_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
