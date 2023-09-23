package com.test.sea.lucas.services;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.entities.Setor;
import com.test.sea.lucas.exceptions.CargoIdSemCorrespondencia;
import com.test.sea.lucas.exceptions.SetorComMesmoNomeException;
import com.test.sea.lucas.exceptions.SetorIdSemCorrespondencia;
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
    public Setor atualizarSetor(Long id, Setor setorAtualizado) throws SetorIdSemCorrespondencia, CargoIdSemCorrespondencia {
        Setor setorById = setorRepo.findSetorById(id);
            if(setorById != null){
                setorById.setNome(setorAtualizado.getNome());
            }
        List<Cargo> cargosAtualizados = setorAtualizado.getCargo();
        if (cargosAtualizados != null) {
            for (Cargo cargoAtualizado : cargosAtualizados) {
                Optional<Cargo> cargoPersistido = cargoRepo.findById(cargoAtualizado.getId());
                if (cargoPersistido.isPresent()) {
                    setorById.getCargo().add(cargoPersistido.get());
                } else {
                }
            }
        }
        return setorRepo.save(setorById);
    }
}

