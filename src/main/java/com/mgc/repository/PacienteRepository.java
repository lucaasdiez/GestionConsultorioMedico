package com.mgc.repository;

import com.mgc.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByEmail(String email);
    Optional<Paciente> findByNombreIgnoreCase(String nombre);
}
