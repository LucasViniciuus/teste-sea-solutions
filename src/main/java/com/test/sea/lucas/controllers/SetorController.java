package com.test.sea.lucas.controllers;

import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.CargoIdSemCorrespondencia;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.exceptions.SetorIdSemCorrespondencia;
import com.test.sea.lucas.services.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/setores", consumes = "application/json")
public class SetorController {
    @Autowired
    private SetorService setorSvc;

    @PostMapping
    public ResponseEntity<?> criarSetor(@RequestBody Setor setor) {
        try {
            Setor novoSetor = setorSvc.criarSetor(setor);
            return new ResponseEntity<>(novoSetor, HttpStatus.CREATED);
        } catch (SetorComMesmoNomeException e) {
            return new ResponseEntity<>("Foi encontrado um setor com o mesmo nome.", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarSetor(@PathVariable Long id, @RequestBody Setor setorAtualizado){
        try {
            Setor setor = setorSvc.atualizarSetor(id, setorAtualizado);
            return new ResponseEntity<>(setor, HttpStatus.OK);
        } catch (SetorIdSemCorrespondencia e) {
            return new ResponseEntity<>("Não foi encontrado nenhum setor para atualização.", HttpStatus.BAD_REQUEST);
        } catch (CargoIdSemCorrespondencia e) {
            return new ResponseEntity<>("Não foi encontrado nenhum cargo para atualização.", HttpStatus.BAD_REQUEST);
        }
    }

}
