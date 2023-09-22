package com.test.sea.lucas.services;

import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepo;

    public Setor criarSetor(Setor setor) throws SetorComMesmoNomeException {
        List<Setor> setoresComMesmoNome = setorRepo.buscaSetorComMesmoNome(setor.getNome());
        if (!setoresComMesmoNome.isEmpty()) {
            throw new SetorComMesmoNomeException("Já existe um setor com o mesmo nome.");
        }
        return setorRepo.save(setor);
    }
    }

