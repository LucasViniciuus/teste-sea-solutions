package com.test.sea.lucas.repositories;

import com.test.sea.lucas.entities.Cargo;
import com.test.sea.lucas.entities.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    boolean existsByNomeAndSetorIsNot(String nome, Setor setor);

    @Query(value = "select * from public.cargo c where setor_id = ?1", nativeQuery = true)
    public List<Cargo> findCargoById(Integer id);
}

