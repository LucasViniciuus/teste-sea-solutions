package com.test.sea.lucas.services;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.exceptions.CargoComMesmoNomeException;
import com.test.sea.lucas.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    public CargoRepository cargoRepo;

    public Cargo criarCargo(Cargo novoCargo) throws CargoComMesmoNomeException {
        boolean existeCargoCadastradoEmOutroSetor = cargoRepo.existsByNomeAndSetorIsNot(novoCargo.getNome(), novoCargo.getSetor());
        if (existeCargoCadastradoEmOutroSetor) {
            throw new CargoComMesmoNomeException("Existe cargo com associação a outro setor.");
        }
        Cargo cargo = new Cargo();
        cargo.setSetor(novoCargo.getSetor());
        cargo.setNome(novoCargo.getNome());
        return cargoRepo.save(cargo);
    }
}
