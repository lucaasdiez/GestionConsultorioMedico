package com.mgc.repository;

import com.mgc.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByEmail(String email);
}
