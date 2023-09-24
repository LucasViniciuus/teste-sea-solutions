package com.test.sea.lucas.services;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.repositories.CargoRepository;
import com.test.sea.lucas.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepo;

    @Autowired
    private CargoRepository cargoRepo;

    public Setor criarSetor(Setor setor) throws SetorComMesmoNomeException {
        List<Setor> setoresComMesmoNome = setorRepo.buscaSetorComMesmoNome(setor.getNome());
        if (!setoresComMesmoNome.isEmpty()) {
            throw new SetorComMesmoNomeException("Já existe um setor com o mesmo nome.");
        }
        return setorRepo.save(setor);
    }
    public Setor encontrarSetorPorId(Long id) {
        return setorRepo.findById(id).orElse(null);
    }
    public Setor salvarSetor(Setor setor) {
        try {
        return setorRepo.save(setor);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Setor buscaSetorId(Long id){
        return setorRepo.findSetorById(id);
    }
}

