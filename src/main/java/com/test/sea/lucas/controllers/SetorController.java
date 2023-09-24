package com.test.sea.lucas.controllers;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.exceptions.SetorIdSemCorrespondencia;
import com.test.sea.lucas.services.SetorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/setores", consumes = "application/json")
public class SetorController {
    @Autowired
    private SetorService setorSvc;

    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<?> findSetorId(@PathVariable Long id) {
        Setor setorId = setorSvc.buscaSetorId(id);
        if(setorId == null){
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(setorId);
    }

    @PostMapping
    @ApiOperation("Responsável por criar o setor.")
    public ResponseEntity<?> criarSetor(@RequestBody Setor setor) {
        try {
            Setor novoSetor = setorSvc.criarSetor(setor);
            return new ResponseEntity<>(novoSetor, HttpStatus.CREATED);
        } catch (SetorComMesmoNomeException e) {
            return new ResponseEntity<>("Foi encontrado um setor com o mesmo nome.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Responsável por atualizar o setor.")
    public ResponseEntity<?> atualizarSetor(@PathVariable Long id, @RequestBody Setor setorAtualizado) {
        try {
            Setor setorExistente = setorSvc.encontrarSetorPorId(id);
            if (setorExistente == null) {
                return ResponseEntity.notFound().build();
            }
            setorExistente.setNome(setorAtualizado.getNome());
            if (setorAtualizado.getCargo() != null) {
                for (Cargo cargoAtualizado : setorAtualizado.getCargo()) {
                    cargoAtualizado.setSetor(setorExistente);
                    setorExistente.getCargo().add(cargoAtualizado);
                }
            }
            Setor setorAtualizadoBanco = setorSvc.salvarSetor(setorExistente);
            return ResponseEntity.ok(setorAtualizadoBanco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}