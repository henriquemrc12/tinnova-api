package com.tinnova.api.veiculo.repository;

import com.tinnova.api.veiculo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    boolean existsVeiculoByNome(String nome);
}
