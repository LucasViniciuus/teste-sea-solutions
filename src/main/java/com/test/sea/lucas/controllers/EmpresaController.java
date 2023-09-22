package com.test.sea.lucas.controllers;

import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.services.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/setores")
public class EmpresaController {
    @Autowired
    private SetorService setorSvc;
    @PostMapping
    public ResponseEntity<String> criaSetor(@RequestBody Setor setor) throws SetorComMesmoNomeException {
        try{
            setorSvc.criarSetor(setor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SetorComMesmoNomeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
