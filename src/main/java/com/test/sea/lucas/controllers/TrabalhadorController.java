package com.test.sea.lucas.controllers;

import com.test.sea.lucas.entities.Trabalhador;
import com.test.sea.lucas.exceptions.TrabalhadorComMesmoCpf;
import com.test.sea.lucas.services.TrabalhadorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trabalhadores")
public class TrabalhadorController {
    @Autowired
    TrabalhadorService trabalhadorSvc;
    @PostMapping
    @ApiOperation(value = "Responsável por criar o trabalhador")
    public ResponseEntity<?> criarTrabalhador(@RequestBody Trabalhador novoTrabalhador) {
        try {
            Trabalhador trabalhador = trabalhadorSvc.criarTrabalhador(novoTrabalhador);
            return new ResponseEntity<>("O trabalhador foi criado com sucesso.", HttpStatus.CREATED);
        } catch (TrabalhadorComMesmoCpf e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

