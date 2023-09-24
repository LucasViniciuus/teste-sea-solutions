package com.test.sea.lucas.controllers;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.exceptions.CargoComMesmoNomeException;
import com.test.sea.lucas.services.CargoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cargos", consumes = "application/json")
public class CargoController {
    @Autowired
    private CargoService cargoSvc;

    @PostMapping
    @ApiOperation(value = "Responsável por criar o cargo.")
    public ResponseEntity<?> criarCargo(@RequestBody Cargo novoCargo) {
        try {
            Cargo cargo = cargoSvc.criarCargo(novoCargo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CargoComMesmoNomeException e) {
            return new ResponseEntity<>("Não foi possível gerar um novo cargo.", HttpStatus.BAD_REQUEST);
        }
    }
}
