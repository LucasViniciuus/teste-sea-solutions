package com.test.sea.lucas.controllers;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.exceptions.SetorIdSemCorrespondencia;
import com.test.sea.lucas.exceptions.SetoresNulosException;
import com.test.sea.lucas.services.SetorService;
import com.test.sea.lucas.services.TrabalhadorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/setores", consumes = "application/json")
public class SetorController {
    @Autowired
    private SetorService setorSvc;

    @Autowired
    private TrabalhadorService trabalhadorSvc;

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

    @GetMapping(value = "/buscarTotalSetores")
    @ApiOperation(value = "Responsável por listar todos os setores criados.")
    public ResponseEntity<?> findAllSetores() throws SetoresNulosException {
        try {
            List<Setor> findAllSetores = setorSvc.findAllSetores();
            if (!findAllSetores.isEmpty()) {
                return new ResponseEntity<>(findAllSetores, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (SetoresNulosException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/buscar/{id}")
    @ApiOperation(value = "Responsável por buscar o setor criado por Id.")
    public ResponseEntity<?> findSetorId(@PathVariable Long id) {
        try {
            Setor setorId = setorSvc.buscaSetorId(id);
            if (setorId == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(setorId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value = "Responsável pela deleção de um setor pelo Id.")
    public ResponseEntity<?> deletarSetor(@PathVariable Long id) throws SetorIdSemCorrespondencia {
        try {
            Setor setorExistente = setorSvc.encontrarSetorPorId(id);
            if (setorExistente == null) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        setorSvc.deletaSetor(id);
        return ResponseEntity.noContent().build();
    }
}