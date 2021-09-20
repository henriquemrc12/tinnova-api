package com.tinnova.api.gestor.repository;

import com.tinnova.api.gestor.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    boolean existsGestorByNome(String nome);

    boolean existsGestorByEmail(String email);

    Optional<Manager> findByEmail(String email);
}
