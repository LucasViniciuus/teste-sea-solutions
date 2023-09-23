package com.test.sea.lucas.services;

import com.test.sea.lucas.entities.Trabalhador;
import com.test.sea.lucas.exceptions.TrabalhadorComMesmoCpf;
import com.test.sea.lucas.repositories.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TrabalhadorService {

    @Autowired
    private TrabalhadorRepository trabalhadorRepo;

    public Trabalhador criarTrabalhador(@RequestBody Trabalhador novoTrabalhador) throws TrabalhadorComMesmoCpf {
        Trabalhador trabalhadorComMesmoCpf = trabalhadorRepo.buscaTrabalhadorPorCpf(novoTrabalhador.getCpf());
        if (trabalhadorComMesmoCpf != null && trabalhadorComMesmoCpf.getCpf() == novoTrabalhador.getCpf()) {
            throw new TrabalhadorComMesmoCpf(HttpStatus.BAD_REQUEST);
        }
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setCargo(novoTrabalhador.getCargo());
        trabalhador.setNome(novoTrabalhador.getNome());
        trabalhador.setCpf(novoTrabalhador.getCpf());
        trabalhador.setSetor(novoTrabalhador.getSetor());
        return trabalhadorRepo.save(trabalhador);
    }
}
