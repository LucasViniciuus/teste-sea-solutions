package com.test.sea.lucas.repositories;

import com.test.sea.lucas.entities.Cargo;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.GeneratedValue;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
  //  - Não deve ser possível cadastrar um setor com o mesmo nome de outro existente;

    //- Cargos ficam vinculados a um setor e não podem ser cadastrados em outros setores;

    //- Um trabalhador está vinculado a um setor e a um cargo;

//    - Não é possível ter dois trabalhadores com mesmo CPF;
}
