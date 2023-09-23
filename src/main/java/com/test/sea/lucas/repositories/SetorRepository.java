package com.test.sea.lucas.repositories;

import ch.qos.logback.core.boolex.EvaluationException;
import com.test.sea.lucas.entities.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    @Query(value = "select * from public.setor where nome = ?1", nativeQuery = true)
    public List<Setor> buscaSetorComMesmoNome(String nome);

    @Query(value = "select * from public.setor where id = ?1",  nativeQuery = true)
    public Setor findSetorById(Long id);
}
