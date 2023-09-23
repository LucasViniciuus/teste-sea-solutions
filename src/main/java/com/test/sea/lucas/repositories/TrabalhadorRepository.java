package com.test.sea.lucas.repositories;

import com.test.sea.lucas.entities.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {
    @Query(value = "select * from public.trabalhador where cpf = ?1", nativeQuery = true)
    public Trabalhador buscaTrabalhadorPorCpf(String cpf);
}
