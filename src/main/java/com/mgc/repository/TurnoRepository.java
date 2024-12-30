package com.mgc.repository;

import com.mgc.models.Paciente;
import com.mgc.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    List<Turno> findAllByPacienteNombreIgnoreCase(String idPaciente);
    boolean existsByProfesionalIdAndFechaAndHora(Integer idProfesional, LocalDate fecha, LocalTime hora);

    boolean existsByConsultorioIdAndFechaAndHora(Integer idConsultorio, LocalDate fecha, LocalTime hora);
}
