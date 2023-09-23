package com.test.sea.lucas.repositories;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.entities.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    boolean existsByNomeAndSetorIsNot(String nome, Setor setor);
}

